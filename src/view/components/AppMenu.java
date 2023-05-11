package view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

import app.App;
import controller.Action;
import model.CellData.CellDataStatus;
import view.components.GlobalSettings.MapViewMode;

public class AppMenu extends JMenuBar {
	private static int statusTool = -1;

	public AppMenu() {
		FileMenu file = new FileMenu();
		this.add(file);

		EditMenu tools = new EditMenu();
		this.add(tools);
	}

	private class FileMenu extends JMenu {
		FileMenu() {
			this.setText("File");

			JMenuItem newFile = new JMenuItem("New tree file");
			newFile.addActionListener(actionEvent -> app.App.controller.createNewTreeFile());

			this.add(newFile);

			JMenuItem saveTreesFile = new JMenuItem("Save tree file");
			saveTreesFile.addActionListener(actionEvent -> app.App.controller.saveTreeFile());

			this.add(saveTreesFile);

			JMenuItem openTreesFile = new JMenuItem("Open tree file");
			openTreesFile.addActionListener(actionEvent -> app.App.controller.loadTreeFile());

			this.add(openTreesFile);

			JMenuItem exit = new JMenuItem("Exit");
			exit.addActionListener(actionEvent -> {
				System.exit(0);
			});
			this.add(exit);
		}
	}

	private class EditMenu extends JMenu {
		private static ButtonGroup statusGroup;

		EditMenu() {
			this.setText("Edit");

			JMenu setStatus = new JMenu("Set status");

			EditMenu.statusGroup = new ButtonGroup();
			JRadioButton unset = new JRadioButton("Unset");
			unset.setActionCommand("UNSET");
			unset.addActionListener(new StatusGroupActionListener(EditMenu.statusGroup));

			JRadioButton set = new JRadioButton("Set");
			set.setActionCommand("SET");
			set.addActionListener(new StatusGroupActionListener(EditMenu.statusGroup));

			JRadioButton nonProperty = new JRadioButton("Non-property");
			nonProperty.setActionCommand("NON_PROPERTY");
			nonProperty.addActionListener(new StatusGroupActionListener(EditMenu.statusGroup));

			EditMenu.statusGroup.add(unset);
			EditMenu.statusGroup.add(set);
			EditMenu.statusGroup.add(nonProperty);

			setStatus.add(unset);
			setStatus.add(set);
			setStatus.add(nonProperty);
			
			this.add(setStatus);
			
			JMenuItem undo = new JMenuItem("Undo");
			undo.addActionListener(actionEvent -> {
				if (!App.controller.getUndoStack().isEmpty()) {
					Action lastAction = App.controller.getUndoStack().pop();
					lastAction.undo();
				}
			});
			
			this.add(undo);
		}
	}

	public static int getStatusTool() {
		return AppMenu.statusTool;
	}

	public void useUnsetTool() {
		AppMenu.statusTool = CellDataStatus.UNSET;
	}

	public void useSetTool() {
		AppMenu.statusTool = CellDataStatus.SET;
	}

	public void useNonPropertyTool() {
		AppMenu.statusTool = CellDataStatus.NON_PROPERTY;
	}

	public static void useNoStatusTool() {
		AppMenu.statusTool = -1;
		EditMenu.statusGroup.clearSelection();
	}

	private class StatusGroupActionListener implements ActionListener {
		private ButtonGroup statusGroup;

		public StatusGroupActionListener(ButtonGroup statusGroup) {
			this.statusGroup = statusGroup;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int viewMode = app.App.view.getControlPanel().getGlobalSettingsTab().getViewModeInput().getValue();

			if (viewMode == MapViewMode.GRAPH) {
				AppMenu.statusTool = -1;
				EditMenu.statusGroup.clearSelection();
				return;
			}
			String statusToolSelected = statusGroup.getSelection().getActionCommand();
			int statusTool = -1;
			switch (statusToolSelected) {
			case "UNSET":
				statusTool = CellDataStatus.UNSET;
				break;
			case "SET":
				statusTool = CellDataStatus.SET;
				break;
			case "NON_PROPERTY":
				statusTool = CellDataStatus.NON_PROPERTY;
				break;
			}

			if (statusTool == AppMenu.statusTool) {
				AppMenu.statusTool = -1;
				statusGroup.clearSelection();
			} else {
				AppMenu.statusTool = statusTool;
			}
		}

	}
}
