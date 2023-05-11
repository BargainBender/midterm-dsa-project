package view.components;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.CellData.CellDataStatus;
import view.components.DropdownInputLabelCombo.DropDownOption;

public class AreaSettings extends JScrollPane {
	private NumberInputLabelCombo treeCountInput;
	private DropdownInputLabelCombo statusInput;
	
	public AreaSettings() {
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JPanel contents = new JPanel();
		contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
		contents.setBorder(ControlPanel.PANEL_HORIZONTAL_PADDING);
		
		treeCountInput = new NumberInputLabelCombo("Tree count");
		
		DropDownOption[] options = {
		         new DropDownOption("Unset", CellDataStatus.UNSET),
		         new DropDownOption("Set", CellDataStatus.SET),
		         new DropDownOption("Non-property", CellDataStatus.NON_PROPERTY)
		};
		
		statusInput = new DropdownInputLabelCombo("Status", options);

		contents.add(treeCountInput);
		contents.add(statusInput);

		contents.add(Box.createVerticalGlue()); // Moves all elements to the top if possible
		this.setViewportView(contents);
	}
	
	public NumberInputLabelCombo getTreeCountInput() {
		return this.treeCountInput;
	}
	
	public DropdownInputLabelCombo getStatusInput() {
		return this.statusInput;
	}
}
