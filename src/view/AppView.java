package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import view.components.AppMenu;
import view.components.ControlPanel;
import view.components.TreeAreasGrid;

public class AppView extends JFrame {
	public AppView() {
		this.setTitle("Color Grid");
		this.setLayout(new BorderLayout());
		TreeAreasGrid teg = new TreeAreasGrid();
		this.setSize(teg.getCellSize() * teg.getCols(), teg.getCellSize() * teg.getRows() + 100);

		ControlPanel cp = new ControlPanel();

		// Create a split pane to hold the scroll pane and the control panel
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, teg, cp);
		split.setResizeWeight(1.0); // Make the grid panel take up all available space

		AppMenu menuBar = new AppMenu();
		
		// Add the split pane to the frame		
		this.add(menuBar, BorderLayout.NORTH);
		this.add(split, BorderLayout.CENTER);
		this.setSize(teg.getCellSize() * teg.getCols() * 2, teg.getCellSize() * teg.getRows());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
