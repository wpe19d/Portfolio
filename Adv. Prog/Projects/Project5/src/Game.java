/**
 * This Class contains the game information and logic.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project5
 * File Name:  Game.java
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Game 
{
	private String[][] board = new String[3][3];
	private String playerOne;
	private String playerTwo;
	private boolean isWin;
	private boolean isQuit;
	private int turn;
	Lock lock = new ReentrantLock();
	
	public Game()
	{
		playerOne = null;
		playerTwo = null;
		isWin = false;
		isQuit = false;
		turn = 1;
	}
	
	public String[][] getBoard()
	{
		return board;
	}
	
	/**
	 * checks to make sure the players choice is within the board limits
	 * @param x
	 * @param y
	 * @param letter
	 * @return
	 */
	public boolean checkChoice(String x, String y, String letter)
	{
		if(Integer.parseInt(x) < 3 && Integer.parseInt(y) < 3 && board[Integer.parseInt(x)][Integer.parseInt(y)] == "-")
		{
			setChoice(x, y, letter);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Modifies the board with the players choice.
	 * @param x
	 * @param y
	 * @param letter
	 */
	public void setChoice(String x, String y, String letter)
	{
		lock.lock();
		try
		{
			board[Integer.parseInt(x)][Integer.parseInt(y)] = letter;
		}
		finally
		{
			lock.unlock();
		}
	}
	
	public String getPlayerOneName()
	{
		return playerOne;
	}
	
	public String getPlayerTwoName()
	{
		return playerTwo;
	}
	public void setPlayerOne(String playerName)
	{
		playerOne = playerName;
	}
	
	public void setPlayerTwo(String playerName)
	{
		playerTwo = playerName;
	}
	
	public void makeBoard()
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				board[i][j] = "-";
			}
		}
	}
	
	public String printBoard()
	{
		String boardString = "";
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				boardString += board[i][j];
				if(j < 2)
					boardString += " ";
			}
			boardString += "\n";
		}
		
		return boardString;
	}
	
	public int getTurn()
	{
		return turn % 2;
	}
	
	public void increaseTurn()
	{
		turn++;
	}
	
	public void checkWinConditions(String marker)
	{
		if((board[0][0] == marker) && (board[0][1] == marker) && (board[0][2] == marker))
		{
			isWin = true;
		}
		else if((board[1][0] == marker) &&  (board[1][1] == marker) && (board[1][2] == marker))
		{
			isWin = true;
		}
		else if((board[2][0] == marker) && (board[2][1] == marker) && (board[2][2] == marker))
		{
			isWin = true;
		}
		else if((board[0][0] == marker) && (board[1][0] == marker) && (board[2][0] == marker))
		{
			isWin = true;
		}
		else if((board[0][0] == marker) && (board[1][0] == marker) && (board[2][0] == marker))
		{
			isWin = true;
		}
		else if((board[0][1] == marker) && (board[1][1] == marker) && (board[2][1] == marker))
		{
			isWin = true;
		}
		else if((board[0][2] == marker) && (board[1][2] == marker) && (board[2][2] == marker))
		{
			isWin = true;
		}
		else if((board[0][0] == marker) && (board[1][1] == marker) && (board[2][2] == marker))
		{
			isWin = true;
		}
		else if((board[0][2] == marker) && (board[1][1] == marker) && (board[2][0] == marker))
		{
			isWin = true;
		}
	}
	
	public void setIsQuit(boolean isQuit)
	{
		this.isQuit = isQuit;
	}
	
	public boolean isQuit()
	{
		return isQuit;
	}
	public boolean isWin()
	{
		lock.lock();
		try
		{
			return isWin;
		}
		finally
		{
			lock.unlock();
		}
	}

}
