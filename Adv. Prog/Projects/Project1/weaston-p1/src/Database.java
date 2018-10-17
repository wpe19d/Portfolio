/**
 * This class creates a database and uses the reflection class to analyze another class.  It 
 * takes the values and the fields from that class to populate a table of information.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project 1
 * File Name:  Database.java
 */

import java.io.IOException;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Database 
{
	Log log = new Log();
	Reflection reflect;
	GenerateVehicle genVehicle = new GenerateVehicle();
	ArrayList<Vehicle> vehicles = new ArrayList();
	ArrayList<Field> fields;
	Field[] field;
	
	Statement stat;
	ResultSet result;
	Connection conn;
	private String sqlStatement;
	private String tableName;
	private String paramName;
	private String paramType;
	private static int colCount;
	private boolean isBadVar;
	

	/**
	 * Constructor that initializes the variables.
	 */
	public Database()
	{	
		this.sqlStatement = "";
		this.tableName = "";
		stat = null;
		conn = null;
		colCount = 0;
	}
	
	/**
	 * Constructor that initializes the variables.
	 * @param tableName  Input tableName
	 */
	public Database(String tableName)
	{	
		this.sqlStatement = "";
		setTableName(tableName);
		stat = null;
		conn = null;
		colCount = 0;
		
		for(int i = 0; i < 10; i++)
		{
			vehicles.add(new Vehicle(genVehicle));
		}
		
		reflect = new Reflection(vehicles.get(0));
		field = reflect.getFieldType();
		fields = new ArrayList<Field>(Arrays.asList(field));
	}
	
	/**
	 * Opens the connection to the database and sets the statement object
	 * @return Returns the Statement
	 */
	public Statement openDB()
	{
		try 
		{
			SimpleDataSource.init("database.properties");
			conn = SimpleDataSource.getConnection();
			stat = conn.createStatement();	
		} 
		catch (ClassNotFoundException | IOException | SQLException e) 
		{
			System.out.println("Could not open Database");
			e.printStackTrace();
		}
		
		return stat;
	}
	
	/**
	 * Closes the database connection and the log file
	 */
	public void closeDB()
	{
		try 
		{
			conn.close();
			log.closeFile();
		} 
		catch (SQLException e) 
		{
			System.out.println("Error closing Database");
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates the table 
	 */
	public void createTable() 
	{
		setSqlStatement("CREATE TABLE " + getTableName());

	}
	
	/**
	 * Drops the table
	 */
	public void dropTable() 
	{
		setSqlStatement("DROP TABLE " + getTableName());
		log.writeToFile(getSqlStatement());
		
		try 
		{
			stat.execute(getSqlStatement());
		}
		catch (SQLException e) {
			System.out.println("\nInitial drop failed\n");
			log.writeToFile("ERROR: Initial drop failed:  " + getSqlStatement());
		}
	}

	/**
	 * Builds the table from the reflection fields.  The conditionals check
	 * the type of the field and generate the appropriate SQL data type.
	 */
	public void buildTable() 
	{
		
		for(int i = 0; i < fields.size(); i++)
		{
			fields.get(i).setAccessible(true);
			isBadVar = false;
			if(fields.get(i).toString().contains("String"))
			{
				paramName = fields.get(i).getName();
				paramType = "CHAR(20)";
			}
			else if(fields.get(i).toString().contains("double"))
			{
				paramName = fields.get(i).getName();
				paramType = "DECIMAL(7, 2)";
			}
			else if(fields.get(i).toString().contains("boolean"))
			{
				paramName = fields.get(i).getName();
				paramType = "CHAR(10)";
			}
			else
			{
				paramName = fields.get(i).getName();
				paramType = fields.get(i).toString();
				fields.remove(i);
				isBadVar = true;
				i--;
				log.writeToFile("ERROR: " + paramName + " of is of an invalid data type (" + paramType + ")");
			}
			
			if(!isBadVar)
			{
				if(colCount == 0)
				{
					setSqlStatement(getSqlStatement() + " (" + paramName + " " + paramType + ")");
				}
				else
				{
					setSqlStatement("ALTER TABLE " + getTableName() + " ADD " + paramName + " " + paramType);
				}
				
				try 
				{
					stat.execute(getSqlStatement());
					log.writeToFile(getSqlStatement());	
				}  
				catch (SQLException e) 
				{
					System.out.println("Error Adding Column");
					log.writeToFile("ERROR:  " + getSqlStatement());
					e.printStackTrace();
				}
				finally
				{
					colCount++;
				}	
			}
		}
	}
	
	/**
	 * Inserts values into the table by building the SQL statement.
	 */
	public void insert()
	{
		for(Vehicle vehicle : vehicles)
		{
			String value = "";
			String values ="";
			String colNames = "";
			
			for(int i = 0; i < fields.size(); i++)
			{	
				paramName = fields.get(i).getName();
			
				if(i < (fields.size() - 1))
				{
					colNames += paramName + ", ";
				}
				else
				{
					colNames += paramName;
				}
				
				value = reflect.getValue(paramName, vehicle);
				
				if(fields.get(i).toString().contains("String") || fields.get(i).toString().contains("boolean"))
				{
					if(i < (fields.size() - 1))
					{
						values += "'" + value + "', ";
					}
					else
					{
						values += "'" + value + "'";
					}
					
				}
				else if(fields.get(i).toString().contains("double"))
				{
					double numb = Double.parseDouble(value);
					value = String.format("%.2f", numb);
					
					if(i < (fields.size() - 1))
					{
						values += value + ", ";
					}
					else
					{
						values += value;
					}
				}
			}
			
			setSqlStatement("INSERT INTO "+ getTableName() + " (" + colNames + ") VALUES (" + values + ")");
			try 
			{
				stat.execute(getSqlStatement());
				log.writeToFile(getSqlStatement());
			}
			catch (SQLException e) 
			{
				System.out.println("Error Inserting Row");
				log.writeToFile("ERROR:  " + getSqlStatement());
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Prints the data from the table to the screen.
	 * @throws SQLException
	 */
	public void getResults() throws SQLException
	{
		setSqlStatement("SELECT * FROM " + getTableName());
		log.writeToFile(getSqlStatement());
		try 
		{
			result = stat.executeQuery(getSqlStatement());
		} 
		catch (SQLException e) 
		{
			System.out.println("Error getting result set");
			log.writeToFile("ERROR:  " + getSqlStatement());
			e.printStackTrace();
		}
		
		ResultSetMetaData rsm = result.getMetaData();
		
		int cols = rsm.getColumnCount();
		
		while(result.next())
		{
			for(int i = 1; i <= cols; i++)
			{
				System.out.print(result.getString(i)+" ");
			}	
			
			System.out.println("");      
		}
	}
	
	/**
	 * @return Returns the current SQL Statement 
	 */
	public String getSqlStatement() 
	{
		return sqlStatement;
	}

	/**
	 * Sets the SQL Statement
	 * @param sqlStatement Input SQL Statement
	 */
	public void setSqlStatement(String sqlStatement) 
	{
		this.sqlStatement = sqlStatement;
	}

	/**
	 * @return Returns the table name
	 */
	public String getTableName() 
	{
		return tableName;
	}

	/**
	 * Sets the table name
	 * @param tableName Input table name
	 */
	public void setTableName(String tableName) 
	{
		this.tableName = tableName;
	}
}
