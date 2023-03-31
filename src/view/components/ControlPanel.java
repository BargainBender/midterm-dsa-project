package view.components;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
	public ControlPanel() {
		JButton b1 = new JButton("Button1");
		JButton b2 = new JButton("Button2");
		this.setLayout(new FlowLayout());
		this.add(b1);
		this.add(b2);
	}
}
