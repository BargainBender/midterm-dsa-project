package view.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.CellData.CellDataStatus;
import view.components.GlobalSettings.MapViewMode;

/**
 * Visualizing tree density for one large area,
 * split into cells.
 */
public class TreeAreasGrid extends JPanel {
	public static final int CELL_SIZE = 30;
	public static Color[] CURRENT_SCHEME = CellColorSchemes.INFORMATIVE;
	private TreeAreasGridCell highlightedCell = null;
	private TreeAreasGridCell[][] gridCells;
	private int rows;
	private int cols;
	private int maxTrees;
	
	public TreeAreasGrid(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.setLayout(new GridLayout(rows, cols));
		this.maxTrees = 100;
		this.gridCells = new TreeAreasGridCell[rows][cols];
		
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				final int currRow = row;
				final int currCol = col;
				TreeAreasGridCell panel = new TreeAreasGridCell();
				panel.addMouseListener(new CellSelectionListener(panel, currRow, currCol));
				this.add(panel);
				gridCells[row][col] = panel;
			}
		}
	}
	
	private class CellSelectionListener extends MouseAdapter {
        private final TreeAreasGridCell cell;
        private final int currRow;
        private final int currCol;
        
        public CellSelectionListener(TreeAreasGridCell cell, int row, int col) {
            this.cell = cell;
            this.currRow = row;
            this.currCol = col;
        }

        @Override
		public void mouseEntered(MouseEvent mouseEvent) {
        	final TreeAreasGridCell clickedPanel = (TreeAreasGridCell) mouseEvent.getComponent();
        	if (AppMenu.getStatusTool() != -1) {
        		clickedPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        	}
		}

		@Override
		public void mouseExited(MouseEvent mouseEvent) {
			final TreeAreasGridCell clickedPanel = (TreeAreasGridCell) mouseEvent.getComponent();
			clickedPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
		}

		@Override
		public void mousePressed(MouseEvent mouseEvent) {
			
			final TreeAreasGridCell clickedPanel = (TreeAreasGridCell) mouseEvent.getComponent();
			
			int viewMode = app.App.view.getControlPanel().getGlobalSettingsTab().getViewModeInput().getValue();
			
			if (viewMode == MapViewMode.GRAPH) {
				return;
			}
			
			this.cell.setRow(this.currRow);
			this.cell.setCol(this.currCol);
			
			// When a tool is selected, update cell on click
			if (AppMenu.getStatusTool() != -1 && clickedPanel != null) {
				app.App.controller.getModelData().getGrid()[clickedPanel.getRow()][clickedPanel.getCol()].setStatus(AppMenu.getStatusTool());
				
				if (AppMenu.getStatusTool() == CellDataStatus.UNSET) {
					clickedPanel.unsetValues();
					app.App.controller.getModelData().getGrid()[clickedPanel.getRow()][clickedPanel.getCol()].setTreeCount(0);
				} else if (AppMenu.getStatusTool() == CellDataStatus.SET && clickedPanel.getStatus() != CellDataStatus.SET) {
					clickedPanel.setProperty();
					clickedPanel.setTreeCount(0, maxTrees);
				} else if (AppMenu.getStatusTool() == CellDataStatus.NON_PROPERTY) {
					clickedPanel.setNonProperty();
					app.App.controller.getModelData().getGrid()[clickedPanel.getRow()][clickedPanel.getCol()].setTreeCount(0);
				}
				return;
			}
			
			// Check if the clicked cell is already the highlighted cell
	        if (clickedPanel == highlightedCell) {
	            // If it is, unhighlight it by setting its background color to the default color
	        	clickedPanel.setBackground(clickedPanel.getCellColor());
	        	clickedPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        	app.App.view.getControlPanel().openGlobalSettingsTab();
            	app.App.view.getControlPanel().disableAreaSettingsTab();
	            // Set highlightedCell to null to indicate that no cell is currently highlighted
	            highlightedCell = null;
	        } else {
	            // If the clicked cell is not already the highlighted cell, unhighlight the previously highlighted cell (if there is one)
	            if (highlightedCell != null) {
	                highlightedCell.setBackground(highlightedCell.getCellColor());
	                highlightedCell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	            }
	            // Highlight the clicked cell by setting its background color to the highlight color
	            clickedPanel.setBackground(clickedPanel.getCellColor().brighter());
	            clickedPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	            app.App.view.getControlPanel().openAreaSettingsTab();
            	app.App.view.getControlPanel().enableAreaSettingsTab();
	            // Set highlightedCell to the clicked cell to indicate that it is now highlighted
	            highlightedCell = clickedPanel;
	        }
	        
	        app.App.view.getControlPanel().getAreaSettingsTab().getStatusInput().setValue(clickedPanel.getStatus());
	        app.App.view.getControlPanel().getGlobalSettingsTab().getViewModeInput().setValue(MapViewMode.TOP_DOWN);
			try {
				app.App.view.getControlPanel().getAreaSettingsTab().getTreeCountInput().setValue(this.cell.getTreeCount());
				app.App.view.getControlPanel().getAreaSettingsTab().getTreeCountInput().userModified = false;
			} catch (Exception e) {
				System.err.println(e);
			}
		}
    }

	public int getRows() {
		return this.rows;
	}

	public int getCols() {
		return this.cols;
	}

	public int getCellSize() {
		return CELL_SIZE;
	}
	
	public int getMaxTrees() {
		return maxTrees;
	}

	public void setMaxTrees(int maxTrees) {
		this.maxTrees = maxTrees;
	}
	
	public TreeAreasGridCell getHighlightedCell() {
		return this.highlightedCell;
	}
	
	public void setHighlightedCell(TreeAreasGridCell cell) {
		TreeAreasGridCell hcell = this.gridCells[cell.getRow()][cell.getCol()];
		
            // Highlight the clicked cell by setting its background color to the highlight color
            hcell.setBackground(hcell.getCellColor().brighter());
            hcell.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            app.App.view.getControlPanel().openAreaSettingsTab();
        	app.App.view.getControlPanel().enableAreaSettingsTab();
            // Set highlightedCell to the clicked cell to indicate that it is now highlighted
        	this.highlightedCell = cell;
	}
	
	public void removeHighlight() {
		if (this.highlightedCell != null) {
			this.highlightedCell.setBackground(highlightedCell.getCellColor());
			this.highlightedCell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.highlightedCell = null;			
		}
	}
	
	public void addCellMouseListener(MouseAdapter mouseAdapter) {
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				gridCells[row][col].addMouseListener(mouseAdapter);
			}
		}
	}
	
	public TreeAreasGridCell[][] getGridCells() {
		return this.gridCells;
	}

	public void updateGrid(int[][] treeCounts, int[][] statuses, TreeAreasGridCell highlightedCell) {
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				TreeAreasGridCell currentCell = this.gridCells[row][col];
				
				// Issue here: change only reflects on second edit
				if (currentCell == highlightedCell && !app.App.view.getControlPanel().getAreaSettingsTab().getTreeCountInput().userModified) {
					app.App.view.getControlPanel().getAreaSettingsTab().getTreeCountInput().userModified = true;
					continue;
				}
				if (statuses[row][col] == CellDataStatus.SET) {
					currentCell.setProperty();
					currentCell.setTreeCount(treeCounts[row][col], maxTrees);
				} else if (statuses[row][col] == CellDataStatus.UNSET) {
					currentCell.unsetValues();
				} else if (statuses[row][col] == CellDataStatus.NON_PROPERTY) {
					currentCell.setNonProperty();
				}
			}
		}
	}
}