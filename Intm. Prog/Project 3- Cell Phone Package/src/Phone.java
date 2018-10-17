/**
 * This class creates the Phone object and stores the necessary info.
 * 
 * @author Wes's PC
 * @version 1.0
 * 
 * COP 3022 Project 3
 * File Name:  Phone.java
 */


public class Phone {
	
	private String model;
	private String phonePrice;
	
	/**
	 * Constructor that initializes the variables
	 */
	public Phone(){
		
		
		setModel("");
		setPhonePrice("");
	}
	
	/**
	 * Constructor that sets the phone model and price to the input information.
	 * @param newModel  The input phone model
	 * @param newPhonePrice  The price of the phone
	 */
	
	public Phone(String newModel, String newPhonePrice){
		
		setModel(newModel);
		setPhonePrice(newPhonePrice);
		
	}
	
	/**
	 * Returns the phone model
	 * @return  Returns the phone model
	 */
	
	public String getModel() {
		
		return model;
		
	}
	
	/**
	 * Sets the phone model
	 * @param newModel  The input phone model
	 */
	public void setModel(String newModel) {
		
		model = newModel;
		
	}

	/**
	 * Returns the price of the phone
	 * @return  Returns the price of the phone
	 */
	public String getPhonePrice() {
		
		return phonePrice;
		
	}

	/**
	 * Sets the price of the phone
	 * @param newPhonePrice  The price of the phone
	 */
	public void setPhonePrice(String newPhonePrice) {
		
		phonePrice = newPhonePrice;
		
	}
	
	/**
	* Returns the phone plan information.
	*/
	
	public String toString(){
		
		return(getModel()  + " " +  getPhonePrice());
		
	}
	

}
