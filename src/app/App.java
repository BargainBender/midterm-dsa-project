package app;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.AppController;
import model.GridData;
import view.AppView;

public class App {
	public static AppView view;
	public static AppController controller;

	public static void main(String[] args) {
		GridData data = new GridData(1, 1);
		view = new AppView(data.getRows(), data.getCols());
		controller = new AppController(view, data);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Object[] options = { "New tree File", "Load tree file", "Exit" };
		int n = JOptionPane.showOptionDialog(new JFrame(), "Choose action", "Tree Logging Tracker",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (n == JOptionPane.YES_OPTION) {
			controller.createNewTreeFile();
		}
		if (n == JOptionPane.NO_OPTION) {
			controller.loadTreeFile();
		}
		if (n == JOptionPane.CANCEL_OPTION) {
			System.exit(0);
		}
	}
}
