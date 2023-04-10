package view.components;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AreaSettings extends JScrollPane {
	private InputLabelCombo treeCountInput;
	
	public AreaSettings() {
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JPanel contents = new JPanel();
		contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
		contents.setBorder(ControlPanel.PANEL_HORIZONTAL_PADDING);
		treeCountInput = new InputLabelCombo("Tree count");

		contents.add(treeCountInput);

		contents.add(Box.createVerticalGlue()); // Moves all elements to the top if possible
		this.setViewportView(contents);
	}
	
	public InputLabelCombo getTreeCountInput() {
		return this.treeCountInput;
	}
}
