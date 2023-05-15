package view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import view.components.DropdownInputLabelCombo.DropDownOption;
/**
 *  global settings is the setting you see in the right of the main application 
 *  here you can see and change in information about, how dense the trees are. and suchj
 * @author SOUTAISEIRIRON
 *
 */
public class GlobalSettings extends JScrollPane {
	private NumberInputLabelCombo maxTreesInput;
	private DropdownInputLabelCombo viewModeInput;
	private Queue<String> companyQueue;
	private JTextField lowerLeft;
	private JTextField upperLeft;
	private JButton upperRight;
	private JButton lowerRight;
	
	public GlobalSettings() {
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JPanel contents = new JPanel();
		contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
		contents.setBorder(ControlPanel.PANEL_HORIZONTAL_PADDING);
		maxTreesInput = new NumberInputLabelCombo("Max trees per area");
		try {
			maxTreesInput.setValue(100);
		} catch (Exception e) {
			System.err.println(e);
		}
		
		DropDownOption[] viewModeOptions = {
		         new DropDownOption("Top down", MapViewMode.TOP_DOWN),
		         new DropDownOption("Graph", MapViewMode.GRAPH)
		};
		
		viewModeInput = new DropdownInputLabelCombo("View", viewModeOptions);
		
		lowerLeft = new JTextField();
        ((JTextComponent) lowerLeft).setText("Enter company names (separated by commas):");
        
        upperLeft = new JTextField();
        ((JTextComponent) upperLeft).setText("Set queue here");
        
        upperRight = new JButton("Set Queue");
        lowerRight = new JButton("Cut");
        
        companyQueue = new LinkedList<>(); // Initialize the queue

        // Button click event listener
        upperRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String names = lowerLeft.getText().trim(); // Get the input from lowerLeft JTextField
                String[] companyNames = names.split(","); // Split the input by commas
                for (String name : companyNames) {
                    companyQueue.offer(name.trim()); // Enqueue each company name into the queue
                }
                String queueText = String.join(", ", companyQueue); // Join the queue elements with commas
                upperLeft.setText(queueText); // Set the upperLeft JTextField text to the queue elements
            }
        });

        
        // Button click event listener for dequeue
        lowerRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!companyQueue.isEmpty()) {
                    String dequeuedName = companyQueue.poll(); // Dequeue the first company name from the queue
                    upperLeft.setText(dequeuedName); // Set the upperLeft JTextField text to the dequeued name
                }
            }
        });

        
		contents.add(maxTreesInput);
		contents.add(viewModeInput);
		contents.add(lowerLeft);
		contents.add(upperLeft);
		contents.add(upperRight);
		contents.add(lowerRight);
		
		contents.add(Box.createVerticalGlue()); // Moves all elements to the top if possible
		this.setViewportView(contents);
		
	}
	
	public class MapViewMode {
		public static final int TOP_DOWN = 0;
		public static final int GRAPH = 1;
	}
	
	public NumberInputLabelCombo getMaxTreesInput() {
		return this.maxTreesInput;
	}
	
	public DropdownInputLabelCombo getViewModeInput() {
		return this.viewModeInput;
	}
	
	
}
