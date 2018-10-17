/**
 * This class reads in a file.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project 2
 * File Name:  InputFile.java
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class InputFile 
{
	private File fname;
	private BufferedReader fileReader;
	
	/**
	 * Constructor to initialize the variables
	 */
	public InputFile()
	{
		fname = null;
	}
	
	/**
	 * Constructor to initialize the variables
	 * @param fname input file name
	 */
	public InputFile(String fname)
	{
		this.fname = new File(fname);
		if(this.fname == null)
		{
			System.out.println("ERROR: file: " + fname + " not found");
		}
		
		try 
		{
			fileReader = new BufferedReader(new FileReader(this.fname));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
	}
	
	/**
	 * Reads the file and returns one line.
	 * @return returns one line from the file.
	 */
	public String readFile()
	{
		String line = null;
		
		try 
		{
			line = fileReader.readLine();
		} 
		catch (IOException e) 
		{
			System.out.println("ERROR: Unable to read from file");
			e.printStackTrace();
		}

		return line;
	}
	
	/**
	 * Closes the file
	 */
	public void closeFile()
	{
		try 
		{
			fileReader.close();
		} 
		catch (IOException e) 
		{
			System.out.println("ERROR:  Unable to close file.");
			e.printStackTrace();
		}
	}

}
