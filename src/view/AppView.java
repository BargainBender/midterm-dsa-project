package view;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import view.components.ControlPanel;
import view.components.TreeAreasGrid;

public class AppView extends JFrame {
	public AppView() {
		this.setTitle("Color Grid");
		TreeAreasGrid teg = new TreeAreasGrid();
		this.setSize(teg.getCellSize() * teg.getCols(), teg.getCellSize() * teg.getRows() + 100);

		ControlPanel cp = new ControlPanel();

		// Create a split pane to hold the scroll pane and the control panel
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, teg, cp);
		split.setResizeWeight(1.0); // Make the grid panel take up all available space

		// Add the split pane to the frame
		this.getContentPane().add(split);
		this.setSize(teg.getCellSize() * teg.getCols() * 2, teg.getCellSize() * teg.getRows());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
