package controller;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import model.CellData;
import model.CellData.CellDataStatus;
import model.GridData;
import model.TreeFileUtilities;
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
			view.getTreeAreasGrid()
					.setMaxTrees(view.getControlPanel().getGlobalSettingsTab().getMaxTreesInput().getValue());
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
			CellData currentWorkingData = this.modelData.getGrid()[lastSelectedCell.getRow()][lastSelectedCell
					.getCol()];
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
						} else if (currentWorkingData.getStatus() == CellDataStatus.SET
								&& currentWorkingData.getTreeCount() < 0) {
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

	public void createNewTreeFile() {
		JSpinner rowsField = new JSpinner();
		JSpinner columnsField = new JSpinner();

		rowsField.setValue(1);
		rowsField.setPreferredSize(new Dimension(60, 20));
		columnsField.setValue(1);
		columnsField.setPreferredSize(new Dimension(70, 20));

		rowsField.addChangeListener(changeEvent -> {
			if ((int) rowsField.getValue() < 1) {
				rowsField.setValue(1);
			}
		});

		columnsField.addChangeListener(changeEvent -> {
			if ((int) rowsField.getValue() < 1) {
				rowsField.setValue(1);
			}
		});

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("rows:"));
		myPanel.add(rowsField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("columns:"));
		myPanel.add(columnsField);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter tree file size", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			GridData data = new GridData((int) rowsField.getValue(), (int) columnsField.getValue());
			app.App.view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			app.App.view.dispatchEvent(new WindowEvent(app.App.view, WindowEvent.WINDOW_CLOSING));
			app.App.view = new AppView((int) rowsField.getValue(), (int) columnsField.getValue());
			app.App.controller = new AppController(app.App.view, data);
			app.App.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			app.App.view.setVisible(true);
		}
	}

	public void saveTreeFile() {
		FileDialog fd = new FileDialog(app.App.view, "Save TREE file", FileDialog.SAVE);
		fd.setDirectory("C:\\");
		fd.setFile("*.tree");
		fd.setVisible(true);
		String fileName = fd.getFile();
		String directory = fd.getDirectory();

		String filePath = directory + fileName;
		if (fileName == null && directory == null) {
			return;
		}
		TreeFileUtilities tfu = new TreeFileUtilities();
		try {
			tfu.saveTreeFile(app.App.controller.getModelData(), filePath);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadTreeFile() {
		FileDialog fd = new FileDialog(app.App.view, "Open TREE file", FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setFile("*.tree");
		fd.setVisible(true);
		String fileName = fd.getFile();
		String directory = fd.getDirectory();

		String filePath = directory + fileName;
		if (fileName == null && directory == null) {
			return;
		}
		TreeFileUtilities tfu = new TreeFileUtilities();
		try {
			GridData data = tfu.loadTreeFile(filePath);
			app.App.view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			app.App.view.dispatchEvent(new WindowEvent(app.App.view, WindowEvent.WINDOW_CLOSING));
			app.App.view = new AppView(data.getRows(), data.getCols());
			app.App.controller = new AppController(app.App.view, data);
			app.App.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			app.App.view.setVisible(true);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
