package view.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TreeAreasGridCell extends JPanel {
	
	private Color cellColor;
	private int treeCount;

	public TreeAreasGridCell() {
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setPreferredSize(new Dimension(TreeAreasGrid.CELL_SIZE, TreeAreasGrid.CELL_SIZE));
		this.unsetValues();
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
		this.treeCount = 0;
		this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[CellColorSchemes.UNSET]);
	}
	
	/**
	 * Sets the cell to not be part of user's property.
	 */
	public void setNonProperty() {
		this.treeCount = 0;
		this.setCellColor(TreeAreasGrid.CURRENT_SCHEME[CellColorSchemes.NON_PROPERTY]);
	}

	/**
	 * Setting this automatically calls setCellColor(), and based on percentage, selects
	 * a color for CellColorSchemes.
	 */
	public void setTreeCount(int treeCount, int maxTreeCount) {
		this.treeCount = treeCount;
		float percentage = ((treeCount * 100) / maxTreeCount);
		System.out.println(String.format("%.2f", percentage));
		
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
	}

}
