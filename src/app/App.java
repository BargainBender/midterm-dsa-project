package app;
import controller.AppController;
import model.GridData;
import view.AppView;

public class App {
	public static AppView view;
	public static AppController controller;
	public static void main(String[] args) {
		GridData data = new GridData(20, 20);
		view = new AppView(data.getRows(), data.getCols());
		controller = new AppController(view, data);
		view.setVisible(true);
	}
}
