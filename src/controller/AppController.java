package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.CellData.CellDataStatus;
import model.GridData;
import view.AppView;
import view.components.AppMenu;
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
		
		// On cell status changed
		view.getControlPanel().getAreaSettingsTab().getStatusInput().addChangeListener(changeEvent -> {
			int status = view.getControlPanel().getAreaSettingsTab().getStatusInput().getValue();
			TreeAreasGridCell lastSelectedCell = view.getTreeAreasGrid().getLastSelectedCell();
			this.modelData.getGrid()[lastSelectedCell.getRow()][lastSelectedCell.getCol()].setStatus(status);
			
			this.reflectData();
		});
		
		// Set
		view.getTreeAreasGrid().addCellMouseListener(new UseStatusToolListener(new ReflectsDataCallback() {
			
			@Override
			public void reflectData(int rowToUpdate, int colToUpdate, int status) {
				modelData.getGrid()[rowToUpdate][colToUpdate].setStatus(status);
				System.out.println(modelData.getGrid()[rowToUpdate][colToUpdate].getStatus());
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
		}));
		
		int maxTrees = view.getControlPanel().getGlobalSettingsTab().getMaxTreesInput().getValue();
		view.getTreeAreasGrid().setMaxTrees(maxTrees);
	}
	
	private class UseStatusToolListener extends MouseAdapter {
		ReflectsDataCallback callback;
		
		public UseStatusToolListener(ReflectsDataCallback callback) {
			this.callback = callback;
		}
		
		@Override
		public void mousePressed(MouseEvent mouseEvent) {
			int statusTool = AppMenu.getStatusTool();
			if (statusTool == -1) {
				return;
			}
			
			final TreeAreasGridCell clickedPanel = (TreeAreasGridCell) mouseEvent.getComponent();
			callback.reflectData(clickedPanel.getRow(), clickedPanel.getCol(), statusTool);
		}
	}
	
	private interface ReflectsDataCallback {
		public void reflectData(int row, int col, int status);
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
		view.getTreeAreasGrid().updateGrid(treeCounts, statuses, view.getTreeAreasGrid().getLastSelectedCell());
	}
}
