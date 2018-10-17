/**
 * This class contains the different drinks and price for each.
 * 
 * 
 * @author Wes's PC
 * @version 1.0
 * 
 * COP 3022 Project 6
 * File Name:  Drinks.java
 *
 */



public class Drinks {
	
	private double water;
	private double hardLiquor;
	private double beer;
	
	
	/**
	 * Constructor that initializes all of the variables
	 */
	
	public Drinks()
	{
		setWater(0);
		setHardLiquor(3.00);
		setBeer(1.50);
		
	}
	
	/**
	 * returns the price of water
	 * @return
	 */
	
	public double getWater() 
	{
		
		return water;
		
	}

	/**
	 * sets the price of water
	 * @param water  input water price
	 */
	
	public void setWater(double water) 
	{
		
		this.water = water;
		
	}

	/**
	 * returns the price of liquor
	 * @return
	 */
	
	public double getHardLiquor() 
	{
		
		return hardLiquor;
		
	}

	
	/**
	 * Sets the price of liquor
	 * @param hardLiquor  input price of liquor
	 */
	public void setHardLiquor(double hardLiquor) 
	{
		
		this.hardLiquor = hardLiquor;
		
	}

	/**
	 * returns the price of beer
	 * @return
	 */
	
	public double getBeer() 
	{
		
		return beer;
		
	}

	/**
	 * sets the price of beer
	 * @param beer  input price of beer
	 */
	
	public void setBeer(double beer) 
	{
		
		this.beer = beer;
		
	}

}
