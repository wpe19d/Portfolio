/**
 * This class creates/opens a log file and writes the SQL statements to it.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project 1
 * File Name:  Log.java
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

	private File fname;
	private BufferedWriter file;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime time = LocalDateTime.now();
	
	/**
	 * Constructor to initialize the variables
	 */
	public Log()
	{
		try 
		{
			fname = new File("dbOperations.log");
			
			if(fname == null)
			{
				System.out.println("Fail");
			}
			file = new BufferedWriter(new FileWriter(fname));
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes to the Log File the input string and time
	 * @param dbStatement The input string that will be written to the file
	 */
	public void writeToFile(String dbStatement)
	{
		try 
		{
			file.write(time.now() + "    " + dbStatement);
			file.newLine();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the log file.
	 */
	public void closeFile()
	{
		try
		{
			file.close();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
