package view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

public class ControlPanel extends JPanel {

	/**
	 * Use for vertical spacing between elements inside panels
	 */
	public static final Border ELEMENTS_VERTICAL_SPACING = BorderFactory.createEmptyBorder(5, 0, 5, 0);

	/**
	 * The padding inside the control panel. Used in each tab panel.
	 */
	public static final Border PANEL_HORIZONTAL_PADDING = BorderFactory.createEmptyBorder(0, 5, 0, 5);

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
