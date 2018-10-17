/**
 * This class reads in a file.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project3
 * File Name:  InputFile.java
 */
package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.TextArea;

public class InputFile 
{
	private File fname;
	private BufferedReader fileReader;
	private BufferedWriter fileWriter;
	//private ArrayList<String> listOfWords = new ArrayList<String>();
	private String [] words;
	
	/**
	 * Constructor to Initialize the variables
	 */
	public InputFile()
	{
		fname = null;
	}
	
	/**
	 * Constructor to initialize the variables
	 * @param fname input File name
	 */
	public InputFile(File fname)
	{
			this.fname = fname;
			if(this.fname == null)
			{
				System.out.println("ERROR: file: " + fname + " not found");
			}
			
			setFileReader(fname);
			
	}
	
	/**
	 * Reads a line from a file and adds the words to a list
	 * @param addToList boolean to tell the method to store the words
	 * @return returns a string as a line
	 */
	public String readLine(boolean addToList)
	{
		String line = null;
		
		try 
		{
			line = fileReader.readLine();
			
			if(addToList && line != null)
			{
				words = splitLine(line);
			}
		} 
		catch (IOException e) 
		{
			System.out.println("ERROR: Unable to read from file");
			e.printStackTrace();
		}
		
		return line;
	}
	
	/**
	 * Reads in all lines from the file
	 * @return returns the words as a string array
	 */
	public String[] readAllLines()
	{
		String fileString;
		
		while((fileString = readLine(true)) != null);
		
		return getListOfWords();
	}
	
	/**
	 * Writes from textArea to a file
	 * @param file file name
	 * @param textArea edited textArea
	 */
	public void writeToFile(File file, TextArea textArea)
	{
		try 
		{
			fileWriter = new BufferedWriter(new FileWriter(file));
			fileWriter.write(textArea.getText());
		} 
		catch (IOException e) 
		{
			System.out.println("Failed to Save to file");
			e.printStackTrace();
		}
	}
	
	public void closeFileReader()
	{
		try 
		{
			fileReader.close();

		} 
		catch (IOException e) 
		{
			System.out.println("ERROR:  Unable to close file");
			e.printStackTrace();
		}
	}
	
	
	public void closeFileWriter()
	{
		try 
		{
			fileWriter.close();

		} 
		catch (IOException e) 
		{
			System.out.println("ERROR:  Unable to close file");
			e.printStackTrace();
		}
	}
	
	public String[] getListOfWords()
	{
		return words;
	}
	
	public void setFileReader(File file)
	{
		try
		{
			fileReader = new BufferedReader(new FileReader(file));
		}
		catch(IOException e)
		{
			e.getMessage();
		}
	}
	
	public String[] splitLine(String line)
	{
		return line.split("\\s+");
	}
}
