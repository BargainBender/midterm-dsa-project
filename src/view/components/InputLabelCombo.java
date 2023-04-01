package view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class InputLabelCombo extends JPanel {
	private JLabel label;
	private JSpinner input;

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
}
