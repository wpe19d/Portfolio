/**
 * This class contains the information for the Building carbon footprint.  It uses the following formula to calculate
 * the footprint:
 * 
 * buildingEmissionsTotal = (averageBill / PRICE_PER_KWH) * electricEmissionsFactor * months;
 * 
 * Uses CarbonFootprint Interface
 * 
 * @author  Wesley Easton
 * @version 1.0
 * 
 * COP 3022 Project 5
 * File Name:  Building.java
 *
 */


public class Building implements CarbonFootprint {
	
	
	private static final double PRICE_PER_KWH = 0.25;
	private String name;
	private double averageBill;
	private int months;
	private double electricEmissionsFactor;
	private double buildingEmissionsTotal;
	
	
	
	
	/**
	 * Constructor to initialize the variables
	 */
	
	public Building() {
		
		setAverageBill(0);
		setMonths(0);
		setElectricEmissionsFactor(0);
		setName("");
		setBuildingEmissionsTotal(0);
	}

	/**
	 * Constructor to set the variables to input values
	 * @param averageBill input average electrical monthly bill
	 * @param months input number of months
	 * @param electricEmissionsFactor input emissions factor for the area
	 * @param newName input name of the building
	 */
	
	public Building(double averageBill, int months, double electricEmissionsFactor, String newName) {
		
		setAverageBill(averageBill);
		setMonths(months);
		setElectricEmissionsFactor(electricEmissionsFactor);
		setName(newName);
	}

	/**
	 * Returns the total carbon footprint of the building
	 */
	
	public double getCarbonFootprint() {
		
		buildingEmissionsTotal = (averageBill / PRICE_PER_KWH) * electricEmissionsFactor * months;
		
		return buildingEmissionsTotal;
	}

	/**
	 * Returns the average monthly electrical bill
	 * @return
	 */
	
	public double getAverageBill() {
		
		return averageBill;
		
	}

	/**
	 * Sets the average monthly electrical bill
	 * 
	 * @param averageBill input average monthly electrical bill
	 */
	public void setAverageBill(double averageBill) {
		
		this.averageBill = averageBill;
		
	}

	/**
	 * Returns the number of months
	 * @return
	 */
	
	public int getMonths() {
		
		return months;
		
	}

	/**
	 * Sets the number of months
	 * 
	 * @param months input number of months
	 */
	
	public void setMonths(int months) {
		
		this.months = months;
		
	}

	/**
	 * Returns the area electrical emissions factor
	 * @return
	 */
	
	public double getElectricEmissionsFactor() {
		
		return electricEmissionsFactor;
		
	}

	/**
	 * Sets the area electrical emissions factor
	 * 
	 * @param electricEmissionsFactor input electrical emissions factor
	 */
	
	public void setElectricEmissionsFactor(double electricEmissionsFactor) {
		
		this.electricEmissionsFactor = electricEmissionsFactor;
		
	}

	/**
	 * Returns the total carbon footprint
	 * 
	 * @return
	 */
	
	public double getBuildingEmissionsTotal() {
		
		return buildingEmissionsTotal;
		
	}

	/**
	 * Sets the total carbon footprint
	 * 
	 * @param buildingEmissionsTotal input carbon footpring total
	 */
	
	public void setBuildingEmissionsTotal(double buildingEmissionsTotal) {
		
		this.buildingEmissionsTotal = buildingEmissionsTotal;
		
	}

	/**
	 * Returns the name of the building
	 * 
	 * @return
	 */
	
	public String getName() {
		
		return name;
		
	}

	/**
	 * Sets the name of the building
	 * @param name input building name
	 */
	
	public void setName(String name) {
		
		this.name = name;
		
	}

	/**
	 * toString to display the information.
	 */
	public String toString(){
		
		return String.format(getName() + " contributes %.2f lbs per year to the Carbon Footprint.", getCarbonFootprint());
	}
}
