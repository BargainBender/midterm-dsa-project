package view.components;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.CellData.CellDataStatus;
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

		contents.add(maxTreesInput);
		contents.add(viewModeInput);
		
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
