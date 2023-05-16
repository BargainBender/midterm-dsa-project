package view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.App;
import model.Customer;
import model.Order;

public class OrderDialog extends JDialog {

	public OrderDialog(Order order) {
        super((JFrame) null, "New order", true); // Set modal dialog
		this.setTitle("New order");
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);

		try {
			this.setIconImage(ImageIO.read(new File("res/favicon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setSize(350, 500);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JPanel contents = new JPanel();
		contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
		contents.setBorder(ControlPanel.PANEL_HORIZONTAL_PADDING);
		contents.setPreferredSize(new Dimension(300, 500));

		JLabel customerNameLabel = new JLabel("Customer name:");
		JTextField customerNameField = new JTextField(20);
		customerNameField.setText(order.getCustomer().getCustomerName());
		customerNameField.setEditable(false);
		customerNameLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		customerNameField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contents.add(customerNameLabel);
		contents.add(customerNameField);

		JLabel companyNameLabel = new JLabel("Company name");
		JTextField companyField = new JTextField(20);
		companyField.setText(order.getCustomer().getCustomerCompany());
		companyField.setEditable(false);
		companyNameLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		companyField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contents.add(companyNameLabel);
		contents.add(companyField);

		JLabel contactNumLabel = new JLabel("Contact no.");
		JTextField contactNumField = new JTextField(20);
		contactNumField.setText(order.getCustomer().getContactNumber());
		contactNumField.setEditable(false);
		contactNumLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contactNumField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contents.add(contactNumLabel);
		contents.add(contactNumField);

		JLabel emailLabel = new JLabel("Email");
		JTextField emailField = new JTextField(20);
		emailField.setText(order.getCustomer().getEmailAddress());
		emailField.setEditable(false);
		emailLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		emailField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contents.add(emailLabel);
		contents.add(emailField);

		JLabel addressLabel = new JLabel("Address");
		JTextField addressField = new JTextField(20);
		addressField.setText(order.getCustomer().getFullAddress());
		addressField.setEditable(false);
		addressLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		addressField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contents.add(addressLabel);
		contents.add(addressField);

		JLabel qtyLabel = new JLabel("Quantity");
		JTextField qtyField = new JTextField(20);
		qtyField.setText(Integer.toString(order.getQuantity()));
		qtyField.setEditable(false);
		qtyLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		qtyField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contents.add(qtyLabel);
		contents.add(qtyField);

		JLabel priceLabel = new JLabel("Price");
		JTextField priceField = new JTextField(20);
		priceLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		priceField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contents.add(priceLabel);
		contents.add(priceField);
		priceField.setText("$50 per log");
		priceField.setEditable(false);

		JLabel notesLabel = new JLabel("Notes");
		JTextArea notesField = new JTextArea(5, 20);
		JScrollPane notesScrollPane = new JScrollPane(notesField);
		notesField.setText(order.getNotes());
		notesField.setEditable(false);
		notesLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		notesScrollPane.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		notesField.setLineWrap(true);
		notesField.setWrapStyleWord(true);
		notesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		notesScrollPane.setPreferredSize(new Dimension(350, 100)); // Adjust the preferred size accordingly
		contents.add(notesLabel);
		contents.add(notesScrollPane);

		JButton close = new JButton("Close");
		close.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		contents.add(close);

		close.addActionListener(e -> {
			this.setVisible(false);
			this.dispose();
		});

		scrollPane.add(contents);

		this.getContentPane().add(scrollPane);
		scrollPane.setViewportView(contents);
	}

	public OrderDialog() {
        super((JFrame) null, "New order", true); // Set modal dialog
		this.setTitle("New order");
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);

		try {
			this.setIconImage(ImageIO.read(new File("res/favicon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setSize(350, 500);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JPanel contents = new JPanel();
		contents.setLayout(new BoxLayout(contents, BoxLayout.Y_AXIS));
		contents.setBorder(ControlPanel.PANEL_HORIZONTAL_PADDING);
		contents.setPreferredSize(new Dimension(300, 500));

		JLabel customerNameLabel = new JLabel("Customer name:");
		JTextField customerNameField = new JTextField(20);
		customerNameLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		customerNameField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contents.add(customerNameLabel);
		contents.add(customerNameField);

		JLabel companyNameLabel = new JLabel("Company name");
		JTextField companyField = new JTextField(20);
		companyNameLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		companyField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contents.add(companyNameLabel);
		contents.add(companyField);

		JLabel contactNumLabel = new JLabel("Contact no.");
		JTextField contactNumField = new JTextField(20);
		contactNumLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contactNumField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contents.add(contactNumLabel);
		contents.add(contactNumField);

		JLabel emailLabel = new JLabel("Email");
		JTextField emailField = new JTextField(20);
		emailLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		emailField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contents.add(emailLabel);
		contents.add(emailField);

		JLabel addressLabel = new JLabel("Address");
		JTextField addressField = new JTextField(20);
		addressLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		addressField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contents.add(addressLabel);
		contents.add(addressField);

		JLabel qtyLabel = new JLabel("Quantity");
		JTextField qtyField = new JTextField(20);
		qtyLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		qtyField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contents.add(qtyLabel);
		contents.add(qtyField);

		JLabel priceLabel = new JLabel("Price");
		JTextField priceField = new JTextField(20);
		priceLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		priceField.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		contents.add(priceLabel);
		contents.add(priceField);
		priceField.setText("$50 per log");
		priceField.setEditable(false);

		JLabel notesLabel = new JLabel("Notes");
		JTextArea notesField = new JTextArea(5, 20);
		JScrollPane notesScrollPane = new JScrollPane(notesField);
		notesLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		notesScrollPane.setAlignmentX(JComponent.LEFT_ALIGNMENT);
		notesField.setLineWrap(true);
		notesField.setWrapStyleWord(true);
		notesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		notesScrollPane.setPreferredSize(new Dimension(350, 100)); // Adjust the preferred size accordingly
		contents.add(notesLabel);
		contents.add(notesScrollPane);

		JButton addToQueue = new JButton("Add to queue");
		addToQueue.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		contents.add(addToQueue);

		addToQueue.addActionListener(e -> {
			this.setVisible(false);
			int qtyValue = 0;
			try {
				qtyValue = Integer.parseInt(qtyField.getText());
			} catch (NumberFormatException err) {}
			int priceValue = 50;

			Customer customer = new Customer(customerNameField.getText(), companyField.getText(),
					contactNumField.getText(), emailField.getText(), addressField.getText());
			Order order = new Order(customer, qtyValue, priceValue, notesField.getText().replace("\n", "\\n"), false);
			App.controller.enqueueOrder(order);
			this.dispose();
		});

		JButton clear = new JButton("Clear");
		clear.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		contents.add(clear);

		clear.addActionListener(e -> {
			customerNameField.setText(" ");
			companyField.setText(" ");
			contactNumField.setText(" ");
			emailField.setText(" ");
			addressField.setText(" ");
			qtyField.setText(" ");
			notesField.setText(" ");
		});

		scrollPane.add(contents);

		this.getContentPane().add(scrollPane);
		scrollPane.setViewportView(contents);
	}
}
