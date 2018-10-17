/**
 * This Class handles the client logic and the ui for the user.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project5
 * File Name:  GameCLient.java
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameClient 
{
	static boolean win = false;
	
	public static void main(String[] args) throws IOException
	{
		final int PORT = 8888;
		boolean wait = false;
		int count = 0;
		
		Player player = new Player();
		Socket socket = new Socket("localhost", PORT);
		
		InputStream inputStream = socket.getInputStream();
		OutputStream outputStream = socket.getOutputStream();
		
		Scanner input = new Scanner(inputStream);
		PrintWriter output = new PrintWriter(outputStream);
		
		output.print(player.joinPlayer() + "\n");
		output.flush();
		
		if(player.getPlayerId() == 0)
		{
			if(input.hasNext())
			{
				player.setPlayerId(Integer.parseInt(input.nextLine()));
				System.out.println(input.nextLine());
			}
		}	
		
		//preforms the main game logic while there is no win
		while(!win) 
		{
			if(input.hasNext())
			{
				if(count < 1)
				{
					printBoard(input);
					count++;
				}
				
				String nextInput = input.nextLine();
				
				if((!nextInput.equals("")) && Integer.parseInt(nextInput) == player.getPlayerId())
				{
					output.println(player.makeChoice());
					count = 0;
					wait = true;
				}
				
				output.flush();
				
				if(wait)
				{
					nextInput = input.nextLine();
					checkTurn(nextInput, player, output, input);
					wait = false;
					nextInput = input.nextLine();
					if(nextInput.toUpperCase().contains("WON"))
					{
						System.out.println(nextInput);
						win = true;
					}
				}
			}
		}
		
		input.close();
		socket.close();
	}
	
	/**
	 * prins the board
	 * @param input
	 */
	public static void printBoard(Scanner input)
	{
		int count = 0;
		
		while(count < 3)
		{
			System.out.println(input.nextLine());
			count++;
		}
	}
	
	/**
	 * checks to make sure the move is valid
	 * @param nextInput
	 * @param player
	 * @param output
	 * @param input
	 */
	public static void checkTurn(String nextInput, Player player, PrintWriter output, Scanner input)
	{
		if(nextInput.toUpperCase().equals("FALSE"))
		{
			while(nextInput.toUpperCase().equals("FALSE"))
			{
				System.out.println("Invalid move.  Please enter a valid range");
				output.println(player.makeChoice());
				output.flush();
				nextInput = input.nextLine();
			}
		}
		else if(nextInput.toUpperCase().equals("QUIT"))
		{
			win = true;
			System.exit(0);
		}
	}
}

