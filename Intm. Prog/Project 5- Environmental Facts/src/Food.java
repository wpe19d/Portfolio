/**
 * This class contains the information for the Food carbon footprint.  It uses the following formula to calculate
 * the footprint:
 * 
 * foodEmissionsTotal = (categoryCostPerMonth * emissionsFactor * months) * GRAMS_TO_LBS;
 * 
 * Uses CarbonFootprint Interface
 * 
 * @author  Wesley Easton
 * @version 1.0
 * 
 * COP 3022 Project 5
 * File Name:  Food.java
 *
 */



public class Food implements CarbonFootprint {
	
	private static final double GRAMS_TO_LBS = .0022;
	private String foodCategory;
	private String foodName;
	private double categoryCostPerMonth;
	private double emissionsFactor;
	private int months;
	private double foodEmissionsTotal;
	
	
	public Food() {
		
		setFoodName("");
		setFoodCategory("");
		setCategoryCostPerMonth(0);
		setMonths(0);
		setFoodEmissionsTotal(0);
		emissionsFactor = 0;
		
	}

	
	public Food(String newFoodName, String newFoodCategory, double newCategoryCostPerMonth, int newMonths) {
		
		setFoodName(newFoodName);
		setFoodCategory(newFoodCategory);
		setCategoryCostPerMonth(newCategoryCostPerMonth);
		setMonths(newMonths);
		
	}

	
	public double getCarbonFootprint() {
	
		foodEmissionsTotal = (categoryCostPerMonth * emissionsFactor * months) * GRAMS_TO_LBS;
		
		return foodEmissionsTotal;
	}


	public String getFoodCategory() {
		
		return foodCategory;
		
	}

	public void setFoodCategory(String foodCategory) {
		
		this.foodCategory = foodCategory;
		setEmissionsFactor();
		
	}
	
	public double getCategoryCostPerMonth() {
		
		return categoryCostPerMonth;
		
	}

	public void setCategoryCostPerMonth(double categoryCostPerMonth) {
		
		this.categoryCostPerMonth = categoryCostPerMonth;
		
	}

	public double getEmissionsFactor() {
		
		return emissionsFactor;
		
	}

	public void setEmissionsFactor() {
		
		if(getFoodCategory().equalsIgnoreCase("meat") || getFoodCategory().equalsIgnoreCase("fish") 
				|| getFoodCategory().equalsIgnoreCase("eggs")){
			
			emissionsFactor = 1452;
			
		}else if(getFoodCategory().equalsIgnoreCase("cereal") || getFoodCategory().equalsIgnoreCase("bakery")){
			
			emissionsFactor = 741
					;
		}else if(getFoodCategory().equalsIgnoreCase("dairy")){
			
			emissionsFactor = 1911;
			
		}else if(getFoodCategory().equalsIgnoreCase("fruit") || getFoodCategory().equalsIgnoreCase("vegetable")){
			
			emissionsFactor = 1176;
			
		}else if(getFoodCategory().equalsIgnoreCase("eating out")){
			
			emissionsFactor = 368;
			
		}else
			emissionsFactor = 467;
		
	}

	public int getMonths() {
		
		return months;
		
	}

	public void setMonths(int months) {
		
		this.months = months;
		
	}


	public String getFoodName() {
		
		return foodName;
		
	}


	public void setFoodName(String foodName) {
		
		this.foodName = foodName;
		
	}


	public double getFoodEmissionsTotal() {
		
		return foodEmissionsTotal;
		
	}


	public void setFoodEmissionsTotal(double foodEmissionsTotal) {
		
		this.foodEmissionsTotal = foodEmissionsTotal;
		
	} 
	
	public String toString(){
		
		return String.format("%.2f lbs is contributed to the Carbon Footprint by the food " + getFoodName(), getCarbonFootprint());
	}
}
