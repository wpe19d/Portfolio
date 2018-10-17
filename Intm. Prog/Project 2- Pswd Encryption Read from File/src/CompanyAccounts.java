/**
 * This class creates the Account object and implements the User class to create the accounts.
 * 
 * @author Wesley Easton
 * @version 2.0
 * 
 * COP 3022 Project 2
 * File Name:  CompanyAccounts.java
 *
 */

public class CompanyAccounts {
	
	private String companyName;
	private String companyAddress;
	private Account [] account;
	private int numOfElements = 0;
	private final int NOTFOUND = -1;
	
	/**
	 * Constructor that initializes the variables.
	 */
	public CompanyAccounts(){
		
		companyName = "";
		companyAddress = "";
		account = new Account [10];
		
	}
	/**
	 * Constructor that sets the variables with the input information.
	 * 
	 * @param newName  The company name
	 * @param newAddress  The company address
	 */
	
	public CompanyAccounts(String newName, String newAddress){
		
		companyName = newName;
		companyAddress = newAddress;
		account = new Account[10];
		
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
	 * 	Creates an account object array and creates a Account object with the input information.
	 *  After the object has been created, 1 is added to numOfElements.
	 * 
	 * @param newAccount  Sets the Account object equal to the input information
	 * 
	 */
	
	public void addAccount(Account newAccount){
		
		account[numOfElements] = newAccount;
		numOfElements++;
		
	}
	
	/**
	 * Retrieves the account information that is input and uses the findAccount method to search
	 * for the ID.  The account information is then displayed.  If the account is not found, an
	 * error message is displayed.
	 * 
	 * @param searchId  The ID that is input to be searched for.
	 * @return account[findAccount(searchId)]  Returns the account information if the account ID exists.
	 * @return null  Returns null if the ID name does not exist.
	 */
	
	public Account getAccount(int searchId){
		
		if(findAccount(searchId) >= 0){
			
			
			return account[findAccount(searchId)];
			
		}else 
			
			System.out.printf("\n%s not found.\n\n", searchId);
			return null;
			
		}
	
	/**
	 * Searches for the account that is input and deletes the account from the object array if found.
	 * 
	 * @param searchId  The ID that is being searched for
	 * @return true  The account was found and deleted.
	 * @return false  The account was not found and error message is displayed
	 */
	
	public boolean deleteAccount(int searchId){
		
		if((findAccount(searchId) >= 0) && (findAccount(searchId) != (numOfElements -1))){
			
			System.out.printf("\nAccount # %d has been deleted.\n\n", searchId);

			for(int i = findAccount(searchId); i < (account.length - 1); i++){
				
				account[i] = account[i + 1];
				
			}
			
			numOfElements --;
			return true;
			
		}
		else if(findAccount(searchId) == (numOfElements - 1)){
				
				System.out.printf("\nAccount # %d has been deleted.\n\n", searchId);
				
				for(int i = findAccount(searchId); i < (account.length - 1); i++){
					
					account[i] = account[i + 1];
				
			}
			
			numOfElements --;
			return true;
		
		}
		else
			
			System.out.printf("\nAccount # %d not found.\n\n", searchId);
			return false;
			
		
	}
	
	/**
	 * Retrieves the account information that was input.
	 *  
	 * @param searchId  The account ID that is being searched for
	 * @return  i  The int variable that is returned if the account ID is found
	 * @return NOTFOUND  The int variable that is returned if the account ID is not found
	 * 
	 */
	
	private int findAccount(int searchId){
		
		for(int i = 0; i < numOfElements; i++){
			
			if(account[i].getAccountId() == searchId){
				
				return i;
			}
		}
		return NOTFOUND;
	}
	
	/**
	 * Displays the Account object information along with all account info 
	 * associated with that account.
	 * 
	 * @return info  Returns the Account information
	 * 
	 */
	public String toString(){
		
		String info;
		
		info = String.format("%-17s %s\n", getCompanyName(), getCompanyAddress());
		
		for(int i = 0; i < numOfElements; i++){
			
			//Error Message
			if(account[i].getClearPassword().equals("")){

				info += account[i] + ("\nInvalid Password for user " + account[i].getAccountId() + 
						". Password should contain a minimum of 8 characters, one special character, "
						+ "and one number.\n\n");
			}else
			
			info += account[i].toString();
			
		}
		
		return info;
	
	}
	
}
