package controller;

import model.CellData.CellDataStatus;
import model.GridData;
import view.AppView;
import view.components.TreeAreasGridCell;

public class AppController {
	private GridData modelData;
	private AppView view;
	
	public AppController(AppView view, GridData modelData) {
		this.view = view;
		this.modelData = modelData;
		
		// Random data
		for (int row = 0; row < modelData.getRows(); row++) {
			for (int col = 0; col < modelData.getCols(); col++) {
				int min = 0;
				int max = 100;
				modelData.getGrid()[row][col].setTreeCount((int) Math.floor((Math.random() * ((max - min) + 1)) + min));
				modelData.getGrid()[row][col].setStatus(CellDataStatus.SET);
			}
		}
		
		this.reflectData();
		
		// On max trees changed
		view.getControlPanel().getGlobalSettingsTab().getMaxTreesInput().addChangeListener(changeEvent -> {
			int value = view.getControlPanel().getGlobalSettingsTab().getMaxTreesInput().getValue();
			if (value < 0) {
				view.getControlPanel().getGlobalSettingsTab().getMaxTreesInput().resetValue();
				return;
			}
			view.getTreeAreasGrid().setMaxTrees(view.getControlPanel().getGlobalSettingsTab().getMaxTreesInput().getValue());
			
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
			TreeAreasGridCell lastSelectedCell = view.getTreeAreasGrid().getLastSelectedCell();
			
			if (lastSelectedCell != null) {
				modelData.getGrid()[lastSelectedCell.getRow()][lastSelectedCell.getCol()].setTreeCount(value);
			}
			this.reflectData();
		});
		
		int maxTrees = view.getControlPanel().getGlobalSettingsTab().getMaxTreesInput().getValue();
		view.getTreeAreasGrid().setMaxTrees(maxTrees);
	}
	
	private void reflectData() {
		int[][] treeCounts = new int[modelData.getRows()][modelData.getCols()];
		int[][] statuses = new int[modelData.getRows()][modelData.getCols()];
		
		for (int row = 0; row < modelData.getRows(); row++) {
			for (int col = 0; col < modelData.getCols(); col++) {
				treeCounts[row][col] = modelData.getGrid()[row][col].getTreeCount();
				statuses[row][col] = modelData.getGrid()[row][col].getStatus();
			}
		}
		view.getTreeAreasGrid().updateGrid(treeCounts, statuses, view.getTreeAreasGrid().getLastSelectedCell());
	}
}
