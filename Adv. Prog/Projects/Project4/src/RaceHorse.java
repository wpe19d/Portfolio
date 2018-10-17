/**
 * This class runs a thread.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project4
 * File Name:  RaceHorse.java
 */
import java.util.Random;

public class RaceHorse implements Runnable
{
	MainController controller;
	Random random;
	
	private int horseNumber;
	
	/**
	 * Constructor 
	 * @param controller for the program
	 * @param horseNumber the number of the horse
	 */
	public RaceHorse(MainController controller, int horseNumber)
	{
		this.controller = controller;
		random = new Random();
		this.horseNumber = horseNumber;
	}
	
	/**
	 * runs the thread.
	 */
	@Override
	public void run() 
	{
		try 
		{
			race();
		} 
		catch (InterruptedException e) 
		{
			System.out.println("ERROR:  Error Running thread");
			e.printStackTrace();
		}
	}
	
	/**
	 * starts the race for the thread.
	 * @throws InterruptedException
	 */
	public void race() throws InterruptedException
	{
		while(!controller.getIsWinner())
		{
			controller.moveImage(horseNumber);
		}
	}
}
