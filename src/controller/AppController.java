package controller;

import java.awt.event.ItemEvent;
import java.util.ArrayList;

import model.CellData;
import model.CellData.CellDataStatus;
import model.GridData;
import view.AppView;
import view.components.AppMenu;
import view.components.GlobalSettings.MapViewMode;
import view.components.TreeAreasGridCell;

public class AppController {
	private GridData modelData;
	private AppView view;

	public AppController(AppView view, GridData modelData) {
		this.view = view;
		this.modelData = modelData;
		
		this.reflectData();
		
		// On max trees changed
		view.getControlPanel().getGlobalSettingsTab().getMaxTreesInput().addChangeListener(changeEvent -> {
			int value = view.getControlPanel().getGlobalSettingsTab().getMaxTreesInput().getValue();
			if (value < 0) {
				view.getControlPanel().getGlobalSettingsTab().getMaxTreesInput().resetValue();
				return;
			}
			view.getTreeAreasGrid().setMaxTrees(view.getControlPanel().getGlobalSettingsTab().getMaxTreesInput().getValue());
			view.getControlPanel().getGlobalSettingsTab().getViewModeInput().setValue(MapViewMode.TOP_DOWN);
			this.reflectData();
			System.out.println("Max trees: " + view.getTreeAreasGrid().getMaxTrees());
		});
		
		// On cell tree count changed
		view.getControlPanel().getAreaSettingsTab().getTreeCountInput().addChangeListener(changeEvent -> {
			int value = view.getControlPanel().getAreaSettingsTab().getTreeCountInput().getValue();
			if (value < 0) {
				view.getControlPanel().getAreaSettingsTab().getTreeCountInput().resetValue();
				return;
			}
			TreeAreasGridCell lastSelectedCell = view.getTreeAreasGrid().getHighlightedCell();
			
			if (lastSelectedCell != null) {
				modelData.getGrid()[lastSelectedCell.getRow()][lastSelectedCell.getCol()].setTreeCount(value);
			}
			view.getControlPanel().getGlobalSettingsTab().getViewModeInput().setValue(MapViewMode.TOP_DOWN);
			this.reflectData();
		});
		
		// On cell status changed
		view.getControlPanel().getAreaSettingsTab().getStatusInput().addChangeListener(changeEvent -> {
			if (changeEvent.getStateChange() != ItemEvent.SELECTED) {
                return;
            }
			
			int status = view.getControlPanel().getAreaSettingsTab().getStatusInput().getValue();
			TreeAreasGridCell lastSelectedCell = view.getTreeAreasGrid().getHighlightedCell();
			if (lastSelectedCell == null) {
				return;
			}
			CellData currentWorkingData = this.modelData.getGrid()[lastSelectedCell.getRow()][lastSelectedCell.getCol()];
			currentWorkingData.setStatus(status);
			if (status == CellDataStatus.UNSET) {
				currentWorkingData.setTreeCount(-2);
			} else if (status == CellDataStatus.NON_PROPERTY) {
				currentWorkingData.setTreeCount(-1);
			} else if (status == CellDataStatus.SET && currentWorkingData.getTreeCount() < 0) {
				currentWorkingData.setTreeCount(0);
			}
			this.view.getTreeAreasGrid().removeHighlight();
			this.reflectData();
			this.view.getTreeAreasGrid().setHighlightedCell(lastSelectedCell);
		});
		
		// On view mode change
		view.getControlPanel().getGlobalSettingsTab().getViewModeInput().addChangeListener(changeEvent -> {
			if (changeEvent.getStateChange() != ItemEvent.SELECTED) {
                return;
            }
			AppMenu.useNoStatusTool();
			view.getTreeAreasGrid().removeHighlight();
			app.App.view.getControlPanel().openGlobalSettingsTab();
			app.App.view.getControlPanel().disableAreaSettingsTab();
			int viewMode = view.getControlPanel().getGlobalSettingsTab().getViewModeInput().getValue();
			if (viewMode == MapViewMode.GRAPH) {
				ArrayList<CellData> dataArr = new ArrayList<>();
				for (int row = 0; row < this.modelData.getRows(); row++) {
					for (int col = 0; col < this.modelData.getCols(); col++) {
						CellData currentWorkingData = this.modelData.getGrid()[row][col];
						if (currentWorkingData.getStatus() == CellDataStatus.UNSET) {
							currentWorkingData.setTreeCount(-2);
						} else if (currentWorkingData.getStatus() == CellDataStatus.NON_PROPERTY) {
							currentWorkingData.setTreeCount(-1);
						} else if (currentWorkingData.getStatus() == CellDataStatus.SET && currentWorkingData.getTreeCount() < 0) {
							currentWorkingData.setTreeCount(0);
						}
						dataArr.add(this.modelData.getGrid()[row][col]);
					}
				}
				MergeSortCellData.sort(dataArr);
				int counter = 0;
				for (int row = 0; row < this.modelData.getRows(); row++) {
					for (int col = 0; col < this.modelData.getCols(); col++) {
						this.modelData.getGraphGrid()[row][col] = dataArr.get(counter++);
					}
				}
				this.reflectGraphData();
			} else if (viewMode == MapViewMode.TOP_DOWN) {
				this.reflectData();				
			}
		});
		
		int maxTrees = view.getControlPanel().getGlobalSettingsTab().getMaxTreesInput().getValue();
		view.getTreeAreasGrid().setMaxTrees(maxTrees);
	}
	
	public void reflectGraphData() {
		int[][] treeCounts = new int[modelData.getRows()][modelData.getCols()];
		int[][] statuses = new int[modelData.getRows()][modelData.getCols()];
		
		for (int row = 0; row < modelData.getRows(); row++) {
			for (int col = 0; col < modelData.getCols(); col++) {
				treeCounts[row][col] = modelData.getGraphGrid()[row][col].getTreeCount();
				statuses[row][col] = modelData.getGraphGrid()[row][col].getStatus();
			}
		}
		view.getTreeAreasGrid().updateGrid(treeCounts, statuses, view.getTreeAreasGrid().getHighlightedCell());
	}
	
	public void reflectData() {
		int[][] treeCounts = new int[modelData.getRows()][modelData.getCols()];
		int[][] statuses = new int[modelData.getRows()][modelData.getCols()];
		
		for (int row = 0; row < modelData.getRows(); row++) {
			for (int col = 0; col < modelData.getCols(); col++) {
				treeCounts[row][col] = modelData.getGrid()[row][col].getTreeCount();
				statuses[row][col] = modelData.getGrid()[row][col].getStatus();
			}
		}
		view.getTreeAreasGrid().updateGrid(treeCounts, statuses, view.getTreeAreasGrid().getHighlightedCell());
	}
	
	public GridData getModelData() {
		return modelData;
	}
	
	public void setModelData(GridData grid) {
		this.modelData = grid;
		this.reflectData();
	}
	
	public AppView getView() {
		return view;
	}
}
