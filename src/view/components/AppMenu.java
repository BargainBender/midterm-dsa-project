package view.components;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import controller.AppController;
import model.TreeFileUtilities;
import model.CellData.CellDataStatus;
import model.GridData;
import view.AppView;
import view.components.GlobalSettings.MapViewMode;

public class AppMenu extends JMenuBar {
	private static int statusTool = -1;

	public AppMenu() {
		FileMenu file = new FileMenu();
		this.add(file);

		ToolMenu tools = new ToolMenu();
		this.add(tools);
	}

	private class FileMenu extends JMenu {
		FileMenu() {
			this.setText("File");
			
			JMenuItem saveTreesFile = new JMenuItem("Save trees file");
			saveTreesFile.addActionListener(actionEvent -> {
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
//				JOptionPane.showMessageDialog(app.App.view, "you saved " + filePath);
			});
			
			this.add(saveTreesFile);
			
			JMenuItem openTreesFile = new JMenuItem("Open tree file");
			openTreesFile.addActionListener(actionEvent -> {
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
//				JOptionPane.showMessageDialog(app.App.view, "you chose " + filePath);
			});

			this.add(openTreesFile);
			
			JMenuItem exit = new JMenuItem("Exit");
			exit.addActionListener(actionEvent -> {
				System.exit(0);
			});
			this.add(exit);
		}
	}

	private class ToolMenu extends JMenu {
		private static ButtonGroup statusGroup;

		ToolMenu() {
			this.setText("Tools");

			JMenu setStatus = new JMenu("Set status");

			ToolMenu.statusGroup = new ButtonGroup();
			JRadioButton unset = new JRadioButton("Unset");
			unset.setActionCommand("UNSET");
			unset.addActionListener(new StatusGroupActionListener(ToolMenu.statusGroup));

			JRadioButton set = new JRadioButton("Set");
			set.setActionCommand("SET");
			set.addActionListener(new StatusGroupActionListener(ToolMenu.statusGroup));

			JRadioButton nonProperty = new JRadioButton("Non-property");
			nonProperty.setActionCommand("NON_PROPERTY");
			nonProperty.addActionListener(new StatusGroupActionListener(ToolMenu.statusGroup));

			ToolMenu.statusGroup.add(unset);
			ToolMenu.statusGroup.add(set);
			ToolMenu.statusGroup.add(nonProperty);

			setStatus.add(unset);
			setStatus.add(set);
			setStatus.add(nonProperty);

			this.add(setStatus);
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
		ToolMenu.statusGroup.clearSelection();
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
				ToolMenu.statusGroup.clearSelection();
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
