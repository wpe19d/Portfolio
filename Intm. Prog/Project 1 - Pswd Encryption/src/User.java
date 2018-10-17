/**
 * This class creates a user object.  This object will create allow a user to create a user name,
 * a clear password (password before it is encrypted), and a key that will be used to encrypt the password.
 * 
 * @author Wesley Easton
 * @version 1.0
 * 
 * COP 3022 Project 1
 * File Name:  User.java
 *
 */

public class User {
	
	private String username;
	private String clearPassword;
	private String encryptedPassword;
	private String key;
	
	/**
	 * Constructor that initializes the variables
	 */
	
	public User(){
		
		username = "";
		clearPassword = "";
		encryptedPassword = "";
		key = "";
		
	}
	
	/**
	 * Constructor that sets the variables to the input information
	 * 
	 * @param newName  The name that is input for the user name
	 * @param newClearPassword  The password that is input for the Clear Password
	 * @param newKey  The key that is input for the password key
	 */
	
	public User(String newName, String newClearPassword, String newKey){
		
		username = newName;
		clearPassword = newClearPassword;
		key = newKey;
		encrypt();
		
	}
	
	/**
	 * Retrieves the user name
	 * 
	 * @return username  Returns the user name
	 */
	
	public String getUserName(){
		
		return username;
	}
	
	/**
	 * Sets the user name to the input name
	 * 
	 * @param newName  The name that is input for user name
	 */
	public void setUserName(String newName){
		
		username = newName;
	}
	
	/**
	 * Retrieves the clear password
	 * 
	 * @return clearPassword  Returns the clear password
	 */
	
	public String getClearPassword(){
		
		return clearPassword;
		
	}
	
	/**
	 * Sets the clear password to the input password
	 * 
	 * @param newClearPassord  The new password 
	 */
	
	public void setClearPassword(String newClearPassword){
		
		clearPassword = newClearPassword;
		
	}
	
	/**
	 * Retrieves the encrypted password
	 * 
	 * @return encrytedPassword  Returns the encrypted password
	 */
	
	public String getEncryptedPassword(){
		
		return encryptedPassword;
		
	}
	
	/**
	 * Sets the password to the input password.  Calls the encrypt method to encrypt.
	 * 
	 * @param newEncryptedPassword  The new encrypted password
	 * 
	 */
	
	public void setEncryptedPassword(String newEncryptedPassword){
		
		clearPassword = newEncryptedPassword;
		encrypt();
		
	}
	
	/**
	 * Retrieves the password key
	 * 
	 * @return key  Returns the key
	 */
	
	public String getKey(){
		
		return key;
	}
	
	/**
	 * Sets the key to the input key.  Calls the encrypt method to use key for encryption.
	 * 
	 * @param newKey  The new key 
	 */
	
	public void setKey(String newKey){
		
		key = newKey;
		encrypt();
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
	 * Displays the User information in the from of a String
	 * 
	 * @param info  A string that contains the User info
	 * @return info  Returns the user info
	 */
	
	public String toString(){
		
		String info;
		
		info = String.format("%-10s %15s %20s %15s", getUserName(), getEncryptedPassword(), getClearPassword(), getKey());
		
		return info;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
