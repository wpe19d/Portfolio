/**
 * This class creates the panel and selection buttons for the different drink types.
 * 
 * 
 * @author Wes's PC
 * @version 1.0
 * 
 * COP 3022 Project 6
 * File Name:  DrinksPanel.java
 *
 */

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class DrinksPanel extends JPanel{

	
	Drinks beverages = new Drinks();
	
	ButtonGroup drinkButtons;
	JRadioButton waterButton;
	JRadioButton hardLiquorButton;
	JRadioButton beerButton;
	
	/**
	 * Constructor that builds the drink selection panel
	 */
	
	public DrinksPanel(){
		
		super();
		setLayout(new GridLayout(4, 1));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Drinks"));
		
		drinkButtons = new ButtonGroup();
		
		waterButton = new JRadioButton("Water");
		add(waterButton);
		drinkButtons.add(waterButton);
		
		hardLiquorButton = new JRadioButton("Hard Liquor");
		add(hardLiquorButton);
		drinkButtons.add(hardLiquorButton);
		
		beerButton = new JRadioButton("Beer");
		add(beerButton);
		drinkButtons.add(beerButton);
		
		//add(drinkButtons);
		
		
	}
	
	/**
	 * Returns the drink total
	 * @return
	 */
	
	public double getDrinkSelection(){
		
		if(waterButton.isSelected()){
			
			return beverages.getWater();
		}
		else if(hardLiquorButton.isSelected()){
			
			return beverages.getHardLiquor();
		}
		else if(beerButton.isSelected()){
			
			return beverages.getBeer();
		}
		else return 0;
	}
}
