/**
 * This class creates the User object that extends the account object.
 * 
 * @author Wesley Easton
 * @version 2.0
 * 
 * COP 3022 Project 2
 * File Name:  User.java
 *
 */


public class User extends Account {
	
	private String username;
	private String fullName;
	private int deptCode;	
	
	/**
	 * Constructor to initialize the variables.
	 */
	public User(){
		
		
		super("", "");
		setUsername("");
		setFullName("");
		setDeptCode(0);
		
	}
	
	/**
	 * Constructor to set the variables to the input information.
	 * 
	 * @param newUsername  The user's username
	 * @param newFullName  The user's full name
	 * @param newDeptCode  The user's deptartment code
	 * @param newClearPassword  The user's password
	 * @param newKey  The user's password key
	 * 
	 */
	public User(String newUsername, String newFullName, int newDeptCode, String newClearPassword, String newKey){
		
		
		
		super(newClearPassword, newKey);
		

		setUsername(newUsername);
		setFullName(newFullName);
		setDeptCode(newDeptCode);
		
	}
	
	/**
	 * Method to return the user's username.
	 * 
	 * @return username  The user's username
	 * 
	 */
	
	public String getUsername() {
		
		return username;
		
	}
	
	/**
	 * Method to set the username
	 * 
	 * @param newUsername  The input username
	 * 
	 */
	public void setUsername(String newUsername) {
		
		username = newUsername;
	}

	/**
	 * Method to return the user's full name.
	 * 
	 * @return fullName  The user's full name
	 * 
	 */
	public String getFullName() {
		
		return fullName;
		
	}

	/**
	 * Method to set the user's full name
	 * 
	 * @param newFullName  The input full name
	 * 
	 */
	public void setFullName(String newFullName) {
		
		fullName = newFullName;
		
	}

	/**
	 * Method to return the department code
	 * 
	 * @return deptCode  The department code
	 * 
	 */
	public int getDeptCode() {
		
		return deptCode;
		
	}

	/**
	 * Method to set the department code.
	 * 
	 * @param newDeptCode  the input department code
	 * 
	 */
	public void setDeptCode(int newDeptCode) {
		
		deptCode = newDeptCode;
		
	}
	
	/**
	 * Returns the information for the user.
	 * 
	 */
	
	public String toString(){
		
		String info;
		
		info = String.format("%-5s  %s  %d  ", getUsername(), getFullName(), getDeptCode());
		info += super.toString();
		return info;
				//(getUsername() + " " + getFullName() + " " + getDeptCode() + "" + super.toString());
		
	}

}
