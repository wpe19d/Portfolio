/**
 * This is the main class that runs the application.  The overall application will read in a file of two numbers and an operator.  It will then
 * calculate the sum or difference between those two numbers depending on the operator.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project2_HomeMadeLL
 * File Name:  MyLinkedList.java
 */

import javax.script.ScriptException;

public class Driver 
{
	public static void main(String[] args)
	{
		InputFile file;
		String fileName;
		String fileString = null;
		ArithmeticNode node = new ArithmeticNode();

		fileName = "addsAndSubtracts.txt";
		file = new InputFile(fileName);
		
		//ReadFile
		while((fileString = file.readFile()) != null)
		{
			node = new ArithmeticNode(node.spitFromFile(fileString));
			node.addToList();
			
			if(node.getOperator().equals("+"))
			{
				node.add();
			}
			if(node.getOperator().equals("-"))
			{
				try {
					
					node.subtract();
				} 
				catch (ScriptException e) 
				{
					e.printStackTrace();
				}
			}
			
			node.pullFromList();
		}
		
		file.closeFile();
	}
}
