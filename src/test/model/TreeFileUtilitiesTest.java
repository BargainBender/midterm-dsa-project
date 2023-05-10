package test.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import model.CellData.CellDataStatus;
import model.GridData;
import model.TreeFileUtilities;

class TreeFileUtilitiesTest {

	@Test
	void testLoadTreeFile() {
		TreeFileUtilities tfu = new TreeFileUtilities();
		try {
			GridData data = tfu.loadTreeFile("testfile.tree").grid();
			System.out.println(Arrays.deepToString(data.getGrid()));
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
		try {
			tfu.saveTreeFile(data, 100, "C:\\Users\\Sebastian\\Desktop\\testfile.tree");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
