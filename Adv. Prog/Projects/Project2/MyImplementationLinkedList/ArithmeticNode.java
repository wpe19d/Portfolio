/**
 * This class contains the logic for the calculations and pushes/removes the numbers in and out of the LinkedList.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project2_HomeMadeLL
 * File Name:  ArithmeticNode.java
 */

import java.util.ArrayList;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class ArithmeticNode 
{
	private String numOne;
	private String numTwo;
	private String operator;
	private String sign;
	private MyLinkedList firstList;
	private MyLinkedList secondList;
	private MyLinkedList resultList;
	private ScriptEngineManager mgr;
	private ScriptEngine engine;
	private String displayOne;
	private String displayTwo;
	private String displayOperator;
	
	/**
	 * Constructor to initialize the variables
	 */
	public ArithmeticNode() 
	{	
		numOne = null;
		numTwo = null;
		operator = null;
		firstList = null;
		secondList = null;
		resultList = null;
		mgr = new ScriptEngineManager();
		engine = mgr.getEngineByName("JavaScript");
	}

	/**
	 * Constructor to initialize the variables with input parameters.
	 * @param fileString the input file string.
	 */
	public ArithmeticNode(String[] fileString)
	{
		sign = "+";
		check(fileString);	
		firstList = new MyLinkedList();
		secondList = new MyLinkedList();
		resultList = new MyLinkedList();
		mgr = new ScriptEngineManager();
		engine = mgr.getEngineByName("JavaScript");	
	}

	/**
	 * This method will add the numbers in the Linked Lists together
	 * and input them into a resultList
	 */
	public void add()
	{
		int size = 0;
		String temp = "";
		String remainder = "0";
		String result = "";
		
		if(firstList.size() == secondList.size())
		{
			size = firstList.size();
		}
		else
		{
			System.out.println("ERROR:  Linked Lists are of different sizes");
		}
		
		for(int i = 0; i < size; i++)
		{
			temp = firstList.get(i) + " " + getOperator() + " " + secondList.get(i) + " + " + remainder;
			remainder = "0";
			try 
			{
				result = engine.eval(temp).toString();
			} 
			catch (ScriptException e) 
			{
				System.out.println("ERROR: error occurred while adding numbers");
				e.printStackTrace();
			}
			
			if(result.length() > 1 && !result.substring(0, 1).equals("-"))
			{
				remainder = result.substring(0, 1);
				resultList.push(result.substring(1, 2));
				
			}
			else if(result.length() > 1 && result.substring(0, 1).equals("-"))
			{
				sign = "-";
				resultList.push(result.substring(1, 2));
			}
			else
			{
				resultList.push(result);
			}
		}
		resultList.push(sign);
	}
	
	/**
	 * This method will subtract the numbers in the Linked Lists together
	 * and input them into a resultList
	 * @throws ScriptException
	 */
	public void subtract() throws ScriptException
	{
		int size = 0;
		String temp = "";
		String remainder = "0";
		String result = "";
		
		if(firstList.size() == secondList.size())
		{
			size = firstList.size();
		}
		else
		{
			System.out.println("ERROR:  Linked Lists are of different sizes");
		}

		for(int i = 0; i < size; i++)
		{
			if(Double.parseDouble(firstList.get(i)) < Double.parseDouble(secondList.get(i)))
			{
				int index = i + 1;
				boolean done = false;
				while(!done)
				{
					if(Double.parseDouble(firstList.get(index)) != 0)
					{
						firstList.set(index, engine.eval(firstList.get(index) + " - 1").toString()); 
						firstList.set(i, engine.eval(firstList.get(i) + " + 10").toString());
						done = true;
					}
					index++;
					if(index == size)
					{
						firstList.push("9");
						secondList.push("0");
						i++;
						size++;
						firstList.set(i, engine.eval(firstList.get(i) + " + 10").toString());
						done = true;
					}
				}
			}
			
			temp = firstList.get(i) + " " + getOperator() + " " + secondList.get(i) + " + " + remainder;
			remainder = "0";

				result = engine.eval(temp).toString();

			
			if(result.length() > 1 && !result.substring(0, 1).equals("-"))
			{
				remainder = result.substring(0, 1);
				resultList.push(result.substring(1, 2));
			}
			else
			{
				resultList.push(result);
			}
		}
		resultList.push(sign);
	}
	
	/**
	 * This is the logic that determines how to add/subtract the numbers.  It compares both input numbers and 
	 * will assign them to the proper Linked List.  It also determines the proper operator
	 * @param fileString the input file string
	 */
	public void check(String[] fileString)
	{
		displayOne = fileString[0];
		displayTwo = fileString[2];
		displayOperator = fileString[1];
		
		if(Double.parseDouble(fileString[0]) > Double.parseDouble(fileString[2]) && !fileString[0].contains("-") && fileString[2].contains("-") && fileString[1].equals("+"))
		{
			if((Double.parseDouble(fileString[0])) < Double.parseDouble(fileString[2]) * -1)
			{
				sign = "-";
				setOperator("-");
				setNumOne(fileString[2]);
				setNumTwo(fileString[0]);
			}
			else
			{
				setOperator("-");
				setNumOne(fileString[0]);
				setNumTwo(fileString[2]);
			}
		}
		else if(Double.parseDouble(fileString[0]) > Double.parseDouble(fileString[2]) && !fileString[0].contains("-") && fileString[2].contains("-") && fileString[1].equals("-"))
		{
			setOperator("+");
			setNumOne(fileString[0]);
			setNumTwo(fileString[2]);
		}
		else if(Double.parseDouble(fileString[0]) > Double.parseDouble(fileString[2]) && fileString[0].contains("-") && fileString[2].contains("-") && fileString[1].equals("-"))
		{
			sign = "+";
			setOperator("-");
			setNumOne(fileString[2]);
			setNumTwo(fileString[0]);
		}
		else if(Double.parseDouble(fileString[0]) < Double.parseDouble(fileString[2]) && fileString[0].contains("-") && fileString[2].contains("-") && fileString[1].equals("-"))
		{
			sign = "-";
			setOperator(fileString[1]);
			setNumOne(fileString[0]);
			setNumTwo(fileString[2]);
		}
		else if(Double.parseDouble(fileString[0]) < Double.parseDouble(fileString[2]) && fileString[0].contains("-") && fileString[1].equals("-"))
		{
			sign = "-";
			setOperator("+");
			setNumOne(fileString[0]);
			setNumTwo(fileString[2]);
		}
		else if(Double.parseDouble(fileString[0]) < Double.parseDouble(fileString[2]) && fileString[0].contains("-") && !fileString[2].contains("-") && fileString[1].equals("+"))
		{
			if((Double.parseDouble(fileString[0]) * -1) < Double.parseDouble(fileString[2]))
			{
				setOperator("-");
				setNumOne(fileString[2]);
				setNumTwo(fileString[0]);
			}
			else
			{
				sign = "-";
				setOperator("-");
				setNumOne(fileString[0]);
				setNumTwo(fileString[2]);
			}
		}
		else if(Double.parseDouble(fileString[0]) < Double.parseDouble(fileString[2]) && fileString[0].contains("-") && fileString[2].contains("-") && fileString[1].equals("+"))
		{
			sign = "-";
			setOperator("+");
			setNumOne(fileString[0]);
			setNumTwo(fileString[2]);
		}
		else if(Double.parseDouble(fileString[0]) < Double.parseDouble(fileString[2]))
		{
			if(fileString[1].equals("-"))
			{
				sign = "-";
			}
			setOperator(fileString[1]);
			setNumOne(fileString[2]);
			setNumTwo(fileString[0]);
		}
		else
		{
			setOperator(fileString[1]);
			setNumOne(fileString[0]);
			setNumTwo(fileString[2]);
		}
		if(fileString[0].contains("-") && fileString[2].contains("-") && fileString[1].equals("+")) 
		{
			sign = "-";
		}
	}
	
	/**
	 * Adds the array of numbers into a Linked List
	 */
	public void addToList()
	{
		String[] stringArrayOne = splitString(getNumOne());
		String[] stringArrayTwo = splitString(getNumTwo());
		
		for(int i = 0; i < stringArrayOne.length; i++)
		{
			if(stringArrayOne[i].equals("+") || stringArrayOne[i].equals("-"))
			{
				firstList.push(stringArrayOne[i + 1]);
				i++;
			}
			else
			{
				firstList.push(stringArrayOne[i]);
			}
		}
		
		for(int i = 0; i < stringArrayTwo.length; i++)
		{
			if(stringArrayTwo[i].equals("+") || stringArrayTwo[i].equals("-"))
			{
				secondList.push(stringArrayTwo[i + 1]);
				i++;
			}
			else
			{
				secondList.push(stringArrayTwo[i]);
			}
		}
		
		appendZeros();
	}
	
	/**
	 * Pulls the numbers from the Linked List and prints them to the screen.
	 */
	public void pullFromList()
	{
		String result = "";
		String firstNum = "";
		String secondNum = "";
		
		for(int i = 0; i < resultList.size(); i++)
		{
			result += resultList.get(i);
		}

		System.out.println(displayOne + " " + displayOperator + " " + displayTwo + " = " + result + "\n");
	}
	
	/**
	 * Splits the lines from the file into two numbers and an operator
	 * @param line the input line from the file
	 * @return returns a String Array
	 */
	public String[] spitFromFile(String line)
	{
		return line.split(" ");
	}
	
	/**
	 * Splits the numbers into individual numbers.  Ex: 123 would be '1', '2', '3'
	 * @param numberString the input number string
	 * @return returns an array of numbers
	 */
	public String[] splitString(String numberString)
	{
		return numberString.split("");
	}

	/**
	 * Appends zeros to the numbers to make them match in size.
	 */
	public void appendZeros()
	{
		int size = 0;
		if(firstList.size() > secondList.size())
		{
			size = (firstList.size() - secondList.size());
			
			for(int i = 0; i <= size; i++)
			{
				secondList.addLast("0");
			}
			firstList.addLast("0");
		}
		else if(firstList.size() < secondList.size())
		{
			size = (secondList.size() - firstList.size());
			
			for(int i = 0; i <= size; i++)
			{
				firstList.addLast("0");
			}
			secondList.addLast("0");
		}
		else
		{
			firstList.addLast("0");
			secondList.addLast("0");
		}
	}
	
	/**
	 * @return Returns the first number
	 */
	public String getNumOne() 
	{
		return numOne;
	}

	/**
	 * Sets the first numbers
	 * @param numOne input number
	 */
	public void setNumOne(String numOne) 
	{
		this.numOne = numOne;
	}

	/**
	 * @return returns the second number
	 */
	public String getNumTwo() 
	{
		return numTwo;
	}

	/**
	 * Sets the second numbers
	 * @param numTwo input number
	 */
	public void setNumTwo(String numTwo) 
	{
		this.numTwo = numTwo;
	}

	/** 
	 * @return returns the operator
	 */	
	public String getOperator() 
	{
		return operator;
	}

	/**
	 * Sets the operator
	 * @param operator input operator
	 */
	public void setOperator(String operator) 
	{
		this.operator = operator;
	}

	/**
	 * @return returns the first Linked List
	 */
	public MyLinkedList getFirstList()
	{
		return firstList;
	}
	
	/**
	 * Sets the first Linked List
	 * @param firstList input Linked List
	 */
	public void setFirstList(MyLinkedList firstList)
	{
		this.firstList = firstList;
	}
	
	/**
	 * @return returns the second Linked List
	 */
	public MyLinkedList getSecondList() 
	{
		return secondList;
	}

	/**
	 * Sets the second Linked List
	 * @param secondList input Linked List
	 */
	public void setSecondList(MyLinkedList secondList) 
	{
		this.secondList = secondList;
	}

	/**
	 * @return returns the results Linked List
	 */
	public MyLinkedList getResultList() 
	{
		return resultList;
	}

	/**
	 * Sets the results Linked List
	 * @param resultList input Linked List
	 */
	public void setResultList(MyLinkedList resultList) 
	{
		this.resultList = resultList;
	}	
}
