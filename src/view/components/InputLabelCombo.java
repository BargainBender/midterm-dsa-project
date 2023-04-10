package view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;

public class InputLabelCombo extends JPanel {
	private JLabel label;
	private JSpinner input;
	public boolean userModified = false;

	public InputLabelCombo(String title) {
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getPreferredSize().height));
		this.setBorder(ControlPanel.ELEMENTS_VERTICAL_SPACING);
		this.setAlignmentX(JComponent.LEFT_ALIGNMENT);

		this.label = new JLabel(title);

		this.input = new JSpinner();
		JPanel spacer = new JPanel();
		spacer.setLayout(new BorderLayout());
		spacer.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		spacer.add(input);

		this.add(label, BorderLayout.WEST);
		this.add(spacer, BorderLayout.CENTER);
	}

	public int getValue() {
		return (Integer) this.input.getValue();
	}
	
	public void resetValue() {
		this.input.setValue(0);
	}
	
	public void setValue(int value) throws Exception {
		if (value < 0) {
			throw new Exception("Cannot set value less than 0!");
		}
		this.input.setValue(value);
	}
	
	public void addChangeListener(ChangeListener changeListener) {
		this.input.addChangeListener(changeListener);
	}
}
