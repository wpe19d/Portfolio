/**
 * This class creates the User object that extends the account object.
 * 
 * @author Wesley Easton
 * @version 2.0
 * 
 * COP 3022 Project 2
 * File Name:  Bot.java
 *
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Bot extends Account {
	
	
	private Calendar dateUpdated = new GregorianCalendar();
	private DateFormat dateForm = new SimpleDateFormat("MM/dd/yyyy");
	private Date date = new Date();
	private String botFileName;
	private String category;
	private String createdBy;
	
	/**
	 * Constructor to initialize variables.
	 */
	
	public Bot(){
		
		super("", "");
		setBotFileName("");
		setCategory("");
		setCreatedBy("");
		dateUpdated.setTime(null);
		
	}
	
	/**
	 * Constructor to set variables of the Bot Class.
	 * 
	 * @param newBotFileName  Input name of the bot file
	 * @param newCategory  Input category name
	 * @param newDate  Input date
	 * @param newCreatedBy  Input name of the person who created the bot
	 * @param newClearPassword  Input password
	 * @param newKey  Input password key
	 * 
	 */
	public Bot(String newBotFileName, String newCategory, String newDate, String newCreatedBy, String newClearPassword, String newKey){
		
		super(newClearPassword, newKey);
		setBotFileName(newBotFileName);
		setCategory(newCategory);
		setCreatedBy(newCreatedBy);
		setDateUpdated(newDate);

	}

	/**
	 * Method to return the bot's file name
	 * 
	 * @return botFileName  The bot's file name
	 * 
	 */
	
	public String getBotFileName() {
		
		return botFileName;
		
	}
	
	/**
	 * Method to set the bot file name
	 * 
	 * @param newBotFileName  Input bot file name
	 */

	public void setBotFileName(String newBotFileName) {
		
		botFileName = newBotFileName;
		
	}
	
	/**
	 * Method to return the category of the bot
	 * 
	 * @return category  The category of the bot
	 * 
	 */
	public String getCategory() {
		
		return category;
		
	}
	
	/**
	 * Method to set the bot category
	 * 
	 * @param newCategory  Input category name
	 */

	public void setCategory(String newCategory) {
		
		category = newCategory;
		
	}
	
	/**
	 * Method to return the date the bot file was updated in the Gregorian Calendar format.
	 * 
	 * @return dateUpdated  Returns the date formated in the Gregorian Calendar format.
	 */
	
	public String getDateUpdated(){
		
		return (dateUpdated.get(GregorianCalendar.MONTH) + 1) + "/" + dateUpdated.get(GregorianCalendar.DAY_OF_MONTH) 
		+ "/" + dateUpdated.get(GregorianCalendar.YEAR);
		
	}
	
	/**
	 * Method to set the date the bot file was updated.  
	 * 
	 * @param newDate  Input date of the bot file update
	 */
	public void setDateUpdated(String newDate){
		
	try {
			
			date = dateForm.parse(newDate);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		dateUpdated.setTime(date);
	}
	
	/**
	 * Method to return the creator of the bot
	 * 
	 * @return createdBy  The person who created the bot
	 */

	public String getCreatedBy() {
		
		return createdBy;
		
	}

	/**
	 * Method to set the name of the person who created the bot
	 * 
	 * @param newCreatedBy  Input name of the person who created the bot
	 */
	public void setCreatedBy(String newCreatedBy) {
		
		createdBy = newCreatedBy;
		
	}
	
	/**
	 * Returns the information for the user.
	 * 
	 */
	public String toString(){
		
		String info;
		
		info = String.format("%-5s  %s  %s  %s  %s  ", getBotFileName(), getCategory(), getDateUpdated(), getCategory(), getCreatedBy());
		
		info += super.toString();
		
		return info;

	}
}
