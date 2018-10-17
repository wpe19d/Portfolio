/**
 * This class creates the Account object.
 * 
 * @author Wesley Easton
 * @version 2.0
 * 
 * COP 3022 Project 2
 * File Name:  Account.java
 *
 */

public class Account {
	
	private String clearPassword;
	private String encryptedPassword;
	private String key;
	private int accountId;
	private static int nextIDNum = 1000;
	
	/**
	 * Constructor that initializes the variables.
	 */
	public Account(){
	
		clearPassword = "";
		encryptedPassword = "";
		key = "";
		accountId = 0;
	}
	
	/**
	 * Constructor that sets the variables equal to the input information.  It calls on the passwordCheck
	 * method to make sure the password meets the requirements.  It calls the encrypt method to encrypt
	 * the password.  It calls the addAccountId method to give the Account and ID number.
	 * 
	 * @param newClearPassword  The input password.
	 * @param newKey  The input key for the password.
	 * 
	 */
	public Account(String newClearPassword, String newKey){
		
		
		clearPassword = newClearPassword;
		key = newKey;
		
		passwordCheck();
			
		if(passwordCheck() == false){
			
			clearPassword = "";
			encryptedPassword = "";
			
		}

		addAccountId();
		encrypt();
		
	}
	
	/**
	 * Method to set the password.  It calls on the passwordCheck method to make sure the password 
	 * meets the requirements.  It also calls the encrypt method to encrypt the password.
	 * 
	 * @param newClearPassword  The input password.
	 * 
	 */
	public void setClearPassword(String newClearPassword){
		
		clearPassword = newClearPassword;
		passwordCheck();
		
		if(passwordCheck() == false){
			
			clearPassword = "";
			encryptedPassword = "";

		}
		
		encrypt();
		
	}
	
	/**
	 * Retrieves the clear password
	 * 
	 * @return clearPassword  Returns the clear password.
	 * 
	 */
	
	public String getClearPassword(){
		
		return clearPassword;
		
	}
	
	/**
	 * Retrieves the encrypted password
	 * 
	 * @return encrytedPassword  Returns the encrypted password
	 * 
	 */
	
	public String getEncryptedPassword(){
		
		return encryptedPassword;
		
	}
	
	/**
	 * Retrieves the password key
	 * 
	 * @return key  Returns the key
	 * 
	 */
	
	public String getKey(){
		
		return key;
	}
	
	/**
	 * Sets the key to the input key.  Calls the encrypt method to use key for encryption.
	 * 
	 * @param newKey  The new key 
	 * 
	 */
	
	public void setKey(String newKey){
		
		key = newKey;
		encrypt();
	}
	
	/**
	 * Returns the account ID
	 * 
	 * @return accountId  Returns the account ID
	 * 
	 */
	
	public int getAccountId(){
		
		return accountId;
	}
	
	/**
	 * Method to make sure the password meets the requirements of one special character, one number and 8 characters
	 * long or more.
	 * 
	 * @return true  Returns true if requirements are met.
	 * @return false  Returns false if requirements are not met.
	 * 
	 */
	
	private boolean passwordCheck(){
		
		int asciiValueClearPswd[] = new int [100];
		int asciiValueClearPswdTwo[] = new int [100];
		
		
		for(int i = 0; i < clearPassword.length(); i++){
			
			asciiValueClearPswd[i] = clearPassword.charAt(i);
			
			for(int j = 0; j < clearPassword.length(); j++){
				
				asciiValueClearPswdTwo[j] = clearPassword.charAt(j);
			
				if((asciiValueClearPswd[i] >= 35 && asciiValueClearPswd[i] <= 38) && (asciiValueClearPswdTwo[j] >= 48 && asciiValueClearPswdTwo[j] <= 57)
						&& (clearPassword.length() > 7)){
				
					return true;
				}
				
			}
			
		}

		return false;
	}
	
	/**
	 * The method that encrypts the clear password.  Uses Vigenere Cipher for Ascii characters
	 * 33-122.  
	 * 
	 * @param j  used to reset the key to the first character
	 * @param encryptNum[]  Array of the Ascii values for the characters of the encrypted passward
	 * @param asciiValueKey[]  Array of the Ascii values for the key characters
	 * @param asciiValueClearPswd[]  Array of the Ascii values for the clear password
	 * @param encryptByte[]  Used to convert the Ascii values for the encrypted password to bytes
	 * so that they can be converted to char.
	 * 
	 */
	
	private void encrypt(){
		
		int j = 0;
		int encryptNum[] = new int[100];
		int asciiValueKey[] = new int[100];
		int asciiValueClearPswd[] = new int [100];
		byte encryptByte [] = new byte [clearPassword.length()];	
		
		for(int i = 0; i < clearPassword.length(); i++){
			
			
			asciiValueKey[i] = key.charAt(j);
			asciiValueClearPswd[i] = clearPassword.charAt(i);
			
			encryptNum[i] = asciiValueKey[i] - 33;
			encryptNum[i] = (encryptNum[i] + asciiValueClearPswd[i]) - 90;
			
			encryptByte[i] = (byte) encryptNum[i];
			
				if(encryptNum[i] < 33){
				
					encryptNum[i] = (encryptNum[i] - 32) + 122;
				
					encryptByte[i] = (byte) encryptNum[i];
				}
				if(key.length() - j == 1){
					j = 0;
				}else
				j++;
				
		}
		
		encryptedPassword = new String(encryptByte);
		
	}
	
	/**
	 * Adds an account ID number to the Account.
	 * 
	 * @return accountId  The new account ID number
	 */
	public int addAccountId(){
		
		accountId = nextIDNum;
		nextIDNum++;
		
		return accountId;
		
	}
	
	/**
	 * Returns the information for the account.
	 * 
	 */
	
	public String toString(){
		
		String info;
		
		info = String.format("%-5d %s  %s  %s\n", getAccountId(), getEncryptedPassword(), getClearPassword(), getKey());
		
		return info;
	
	}
	
}
