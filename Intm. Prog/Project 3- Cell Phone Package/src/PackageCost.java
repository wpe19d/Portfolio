import java.util.Scanner;

/**
 * This class creates the PackageCost object and stores the cost of each option chosen by the user.  It then calculates the shipping
 * cost and comes up with the total cost of the Cell Phone Package.
 * 
 * @author Wes's PC
 * @version 1.0
 * 
 * COP 3022 Project 3
 * File Name:  PackageCost.java
 */

public class PackageCost {
	
	Scanner keyboard = new Scanner(System.in);
	private double talkPrice;
	private double phonePrice;
	private double dataPrice;
	private double totalCost;
	private double shippingCost;
	
	/**
	* Constructor that initializes the variables
	*/
	public PackageCost(){
		
		setTalkPrice("0");
		setPhonePrice("0");
		setDataPrice("0");
		setShippingCost("0");
		setTotalCost();
		
	}
	
	/**
	 * Constructor that sets the package costs to the input information
	 * @param newTalk  The cost of the talk package
	 * @param newPhone  The cost of the phone package
	 * @param newData  The cost of the data package
	 */
	
	public PackageCost(String newTalk, String newPhone, String newData){
		
		setTalkPrice(newTalk);
		setPhonePrice(newPhone);
		setDataPrice(newData);
		
	}

	/**
	 * Returns the price of the talk package
	 * @return  Returns the price of the talk package
	 */
	
	public double getTalkPrice() {
		
		return talkPrice;
		
	}

	/**
	 * Sets the price of the talk package
	 * @param newTalkPrice  The price of the talk package
	 */
	
	public void setTalkPrice(String newTalkPrice) {
		
		try{
			talkPrice = Double.parseDouble(newTalkPrice);;
		}catch(NumberFormatException e){
			
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	/**
	 * Returns the price of the phone
	 * @return  Returns the price of the phone
	 */
	
	public double getPhonePrice() {
		
		return phonePrice;
		
	}

	/**
	 * Sets the price of the phone
	 * @param newPhonePrice  The price of the phone
	 */
	
	public void setPhonePrice(String newPhonePrice) {
		
		try{
			phonePrice = Double.parseDouble(newPhonePrice);
			
		}catch(NumberFormatException e){
			
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	/**
	 * Returns the price of the data plan
	 * @return  price of the data plan
	 */
	
	public double getDataPrice() {
		
		return dataPrice;
		
	}

	/**
	 * Sets the price of the data plan
	 * @param newDataPrice  price of the data plan
	 */
	
	public void setDataPrice(String newDataPrice) {
		
		try{
		
			dataPrice = Double.parseDouble(newDataPrice);
		
		}catch(NumberFormatException e){
			
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	
	/**
	 * Returns the cost of shipping
	 * @return  Returns the cost of shipping
	 */
	
	public double getShippingCost() {
		
		return shippingCost;
		
	}

	/**
	 * Sets the cost of shipping depending on the zipcode
	 * @param newShippingCost  The input zipcode
	 */
	public void setShippingCost(String zipcode){
		
		if(Double.parseDouble(zipcode) >= 32500 && Double.parseDouble(zipcode) < 32600){
			
			shippingCost = 0;
		
		}else if((Double.parseDouble(zipcode) >= 99500 && Double.parseDouble(zipcode) < 100000) 
				|| (Double.parseDouble(zipcode) >= 96700 && Double.parseDouble(zipcode) <96900)){
			
			shippingCost = 10.00;
			
		}else
			
			shippingCost = 5.00;

	}

	/**
	 * Calculates and returns the total cost of the whole package
	 * @return  Returns the total cost of the whole package
	 */

	public double getTotalCost() {
		
		totalCost = getTalkPrice() + getPhonePrice() + getDataPrice() + getShippingCost();
		
		return totalCost;
		
	}
	
	/**
	 * Sets the total cost of the whole package.
	 */
	public void setTotalCost(){
		
		totalCost = getTotalCost();
	}
	
	/**
	 * Returns the cost of the whole package a message.
	 */
	public String toString(){
		
		String info;
		info = String.format("The total cost of the entire package is: $%.2f", getTotalCost());
		return info;
		
	}
	

}
