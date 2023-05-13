package view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class OrderWindow extends JFrame {
	
	public OrderWindow() {
		this.setTitle("New order");
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);

		try {
			this.setIconImage(ImageIO.read(new File("res/favicon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setSize(300, 500);
//		this.pack();
		JScrollPane scrollPane = new JScrollPane();

		JPanel contents = new JPanel();
		contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
		contents.setBorder(ControlPanel.PANEL_HORIZONTAL_PADDING);
		contents.setPreferredSize(new Dimension(300, 500));
		
		JLabel customerName = new JLabel("Customer name:");
		contents.add(customerName);

		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.add(contents);
	}
}
