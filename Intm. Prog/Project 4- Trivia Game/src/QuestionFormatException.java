/**
 * This is a format exception that is thrown when the question or answer is not the correct length.
 * 
 * @author Wes's PC
 * @version 1.0
 * 
 * COP 3022 Project 4
 * File Name:  QuestionFormatException.java
 */


public class QuestionFormatException extends Exception{
	
	public QuestionFormatException(){
		
		super("Question Length cannot be more than 50 characters.  Answer length cannot be longer than 20 characters."
				+ "\nPlease re-enter the information.");
		
	}
	
public QuestionFormatException(String message){
		
		super(message);
		
	}

}
