/**
 * This Class starts the gui and runs the program.  The entire project folder must be loaded into Eclipse IDE.  See user manual for help.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project4
 * File Name:  Main.java
 */
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application
{
	/**
	 * Starts the GUI.  It also loads the stage into the controller.
	 */
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("HorseRaceGUI.fxml"));
			Parent root = loader.load();
			MainController controller = (MainController)loader.getController();
			//controller.setStageAndSetupListeners(primaryStage); 
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		
		launch(args);
		
		System.exit(0);
		
	}

}
