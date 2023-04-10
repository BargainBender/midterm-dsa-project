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

	private JTabbedPane tp;
	private GlobalSettings gs;
	private AreaSettings as;
	
	public ControlPanel() {
		this.setLayout(new BorderLayout());
		this.tp = new JTabbedPane();

		this.gs = new GlobalSettings();
		this.tp.add("Global", gs);

		this.as = new AreaSettings();
		this.tp.add("Selected Area", as);
		
		this.disableAreaSettingsTab();

		this.setPreferredSize(new Dimension(200, this.getSize().height));
		this.add(BorderLayout.CENTER, tp);
	}
	
	public GlobalSettings getGlobalSettingsTab() {
		return gs;
	}

	public AreaSettings getAreaSettingsTab() {
		return as;
	}

	public void openGlobalSettingsTab() {
		this.tp.setSelectedIndex(0);
	}
	
	public void openAreaSettingsTab() {
		this.tp.setSelectedIndex(1);
	}
	
	public void disableAreaSettingsTab() {
		this.tp.setEnabledAt(1, false);
	}
	
	public void enableAreaSettingsTab() {
		this.tp.setEnabledAt(1, true);
	}
}
