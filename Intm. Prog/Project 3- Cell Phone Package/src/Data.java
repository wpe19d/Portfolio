/**
 * This class creates the Data object and stores the necessary info.
 * 
 * @author Wes's PC
 * @version 1.0
 * 
 * COP 3022 Project 3
 * File Name:  Data.java
 */



public class Data{
	
	private String dataAmount;
	private String dataPrice;
	
	
	/**
	 * Constructor to initialize the variables.
	 */
	
	public Data(){
		
		setDataAmount("");
		setDataPrice("");
		
	}
	
	/**
	 * Constructor to set the variables to input information
	 * @param newDataAmount The amount of the data plan selected
	 * @param newDataPrice  The price of the data plan
	 */
	
	public Data(String newDataAmount, String newDataPrice){
		
		setDataAmount(newDataAmount);
		setDataPrice(newDataPrice);
		
	}
	
	/**
	 * Returns the data amount
	 * @return   data amount
	 */
	
	public String getDataAmount() {
		
		return dataAmount;
		
	}

	/**
	 * Sets the data amount
	 * @param newDataAmount  the input data amount
	 */
	
	public void setDataAmount(String newDataAmount) {
		
		dataAmount = newDataAmount;
		
	}

	/**
	 * Returns the price of the data plan
	 * @return  price of the data plan
	 */
	
	public String getDataPrice() {
		
		return dataPrice;
		
	}

	/**
	 * Sets the price of the data plan
	 * @param newDataPrice  price of the data plan
	 */
	
	public void setDataPrice(String newDataPrice) {
		
		dataPrice = newDataPrice;
		
	}

	/**
	 * Returns the data plan information.
	 */
	
	public String toString(){
		
		return(getDataAmount()  + " " + getDataPrice());
		
	}
	
}
