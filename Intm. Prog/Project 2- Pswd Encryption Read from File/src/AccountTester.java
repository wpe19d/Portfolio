/**
 * This class creates the CompanyAccounts object and implements the User and Bot classes to create the Accounts.
 * 
 * @author Wesley Easton
 * @version 2.0
 * 
 * COP 3022 Project 2
 * File Name:  AccountTester.java
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;


public class AccountTester {
	
	
	public static void main(String [] args){
		
		
		
		CompanyAccounts comp1 = new CompanyAccounts("Sispsco", "Sipsville");
		
		try {

			BufferedReader inputFile = new BufferedReader(new FileReader("Data.txt"));
			
			String delimeter = ",";
			String [] a = new String [7];
			String  line = inputFile.readLine();
			
			//Reads the file while the line does not equal null
			while(line != null){
				
				//Tokenizer that delimits with commas.
				StringTokenizer inputToken = new StringTokenizer(line, delimeter);
				int numberOfTokens = inputToken.countTokens();
				
				
				for(int i = 0; i < numberOfTokens; i++){
					
					a[i] = inputToken.nextToken();
					
					
				}
				
				//If User
				if(a[0].equalsIgnoreCase("u")){
					
			
					Account user1 = new User(a[1], a[2], Integer.parseInt(a[3]), a[4], a[5]);
					comp1.addAccount(user1);
					
				//If Bot
				}else if(a[0].equalsIgnoreCase("b")){
					
					Account bot1 = new Bot(a[1], a[2], a[3], a[4], a[5], a[6]);
					comp1.addAccount(bot1);
					
				//Error
				}else{
					
					System.out.println("wrong user type entered.");
					System.exit(0);
				}
				
				line = inputFile.readLine();
				
			}
			
			
			System.out.println(comp1.toString());
			comp1.deleteAccount(1002);
			System.out.println(comp1.toString());
			System.out.println(comp1.getAccount(1001));
				
			
		}
		catch (FileNotFoundException e) {
			
			System.out.println("File not found.");
			System.exit(0);
			
		}
		catch (IOException e){
			
			System.out.println("Could not read");
		}

		
	}

}
