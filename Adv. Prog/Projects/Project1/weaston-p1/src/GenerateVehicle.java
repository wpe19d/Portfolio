/**
 * This class generates a random vehicle from the vehicle class.  
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project 1
 * File Name:  GenerateVehicle.java
 */
import java.util.Random;

public class GenerateVehicle 
{
	private int randNum;
	private double randDouble;
	private Random rand;
	private String make;
	private String model;
	private double weight;
	private double engineSize;
	
	/**
	 * Constructor to initialize the variables.
	 */
	public GenerateVehicle()
	{
		randNum = 0;
		randDouble = 0;
		rand = new Random();
		make = "";
		model = "";
		weight = 0;
		engineSize = 0;
	}
	
	
	/**
	 * Generates a random make.
	 * @return Returns a random make.
	 */
	public String genMake()
	{
		randNum = rand.nextInt(5) + 1;

		switch(randNum)
		{
			case 1: 
				make = "Chevy";
				break;
			case 2:
				make = "Ford";
				break;
			case 3: 
				make = "Toyota";
				break;
			case 4:
				make = "Nissan";
				break;
			case 5:
				make = "Hyundai";
				break;
			default:
				System.out.println("\nInvalid Make\n");
				break;	
		}
		
		return make;
	}
	
	/**
	 * Generates a random models
	 * @return Returns a model.
	 */
	public String genModel()
	{
		randNum = rand.nextInt(6) + 1;
		switch(randNum)
		{
			case 1: 
				model = "compact";
				break;
			case 2:
				model = "intermediate";
				break;
			case 3: 
				model = "fullSized";
				break;
			case 4:
				model = "van";
				break;
			case 5:
				model = "suv";
				break;
			case 6:
				model = "pickup";
				break;
			default:
				System.out.println("\nInvalid Model\n");
				break;	
		}
		
		return model;
	}
	
	/**
	 * Generates a random weight based off of what the model is
	 * @return Returns the weight
	 */
	public double genWeight()
	{
		double weight = 0;
		double min = 0;
		double max = 0;
		
		if( model.compareTo("compact") == 0)
		{
			min = 1500;
			max = 2000;
		}
		else if(model.compareTo("intermediate") == 0)
		{
			min = 2000;
			max = 2500;
		}
 		else if(model.compareTo("fullSized") == 0 ||  model.compareTo("van") == 0 ||  model.compareTo("pickup") == 0 || model.compareTo("suv") == 0)
		{
			min = 2500;
			max = 4000;
		}
		else
		{
			System.out.println("Error creating weight for vehicle " + model);
		}
		
		randDouble = (rand.nextDouble() * (max - min));
		weight = randDouble + min;
		return weight;
	}
	
	/**
	 * Generates a random engine size in Liters
	 * @return Returns the engine size
	 */
	public double genEngineSize()
	{
		randDouble = rand.nextDouble() * (6 - 1);
		engineSize = randDouble + 1;
		return engineSize;
	}
	
	/**
	 * Checks to see if the car is an import or not based off of the make
	 * @return Returns true or false.
	 */
	public boolean genIsImport() 
	{
		if(make.compareTo("Toyota") == 0 || make.compareTo("Nissan") == 0 || make.compareTo("Hyundai") == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
