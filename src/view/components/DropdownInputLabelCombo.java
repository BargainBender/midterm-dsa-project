package view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.CellData.CellDataStatus;

public class DropdownInputLabelCombo extends JPanel {
	private JLabel label;
	private JComboBox<DropDownOption> input;
//	public boolean userModified = false;

	public DropdownInputLabelCombo(String title, DropDownOption[] options) {
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getPreferredSize().height));
		this.setBorder(ControlPanel.ELEMENTS_VERTICAL_SPACING);
		this.setAlignmentX(JComponent.LEFT_ALIGNMENT);

		this.label = new JLabel(title);

		this.input = new JComboBox<>();
		for (var option : options) {
			this.input.addItem(option);			
		}
		
		JPanel spacer = new JPanel();
		spacer.setLayout(new BorderLayout());
		spacer.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		spacer.add(input);

		this.add(label, BorderLayout.WEST);
		this.add(spacer, BorderLayout.CENTER);
	}

	public int getValue() {
		return ((DropDownOption) this.input.getSelectedItem()).getValue();
	}

	public void resetValue() {
		this.input.setSelectedIndex(CellDataStatus.UNSET);
	}

	public void setValue(int value) {
		this.input.setSelectedIndex(value);
	}

	public void addChangeListener(ItemListener itemListener) {
		this.input.addItemListener(itemListener);
	}

	public static class DropDownOption {
		private String label;
		private int value;

		public DropDownOption(String label, int value) {
			this.label = label;
			this.value = value;
		}

		@Override
		public String toString() {
			return label;
		}

		public int getValue() {
			return value;
		}
	}
}
