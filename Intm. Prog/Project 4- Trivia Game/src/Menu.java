/**
 * This class populates the menu for the admin and player.  It also runs the game interface for the user.
 * 
 * @author Wesley Easton
 * @version 1.0
 * 
 * COP 3022 Project 4
 * File Name:  Menu.java
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Menu {
	
	Scanner input = new Scanner(System.in);
	
	
	Admin a1 = new Admin();
	QuestionGen gen1 = new QuestionGen();
	Player player1 = new Player();
	
	ObjectInputStream objectInput;
	ObjectOutputStream output;
	
	private int adminOption;
	private boolean done;
	private String choice;
	private String name;
	private String username;
	
	/**
	 * Constructor that initializes the variables
	 */
	
	public Menu(){
		
		adminOption = 0;
		//playerOption = 0;
		done = false;
		choice = "";
		name = "";
		username = "";
		
	}

	/**
	 * Method that allows the admin list all questions, add or delete a question, and quit the program.
	 * @throws IOException
	 */
	
	public void adminMenu() 
			throws IOException
	{
		a1.open();		
		a1.readFile();
		a1.storeQuestions();
	
		while(done != true){
			adminOption = 0;
				System.out.println("Please select a menu option.");
				System.out.println("1. List all questions.\n2. Delete question.\n3. Add question.\n4. Exit System");
		
				adminOption = input.nextInt();
				input.nextLine();
		
				switch(adminOption){
			
				case 1: 
				
					System.out.println(a1.size());
					a1.printAllQuestions();
					break;
		
				case 2:
				
					System.out.println("What question would you like to delete?");
				
					int delete = input.nextInt();
					input.nextLine();
					a1.adminDelete(delete);
					break;
			
				case 3:
				
					System.out.println("Add question, answer, points.");
					
					while(done != true){
						try{
							
							System.out.println("Question:");
							String question = input.nextLine();
							a1.checkQuestionLength(question);
							
							System.out.println("Answer:");
							String answer = input.nextLine();
							a1.checkAnswerLength(answer);
						
							System.out.println("Point Value:");
							int pointValue = input.nextInt();
							input.nextLine();
							a1.addQuestion(question, answer, pointValue);
			
							a1.adminAdd();
							break;
						}
						catch(QuestionFormatException e){
						
							System.out.println(e.getMessage());
						}
					}
					break;
				case 4: 
					System.out.println("System is exiting.");
					System.exit(0);
					break;
		
				default:
			
					System.out.println("\n\n\nInvalid entry.  Please try again.");
			
				}
		
				System.out.println("Are you done? y/n");
				choice = input.nextLine();
			
				if(choice.equalsIgnoreCase("y")){
				
					done = true;
					a1.close();
				}
		}
		
	}
	
	/**
	 * Method that allows a player to create or load a game file.  It will also allow the player to display
	 * their file information.
	 */
	
	public void playerMenu(){
		
		done = false;
		
		System.out.println("Do you have a player account set up? y/n");
		choice = input.nextLine();
		
		
		while(done != true){
			
			try{
			
				if(choice.equalsIgnoreCase("y")){
				
					System.out.println("Enter the player name followed by .dat");
				
					name = input.nextLine();
				
					objectInput = new ObjectInputStream(new FileInputStream(name));
		
					player1 = (Player)objectInput.readObject();
				
					done = true;
		
				}
		
				else if(choice.equalsIgnoreCase("n")){
				
					System.out.println("Please enter your name and username:");
					
					System.out.println("Name:");
					name = input.nextLine();
					System.out.println("Username");
					username = input.nextLine();
				
					output = new ObjectOutputStream(new FileOutputStream(name + ".dat"));
				
					player1 = new Player(name, username);
			
					output.writeObject(player1);
			
					output.close();
			
					System.out.println("File created successfully");
				
					done = true;
				}
			}
			catch(FileNotFoundException e){
			
				System.out.println("File not found");
			
			}
			catch(IOException e){
			
				System.out.println("File input error.");
			
			}
			catch(ClassNotFoundException e){
			
				System.out.println("Class not found.");
			}
	
		}
		System.out.println("Do you want to display your player info? y/n");
		
		choice = input.nextLine();
		
		if(choice.equalsIgnoreCase("y")){
			
			
			
			System.out.println("The player info is:");
			System.out.println(player1);
			
		}
	}
	
	/**
	 * Method that runs the actual game.  It will as the user if they want to start a new game.  The method will ask the user
	 * five questions and wait for the answers.  It will total up the score and add it to the user's file.  The user has the option
	 * to play again if they would like after the questions have been asked.
	 * @throws IOException
	 */
	
	public void runGame() 
			throws IOException
	{
		
		done = false;
		
		System.out.println("Would you like to start a new game?");
		
		choice = input.nextLine();
		
		if(choice.equalsIgnoreCase("y")){
		
			gen1.open();
			
			while(done != true){
			
				gen1.genRandQuestions();
				

				do{
					System.out.println(gen1.getRandQuestion().trim());
				
					choice = input.nextLine();
				
					if(choice.equalsIgnoreCase(gen1.getRandAnswer().trim())){
					
						System.out.println("Correct!");
					
						player1.setTotalScore(gen1.getRandPointValue());
						
						System.out.println("Points Awarded: " + gen1.getRandPointValue());
					
					}else
						System.out.println("Incorrect, the correct answer is " + gen1.getRandAnswer());
					
					
				}while(gen1.getRandQuestionNumber() < 4);
			
				System.out.println("Your new Total Score is:" + player1.getTotalScore());
				
				output = new ObjectOutputStream(new FileOutputStream(player1.getName() + ".dat"));
				
				output.writeObject(player1);
				
				output.close();
				
				System.out.println("\n" + player1.getUsername() +" Total Score: " + player1.getTotalScore());
				
				System.out.println("Would you like to play again?");
			
				choice = input.nextLine();
			
				if(choice.equalsIgnoreCase("n")){
				
					System.out.println("Thanks for playing!");
					
					done = true;
					gen1.close();
				}
				gen1.resetRandQuestionNumber();
				gen1.resetRandQuestions();

			}
		}
		else if(choice.equalsIgnoreCase("n")){
			
			System.out.println("System exiting");
			System.exit(0);
		
		}
		else
			System.out.println("Invalid entry");

	}

}
