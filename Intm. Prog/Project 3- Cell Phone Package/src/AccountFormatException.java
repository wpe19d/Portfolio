/**
 * The Exception for any account information that is not formatted correctly.
 * @author Wes's PC
 * @version 1.0
 * 
 * COP 3022 Project 3
 * File Name:  AccountFormatException.java
 */
public class AccountFormatException extends Exception{
	
	/**
	 * Sets the message that will be generated if an exception occurs.
	 */
	
	public AccountFormatException(){
		
		super("Customer Account Information Exception:  Address and or Phone number are in the wrong format.  "
				+ "\nPlease look at the format guidelines and re-enter the information.");
		
	}
	
	/**
	 * Displays a message when an exception occurs
	 */
	
	public AccountFormatException(String message){
		
		super(message);
		
	}
}
