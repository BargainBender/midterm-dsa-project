package model;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TreeFileUtilities {

	public record TreeFileData(GridData grid, int maxTrees, Queue<Order> orders) {
	}

	public TreeFileData loadTreeFile(String path) throws FileNotFoundException, IOException, NumberFormatException {
		RandomAccessFile reader = new RandomAccessFile(path, "r");
		boolean rowsSet = false, colsSet = false, maxTreesSet = false;
		int rows = 0, cols = 0, maxTrees = 0;
		String line = "";
		Pattern pattern;
		Matcher matcher;
		while ((line = reader.readLine()) != null && !(rowsSet && colsSet && maxTreesSet)) {
			pattern = Pattern.compile("^(rows|cols|maxTrees):[ ]*(\\d+)$");
			matcher = pattern.matcher(line);
			if (matcher.find()) {
				String setting = matcher.group(1);
				int data = Integer.parseInt(matcher.group(2));

				if (setting.equals("rows")) {
					rowsSet = true;
					rows = data;
				} else if (setting.equals("cols")) {
					colsSet = true;
					cols = data;
				} else if (setting.equals("maxTrees")) {
					colsSet = true;
					maxTrees = data;
				}
			}
		}
		GridData grid = new GridData(rows, cols);
		reader.seek(0);
		int row = 0;
		while ((line = reader.readLine()) != null) {
			int col = 0;
			pattern = Pattern.compile("<(\\d+),(\\d+)>");
			matcher = pattern.matcher(line);
			while (matcher.find()) {
				int treeCount = Integer.parseInt(matcher.group(1));
				int status = Integer.parseInt(matcher.group(2));
				CellData cellData = new CellData();
				cellData.setTreeCount(treeCount);
				cellData.setStatus(status);
				grid.getGrid()[row][col] = cellData;
				grid.getGraphGrid()[row][col++] = cellData;
				if (col == cols) {
					row++;
				}
			}
		}
		reader.seek(0);
		Queue<Order> orders = new LinkedList<>();
		boolean atOrdersQueue = false;
		while ((line = reader.readLine()) != null) {
			pattern = Pattern.compile("^orders:.*");
			matcher = pattern.matcher(line);
			if (atOrdersQueue) {
				line = line.replace("\\n", "\n");
				String[] data = line.split(",");
				Order order = new Order();
				Customer customer = new Customer();
				customer.setCustomerName(data[0]);
				customer.setCustomerCompany(data[1]);
				customer.setContactNumber(data[2]);
				customer.setEmailAddress(data[3]);
				customer.setFullAddress(data[4]);
				order.setCustomer(customer);
				order.setNotes(data[8]);
				order.setOrderId(data[9]);
				orders.offer(order);
				try {
					order.setQuantity(Integer.parseInt(data[5]));
					order.setQuotation(Integer.parseInt(data[6]));
					order.setFulfilled(Boolean.parseBoolean(data[7]));
				} catch (Exception e) {
					e.printStackTrace();
				}
				continue;
			}
			if (matcher.find()) {
				atOrdersQueue = true;
			}

		}
		
		reader.close();
		return new TreeFileData(grid, maxTrees, orders);
	}

	public void saveTreeFile(GridData gridData, int maxTrees, Queue<Order> orders, String path)
			throws FileNotFoundException, IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(path));
		writer.write("rows:" + Integer.toString(gridData.getRows()) + "\n");
		writer.write("cols:" + Integer.toString(gridData.getCols()) + "\n");
		writer.write("maxTrees:" + Integer.toString(maxTrees) + "\n");
		for (int i = 0; i < gridData.getRows(); i++) {
			for (int j = 0; j < gridData.getCols(); j++) {
				writer.write("<");
				writer.write(gridData.getGrid()[i][j].getTreeCount() + ",");
				writer.write(Integer.toString(gridData.getGrid()[i][j].getStatus()));
				writer.write(">");
			}
			if (i < gridData.getGrid().length - 1) {
				writer.write("\n");
			}
		}
		writer.write("\norders: ");
		writer.write("name,company,contact_no,email,address,qty,quotation,note,is_fulfilled,id\n");
		for (Order order : orders) {
			writer.write(order.getFullStringData() + "\n");
		}

		writer.close();
	}
}
