package view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ControlPanel extends JPanel {
	public ControlPanel() {
		this.setLayout(new BorderLayout());
		JTabbedPane tp = new JTabbedPane();
		
		GlobalSettings gs = new GlobalSettings();
		tp.add("Global", gs);
		
		AreaSettings as = new AreaSettings();
		tp.add("Selected Area", as);
		
		this.setPreferredSize(new Dimension(200, this.getSize().height));
		this.add(BorderLayout.CENTER, tp);
	}
}
