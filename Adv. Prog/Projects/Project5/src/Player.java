/**
 * This is the player class that contains the player info and options.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project5
 * File Name:  Player.java
 */
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Player 
{
	private int playerId;
	Scanner userInput = new Scanner(System.in);
	
	public Player()
	{
		playerId = 0;
		System.out.println("Ready to connect to a game.\n");
	}
	
	public String joinPlayer()
	{
		System.out.println("Please enter 'JOIN' <name> to begin a game:");
		return userInput.nextLine();
	}
	
	public String makeChoice()
	{
		System.out.println("Player " + getPlayerId() + "'s turn.  Please enter CHOICE followed by your <x> <y> coordinate.  Enter quit to opt out of the game\n");
		
		return userInput.nextLine();
	}
	public String playerResponse()
	{
		return userInput.nextLine();
	}
	
	public String quitPlayer()
	{
		return ("QUIT");
	}
	
	public int getPlayerId()
	{
		return playerId;
	}
	
	public void setPlayerId(int playerId)
	{
		this.playerId = playerId;
	}

}
