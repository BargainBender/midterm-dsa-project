package test.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import model.CellData.CellDataStatus;
import model.Customer;
import model.GridData;
import model.Order;
import model.TreeFileUtilities;

class TreeFileUtilitiesTest {

	@Test
	void testLoadTreeFile() {
		TreeFileUtilities tfu = new TreeFileUtilities();
		try {
			GridData data = tfu.loadTreeFile("testfile.tree").grid();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testSaveTreeFile() {
		GridData data = new GridData(10, 2);

		// Setup random data
		for (int row = 0; row < data.getRows(); row++) {
			for (int col = 0; col < data.getCols(); col++) {
				int min = 0;
				int max = 100;
				data.getGrid()[row][col].setTreeCount((int) Math.floor((Math.random() * ((max - min) + 1)) + min));
				data.getGrid()[row][col].setStatus(CellDataStatus.SET);
			}
		}
		
		TreeFileUtilities tfu = new TreeFileUtilities();
		Queue<Order> orders = new LinkedList<>();
		orders.add(new Order(
				new Customer("sebastian", "self", "0921", "seb@gmail.com", "las pinas")
				, 20, 1000, "pog\\ni", false));
		orders.add(new Order(
				new Customer("sebastian", "self", "0921", "seb@gmail.com", "las pinas")
				, 20, 1000, "po\\ngi", false));
		orders.add(new Order(
				new Customer("sebastian", "self", "0921", "seb@gmail.com", "las pinas")
				, 20, 1000, "p\\nogi", false));
		try {
			tfu.saveTreeFile(data, 100, orders, "testfile.tree");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
