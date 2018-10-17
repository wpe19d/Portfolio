/**
 * This Class handles all of the logic for the program.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project4
 * File Name:  MainController.java
 */
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MainController implements Initializable
{
	private double startTime;
	private double endTime;
	private double finalTime;
	private boolean isWinner;
	private final double FINISH_LINE = 775;
	Random random;
	Race race;
	Lock raceLock = new ReentrantLock();
	
	@FXML
	private ImageView horseOne;
	@FXML
	private ImageView horseTwo;
	@FXML
	private ImageView horseThree;
	@FXML
	private ImageView horseFour;
	@FXML
	private ImageView horseFive;
	@FXML
	private Label winningHorse;
	@FXML
	private Label winningTime;
	
	/**
	 * satisfies the Implements interface.
	 */
	@Override
	public void initialize(URL url, ResourceBundle resource) 
	{
		random = new Random();
	}
	
	/**
	 * start method to start the race.
	 */
	public void start()
	{
		startTime = System.nanoTime();
		isWinner = false;
		race = new Race(this);
	}
	
	/**
	 * resets the position of the ImageViews and resets the Label text
	 */
	public void reset()
	{
		horseOne.setX(0);
		horseTwo.setX(0);
		horseThree.setX(0);
		horseFour.setX(0);
		horseFive.setX(0);
		winningHorse.setText("");
		winningTime.setText("");
	}
	
	/**
	 * This method moves the image view a random number in the +x direction of the horse number passed into the method.
	 * Locks the code until the thread is complete.  It then
	 * puts the thread to sleep for a random amount of time.
	 * @param horseNumber the number of the horse.  
	 * @throws InterruptedException
	 */
	public void moveImage(int horseNumber) throws InterruptedException
	{
		try
		{
			raceLock.lock();
		
		if(horseNumber == 0)
		{
			if(checkForWin(horseOne.getX()))
			{
				setWinner(horseNumber + 1);
				Thread.interrupted();
			}
			else
			{
				horseOne.setX(horseOne.getX() + (random.nextInt(5) + 5));
			}
		}
		else if(horseNumber == 1)
		{
			if(checkForWin(horseTwo.getX()))
			{
				setWinner(horseNumber + 1);
				Thread.interrupted();
			}
			else
			{
				horseTwo.setX(horseTwo.getX() + (random.nextInt(5) + 5));
			}
		}
		else if(horseNumber == 2)
		{
			if(checkForWin(horseThree.getX()))
			{
				setWinner(horseNumber + 1);
				Thread.interrupted();
			}
			else
			{
				horseThree.setX(horseThree.getX() + (random.nextInt(5) + 5));
			}
		}
		else if(horseNumber == 3)
		{
			if(checkForWin(horseFour.getX()))
			{
				setWinner(horseNumber + 1);
				Thread.interrupted();
			}
			else
			{
				horseFour.setX(horseFour.getX() + (random.nextInt(5) + 5));
			}
		}
		else if(horseNumber == 4)
		{
			if(checkForWin(horseFive.getX()))
			{
				setWinner(horseNumber + 1);
				Thread.interrupted();
			}
			else
			{
				horseFive.setX(horseFive.getX() + (random.nextInt(5) + 5));
			}
		}
		}
		finally
		{
			raceLock.unlock();
			Thread.sleep(random.nextInt(50) + 25);
		}
	}
	
	/**
	 * gets the Final time.
	 * @return returns the final time as a string
	 */
	public String getTime()
	{
		return String.format("%.2f", finalTime);
	}
	
	
	/**
	 * checks for the win condition
	 * @param xValue xValue of a imageView
	 * @return returns true or false
	 */
	public boolean checkForWin(double xValue) 
	{
		if(!getIsWinner())
		{
			if(xValue > FINISH_LINE)
			{
				setIsWinner(true);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * returns whether there is a winner or not
	 * @return returns true or false
	 */
	public boolean getIsWinner()
	{
		return isWinner;
	}
	
	/**
	 * sets the winner and changes the Label text to display the correct information
	 * @param horseNumber the winning horseNumber
	 */
	public void setWinner(int horseNumber)
	{
		Platform.runLater(() -> {
			winningHorse.setText("Horse Number " + (horseNumber) + " is the Winner!");
			winningTime.setText("Final time: " + getTime() + "s");
        });
	}
	
	
	/**
	 * calculates the time when winner is calculated
	 * @param response
	 */
	public void setIsWinner(boolean response)
	{
		endTime = System.nanoTime();
		finalTime = (endTime - startTime) / 1000000000;
		getTime();
		isWinner = response;
	}
	
	/**
	 * closes the window
	 * @param event
	 */
	public void closeWindow(ActionEvent event)
	{
		Platform.exit();
		System.exit(0);
	}
}
