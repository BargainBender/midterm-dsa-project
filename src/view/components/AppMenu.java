package view.components;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class AppMenu extends JMenuBar {
	public AppMenu() {
		FileMenu file = new FileMenu("File");
		this.add(file);
	}
	
	private class FileMenu extends JMenu {
		FileMenu(String title) {
			this.setText(title);
			JMenuItem exit = new JMenuItem("Exit");
			exit.addActionListener(actionEvent -> {
				System.exit(0);
			});
			this.add(exit); 
		}
	}
}
