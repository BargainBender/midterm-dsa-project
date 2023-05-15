package view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Customer;
import model.Order;
import model.orderReceipt;

public class OrderWindow extends JFrame {

	public OrderWindow() {
		this.setTitle("New order");
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		try {
			this.setIconImage(ImageIO.read(new File("res/favicon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setSize(300, 500);
		JScrollPane scrollPane = new JScrollPane();

		 JPanel contents = new JPanel();
	        contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
	        contents.setBorder(ControlPanel.PANEL_HORIZONTAL_PADDING);
	        contents.setPreferredSize(new Dimension(300, 500));

	        
	        JPanel customerSheet = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        
		        JLabel customerNameLabel = new JLabel("Customer name:");
		        JTextField customerNameField = new JTextField(17);
		        customerSheet.add(customerNameLabel);
		        customerSheet.add(customerNameField);
	
		        JLabel companyNameLabel = new JLabel("Company name:");
		        JTextField companyField = new JTextField(16);
		        customerSheet.add(companyNameLabel);
		        customerSheet.add(companyField);;
		        
		        JLabel contactNumLabel = new JLabel("Contact no:");
		        JTextField contactNumField = new JTextField(19);
		        customerSheet.add(contactNumLabel);
		        customerSheet.add(contactNumField);
		        
		        JLabel emailLabel = new JLabel("Email:");
		        JTextField emailField = new JTextField(22);
		        customerSheet.add(emailLabel);
		        customerSheet.add(emailField);
		        
		        JLabel addressLabel = new JLabel("Address:");
		        JTextField addressField = new JTextField(20);
		        customerSheet.add(addressLabel);
		        customerSheet.add(addressField);
		        
		        JLabel qtyLabel = new JLabel("Quantity:");
		        JTextField qtyField = new JTextField(20);
		        customerSheet.add(qtyLabel);
		        customerSheet.add(qtyField);
		        
		        JLabel prizeLabel = new JLabel("Prize:");
		        JTextField prizeField = new JTextField(21);
		        customerSheet.add(prizeLabel);
		        customerSheet.add(prizeField);
		        prizeField.setText("$50 per log");
		        prizeField.setEditable(false);
		        
		        JLabel notesLabel = new JLabel("Notes:");
		        JTextField notesField = new JTextField();
		        notesField.setPreferredSize(new Dimension (210, 50));
		        customerSheet.add(notesLabel);
		        customerSheet.add(notesField);
		        
		        JButton printFile = new JButton("Print");
		        customerSheet.add(printFile);
		        
		        printFile.addActionListener(e ->{
		        	
		        	float qtyValue = Integer.parseInt(qtyField.getText());
		        	float prizeValue = 50;
		        	
		        	    Customer customer = new Customer(customerNameField.getText(), companyField.getText(),
		        	            contactNumField.getText(), emailField.getText(), addressField.getText(), notesField.getText());
		        	    Order order = new Order(customer, qtyValue , prizeValue, false);
		        	    orderReceipt.printReceipt(customer, customer, customer, customer, customer, order, order, customer);

		        });
		        
		        JButton clear = new JButton("Clear");
		        customerSheet.add(clear);
		        
		        clear.addActionListener(e ->{
		        	customerNameField.setText(" ");
		        	companyField.setText(" ");
		        	contactNumField.setText(" ");
		        	emailField.setText(" ");
		        	addressField.setText(" ");
		        	qtyField.setText(" ");
		        	notesField.setText(" ");
		        });
		        
		       
		        
	    contents.add(customerSheet);
		
		this.add(scrollPane);
		this.setVisible(true);
		
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportView(contents);
	}
}
