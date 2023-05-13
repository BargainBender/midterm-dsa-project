package model;

public class Customer {
	private String customerName;
	private String customerCompany;
	private String contactNumber;
	private String emailAddress;
	private String fullAddress;

	public Customer(String customerName, String customerCompany, String contactNumber, String emailAddress,
			String fullAddress, String notes) {
		super();
		this.customerName = customerName;
		this.customerCompany = customerCompany;
		this.contactNumber = contactNumber;
		this.emailAddress = emailAddress;
		this.fullAddress = fullAddress;
		this.notes = notes;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerCompany() {
		return customerCompany;
	}
	public void setCustomerCompany(String customerCompany) {
		this.customerCompany = customerCompany;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	private String notes;
}
