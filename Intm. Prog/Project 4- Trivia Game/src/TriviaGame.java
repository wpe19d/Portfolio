/**
 * This class is the main class that calls the classes to run the game.
 * 
 * @author Wesley Easton
 * @version 1.0
 * 
 * COP 3022 Project 4
 * File Name:  TriviaGame.java
 */

import java.io.IOException;
import java.util.Scanner;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class TriviaGame {
	
	public static void main(String[] args) throws IOException{
	
		Scanner input = new Scanner(System.in);
		Menu run1 = new Menu();
		
		String choice;
		
		System.out.println("Are you admin? y/n");
		choice = input.nextLine();
		
		if(choice.equalsIgnoreCase("y")){
			
			run1.adminMenu();
			
		}
		
		run1.playerMenu();
		run1.runGame();

		
	}

}
