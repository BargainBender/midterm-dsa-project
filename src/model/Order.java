package model;

public class Order {
	private Customer customer;
	private float quantity;
	private float quotation;
	private boolean fulfilled;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public float getQuantity() {
		return quantity;
	}

	public float setQuantity(float string) {
		return this.quantity = string;
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

	public Order(Customer customer, float qtyValue, float quotation, boolean fulfilled) {
		super();
		this.customer = customer;
		this.quantity = qtyValue;
		this.quotation = quotation;
		this.fulfilled = fulfilled;
	}

}
