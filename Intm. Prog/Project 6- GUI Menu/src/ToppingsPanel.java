/**
 * This class creates the panel and selection buttons for the different topping types.
 * 
 * 
 * @author Wes's PC
 * @version 1.0
 * 
 * COP 3022 Project 6
 * File Name:  ToppingsPanel.java
 *
 */


import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;


public class ToppingsPanel extends JPanel {
	
	Toppings toppings = new Toppings();
	
	JCheckBox cheeseButton;
	JCheckBox pepperoniButton;
	JCheckBox mushroomsButton;
	JCheckBox greenPeppersButton;
	JCheckBox sausageButton;
	
	/**
	 * Constructor that builds the toppings selection panel
	 */
	
	public ToppingsPanel() {
		
		super();
		setLayout(new GridLayout(5,1));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Toppings"));
		
		cheeseButton = new JCheckBox("Cheese");
		add(cheeseButton);
		
		pepperoniButton = new JCheckBox("Pepperoni");
		add(pepperoniButton);
		
		mushroomsButton = new JCheckBox("Mushrooms");
		add(mushroomsButton);
		
		greenPeppersButton = new JCheckBox("Green Peppers");
		add(greenPeppersButton);
		
		sausageButton = new JCheckBox("Sausage");
		add(sausageButton);

	}
	
	/**
	 * Returns the total for all of the selected toppings
	 * @return
	 */
	
	public double getToppingsSelection(){
		
		if(cheeseButton.isSelected()){
			
			toppings.setToppingsTotal(toppings.getCheese());
		}
		
		if(pepperoniButton.isSelected()){
			
			toppings.setToppingsTotal(toppings.getPepperoni());
		}
		
		if(mushroomsButton.isSelected()){
			
			toppings.setToppingsTotal(toppings.getMushrooms());
		}
		
		if(greenPeppersButton.isSelected()){
			
			toppings.setToppingsTotal(toppings.getGreenPeppers());
		}
	
		if(sausageButton.isSelected()){
			
			toppings.setToppingsTotal(toppings.getSausage());
		}
	
		return toppings.getToppingsTotal();
	}

}
