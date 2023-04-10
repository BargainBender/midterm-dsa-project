package model;

public class GridData {
	private int rows;
	private int cols;
	private CellData[][] grid;
	
	public GridData(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		
		this.grid = new CellData[rows][cols];
		
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				this.grid[row][col] = new CellData();
			}
		}
	}

	public CellData[][] getGrid() {
		return grid;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}
}
