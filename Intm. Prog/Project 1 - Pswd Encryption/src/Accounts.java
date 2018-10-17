/**
 * This class creates the Account object and implements the User class to create the accounts.
 * 
 * @author Wesley Easton
 * @version 1.0
 * 
 * COP 3022 Project 1
 * File Name:  Accounts.java
 *
 */

public class Accounts {
	
	private String companyName;
	private String companyAddress;
	private User [] user;
	private int numOfElements = 0;
	private final int NOTFOUND = -1;
	
	/**
	 * Constructor that initializes the variables.
	 */
	public Accounts(){
		
		companyName = "";
		companyAddress = "";
		user = new User [10];
		
	}
	/**
	 * Constructor that sets the variables with the input information.
	 * 
	 * @param newName  The company name
	 * @param newAddress  The company address
	 */
	
	public Accounts(String newName, String newAddress){
		
		companyName = newName;
		companyAddress = newAddress;
		user = new User[10];
		
	}
	/**
	 * Retrieves the company name.
	 * 
	 * @return companyName  Returns the company's name
	 */
	
	public String getCompanyName(){
		
		return companyName;
		
	}
	
	/**
	 * 	Sets the company name to the input information.
	 * 
	 * @param newName  The input name of the company
	 */

	public void setCompanyName(String newName){
		
		companyName = newName;
		
	}
	/**
	 * 	Retrieves the company address.
	 * 
	 * @return  Returns the company's address
	 */
	
	public String getCompanyAddress(){
		
		return companyAddress;
		
	}
	
	/**
	 * 	Sets the company address to information that is input.
	 * 
	 * @param newAddress  Sets the company address to the address that is input
	 */
	
	public void setCompanyAddress(String newAddress){
		
		companyAddress = newAddress;
		
	}
	
	/**
	 * 	Creates a user object array and sets the object variables to the input information.
	 *  After the object has been created, 1 is added to numOfElements.
	 * 
	 * @param newName  Sets the user name to the input name
	 * @param newClearPassword  Sets the clear password to the input password
	 * @param newKey  Sets the key to the input key
	 */
	
	public void addUser(String newName, String newClearPassword, String newKey){
		
		user[numOfElements] = new User();
		user[numOfElements].setUserName(newName);
		user[numOfElements].setClearPassword(newClearPassword);
		user[numOfElements].setKey(newKey);
		
		numOfElements++;
		
	}
	
	/**
	 * Retrieves the user information that is input and uses the findUser method to search
	 * for the name.  The user information is then displayed.  If the user is not found, an
	 * error message is displayed.
	 * 
	 * @param searchName  The name that is input to be searched for.
	 * @return user[findUser(searchName)]  Returns the user information if the user name exists.
	 * @return null  Returns null if the user name does not exist.
	 */
	
	public User getUser(String searchName){
		
		if(findUser(searchName) >= 0){
			
			System.out.println(user[findUser(searchName)]);
			return user[findUser(searchName)];
			
		}else 
			
			System.out.printf("\n%s not found.\n\n", searchName	);
			return null;
			
		}
		
	/**
	 * Searches for the user that is input and deletes the user from the object array.
	 * 
	 * @param searchName  The name that is being searched for
	 * @return true  The user was found and deleted.
	 * @return false  The user was not found and error message is displayed
	 */
	
	public boolean deleteUser(String searchName){
		
		if(findUser(searchName) >= 0){
			
			System.out.printf("\nUser %s has been deleted.\n\n", searchName);
			user[findUser(searchName)] = user[findUser(searchName) + 1];
			
			
			for(int i = 1; i < (user.length - 1); i++){
				
				user[i] = user[i + 1];
				
			}
			
			numOfElements --;
			return true;
		
		}else
			
			System.out.printf("\nUser %s not found.\n\n", searchName);
			return false;
			
		
	}
	
	/**
	 * Retrieves the user information that was input.
	 * 
	 * @param searchName  The user name that is being searched for
	 * @return  i  The int variable that is returned if the user name is found
	 * @return NOTFOUND  The int variable that is returned if the user name is not found.
	 */
	
	private int findUser(String searchName){
		
		for(int i = 0; i < numOfElements; i++){
			
			if(user[i].getUserName().equals(searchName)){
				
				return i;
			}
		}
		return NOTFOUND;
	}
	
	/**
	 * Displays the Account object information along with all user info 
	 * associated with that account.
	 * 
	 * @return info  Returns the Account information
	 * 
	 */
	public String toString(){
		
		String info;
		
		info = String.format("%-17s %s\n", getCompanyName(), getCompanyAddress());
		
		for(int i = 0; i < numOfElements; i++){
			
			info += user[i].toString() + "\n";
			
		}
		
		return info;
	
	}
	
}
