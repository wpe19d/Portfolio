/**
 * The Questions class reads in text from a file and generates a Random Access File that stores all of the questions, answers, point values, and question ID's.
 * The class will also allow user's to add and delete questions from the RAF and read the updated RAF.
 * 
 * @author Wesley Easton
 * @version 1.0
 * 
 * COP 3022 Project 4
 * File Name:  Questions.java
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

public class Questions {
	
	private static final int QUESTION_SIZE = 50;
	private static final int ANSWER_SIZE = 20;
	private static final int POINT_SIZE = 4;
	private static final int ID_SIZE = 4;
	private static final int RANDFILE_SIZE = QUESTION_SIZE + ANSWER_SIZE + POINT_SIZE + ID_SIZE;
	private String question;
	private String answer;
	private int pointValue;
	private static int counter;
	private int questionID;
	private String line;
	private String delimeter;
	private int numOfTokens;
	private String[] a = new String[3];
	private RandomAccessFile randomFile;
	private BufferedReader file;
	private static int numOfElements;
	
	/**
	* Constructor that initializes the variables
	*/
	
	public Questions(){
		
		question = "";
		answer = "";
		pointValue = 0;
		line = "";
		delimeter = ",";
		numOfTokens = 0;
		randomFile = null;
		numOfElements = 0;
		questionID = 0;
		
	}
	
	/**
	 * Constructor that is formatted to set the question, answer, point value, and generate a question ID.
	 * 
	 * @param newQuestion  Input question
	 * @param newAnswer  Input answer
	 * @param newPointValue  Input point value
	 */
	
	public Questions(String newQuestion, String newAnswer, int newPointValue){
		
		setQuestion(newQuestion);
		setAnswer(newAnswer);
		setPointValue(newPointValue);
		genQuestionID();
	}
	
	/**
	 * Constructor that is formatted to set the question, answer, point value, and questino ID variables 
	 * 
	* @param newQuestion  Input question
	 * @param newAnswer  Input answer
	 * @param newPointValue  Input point value
	 * @param newQuestionID  Input questionID
	 */
	
	public Questions(String newQuestion, String newAnswer, int newPointValue, int newQuestionID)
	{
		
		setQuestion(newQuestion);
		setAnswer(newAnswer);
		setPointValue(newPointValue);
		setQuestionID(newQuestionID);
		
	}
	
	/**
	 * @return Returns the question
	 */
	
	public String getQuestion() {
		
		return question;
		
	}
	
	/**
	 * Sets the question 
	 * @param newQuestion Input question
	 */
	
	public void setQuestion(String newQuestion) {
		
		question = newQuestion;
		
	}
	
	/**
	 * @return  Returns the answer
	 */
	
	public String getAnswer() {
		
		return answer;
		
	}
	
	/**
	 * Sets the answer
	 * @param newAnswer Input answer
	 */
	
	public void setAnswer(String newAnswer) {
		
		answer = newAnswer;
		
	}
	
	/**
	 * @return Returns the point value
	 */
	
	public int getPointValue() {
		
		return pointValue;
		
	}
	
	/**
	 * Sets the point value
	 * @param newPointValue Input value
	 */
	
	public void setPointValue(int newPointValue) {
		
		pointValue = newPointValue;
		
	}

	/**
	 * @return Returns the question ID
	 */
	
	public int getQuestionID(){
		
		return questionID;
		
	}
	
	/**
	 * Sets the question ID
	 * @param number Input number 
	 * @return Returns the question ID
	 */
	public int setQuestionID(int number){
		
		questionID = number;
		
		return questionID;
		
	}
	
	/**
	 * Generates a new question ID
	 * @return Returns the question ID
	 */
	
	public int genQuestionID(){
		
		counter++;
		
		questionID = counter;
		
		return questionID;
		
	}
	
	/**
	 * Returns the number of elements (questions) in RAF
	 * @return Returns the number of elements
	 */
	
	public int getNumOfElements(){
		
		return numOfElements;
	}
	
	/**
	 * toString to display the Questions object information
	 */
	
	public String toString(){
		
		return (getQuestion() + " The Answer is: " + getAnswer() + " The Point Value is:  " + getPointValue() + " The question ID is: " + getQuestionID() + ".");
		
	}
	
	/**
	 * Opens the RAF
	 */
	
	public void open(){
		  
		try{
			  
			if (file != null) file.close();
				randomFile = new RandomAccessFile("TriviaQuestions", "rw");
		  
		}catch(IOException e){
			  
			e.getMessage();
			System.out.println("Failed to open Random Access File.");
			  
		}
	}
	
	/**
	 * Read the text file and stores the information into the RAF.  The method also makes questions 50 characters
	 * in length and answers 20 in length.
	 * @throws IOException
	 */
	
	public void readFile() 
			throws IOException
	{
		
		file = new BufferedReader(new FileReader("TriviaQuestions.txt"));
		
		line = file.readLine();
		
		while(line != null){
		
			try{
				StringTokenizer inputToken = new StringTokenizer(line, delimeter);
				numOfTokens = inputToken.countTokens();
			
				for(int i = 0; i < numOfTokens; i++){
				
					a[i] = inputToken.nextToken();
				
				}
			
				randomFile.seek(numOfElements * RANDFILE_SIZE);
			
				String newQuestion = a[0];
				String newAnswer = a[1];
			
				for(int j = 0; j < (QUESTION_SIZE - newQuestion.length()); j++){
				
					newQuestion = newQuestion + " ";
				
				}
			
				a[0] = newQuestion;
			
				for(int x = 0; x < (ANSWER_SIZE - newAnswer.length()); x++){
				
					newAnswer = newAnswer + " ";
				
				}
			
				a[1] = newAnswer;
			
				randomFile.writeUTF(a[0]);
				randomFile.writeUTF(a[1]);
				randomFile.writeInt(Integer.parseInt(a[2]));
				randomFile.writeInt(genQuestionID());
			
				line = file.readLine();
				numOfElements++;
			
			}catch(IOException e){
			
				e.getMessage();
				System.out.println("Error reading Random Access File.");
			}
		
		}
		
		System.out.println("File read successfully.");
	
	}
	
	/**
	 * Writes the information from the RAF into question objects
	 * @param number Input number to jump to the correct place in the RAF
	 * @return Returns the Questions object
	 * @throws IOException
	 */
	
	public Questions writeInfo(int number)
			throws IOException
	{
	
		randomFile.seek(number * RANDFILE_SIZE);
		
		
		String newQuestion = randomFile.readUTF();
		String newAnswer = randomFile.readUTF();
		int newPointValue = randomFile.readInt();
		int newQuestionID = randomFile.readInt();
		
		counter = ((int) (randomFile.length() / RANDFILE_SIZE) + 1);
		
		return new Questions(newQuestion, newAnswer, newPointValue, newQuestionID);
		
	}
	
	/**
	 * Adds a question, answer, and point value to the RAF
	 * @param newQuestion Input question
	 * @param newAnswer Input answer
	 * @param newPointValue Input point value
	 */
	
	public void addQuestion(String newQuestion, String newAnswer, int newPointValue) {
		
		try{
			randomFile.seek((size() + 1 ) * RANDFILE_SIZE);
		
			for(int j = 0; j < (QUESTION_SIZE - newQuestion.length()); j++){
			
				newQuestion = newQuestion + " ";
			
			}
		
			for(int x = 0; x < (ANSWER_SIZE - newAnswer.length()); x++){
			
				newAnswer = newAnswer + " ";
			
			}
		
			randomFile.writeUTF(newQuestion);
			randomFile.writeUTF(newAnswer);
			randomFile.writeInt(newPointValue);
			randomFile.writeInt(genQuestionID());
			numOfElements++;
			
		}catch(IOException e){
			
			e.getMessage();
			System.out.println("Failed to write question to the Random Access File.");
		}
	}
	
	/**
	 * Deletes a question based on the question ID that is input
	 * @param number Input question ID
	 */
	
	public void deleteQuestion(int number){
		try{
			if(number <= size()) {
				
				for(int i = number; i <= size(); i++){
			
					randomFile.seek((i) * RANDFILE_SIZE);
				
					String newQuestion = randomFile.readUTF();
					String newAnswer = randomFile.readUTF();
					int newPoint = randomFile.readInt();
					int newID = randomFile.readInt();
				
					randomFile.seek((i - 1) * RANDFILE_SIZE);
				
					randomFile.writeUTF(newQuestion);
					randomFile.writeUTF(newAnswer);
					randomFile.writeInt(newPoint);
					randomFile.writeInt(newID);
			
				}
			
				randomFile.setLength(randomFile.length() - RANDFILE_SIZE);
		
			}else if(number == (size() + 1)){
			
				randomFile.setLength(randomFile.length() - RANDFILE_SIZE);
			
			}
		
			counter--;
			numOfElements--;
		
		}catch(IOException e){
			
			e.getMessage();
			System.out.println("Failed to delete question number " + number);
			
		}
	}
	
	/**
	 * Returns the size (how many questions there are) of the RAF
	 * @return Returns the size
	 * @throws IOException
	 */
	
	public int size() 
			throws IOException
	{
	
		return (int) (randomFile.length() / RANDFILE_SIZE);
		
	}
	
	/**
	 * Closes the RAF
	 */
	
	public void close(){
		
		try{
			if(randomFile != null){
			
				randomFile.close();
		
			}
			else
				randomFile = null;
		}catch(IOException e){
			
			e.getMessage();
			System.out.println("Failed to close the Random Access File.");
		}
	}
			
	/**
	 * Finds a question based on the question ID
	 * @param number Input questino ID user is trying to find
	 * @return returns the locations of the question in the RAF
	 * @throws IOException
	 */
	public int find(int number)
			throws IOException
	{
		
		randomFile.seek(0);
		int c = 0;
		
		for(int i = 0; i <= size(); i++){
			
			randomFile.seek(i * RANDFILE_SIZE);
			randomFile.readUTF();
			randomFile.readUTF();
			randomFile.readInt();
			if(randomFile.readInt() == number){
				
				c = i;
				break;
			}
			
		}
		
		return c;
	}
	
}
