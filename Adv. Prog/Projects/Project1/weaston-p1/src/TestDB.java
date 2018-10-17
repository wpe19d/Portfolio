/**
 * This is the main class that runs the application.  The overall application will read in a class, analyze its
 * fields and data, and build a table from that field data.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project 1
 * File Name:  TestDB.java
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
   Tests a database installation by creating and querying
   a sample table. Call this program as
   java -classpath driver_class_path;. TestDB database.properties
*/
public class TestDB 
{
	
   public static void main(String[] args) throws Exception
   {   
	   Database db = new Database("Vehicle");
	   ResultSet result;
	   db.openDB();  
	   db.dropTable();

	   try
	   {
		   db.createTable();
		   db.buildTable();
		   db.insert();
		   System.out.println("After inserts\n");
		   db.getResults();
	   }
	   finally
	   {
		   db.dropTable();
		   db.closeDB();
		   System.out.println("\nDropped Table " + db.getTableName() + ", closed connection and ending program");  
	   }
   }
}
