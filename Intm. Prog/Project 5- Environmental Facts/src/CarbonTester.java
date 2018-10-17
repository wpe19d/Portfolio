import java.io.IOException;

public class CarbonTester {
	
	public static void main(String [] args) {
		
		CarbonData run1 = new CarbonData();
		
		try{
			
		run1.readFile();
		
		}catch(IOException e){
			
			e.getMessage();
		}
		run1.printFootprint();
		
	}

}
