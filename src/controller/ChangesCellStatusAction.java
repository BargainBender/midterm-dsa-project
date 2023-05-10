package controller;

import app.App;
import model.CellData;
import model.CellData.CellDataStatus;

public class ChangesCellStatusAction implements Action {
	private CellData cellData;
	private int row;
	private int col;
	
	public ChangesCellStatusAction(CellData cellData, int row, int col) {
		this.cellData = cellData;
		this.row = row;
		this.col = col;
	}
	
	@Override
	public void undo() {
		App.view.getTreeAreasGrid().removeHighlight();
		CellData currentWorkingData = App.controller.getModelData().getGrid()[row][col];
		currentWorkingData.setStatus(cellData.getStatus());
		if (cellData.getStatus() == CellDataStatus.UNSET) {
			currentWorkingData.setTreeCount(-2);
		} else if (cellData.getStatus() == CellDataStatus.NON_PROPERTY) {
			currentWorkingData.setTreeCount(-1);
		} else if (cellData.getStatus() == CellDataStatus.SET) {
			currentWorkingData.setTreeCount(cellData.getTreeCount());
		}
		App.controller.reflectData();
	}

}
