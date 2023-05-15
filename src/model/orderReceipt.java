package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class orderReceipt {
    public static void printReceipt(Customer customerName, Customer customerCompany, Customer contactNumber,
                                    Customer emailAddress, Customer fullAddress, Order quantity,  Order prizeValue, Customer notes) {

        String filePath = "C:/Users/Jericho/Desktop/receipt.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Customer Name: " + customerName.getCustomerName());
            writer.newLine();
            writer.write("Company Name: " + customerCompany.getCustomerCompany());
            writer.newLine();
            writer.write("Contact Number: " + contactNumber.getContactNumber());
            writer.newLine();
            writer.write("Email: " + emailAddress.getEmailAddress());
            writer.newLine();
            writer.write("Address: " + fullAddress.getFullAddress());
            writer.newLine();
            writer.write("Quantity: " + quantity.getQuantity());
            writer.newLine();
            writer.write("Total Prize " + quantity.getQuantity() * prizeValue.getQuotation());
            writer.newLine();
            writer.write("Notes: " + notes.getNotes());
            writer.newLine();

            System.out.println("Order details written to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing order details to file: " + e.getMessage());
        }

    }
}
