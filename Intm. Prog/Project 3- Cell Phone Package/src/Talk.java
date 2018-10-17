/**
 * This class creates the Talk object and stores the necessary info.
 * 
 * @author Wes's PC
 * @version 1.0
 * 
 * COP 3022 Project 3
 * File Name:  Talk.java
 */

public class Talk{

	
	private String minutes;
	private String minutesPrice;

	
	/**
	 * Constructor that initializes the variables
	 */
	
	public Talk(){
		
		setMinutes("");
		setMinutesPrice("");

		
	}
	
	/**
	 * Constructor that sets the amount of minutes and the price of the minutes
	 * @param initMinutes  The number of minutes
	 * @param newPrice  The price of the minutes
	 */
	
	public Talk(String initMinutes, String newPrice){
		
		setMinutes(initMinutes);
		setMinutesPrice(newPrice);
		
	}

	/**
	 * Returns the number of minutes
	 * @return  Returns the number of minutes
	 */
	
	public String getMinutes() {
		
		return minutes;
		
	}
	
	/**
	 * Sets the number of minutes
	 * @param initMinutes  The number of minutes
	 */
	
	public void setMinutes(String initMinutes){
		
		minutes = initMinutes;
		
	}

	/**
	 * Returns the price of the minutes
	 * @return  Returns the price of the minutes
	 */
	
	public String getMinutesPrice() {
		
		return minutesPrice;
		
	}

	/**
	 * Sets the price of the minutes
	 * @param newPrice  The price of the minutes
	 */
	
	public void setMinutesPrice(String newPrice){
		
		minutesPrice = newPrice;
		
	}
	
	/**
	 * Returns the amount and price of the talk plan
	 */
	public String toString(){
		
		return(getMinutes()  + " " + getMinutesPrice());
		
	}

}
