/**
 * This class generates 5 random questions to display to the user.  Extends the questions
 * class to get the question objects.
 * 
 * @author Wesley Easton
 * @version 1.0
 * 
 * COP 3022 Project 4
 * File Name:  QuestionGen.java
 */
import java.util.Random;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionGen extends Questions {

	
	Random rand = new Random();
	ArrayList<Questions> randQuestion = new ArrayList<Questions>(5);
	
	int number;
	private static int questionNumber;
	
	/**
	* Constructor that initializes the variables
	*/
	
	public QuestionGen(){
		
		super();
		number = 0;
		questionNumber = -1;
		
	}
	
	/**
	 * Method to generate random questions.  Stores in ArrayList 
	 */
	
	public void genRandQuestions(){
		try{
		for(int i = 0; i < 5; i++){
			
			number = rand.nextInt(size());
			
			randQuestion.add(writeInfo(number));
			
			if(checkRandQuestion(i) == true){
				
				randQuestion.remove(i);
				
				i--;
	
			}
		}
		}catch(IOException e){
			
			e.getMessage();
			System.out.println("Error generation Random Questions.");
		}
		
	}
	
	/**
	 * @return Retruns the random question question
	 */
	public String getRandQuestion(){
		
		questionNumber++;
		
		if(questionNumber > 4){
			
			questionNumber = -1;
		}
		
		return randQuestion.get(questionNumber).getQuestion();
		
	}
	
	/**
	 * @return Returns the random question answer
	 */
	
	public String getRandAnswer(){
		
		return randQuestion.get(questionNumber).getAnswer();
		
	}
	
	/**
	 * @return Returns random question point value.
	 */
	
	public int getRandPointValue(){
		
		return randQuestion.get(questionNumber).getPointValue();
		
	}
	/**
	 * Resets the random question number
	 */
	
	public void resetRandQuestionNumber(){
		
		questionNumber = -1;
	}
	
	/**
	 * 
	 * @return Returns the number that selects the random question
	 */
	
	public int getRandQuestionNumber(){
		
		return questionNumber;
		
	}
	/**
	 * Resets the random number that selects the question.
	 */
	
	public void resetRandQuestions(){
		
		for(int i = 0; i < randQuestion.size(); i++){
			
			randQuestion.remove(i);
		}
	}
	
	/**
	 * Method to check if the question is already in the ArrayList.
	 * @param newQuestion Input Question.
	 * @return Returns a boolean
	 */
	
	public boolean checkRandQuestion(int newQuestion){
		
		boolean repeat = false;
		
		for(int i = 0; i < newQuestion; i++){
		
			if(randQuestion.get(newQuestion).getQuestion().equalsIgnoreCase(randQuestion.get(i).getQuestion())){
				
				repeat = true;
				break;
			}
			
		}
		return repeat;

	}
	
	/**
	 * Displays all random questions from ArrayList
	 */
	
	public void printRandQuestions(){
		
		for(int i = 0; i < randQuestion.size(); i++){
			
			System.out.println(randQuestion.get(i));
			
		}
		
	}

}
