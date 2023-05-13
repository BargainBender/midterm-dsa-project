package model;

public class Order {
	private Customer customer;
	private int quantity;
	private float quotation;
	private boolean fulfilled;

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

	public void setQuotation(float quotation) {
		this.quotation = quotation;
	}

	public boolean isFulfilled() {
		return fulfilled;
	}

	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}

	public Order(Customer customer, int quantity, float quotation, boolean fulfilled) {
		super();
		this.customer = customer;
		this.quantity = quantity;
		this.quotation = quotation;
		this.fulfilled = fulfilled;
	}

}
