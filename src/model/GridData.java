package model;

public class GridData {
	private int rows;
	private int cols;
	private CellData[][] grid;
	private CellData[][] graphGrid;

	public GridData(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		
		this.grid = new CellData[rows][cols];
		this.graphGrid = new CellData[rows][cols];
		
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				this.grid[row][col] = new CellData();
				this.graphGrid[row][col] = new CellData();
			}
		}
	}
	
	public CellData[][] getGraphGrid() {
		return graphGrid;
	}

	public CellData[][] getGrid() {
		return this.grid;
	}

	public int getRows() {
		return this.rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return this.cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}
}
