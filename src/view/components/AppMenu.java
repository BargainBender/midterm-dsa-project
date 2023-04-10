package view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

import model.CellData.CellDataStatus;

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
			JMenuItem exit = new JMenuItem("Exit");
			exit.addActionListener(actionEvent -> {
				System.exit(0);
			});
			this.add(exit); 
		}
	}
	
	private class ToolMenu extends JMenu {
		ToolMenu() {
			this.setText("Tools");
			
			JMenu setStatus = new JMenu("Set status");
			
			ButtonGroup statusGroup = new ButtonGroup();
			JRadioButton unset = new JRadioButton("Unset");
			unset.setActionCommand("UNSET");
			unset.addActionListener(new StatusGroupActionListener(statusGroup));
			
			JRadioButton set = new JRadioButton("Set");
			set.setActionCommand("SET");
			set.addActionListener(new StatusGroupActionListener(statusGroup));
			
			
			JRadioButton nonProperty = new JRadioButton("Non-property");
			nonProperty.setActionCommand("NON_PROPERTY");
			nonProperty.addActionListener(new StatusGroupActionListener(statusGroup));
			
			statusGroup.add(unset);
			statusGroup.add(set);
			statusGroup.add(nonProperty);
			
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
	
	public void useNoStatusTool() {
		AppMenu.statusTool = -1;
	}
	
	private class StatusGroupActionListener implements ActionListener {
		private ButtonGroup statusGroup;
		
		public StatusGroupActionListener(ButtonGroup statusGroup) {
			this.statusGroup = statusGroup;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
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
