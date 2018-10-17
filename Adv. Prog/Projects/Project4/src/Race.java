/**
 * This Class assigns the thread objects and begins their run
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project4
 * File Name:  Race.java
 */

public class Race 
{	
	MainController controller;
	
	/**
	 * Constructor that fires the threads for all of the horses
	 * @param controller the controller that runs the program
	 */
	public Race(MainController controller)
	{	
		this.controller = controller;
		
		for(int i = 0; i < 5; i++)
		{
			RaceHorse horse = new RaceHorse(controller, i);
			Thread horseThread = new Thread(horse);
			
			horseThread.start();
		}
	}
}
