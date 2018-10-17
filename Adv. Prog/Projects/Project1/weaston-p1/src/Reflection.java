import java.lang.reflect.*;
import java.util.ArrayList;


public class Reflection<T> 
{
	private Class inputClass;
	private ArrayList<String> stringArgs = new ArrayList();
	
	/**
	 * Constructor to initialize the variables
	 */
	public Reflection()
	{
		inputClass = null;
	}
	
	/**
	 * Constructor to initialize the variables with input parameters.
	 * @param className input class name
	 */
	public Reflection(Object className)
	{
		inputClass = className.getClass();
	}

	/**
	 * @return Returns an array of the class field types
	 */
	public Field[] getFieldType()
	{
		Field[] fields = inputClass.getDeclaredFields();
		
		for(int i = 0; i < fields.length; i++)
		{
			stringArgs.add(fields[i].toString());
		}
		return fields;
	}
	
	/**
	 * returns the values of the input field
	 * @param paramName the name of the field
	 * @param object the name of the input object
	 * @return returns the value of the input object parameter
	 */
	public String getValue(String paramName, Object object)
	{
		String value = "";
		try 
		{
			Field field = inputClass.getDeclaredField(paramName);
			field.setAccessible(true);
			try 
			{
				value = field.get(object).toString();
			} 
			catch (IllegalArgumentException | IllegalAccessException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (NoSuchFieldException | SecurityException e) 
		{
			e.printStackTrace();
		}
		return value;
	}
	

}
