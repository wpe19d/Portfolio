/**
 * This class is the format for the player objects.  It sets up their name, username, and total score.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 3022 Project 4
 * File Name:  Player.java
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Player implements Serializable{
	
	
	private String name;
	private String username;
	private int totalScore;
	String choice;
	
	/**
	* Constructor that initializes the variables
	*/
	
	public Player(){
		
		setName("");
		setUsername("");
		setTotalScore(0);
		
	}
	
	/**
	 * Constructor that assigns info to the variables.  Formatted for name and username only.
	 * @param newName  The input name
	 * @param newUsername  The input username
	 */
	
	public Player(String newName, String newUsername){
		
		setName(newName);
		setUsername(newUsername);
		getTotalScore();
		
	}

	/**
	 * Constructor that assigns info to the variables.  Formatted for name, username, and the total score.
	 * @param newName  The input name
	 * @param newUsername  The input username
	 * @param newTotal  The input total score
	 */
	
	public Player(String newName, String newUsername, int newTotal){
		
		setName(newName);
		setUsername(newUsername);
		setTotalScore(newTotal);
		
	}

	/**
	 * Returns the player's name
	 * @return Returns the name
	 */
	
	public String getName() {
		
		return name;
		
	}

	/**
	 * Sets the player's name
	 * @param newName The input name
	 */
	
	public void setName(String newName) {
		
		name = newName;
		
	}

	/**
	 * Returns the player's username
	 * @return  Returns the username
	 */
	
	public String getUsername() {
		
		return username;
		
	}
	
	/**
	 * Sets the player's username
	 * @param newUsername  The input username
	 */
	
	public void setUsername(String newUsername) {
		
		username = newUsername;
		
	}
	/**
	 * Returns the player's total score
	 * @return  Returns the total score
	 */
	
	public int getTotalScore() {
		
		return totalScore;
		
	}
	/**
	 * Keeps track of and updates the total score.
	 * @param newTotal  The number of points to add to the score
	 */
	
	public void setTotalScore(int newTotal) {
		
		totalScore += newTotal;
		
	}
	
	/**
	 * toString to display the player info
	 */
	
	public String toString(){
		
		return ("Name: " + getName() + ". Username: " + getUsername() + ". Total Score: " + getTotalScore());
				
	}
	
	
	

}
