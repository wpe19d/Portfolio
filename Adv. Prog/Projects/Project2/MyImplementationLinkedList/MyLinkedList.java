/**
 * This class builds the Nodes and Linked Lists.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project2_HomeMadeLL
 * File Name:  MyLinkedList.java
 */

import java.util.NoSuchElementException;

public class MyLinkedList
{  
	private int size;
	private Node head;
	
	/** 
	Constructs an empty linked list.
	*/
	public MyLinkedList()
	{  
		head = null;
		size = 0;
	}
	   
	/**
	Returns the head element in the linked list.
	@return the head element
	*/
	public String getHead()
	{  
		if (head == null) 
			throw new NoSuchElementException();
		return head.data;
	}
	
	/**
	Removes the head element in the linked list.
	@return the removed element
	*/
	public String pop()
	{  
		if (head == null) 
			throw new NoSuchElementException();
		String element = head.data;
		head = head.next;
		size--;
		return element;
	}
	
	/**
	Adds an element to the front of the linked list.
	@param input element to add
	 */
	public void push(String element)
	{  
		if(head == null)
		{
			Node newNode = new Node(element);
			head = newNode;
		}
		else
		{
			Node temp = new Node(element);
			Node currentNode = head;
			head = temp;
			temp.next = currentNode;		
		}
		size++;
	}
	
	/**
	 * Gets the an element from the Linked List using an index
	 * @param index input index being searched for
	 * @return returns the data from the found location
	 */
	public String get(int index)
	{
		if(index < 0)
		{
			return null;
		}
		
		Node currentNode = null;
		
		if(head != null)
		{
			currentNode = head;
		}
		
		for(int i = 0; i < index; i++)
		{
			if(currentNode.getNext() == null)
			{
				return null;
			}
			
			currentNode = currentNode.getNext();
		}
		
		return currentNode.data;
	}
	
	/**
	 * Sets the data at a particular location inside the Linked List
	 * @param index index to be set
	 * @param element data to be input
	 */
	public void set(int index, String element)
	{
		if(index < 0)
		{
			return;
		}
		
		Node currentNode = null;
		
		if(head != null)
		{
			currentNode = head;
		}
		
		for(int i = 0; i < index; i++)
		{
			if(currentNode.getNext() == null)
			{
				return;
			}
			
			currentNode = currentNode.getNext();
		}
		
		currentNode.data = element;
	}
	
	/**
	 * Adds an element to the end of the Linked List
	 * @param element element to be added
	 */
	public void addLast(String element)
	{
		if(head == null) 
		{
			head = new Node(element);
		}
		
		Node temp = new Node(element);
		Node currentNode = head;
		
		if(currentNode != null)
		{
			while(currentNode.getNext() != null)
			{
				currentNode = currentNode.getNext();
			}
			
			currentNode.setNext(temp);
		}
		size++;
	}
	
	/**
	 * returns the size of the Linked List
	 * @return
	 */
	public int size()
	{
		return size;
	}
	   
	//Node Class
	private class Node
	{  
		public String data;
		public Node next;
		
		/**
		 * Constructor to initialize the node
		 * @param data input data for the node
		 */
		public Node(String data)
		{
			this.data = data;
			next = null;
		}
		
		/**
		 * Constructor to initialize the node
		 * @param data input data for the node
		 * @param nextNode the node that this node will point to
		 */
		public Node(String data, Node nextNode)
		{
			this.data = data;
			next = nextNode;
		}
		
		/**
		 * @return returns the next node
		 */
		public Node getNext() 
		{
			return next;
		}
		
		/**
		 * Sets the next node
		 * @param nextNode input next node 
		 */
		public void setNext(Node nextNode)
		{
			next = nextNode;
		}
		
	}
}
