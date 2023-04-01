package view.components;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 *  global settings is the setting you see in the right of the main application 
 *  here you can see and change in information about, how dense the trees are. and suchj
 * @author SOUTAISEIRIRON
 *
 */
public class GlobalSettings extends JScrollPane {
	public GlobalSettings() {
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JPanel contents = new JPanel();
		contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
		contents.setBorder(ControlPanel.PANEL_HORIZONTAL_PADDING);
		InputLabelCombo areaDensity = new InputLabelCombo("Area density");

		contents.add(areaDensity);
		JButton test = new JButton("Test");
		test.addActionListener(actionEvent -> {
			System.out.println(areaDensity.getValue());
		});

		contents.add(test);
		contents.add(new JLabel("test"));

		contents.add(Box.createVerticalGlue()); // Moves all elements to the top if possible
		this.setViewportView(contents);
	}
}
