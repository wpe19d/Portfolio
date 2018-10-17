import java.util.ArrayList;


public class RentalCarCompany {
	
	private static final int NUM_CARS = 20;
	private static final int NOT_FOUND = -1;
	
	ArrayList<Car> theCars;
	
	private String name;
	private int totalDays;
	private double totalRate;
	private int doorCount2;
	private int doorCount4;
	private int rentCnt;
	
	public RentalCarCompany(){
		
		setName("");
		setTotalDays(0);
		setTotalRate(0);
		setDoorCount2(0);
		setDoorCount4(0);
		setRentCnt(0);
		
	}
	
	public RentalCarCompany(String newName) {
		
		setName(newName);
		theCars = new ArrayList<Car>(NUM_CARS);
		
	}

	public String getName() {
		
		return name;
		
	}
	
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public int getTotalDAys() {
		
		return totalDays;
		
	}
	
	public void setTotalDays(int totalDAys) {
		
		this.totalDays = totalDAys;
		
	}
	
	public double getTotalRate() {
		
		return totalRate;
		
	}
	
	public void setTotalRate(double totalRate) {
		
		this.totalRate = totalRate;
		
	}
	
	public int getDoorCount2() {
		
		return doorCount2;
		
	}
	
	public void setDoorCount2(int doorCount2) {
		
		this.doorCount2 = doorCount2;
		
	}
	
	public int getDoorCount4() {
		
		return doorCount4;
		
	}
	
	public void setDoorCount4(int doorCount4) {
		
		this.doorCount4 = doorCount4;
		
	}
	
	public int getRentCnt() {
		
		return rentCnt;
		
	}
	
	public void setRentCnt(int rentCnt) {
		
		this.rentCnt = rentCnt;
		
	}
	
	public void printRentalList(){
		
		for(Car tempCar: theCars){
			
			System.out.println(tempCar.toString());
		}
	}
	
	public void addReservation(String newRenter, int newNumber, String newCarName, String newType, int newDoor, double newRate, int newDays){
		
		Car tempCar = new Car(newRenter, newNumber, newCarName, newType, newDoor, newRate, newDays);
		
		theCars.add(tempCar);
		
	}
	
	public int lookForReservation(int n){
		
		int j = NOT_FOUND;
		
		for(int i = 0; i < theCars.size(); i++){
			
			if(theCars.get(i).getCarNum() == n){
				
				j = i;
				break;
			}
		}
			
		return j;
		
		}
	
	public void findReservaton(int carNumber){
		
		if(lookForReservation(carNumber) > -1){
			
			System.out.println(theCars.get(lookForReservation(carNumber)).toString());
			
		}else
			System.out.println("There is no resevation found for " + carNumber);
		
		
	}

}
