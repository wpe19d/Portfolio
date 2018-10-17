/**
 * This class stores words into a hash set and check words against them
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project3
 * File Name:  WordDictionary.java
 */
package application;
import java.io.File;
import java.util.HashSet;

public class WordDictionary 
{
	private HashSet<String> set = new HashSet<String>();
	
	/**
	 * writes a dictionary set of words to a hash set.
	 */
	public void writeToSet()
	{	
		String line;
		File fileName = new File("Words.txt");
		InputFile file = new InputFile(fileName);
		
		while((line = file.readLine(false)) != null)
		{
			set.add(line.toUpperCase());
		}
	}
	
	/**
	 * Looks to see if input word exists in dictionary
	 * @param word
	 * @return
	 */
	public boolean checkWord(String word)
	{
		boolean exists = false;
		word = word.toUpperCase();
		
		if(set.contains(word));
		{
			exists = true;
		}	
		return exists;
	}
	
	/**
	 * Returns a hash set
	 * @return
	 */
	public HashSet<String> getSet()
	{
		return set;
	}
}
