/**
 * This class reads in a file of information for the food, auto, and building classes.  It creates the appropriate object and stores
 * it into an ArrayList of type Uses CarbonFootprint.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 3022 Project 5
 * File Name: CarbonDate.java
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CarbonData {
	
	Auto auto1;
	Building bldg1;
	Food food1;
	ArrayList<CarbonFootprint> footprint = new ArrayList<>();
	
	private BufferedReader file;
	private String line;
	private String delimeter;
	private int numOfTokens;
	private StringTokenizer inputToken;
	private String[] a;
	private String category;
	

	
	
	public CarbonData(){
		
		line = "";
		delimeter = ",";
		numOfTokens = 0;
		category = "";
		
	}
	
	
	
	public void readFile() 
		throws IOException
	{
		
		file = new BufferedReader(new FileReader("CarbonFootprintData.txt"));
		line = file.readLine();
		
		while(line != null){
			
			inputToken = new StringTokenizer(line, delimeter);
			numOfTokens = inputToken.countTokens();
			category = inputToken.nextToken();
			
			if(category.equalsIgnoreCase("building")){
				
				bldg1 = new Building();
				a = new String[4];
				
				for(int i = 0; i < (numOfTokens - 1); i++){
					
					a[i] = inputToken.nextToken();
					
				}
				
				bldg1.setName(a[0]);
				bldg1.setAverageBill(Double.parseDouble(a[1]));
				bldg1.setMonths(Integer.parseInt(a[2]));
				bldg1.setElectricEmissionsFactor(Double.parseDouble(a[3]));
				footprint.add(bldg1);

			}else if(category.equalsIgnoreCase("vehicle")){
				
				auto1 = new Auto();
				a = new String [5];
				
				for(int i = 0; i < (numOfTokens - 1); i++){
					
					a[i] = inputToken.nextToken();
					
				}
				
				auto1.setYear(Integer.parseInt(a[0]));
				auto1.setMake(a[1]);
				auto1.setModel(a[2]);
				auto1.setMilesDrivenPerWeek(Integer.parseInt(a[3]));
				auto1.setFuelEfficiency(Integer.parseInt(a[4]));
				footprint.add(auto1);
			
			}else if(category.equalsIgnoreCase("food")){
				
				food1 = new Food();
				a = new String [4];
				
				for(int i = 0; i < (numOfTokens - 1); i++){
					
					a[i] = inputToken.nextToken();
					
				}
				
				food1.setFoodCategory(a[0]);
				food1.setFoodName(a[1]);
				food1.setCategoryCostPerMonth(Integer.parseInt(a[2]));
				food1.setMonths(Integer.parseInt(a[3]));
				//food1.setEmissionsFactor();
				footprint.add(food1);
				
			}else{
				
				System.out.println("ERROR:  Invalid category for Carbon Footprint Calculator.");
				
			}
			
			line = file.readLine();
			
		}
		
	}
	
	public void printFootprint(){
		
		for(CarbonFootprint list : footprint){
			
			System.out.println(list);
		}
	}


	
	
}
