
public class Car {
	
	private String carRenter;
	private int carNum;
	private String carName;
	private String carType;
	private int carDoor;
	private double rate;
	private int days;
	
	public Car(){
		
		setCarRenter("");
		setCarNum(0);
		setCarName("");
		setCarType("");
		setCarDoor(0);
		setRate(0);
		setDays(0);
		
	}
	public Car(String newRenter, int newNumber, String newCarName, String newType, int newDoor, double newRate, int newDays){
		
		setCarRenter(newRenter);
		setCarNum(newNumber);
		setCarName(newCarName);
		setCarType(newType);
		setCarDoor(newDoor);
		setRate(newRate);
		setDays(newDays);
	}

	public String getCarRenter() {
		
		return carRenter;
		
	}


	public void setCarRenter(String carRenter) {
		
		this.carRenter = carRenter;
		
	}

	public int getCarNum() {
		return carNum;
	}

	public void setCarNum(int carNum) {
		
		this.carNum = carNum;
		
	}

	public String getCarName() {
		
		return carName;
		
	}

	public void setCarName(String carName) {
		
		this.carName = carName;
		
	}

	public String getCarType() {
		
		return carType;
		
	}

	public void setCarType(String carType) {
		
		this.carType = carType;
		
	}

	public int getCarDoor() {
		
		return carDoor;
		
	}

	public void setCarDoor(int carDoor) {
	
		this.carDoor = carDoor;
		
	}

	public double getRate() {
		
		return rate;
		
	}

	public void setRate(double rate) {
		
		this.rate = rate;
		
	}

	public int getDays() {
		
		return days;
		
	}

	public void setDays(int days) {
		
		this.days = days;
		
	}
	
	public String toString() {
		return "Car Renter: " + carRenter + "\nCar Number: " + carNum + "\nCar Name: " + carName + "\n Car Type: " + carType
				+ "\nCar Door: " + carDoor + "\nRate: " + rate + "\nDays: " + days;
		
	}

}
