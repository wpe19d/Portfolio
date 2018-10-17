/**
 * This class creates the CustomerAccount object and stores the necessary info.
 * 
 * @author Wes's PC
 * @version 1.0
 * 
 * COP 3022 Project 3
 * File Name:  CustomerAccount.java
 */

public class CustomerAccount {
	
	private String customerName;
	private String address;
	private String phoneNumber;
	private int custID;
	private static int numOfElements = 1000;
	
	/**
	* Constructor that initializes the variables
	*/
	
	public CustomerAccount(){
		
		setCustomerName("");
		setAddress("");
		setPhoneNumber("");
		addCustID();
	}
	
	/**
	 * Sets the customers account to the input information
	 * @param newName  The customer's name
	 * @param newAddress  The customer's address
	 * @param newNumber  The customer's number
	 */
	public CustomerAccount(String newName, String newAddress, String newNumber){
		
		setCustomerName(newName);
		setAddress(newAddress);
		setPhoneNumber(newNumber);
		addCustID();
		
	}

	/**
	 * Returns the customer's name
	 * @return  Returns the customer's name
	 */
	
	public String getCustomerName() {
		
		return customerName;
		
	}

	/**
	 * Sets the customer's name
	 * @param newName  The customer's name
	 */
	public void setCustomerName(String newName) {
		
		customerName = newName;
		
	}

	/**
	 * Returns the customer's address
	 * @return  Returns the customer's address
	 */
	
	public String getAddress() {
		
		return address;
		
	}

	/**
	 * Sets the customer's address
	 * @param newAddress  The customer's address
	 */
	
	public void setAddress(String newAddress) {
		
		address = newAddress;
		
	}

	/**
	 * 
	 * Returns the customer's phone number
	 * @return  Returns the customer's phone number
	 */
	
	public String getPhoneNumber() {
		
		return phoneNumber;
		
	}

	/**
	 * Sets the customer's phone number
	 * @param newNumber  The customer's phone number
	 */
	
	public void setPhoneNumber(String newNumber) {
		
		phoneNumber = newNumber;
		
	}

	/**
	 * Returns the customer's ID
	 * @return  Returns the customer's ID
	 */
	
	public int getCustID() {
		
		return custID;
		
	}

	/**
	 * creates a customer ID
	 * @return  Returns the customer's ID
	 */
	public int addCustID() {
		
		custID = numOfElements;
		numOfElements++;
		
		return custID;
		
	}
	
	/**
	 * Returns the customer's information
	 */
	public String toString(){
		
		return(getCustomerName() + " " + getAddress() + " " + getPhoneNumber()  + " " + getCustID());
	}
	

}
