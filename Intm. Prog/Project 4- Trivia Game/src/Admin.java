/**
 * The Admin class allows the user to modify the Random Access File by either adding or deleting questions.
 * It extends the Questions class.
 * 
 * @author Wesley Easton
 * @version 1.0
 * 
 * COP 3022 Project 4
 * File Name:  Admin.java
 * 
 */

import java.io.IOException;
import java.util.ArrayList;



public class Admin extends Questions{
	
	private ArrayList<Questions> questions = new ArrayList<Questions>(30);
	boolean tooLong;
	
	/**
	 * Constructor that initializes the variables.
	 */
	
	public Admin(){

		super();
		tooLong = false;
	}
	
	/**
	 * Stores the questions in an Array List
	 * @throws IOException
	 */
	
	public void storeQuestions() 
			throws IOException
	{
		
		for(int i = 0; i <= size(); i++){
			
			questions.add(writeInfo(i));
		
		}
	}

	/**
	 * Adds questions to the Array List
	 * @throws IOException
	 */
	
	public void adminAdd() 
			throws IOException
	{
		
		questions.add(writeInfo(size()));
		
	}
	
	/**
	 * Deletes question from Array List based on input question ID
	 * @param inputID Input question ID
	 * @throws IOException
	 */
	
	public void adminDelete(int inputID) 
			throws IOException
	{
		
		questions.remove(find(inputID));
		deleteQuestion(inputID);
	}
	
	/**
	 * Returns question based on input question ID
	 * @param number input question ID
	 * @return Returns a question object
	 * @throws IOException
	 */
	
	public Questions getQuestion(int number) 
			throws IOException
	{
		
		return questions.get(find(number));
		
	}
	
	/**
	 * Checks the question length.  If the question is greater than 50 characters, then the method returns a true boolean
	 * and throws Question Format Exception
	 * @param newQuestion Input question
	 * @throws QuestionFormatException Throws when the question or answer length is too long.
	 */
	
	public void checkQuestionLength(String newQuestion)
		throws QuestionFormatException
		{
		tooLong = false;
		
		if(newQuestion.length() > 50){
			
			tooLong = true;
			
			throw new QuestionFormatException();
		}
		
	}
	
	/**
	 * Checks the answer length.  If the answer is greater than 20 characters, then the method returns a true boolean
	 * and throws Question Format Exception
	 * @param newAnswer Input answer
	 * @throws QuestionFormatException Throws when the question or answer length is too long.
	 */
	
	public void checkAnswerLength(String newAnswer)
			throws QuestionFormatException
	{
		
		tooLong = false;
		
		if(newAnswer.length() > 20){
			
			tooLong = true;
			
			throw new QuestionFormatException();
		}
		
	}
	
	/**
	 * Prints all questions in the Array List
	 */
	public void printAllQuestions(){
		
		for(int i = 0; i < questions.size(); i++){
			
			System.out.println(questions.get(i));
			
		}
	}
	
}
