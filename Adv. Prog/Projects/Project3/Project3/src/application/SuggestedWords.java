/**
 * This class looks for suggested words
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project3
 * File Name:  SuggestedWords.java
 */
package application;

import java.util.ArrayList;

public class SuggestedWords 
{
	
	private ArrayList<String> suggestedWords;
	private ArrayList<String> alphabet;
	private WordDictionary dictionary;
	
	/**
	 * Constructor to initialize the variables
	 */
	public SuggestedWords()
	{
		suggestedWords = new ArrayList<String>();
		alphabet = new ArrayList<String>();
		setAlphabet();
		int a = 1;
	}
	
	/**
	 * Constructor to initialize the variables
	 * @param dictionary input word dictionary
	 */
	public SuggestedWords(WordDictionary dictionary)
	{
		suggestedWords = new ArrayList<String>();
		alphabet = new ArrayList<String>();
		this.dictionary = dictionary;
		setAlphabet();
		int a = 1;
	}
	
	/**
	 * method that looks for suggestions
	 * @param wrongWord input misspelled word
	 */
	public void checkForSuggestions(String wrongWord)
	{
		checkForMissingLetter(wrongWord);
		checkForAddedLetter(wrongWord);
		checkForSwappedCharacters(wrongWord);
	}
	
	/**
	 * Method that looks for a missing letter to spell the word correctly
	 * @param wrongWord
	 */
	public void checkForMissingLetter(String wrongWord)
	{
		StringBuilder word = new StringBuilder(wrongWord);
		
		for(int i = 0; i <= wrongWord.length(); i++)
		{
			for(int j = 0; j < alphabet.size(); j++)
			{
				word.insert(i, alphabet.get(j));
				
				if(dictionary.getSet().contains(word.toString().toUpperCase()))
				{
					addSuggestedWord(word.toString());
				}
				word.deleteCharAt(i);
			}
		}
	}
	
	/**
	 * Method to remove letters to see if there was an extra letter in the word.
	 * @param wrongWord
	 */
	public void checkForAddedLetter(String wrongWord)
	{
		StringBuilder word = new StringBuilder(wrongWord);
		char letter;
		
		for(int i = 0; i < wrongWord.length(); i++)
		{
			letter = word.charAt(i);
			word.deleteCharAt(i);
			
			if(dictionary.getSet().contains(word.toString().toUpperCase()))
			{
				addSuggestedWord(word.toString());
			}
			word.insert(i, letter);
		}
	}
	
	/**
	 * Method to check if letters were swapped around.
	 * @param wrongWord
	 */
	public void checkForSwappedCharacters(String wrongWord)
	{
		StringBuilder word = new StringBuilder(wrongWord);
		char firstLetter;
		char secondLetter;
		
		for(int i = 0; i < (wrongWord.length() - 1); i++)
		{
			firstLetter = word.charAt(i);
			secondLetter = word.charAt(i + 1);
			word.deleteCharAt(i);
			word.deleteCharAt(i);
			word.insert(i, secondLetter);
			word.insert(i + 1, firstLetter);
			String a = word.toString();
			
			if(dictionary.getSet().contains(word.toString().toUpperCase()))
			{
				addSuggestedWord(word.toString());
			}
			word.setLength(0);
			word.append(wrongWord);
		}
	}
	
	/**
	 * returns the alphabet as an ArrayList
	 * @return
	 */
	public ArrayList<String> getAlphabet()
	{
		return alphabet;
	}
	
	/**
	 * sets the alphabet
	 */
	public void setAlphabet()
	{
		for(int i = 97; i < 123; i++)
		{
			alphabet.add(Character.toString((char) i));
		}
	}

	public ArrayList<String> getSuggestedWords() 
	{
		return suggestedWords;
	}

	public void addSuggestedWord(String word) 
	{
		suggestedWords.add(word);
	}
	
	public void clearSuggestedWords()
	{
		suggestedWords.clear();
	}
}
