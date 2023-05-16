package view.components;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

public class ControlPanel extends JSplitPane {

	/**
	 * Use for vertical spacing between elements inside panels
	 */
	public static final Border ELEMENTS_VERTICAL_SPACING = BorderFactory.createEmptyBorder(5, 0, 5, 0);

	/**
	 * The padding inside the control panel. Used in each tab panel.
	 */
	public static final Border PANEL_HORIZONTAL_PADDING = BorderFactory.createEmptyBorder(0, 5, 0, 5);

	private JTabbedPane tabbedPane;
	private GlobalSettings globalSettings;
	private AreaSettings areaSettings;
	private OrdersListView ordersListView;

	public ControlPanel() {
		this.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.setResizeWeight(0.5);
		this.setPreferredSize(new Dimension(200, this.getSize().height));
		this.tabbedPane = new JTabbedPane();
		

		this.globalSettings = new GlobalSettings();
		this.tabbedPane.add("Global", globalSettings);

		this.areaSettings = new AreaSettings();
		this.tabbedPane.add("Selected Area", areaSettings);
		
		this.ordersListView = new OrdersListView();
		
		this.disableAreaSettingsTab();
		
		this.setTopComponent(tabbedPane);
		this.setBottomComponent(ordersListView);
	}
	
	public GlobalSettings getGlobalSettingsTab() {
		return globalSettings;
	}

	public AreaSettings getAreaSettingsTab() {
		return areaSettings;
	}

	public OrdersListView getOrdersListView() {
		return ordersListView;
	}

	public void openGlobalSettingsTab() {
		this.tabbedPane.setSelectedIndex(0);
	}
	
	public void openAreaSettingsTab() {
		this.tabbedPane.setSelectedIndex(1);
	}
	
	public void disableAreaSettingsTab() {
		this.tabbedPane.setEnabledAt(1, false);
	}
	
	public void enableAreaSettingsTab() {
		this.tabbedPane.setEnabledAt(1, true);
	}
}
