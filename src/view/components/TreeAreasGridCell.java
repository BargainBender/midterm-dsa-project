package view.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.CellData.CellDataStatus;

public class TreeAreasGridCell extends JPanel {
	
	private Color cellColor;
	private int treeCount;
	private int status;
	private int row;
	private int col;

	public TreeAreasGridCell() {
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setPreferredSize(new Dimension(TreeAreasGrid.CELL_SIZE, TreeAreasGrid.CELL_SIZE));
		this.status = CellDataStatus.UNSET;
	}

	public Color getCellColor() {
		return cellColor;
	}

	public void setCellColor(Color cellColor) {
		this.cellColor = cellColor;
		this.setBackground(this.cellColor);
	}

	public int getTreeCount() {
		return treeCount;
	}
	
	/**
	 * Unsets the value(s) for the cell. This should be the base values for the cell.
	 */
	public void unsetValues() {
		this.status = CellDataStatus.UNSET;
		this.treeCount = -2;
		this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[CellColorSchemes.UNSET]);
	}
	
	/**
	 * Sets the cell to not be part of user's property.
	 */
	public void setNonProperty() {
		this.status = CellDataStatus.NON_PROPERTY;
		this.treeCount = -1;
		this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[CellColorSchemes.NON_PROPERTY]);
	}
	
	/**
	 * Sets the cell to be part of user's property.
	 */
	public void setProperty() {
		this.status = CellDataStatus.SET;
	}
	
	public int getStatus() {
		return this.status;
	}

	/**
	 * Setting this automatically calls setCellColor(), and based on percentage, selects
	 * a color for CellColorSchemes.
	 */
	public void setTreeCount(int treeCount, int maxTreeCount) {
		if (treeCount < 0 || this.status != CellDataStatus.SET) {
			return;
		}
		
		this.treeCount = treeCount;

		try {
			double percentage = Math.ceil(((treeCount * 100) / maxTreeCount));
			if      (percentage < 1) this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[10]);
			else if (percentage >= 1 && percentage < 11) this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[9]);
			else if (percentage >= 11 && percentage < 21) this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[8]);
			else if (percentage >= 21 && percentage < 31) this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[7]);
			else if (percentage >= 31 && percentage < 41) this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[6]);
			else if (percentage >= 41 && percentage < 51) this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[5]);
			else if (percentage >= 51 && percentage < 61) this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[4]);
			else if (percentage >= 61 && percentage < 71) this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[3]);
			else if (percentage >= 71 && percentage < 81) this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[2]);
			else if (percentage >= 81 && percentage < 91) this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[1]);
			else if (percentage >= 91) this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[0]);
//			System.out.println(String.format("%.2f", percentage));
		} catch (ArithmeticException e) {
			this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[10]);
		}
		
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

}
