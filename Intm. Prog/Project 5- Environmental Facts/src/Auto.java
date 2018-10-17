/**
 * This class contains the information for the Automobile carbon footprint.  It uses the following formula to calculate
 * the footprint:
 * 
 * vehicleEmissionsTotal = ((milesDrivenPerWeek * WEEKS_IN_YEAR) / fuelEfficiency) * CO2_PER_GALLON * OTHER_GAS;
 * 
 * Uses CarbonFootprint Interface
 * 
 * @author  Wesley Easton
 * @version 1.0
 * 
 * COP 3022 Project 5
 * File Name:  Auto.java
 *
 */

public class Auto implements CarbonFootprint {

	private static final int WEEKS_IN_YEAR = 52;
	private static final double CO2_PER_GALLON = 19.4;
	private static final double OTHER_GAS = 100/95;
	private int milesDrivenPerWeek;
	private int fuelEfficiency;
	private int year;
	private String make;
	private String model;
	private double vehicleEmissionsTotal;
	
	
	/**
	 * Constructor that initializes the variables.
	 */
	
	public Auto() {
		
		setMilesDrivenPerWeek(0);
		setFuelEfficiency(0);
		setYear(0);
		setMake("");
		setModel("");
		setVehicleEmissionsTotal(0);
		
	}

	/**
	 * Constructor that takes in values and sets the variables to the input values.
	 * 
	 * @param newMilesDrivenPerWeek miles driver per week
	 * @param newFuelEfficiency fuel efficiency of vehicle
	 * @param newYear year of vehicle
	 * @param newMake make of vehicle
	 * @param newModel model of vehicle
	 */
	
	public Auto(int newMilesDrivenPerWeek, int newFuelEfficiency, int newYear, String newMake, String newModel) {
		
		setMilesDrivenPerWeek(newMilesDrivenPerWeek);
		setFuelEfficiency(newFuelEfficiency);
		setYear(newYear);
		setMake(newMake);
		setModel(newModel);
	}

	/**
	 * method from interface to generate the total carbon footprint of the vehicle
	 */
	
	public double getCarbonFootprint() {
		
		vehicleEmissionsTotal = ((milesDrivenPerWeek * WEEKS_IN_YEAR) / fuelEfficiency) * CO2_PER_GALLON * OTHER_GAS;
		
		return vehicleEmissionsTotal;
	}

	/**
	 * Returns miles driven per week
	 * @return
	 */
	
	public int getMilesDrivenPerWeek() {
		
		return milesDrivenPerWeek;
		
	}

	/**
	 * Sets the number of miles driven per week
	 * 
	 * @param milesDrivenPerWeek input miles driven per week
	 */
	
	public void setMilesDrivenPerWeek(int milesDrivenPerWeek) {
		
		this.milesDrivenPerWeek = milesDrivenPerWeek;
		
	}

	/**
	 * Returns the fuel efficiency
	 * @return
	 */
	
	public int getFuelEfficiency() {
		
		return fuelEfficiency;
		
	}

	/**
	 * Sets the fuel efficiency
	 * 
	 * @param fuelEfficiency input fuel efficiency
	 */
	
	public void setFuelEfficiency(int fuelEfficiency) {
		
		this.fuelEfficiency = fuelEfficiency;
		
	}

	/**
	 * returns the year of the vehicle
	 * 
	 * @return
	 */
	
	public int getYear() {
		
		return year;
		
	}

	/**
	 * sets the year of the vehicle
	 * 
	 * @param year input year of the vehicle
	 */
	
	public void setYear(int year) {
		
		this.year = year;
		
	}
	
	/**
	 * returns the make of the vehicle
	 * 
	 * @return
	 */
	
	public String getMake() {
		
		return make;
		
	}

	/**
	 * sets the make of the vehicle
	 * 
	 * @param make input make of the vehicle
	 */
	
	public void setMake(String make) {
		
		this.make = make;
		
	}

	/**
	 * Returns the vehicle model
	 * 
	 * @return
	 */
	
	public String getModel() {
		
		return model;
		
	}

	/**
	 * Sets the vehicle model
	 * 
	 * @param model input vehicle model
	 */
	
	public void setModel(String model) {
		
		this.model = model;
		
	}

	/**
	 * Returns the vehicle emissions total
	 * 
	 * @return
	 */
	
	public double getVehicleEmissionsTotal() {
		
		
		return vehicleEmissionsTotal;
		
	}

	/**
	 * Sets the vehicle emissions total 
	 * 
	 * @param vehicleEmissionsTotal input vehicle emissions total
	 */
	
	public void setVehicleEmissionsTotal(double vehicleEmissionsTotal) {
		
		this.vehicleEmissionsTotal = vehicleEmissionsTotal;
		
	}

	/**
	 * toString to diplay the information
	 */
	public String toString(){
		
		return String.format("The " + getYear() + " " + getMake() + " " + getModel() + " contributes %.2f lbs to the Carbon Footprint.", getCarbonFootprint());
	}
}
