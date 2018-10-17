import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.regex.*;
/**
 * Class that reads a file, builds the menu from the file, and prints the user selected information to
 * a new file.
 * 
 * @author Wes's PC
 * @version 1.0
 * 
 * COP 3022 Project 3
 * File Name:  Menu.java
 */


public class Menu extends PackageCost{
	
	
	
		Scanner input = new Scanner(System.in);
		PackageCost totalCost = new PackageCost();
		CustomerAccount cust = new CustomerAccount();
		
		private static ArrayList<CustomerAccount> custAccount = new ArrayList<CustomerAccount>(10);
		private ArrayList<Talk> talkInfo = new ArrayList<Talk>(3);
		private ArrayList<Phone> phoneInfo = new ArrayList<Phone>(3);
		private ArrayList<Data> dataInfo = new ArrayList<Data>(3);
		
		private int attempts = 0;
		private String line;
		private String delimeter;
		private String [] a = new String [3];
		private String name;
		private String address;
		private String phoneNumber;
		private int numberOfTokens;
		private int menuOption ;
		private int numOfElements;
		private boolean quit;
		private boolean caught;
		private boolean done;
		
	/**
	* Constructor that initializes the variables
	*/		
	
	public Menu(){
		
		line = "";
		delimeter = ",";
		name = "";
		address = "";
		phoneNumber = "";

		numberOfTokens = 0;
		menuOption = 0;
		quit = false;
		numOfElements = 0;
		caught = false;
		done = false;
	}
	
	/**
	 * Method that runs the menu.  It begins by reading in a file and sorting the information from the file.
	 * It then asks for user input (name, address, phone number) and builds a menu from the file information.
	 * The user selects the options that they want for their phone plan and the program takes that information
	 * and calculates the total cost.  This method then takes that total cost (and previous information) and 
	 * prints it to a file.  
	 */
	public void runMenu(){

		/**
		 * File read and sort.  The sorted information goes to Talk, Phone, and Data objects.  Any read errors
		 * are caught by the exception handlers.
		 */
		
		try{
		
			BufferedReader inputFile = new BufferedReader(new FileReader("CellPhonePackageInfo.txt"));
		
			line = inputFile.readLine();
		
			while(line != null){
			
				StringTokenizer inputToken = new StringTokenizer(line, delimeter);
				numberOfTokens = inputToken.countTokens();
			
				for(int i = 0; i < numberOfTokens; i++){
				
					a[i] = inputToken.nextToken();
				
				}
			
				if(a[0].equalsIgnoreCase("t")){
				
					Talk talk = new Talk(a[1], a[2]);
					talkInfo.add(talk);
				
				}else if(a[0].equalsIgnoreCase("p")){
				
					Phone phone = new Phone(a[1], a[2]);
					phoneInfo.add(phone);
	
				}else if(a[0].equalsIgnoreCase("d")){
				
					Data data = new Data(a[1], a[2]);
					dataInfo.add(data);
				
				}
			
				line = inputFile.readLine();
			}
		}
		catch(FileNotFoundException e){
		
			System.out.println("File name not found");
			System.exit(0);
		}
		catch(IOException e){
		
			System.out.printf("ERROR: ", e.getMessage());
		
		}
		catch(NumberFormatException e){
		
			System.out.println("Error " + e.getMessage());
		
		}
	
		/**
		 * Asks the customer for their name, address, and phone number.  The program then verifies that all the information is input correctly.
		 */
		do{
			while(done == false){
				
				System.out.println("Please enter the following information: Name, Address (street, city, state, and zipcode), and "
						+ "Phone number in the format (xxx) xxx-xxxx.");
		
				System.out.println("Name:  ");
				name = input.nextLine();
				cust.setCustomerName(name);
		
				System.out.println("Address: ");
				address = input.nextLine();
				cust.setAddress(address);
			
				System.out.println("Phone Number: ");
				phoneNumber = input.nextLine();
				cust.setPhoneNumber(phoneNumber);
		
				custAccount.add(cust);
				System.out.println(custAccount.toString().replace("[", "").replace("]", ""));
			
				try{
				
					infoCheck(address);
					infoCheck(phoneNumber);
					attempts = 0;
					caught = false;
					numOfElements++;
					done = true;
					
				}
				catch(AccountFormatException e){
				
					if(attempts == 0){
					
						System.out.println(e.getMessage());
						attempts++;
						caught = true;
					
					}else if(attempts ==1){
					
						System.out.println("Wrong format.  System exiting");
						System.exit(0);
					}
				}
				
				if(caught == true){
					
					custAccount.remove(numOfElements);
					
				}
			}
			
			/**
			 * Asks the customer to select the type of packages they want.
			 */
			
			//Talk Menu
			System.out.println("Please select a Talk Package");
			
			for(int i = 0; i < talkInfo.size(); i++)
				System.out.println((i + 1) + ". " + talkInfo.get(i));
			System.out.println("4. Quit Program");
		
			menuOption = input.nextInt();
		
			switch(menuOption){
		
			case 1:
				System.out.println("You have selected: " + talkInfo.get(0));
				
				totalCost.setTalkPrice(regexCheck("[0-9.0-9]{2,5}", talkInfo.get(0).getMinutesPrice()));
				System.out.println(totalCost.getTalkPrice());
				break;
		
			case 2:
				System.out.println("You have selected: " + talkInfo.get(1));
			
				totalCost.setTalkPrice(regexCheck("[0-9.0-9]{2,5}", talkInfo.get(1).getMinutesPrice()));
				System.out.println(totalCost.getTalkPrice());
				break;
				
			case 3:
				System.out.println("You have selected: " + talkInfo.get(2));
			
				totalCost.setTalkPrice(regexCheck("[0-9.0-9]{2,5}", talkInfo.get(2).getMinutesPrice()));
				System.out.println(totalCost.getTalkPrice());
				break;
		
			case 4:
				System.out.println("You have opted to quit the program.");
				System.out.println("Goodbye!");
				System.exit(0);
			
			default:
				System.out.println("ERROR:  Invalid Number");
				System.exit(0);
			
			}
		
			//Phone Menu
			System.out.println("Please select a Phone Package");
		
			for(int i = 0; i < phoneInfo.size(); i++)
				System.out.println((i + 1) + ". " + phoneInfo.get(i));
			System.out.println("4. Quit Program");
		
			menuOption = input.nextInt();
		
			switch(menuOption){
		
		
			case 1:
				System.out.println("You have selected: " + phoneInfo.get(0));
				
				totalCost.setPhonePrice(regexCheck("[0-9.0-9]{2,5}", phoneInfo.get(0).getPhonePrice()));
				System.out.println(totalCost.getPhonePrice());
				break;
		
			case 2:
				System.out.println("You have selected: " + phoneInfo.get(1));
			
				totalCost.setPhonePrice(regexCheck("[0-9.0-9]{2,5}", phoneInfo.get(1).getPhonePrice()));
				System.out.println(totalCost.getPhonePrice());
				break;
				
			case 3:
				System.out.println("You have selected: " + phoneInfo.get(2));
			
				totalCost.setPhonePrice(regexCheck("[0-9.0-9]{2,5}", phoneInfo.get(2).getPhonePrice()));
				System.out.println(totalCost.getPhonePrice());
				break;
		
			case 4:
				System.out.println("You have opted to quit the program.");
				System.out.println("Goodbye!");
				System.exit(0);
			
			default:
				System.out.println("ERROR:  Invalid Number");
				System.exit(0);
			
			}
		
			//Data Menu
			System.out.println("Please select a Data Package");
		
			for(int i = 0; i < dataInfo.size(); i++)
				System.out.println((i + 1) + ". " + dataInfo.get(i));
			System.out.println("4. Quit Program");
		
			menuOption = input.nextInt();
		
			switch(menuOption){
		
		
			case 1:
				System.out.println("You have selected: " + dataInfo.get(0));
				
				totalCost.setDataPrice(regexCheck("[0-9.0-9]{2,5}", dataInfo.get(0).getDataPrice()));
				System.out.println(totalCost.getDataPrice());
				break;
		
			case 2:
				System.out.println("You have selected: " + dataInfo.get(1));
			
				totalCost.setDataPrice(regexCheck("[0-9.0-9]{2,5}", dataInfo.get(1).getDataPrice()));
				System.out.println(totalCost.getDataPrice());
				break;
				
			case 3:
				System.out.println("You have selected: " + dataInfo.get(2));
			
				totalCost.setDataPrice(regexCheck("[0-9.0-9]{2,5}", dataInfo.get(2).getDataPrice()));
				System.out.println(totalCost.getDataPrice());
				break;
		
			case 4:
				System.out.println("You have opted to quit the program.");
				System.out.println("Goodbye!");
				System.exit(0);
			
			default:
				System.out.println("ERROR:  Invalid Number");
				System.exit(0);
			
			}
		
			totalCost.setShippingCost(regexCheck("[0-9]{5}", address));
	
			printToFile();
			quit = true;

		}while(!quit);

	
	totalCost.setTotalCost();
	System.out.println(totalCost.toString());

	}

	/**
	 * General regex check to use in the program.  It takes in a regex parameter and the string to apply the check to.
	 * @param regexExp  The regex format that is being checked for
	 * @param inputString  The string that is being checked
	 * @return  Returns null
	 */
	public static String regexCheck(String regexExp, String inputString){
	
		Pattern checkRegex = Pattern.compile(regexExp);
		Matcher regexMatcher = checkRegex.matcher(inputString);
	
		while(regexMatcher.find()){
		
			return(regexMatcher.group());
		}
	
		return"";
	}
	
	/**
	 * Method that checks the address zipcode and the phone number to make sure they are formatted correctly.
	 * @param newInfo  The input information
	 * @throws AccountFormatException  The exception thrown when the information is not formatted ocrrectly
	 */
	
	public static void infoCheck(String newInfo)
		throws AccountFormatException{
		
			boolean pass = false;
			int j = 0;
			String regex;
		
			for(int i = 0; i < custAccount.size(); i++){
			
				if(custAccount.get(i).getAddress().equalsIgnoreCase(newInfo)){
				
					j = i;
					break;
				
				}else if(custAccount.get(i).getPhoneNumber().equalsIgnoreCase(newInfo)){
				
					j = i;
					break;
				}
			}
			if(custAccount.get(j).getAddress().equalsIgnoreCase(newInfo)){
			
				regex = "[0-9]{5}";
				Pattern pattern = Pattern.compile(regex);
			
				Matcher matcher = pattern.matcher(newInfo);
				
				if(matcher.find()){
					
					if(Double.parseDouble(regexCheck("[0-9]{5,}", custAccount.get(j).getAddress())) >= 1000 && 
							Double.parseDouble(regexCheck("[0-9]{5,}", custAccount.get(j).getAddress())) <100000){
			
						pass = true;
					}
				}
			
			}else if(custAccount.get(j).getPhoneNumber().equalsIgnoreCase(newInfo)){
			
				regex = "(^([(][0-9]{3}[)]) ([0-9]{3})-([0-9]{4})$)";
				Pattern pattern = Pattern.compile(regex);
			
				Matcher matcher = pattern.matcher(newInfo);
				
				if(matcher.find()){
				
					pass = true;
				
				}
			}
			
			if(pass == false){
			
				throw new AccountFormatException();
			}
		}
	
	/**
	 * Method that creates a new file and prints the user information, packages chosen, and the total price of the package.
	 */
	public void printToFile(){
		
		String fileName = "Cell Phone Cost";
		try{
			
		PrintWriter outputFile = new PrintWriter(fileName);
		
			outputFile.println(cust.toString().replace("[", "").replace("]", ""));
			outputFile.println(totalCost.getTalkPrice() + ", " + totalCost.getPhonePrice() + ", " + totalCost.getDataPrice());
			outputFile.println(totalCost.toString());

			outputFile.close();
			System.out.println("File-write Successful");
			
		}
		catch(FileNotFoundException e){
			
			System.out.println(e.getMessage());
		}
	}
}
