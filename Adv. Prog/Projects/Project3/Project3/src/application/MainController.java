/**
 * This class handles the Gui Actions.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project3
 * File Name:  MainController.java
 */

package application;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class MainController implements Initializable
{

	private Stage primaryStage;
	private WordDictionary dictionarySet;
	private SuggestedWords suggestedWords;
	private InputFile file;
	private File newFile;
	
	
	@FXML 
	private TextArea textArea;
	
	/**
	 * Constructor that initializes the variables
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		dictionarySet = new WordDictionary();
		dictionarySet.writeToSet();
		file = new InputFile();
		suggestedWords = new SuggestedWords(dictionarySet);
	}
	
	/**
	 * Opens a window that allows the user to open a file to be spell checked.
	 * @param event input action event
	 */
	@FXML
	public void openFile(ActionEvent event)
	{
		StringBuilder fileString = new StringBuilder();
		String text;
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");
		setFile(fileChooser.showOpenDialog(primaryStage));
		if(newFile != null)
		{
			setInputFile(newFile);
			while((text = file.readLine(true)) != null)
			{
				textArea.appendText(text + "\n");
			}
			file.closeFileReader();
		}
		else
		{
			System.out.println("");
		}
	}
	
	/**
	 * Saves the contents from the textArea to the file.
	 */
	public void saveFile()
	{
		InputFile file = getInputFile();
		File fname = getFile();
		file.writeToFile(fname, textArea);
		file.closeFileWriter();
	}
	
	/**
	 * Performs a spell check on the text area contents
	 */
	public void spellCheck()
	{
		InputFile file = getInputFile();
		String[] words = textArea.getText().split("\\s+");

		for(String word : words)
		{
			if(word.contains("."))
			{
				word = word.replace(".", "");
			}
			else if(word.contains(","))
			{
				word = word.replace(",", "");
			}
			
			if(!dictionarySet.getSet().contains(word.toUpperCase()))
			{
				suggestedWords.checkForSuggestions(word);
				System.out.println(word + " is misspelled.  Suggested words:");
				System.out.println("---------------------------------------------");
				
				if(suggestedWords.getSuggestedWords().size() > 0)
				{
					for(String suggestedWord : suggestedWords.getSuggestedWords())
					{
						System.out.println(suggestedWord);
					}
					System.out.print("\n");
					suggestedWords.clearSuggestedWords();
				}
				else
				{
					System.out.println("No suggestions could be found\n");
				}
			}
		}
	}
	
	public InputFile getInputFile()
	{
		return file;
	}
	
	public void setInputFile(File file)
	{
		this.file = new InputFile(file);
	}
	
	public File getFile()
	{
		return newFile;
	}
	
	public void setFile(File file)
	{
		newFile = file;
	}
	
	/**
	 * Closes the GUI.
	 * @param event
	 */
	public void closeWindow(ActionEvent event)
	{
		Platform.exit();
		System.exit(0);
	}
}
