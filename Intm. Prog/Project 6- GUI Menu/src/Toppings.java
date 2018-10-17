/**
 * This class contains the different types of toppings and price for each.
 * 
 * 
 * @author Wes's PC
 * @version 1.0
 * 
 * COP 3022 Project 6
 * File Name:  Toppings.java
 *
 */


public class Toppings {
	
	private double cheese;
	private double pepperoni;
	private double mushrooms;
	private double greenPeppers;
	private double sausage;
	private double toppingsTotal;
	
	/**
	 * Constructor to initialize the variables
	 */
	public Toppings()
	{
		setCheese(.50);
		setPepperoni(.50);
		setMushrooms(.50);
		setGreenPeppers(.50);
		setSausage(.50);
		setToppingsTotal(0);
		
	}

	/**
	 * returns the cheese toppings price
	 * @return
	 */
	
	public double getCheese() 
	{
		
		return cheese;
		
	}

	/**
	 * sets the cheese price
	 * @param cheese  the input cheese price
	 */
	
	public void setCheese(double cheese) 
	{
		
		this.cheese = cheese;
		
	}

	/**
	 * returns the pepperoni price
	 * @return
	 */
	
	public double getPepperoni() 
	{
		
		return pepperoni;
		
	}

	/**
	 * sets the pepperoni price
	 * @param pepperoni  input pepperoni price
	 */
	public void setPepperoni(double pepperoni) 
	{
		
		this.pepperoni = pepperoni;
		
	}

	/**
	 * returns the mushrooms price
	 * @return
	 */
	
	public double getMushrooms() 
	{
		
		return mushrooms;
		
	}

	/**
	 * sets the mushrooms price
	 * @param mushrooms  input mushrooms price
	 */
	
	public void setMushrooms(double mushrooms) 
	{
		
		this.mushrooms = mushrooms;
		
	}

	/**
	 * returns the price of greem peppers
	 * @return
	 */
	
	public double getGreenPeppers() 
	{
		
		return greenPeppers;
		
	}

	/**
	 * sets the price of the green peppers
	 * @param greenPeppers  input price of green peppers.
	 */
	
	public void setGreenPeppers(double greenPeppers) 
	{
		
		this.greenPeppers = greenPeppers;
		
	}

	/**
	 * returns the price of the sausage
	 * @return
	 */
	public double getSausage() 
	{
		
		return sausage;
		
	}

	
	/**
	 * sets the price of the sausage
	 * @param sausage  input price of the sausage
	 */
	public void setSausage(double sausage) 
	{
		
		this.sausage = sausage;
		
	}

	/**
	 * returns the total for all the toppings
	 * @return
	 */
	
	public double getToppingsTotal() 
	{
		
		return toppingsTotal;
		
	}

	/**
	 * sets the total for all of the toppings.
	 * @param toppingsTotal  input toppings total
	 */
	public void setToppingsTotal(double toppingsTotal) 
	{
		
		this.toppingsTotal += toppingsTotal;
		
	}

}
