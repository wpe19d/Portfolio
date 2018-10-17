/**
 * This class creates a vehicle.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project 1
 * File Name:  Vehicle.java
 */
import java.util.Random;

public class Vehicle {
	
	private String make;
	private String model;
	private double weight;
	private double engineSize;
	private boolean isImport;
	
	/**
	 * Constructor to initialize the variables
	 */
	public Vehicle() 
	{
		this.make = "";
		this.model = "";
		this.weight = 0;
		this.engineSize = 0;
		this.isImport = false;
	}

	/**
	 * Constructor that generates a new random vehicle
	 * @param genVehicle Generate Vehicle object to randomly generate vehicle values
	 */
	public Vehicle(GenerateVehicle genVehicle) 
	{
		this.make = genVehicle.genMake();
		this.model = genVehicle.genModel();
		this.weight = genVehicle.genWeight();
		this.engineSize = genVehicle.genEngineSize();
		this.isImport = genVehicle.genIsImport();
	}
	
	/**
	 * @return Retunrs the make 
	 */
	public String getMake() 
	{
		return make;
	}
	
	/**
	 * Sets the make
	 * @param make Input make
	 */
	public void setMake(String make)
	{
		this.make = make;
	}
	/**
	 * @return Returns the model
	 */
	public String getModel() 
	{
		return model;
	}

	/**
	 * Sets the model
	 * @param model Input model
	 */
	public void setModel(String model)
	{
		this.model = model;
	}
	/**
	 * @return Returns the weight
	 */
	public double getWeight() 
	{
		return weight;
	}
	
	/**
	 * Sets the weight of the vehicle
	 * @param weight Input weight
	 */
	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	/**
	 * @return Returns the engine size
	 */
	public double getEngineSize() 
	{
		return engineSize;
	}
	
	/**
	 * Sets the engine size in Liters
	 * @param engineSize Input engine size
	 */
	public void setEngineSize(double engineSize)
	{
		this.engineSize = engineSize;
	}

	/**
	 * @return Returns isImport boolean
	 */
	public boolean getIsImport()
	{
		return isImport;
	}
	
	/**
	 * Sets whether the vehicle is an import or not
	 * @param isImport Input boolean to set as import or not.
	 */
	public void setIsImport(boolean isImport) 
	{
		this.isImport = isImport;
	}

}
