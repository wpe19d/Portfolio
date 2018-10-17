
public class RentalCarTester {

	
	public static void main(String [] args){
		
		RentalCarCompany rent1 = new RentalCarCompany("Cheap");
		
		rent1.addReservation("Wes", 123, "Toyota", "Tacoma", 2, 100, 5);
		
		rent1.findReservaton(123);
		
	}
}
