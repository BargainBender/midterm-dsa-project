package model;

import controller.RandomStringGenerator;

public class Order {
	private Customer customer;
	private int quantity;
	private int quotation;
	private String notes;
	private boolean isFulfilled;
	private String orderId;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getQuotation() {
		return quotation;
	}

	public void setQuotation(int quotation) {
		this.quotation = quotation;
	}

	public boolean isFulfilled() {
		return isFulfilled;
	}

	public void setFulfilled(boolean fulfilled) {
		this.isFulfilled = fulfilled;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Order() {
		this.customer = new Customer();
		this.quantity = 0;
		this.quotation = 0;
		this.notes = "";
		this.isFulfilled = false;
		this.orderId = "";
	}

	public Order(Customer customer, int quantity, int quotation, String notes, boolean fulfilled) {
		this.customer = customer;
		this.quantity = quantity;
		this.quotation = quotation;
		this.notes = notes;
		this.isFulfilled = fulfilled;
		this.orderId = RandomStringGenerator.generateRandomString(8);
	}

	public Order(Customer customer, int quantity, int quotation, String notes, boolean fulfilled, String orderId) {
		this.customer = customer;
		this.quantity = quantity;
		this.quotation = quotation;
		this.isFulfilled = fulfilled;
		this.orderId = orderId;
		this.notes = notes;
	}

	public String getFullStringData() {
		return customer + "," + quantity + "," + quotation + ",[" + notes + "],"
				+ isFulfilled + "," + orderId;
	}

	@Override
	public String toString() {
		return orderId + " - " + this.getCustomer().getCustomerName();
	}

}
