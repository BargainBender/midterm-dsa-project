package view.components;

import java.awt.Color;
import java.awt.Dimension;
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
	private final int ROWS = 10;
	private final int COLS = 10;
	private final int CELL_SIZE = 50;
	private int[] lastSelectedCell = { 0, 0 };
	private final Color defaultCellColor = Color.getHSBColor(0.061f, .80f, 0.69f);
	private JPanel[][] gridPanels;

	public TreeAreasGrid() {
		this.setLayout(new GridLayout(ROWS, COLS));
		gridPanels = new JPanel[ROWS][COLS];
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				JPanel panel = new JPanel();
				panel.setBackground(defaultCellColor);
				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
				panel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						JPanel clickedPanel = (JPanel) e.getComponent();
						int row = -1, col = -1;
						for (int i = 0; i < ROWS; i++) {
							for (int j = 0; j < COLS; j++) {
								if (clickedPanel == gridPanels[i][j]) {
									row = i;
									col = j;
									break;
								}
							}
						}
						if (lastSelectedCell[0] == row && lastSelectedCell[1] == col) {
							lastSelectedCell[0] = -1;
							lastSelectedCell[1] = -1;
							gridPanels[row][col].setBackground(defaultCellColor);
						} else {
							for (JPanel[] gpRows : gridPanels) {
								for (JPanel gpCell : gpRows) {
									gpCell.setBackground(defaultCellColor);
								}
							}
							lastSelectedCell[0] = row;
							lastSelectedCell[1] = col;
							// highlighting function
							if (row != -1 && col != -1) {
								Color brighterBg = gridPanels[row][col].getBackground().brighter();
								gridPanels[row][col].setBackground(brighterBg);
							}
						}
					}
				});
				this.add(panel);
				gridPanels[row][col] = panel;
			}
		}
	}

	// Helper method to get the row based on the y coordinate of the mouse click
	private int getRow(int y) {
		int row = (y - gridPanels[0][0].getLocation().y) / CELL_SIZE;
		if (row < 0 || row >= ROWS) {
			return -1;
		}
		return row;
	}

	// Helper method to get the column based on the x coordinate of the mouse click
	private int getCol(int x) {
		int col = (x - gridPanels[0][0].getLocation().x) / CELL_SIZE;
		if (col < 0 || col >= COLS) {
			return -1;
		}
		return col;
	}

	// Helper method to update the color of the panel at the given row and column
	private void updateGridPanel(int row, int col) {
//		gridPanels[row][col].setBackground(currentColor);
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