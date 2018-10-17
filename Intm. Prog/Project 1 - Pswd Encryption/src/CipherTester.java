/**
 * This program allows a business to create an account and add users with passwords.
 * This class tests whether or not the program will run correctly.
 * 
 * @author Wesley Easton
 * @version 1.0
 *
 *COP 3022 Project 1
 *File Name:  CipherTester.java
 */
public class CipherTester {
	
	public static void main(String[] args){
		
		//Creates Account objects.
		Accounts account1 = new Accounts("Mossy Ind.", "4311 Bayou Blvd.");
		Accounts account2 = new Accounts("Sipsco", "5555 Sipsville");
		

		//Account 1 adding users.
		account1.addUser("Alex", "password", "house");
		account1.addUser("Wes", "abcd1234", "argos");
		account1.addUser("Christie", "Disney94", "shiny");
		
		
		//Displays the Account information and all users.
		System.out.println(account1.toString());
		
		//Deletes a user.
		account1.deleteUser("Alex");
		
		System.out.println(account1.toString());
		
		
		//Will get the user that is input.
		account1.getUser("Christie");
		
		//Tests if system will give error message for a user that does not exist.
		account1.getUser("Kristy");
		
		//Same test for account two object.
		account2.addUser("Sips", "BigMoney", "BgFun");
		account2.addUser("Lewis", "pass5000", "Tacos!");
		
		account2.getUser("Lewis");
		
		account2.deleteUser("Lewis");
		account2.addUser("Sjin", "abcd5678", "Venus");
		
		System.out.println(account2.toString());
		

	}

}
