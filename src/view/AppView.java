package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import view.components.AppMenu;
import view.components.ControlPanel;
import view.components.TreeAreasGrid;

public class AppView extends JFrame {
	private ControlPanel cp;
	private TreeAreasGrid tag;
	public AppView(int rows, int cols) {
		this.setTitle("Color Grid");
		this.setLayout(new BorderLayout());
		this.tag = new TreeAreasGrid(rows, cols);
		this.setSize(tag.getCellSize() * tag.getCols(), tag.getCellSize() * tag.getRows() + 100);
		this.setLocationRelativeTo(null);

		this.cp = new ControlPanel();

		// Create a split pane to hold the scroll pane and the control panel
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tag, cp);
		split.setResizeWeight(1.0); // Make the grid panel take up all available space

		AppMenu menuBar = new AppMenu();
		
		// Add the split pane to the frame		
		this.add(menuBar, BorderLayout.NORTH);
		this.add(split, BorderLayout.CENTER);
		this.setSize(tag.getCellSize() * tag.getCols() * 2, tag.getCellSize() * tag.getRows());
		this.pack();
	}
	
	public ControlPanel getControlPanel() {
		return this.cp;
	}
	
	public TreeAreasGrid getTreeAreasGrid() {
		return this.tag;
	}
}
