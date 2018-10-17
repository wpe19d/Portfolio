/**
 * This Class handles the communication between the client and the server
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project5
 * File Name:  GameService.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameService implements Runnable
{
	private Socket socket;
	private Game game;
	private Scanner inStream;
	private PrintWriter outStream;
	private int playerId;
	private String command;
	private boolean done = false;
	private int count = 0;
	
	public GameService(Socket socket, Game game, int playerId)
	{
		this.socket = socket;
		this.game = game;
		this.playerId = playerId;
		
	}
	
	//Run method for the thread.
	@Override
	public void run() 
	{
		try
		{
			try 
			{
				inStream = new Scanner(socket.getInputStream());
				outStream = new PrintWriter(socket.getOutputStream());
				System.out.println("Connected");
				try 
				{
					runGame();
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
			finally
			{
				socket.close();
			}
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * runs the game
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void runGame() throws IOException, InterruptedException
	{
		if(!inStream.hasNext())
			return;
		
		command = inStream.next();

		executeCommand(command);
	}
	
	/**
	 * Executes the first command of the game
	 * @param command
	 * @throws InterruptedException
	 */
	public void executeCommand(String command) throws InterruptedException
	{
		if(command.toUpperCase().contains("JOIN"))
		{
			String name = inStream.next();
			executeJoin(name);
		}
		
		executeChoice();
	}
	
	/**
	 * Allows the player to join a game
	 */
	public void executeJoin(String name) throws InterruptedException
	{
		if(playerId == 1)
		{
			game.setPlayerOne(name);
			outStream.println(1);
			outStream.flush();
			
			if(game.getPlayerTwoName() == null)
				outStream.println("You are Player 1.  Waiting for other players...");
			else
				outStream.println("You are Player 1");
			
			outStream.flush();
		}
		else if(playerId == 2)
		{
			game.setPlayerTwo(name);
			outStream.println(2);
			outStream.flush();
			
			if(game.getPlayerOneName() == null)
				outStream.println("You are Player 2.  Waiting for other players...");
			else
				outStream.println("You are Player 2");
			
			outStream.flush();
		}
		
		while((game.getPlayerOneName() == null) ||(game.getPlayerTwoName() == null))
		{
			Thread.sleep(10);
		}
		
		game.makeBoard();
	}
	
	/**
	 * Executes the player choice options
	 * @throws InterruptedException
	 */
	public void executeChoice() throws InterruptedException
	{
		while(true)
		{
			done = false;
			command = "";
			
			if(game.isWin() || game.isQuit())
				break;
			
			if(game.getTurn() == 0 && playerId == 2)
			{
				sendBoardToClient(2);
				makeMove("O", 2);
			}
			else if(game.getTurn() != 0 && playerId == 1)
			{
				sendBoardToClient(1);
				makeMove("X", 1);
			}
			

			Thread.sleep(10);	
		}
	}
	
	/**
	 * Sends the state of the game board to the client
	 * @param playerId
	 */
	public void sendBoardToClient(int playerId)
	{
		outStream.println((game.printBoard()));
		outStream.flush();
		outStream.println(playerId);
		outStream.flush();
	}
	
	/**
	 * performs the logic for the move
	 * @param marker the players character (X or O)
	 * @param playerId
	 */
	public void makeMove(String marker, int playerId)
	{
		while(!done)
		{
			command = inStream.next();
			
			if(command.toUpperCase().contains("CHOICE"))
			{
				if(game.checkChoice(inStream.next(), inStream.next(), marker))
				{
					outStream.println("TRUE");
					outStream.flush();
					done = true;
				}
				else
				{
					outStream.println("FALSE");
					outStream.flush();
				}
			}
			else if(command.toUpperCase().contains("QUIT"))
			{
				outStream.println("QUIT");
				outStream.flush();
				game.setIsQuit(true);
				done = true;
			}
		}
		
		game.checkWinConditions(marker);
		game.increaseTurn();
		
		if(game.isWin())
			outStream.println("Player " + playerId + " has won!!!");
		else
			outStream.println("No Winner");
		
		outStream.flush();
	}
}
