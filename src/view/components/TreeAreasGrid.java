package view.components;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Visualizing tree density for one large area,
 * split into cells.
 */
public class TreeAreasGrid extends JPanel {
	public static final int CELL_SIZE = 50;
	public static Color[] CURRENT_SCHEME = CellColorSchemes.INFORMATIVE;
	private final int ROWS = 10;
	private final int COLS = 10;
	private TreeAreasGridCell lastSelectedPanel = null;
	private TreeAreasGridCell[][] gridPanels;

	public TreeAreasGrid() {
		this.setLayout(new GridLayout(ROWS, COLS));
		gridPanels = new TreeAreasGridCell[ROWS][COLS];
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				TreeAreasGridCell panel = new TreeAreasGridCell();
				panel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						TreeAreasGridCell clickedPanel = (TreeAreasGridCell) e.getComponent();
						System.out.println(clickedPanel.getBackground());
						
						// If toggled off or selected other cell
						if (lastSelectedPanel != null) {
							lastSelectedPanel.setCellColor(lastSelectedPanel.getCellColor());
							lastSelectedPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						}
						
						// Toggled off
						if (clickedPanel.equals(lastSelectedPanel)) {
							clickedPanel.setBackground(lastSelectedPanel.getCellColor());
							lastSelectedPanel = null;
						// Selected another cell
						} else {
							if (clickedPanel.getCellColor().equals(CellColorSchemes.INFORMATIVE[CellColorSchemes.UNSET])) {
								clickedPanel.setBackground(clickedPanel.getBackground().darker());
							} else {
								clickedPanel.setBackground(clickedPanel.getBackground().brighter());								
							}
							clickedPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
							lastSelectedPanel = clickedPanel;
						}
					}
				});
				this.add(panel);
				gridPanels[row][col] = panel;
			}
		}
	}

	/** 
	 * Helper method to get the row based on the y coordinate of the mouse click
	 * @param y
	 * @return
	 */
	private int getRow(int y) {
		int row = (y - gridPanels[0][0].getLocation().y) / CELL_SIZE;
		if (row < 0 || row >= ROWS) {
			return -1;
		}
		return row;
	}

	/**
	 * Helper method to get the column based on the x coordinate of the mouse click
	 * @param x
	 * @return
	 */
	private int getCol(int x) {
		int col = (x - gridPanels[0][0].getLocation().x) / CELL_SIZE;
		if (col < 0 || col >= COLS) {
			return -1;
		}
		return col;
	}

	/**
	 * Helper method to update the color of the panel at the given row and column
	 * 
	 * @param row
	 * @param col
	 */
	private void updateGridPanel(int row, int col) {
		// gridPanels[row][col].setBackground(currentColor);
	}

	public int getRows() {
		return ROWS;
	}

	public int getCols() {
		return COLS;
	}

	public int getCellSize() {
		return CELL_SIZE;
	}
}