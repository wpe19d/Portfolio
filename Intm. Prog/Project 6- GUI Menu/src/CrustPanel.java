/**
 * This class creates the panel and selection buttons for the different crust types.
 * 
 * 
 * @author Wes's PC
 * @version 1.0
 * 
 * COP 3022 Project 6
 * File Name:  CrustPanel.java
 *
 */


import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class CrustPanel extends JPanel {
	

	CrustType crustType = new CrustType();
	
	ButtonGroup crustButtons;
	JRadioButton pan;
	JRadioButton thin;
	
	/**
	 * Constructor that builds the panel
	 */
	
	public CrustPanel(){
		
		super();
		setLayout(new GridLayout(3, 1));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Crust Type"));
		
		crustButtons = new ButtonGroup();
		
		pan = new JRadioButton("Pan Crust");
		add(pan);
		crustButtons.add(pan);
		
		thin = new JRadioButton("Thin Crust");
		add(thin);
		crustButtons.add(thin);
		
		
	}
	
	/**
	 * Returns the crust price selection
	 * @return
	 */
	public double getCrustSeletion(){
		
		if(pan.isSelected()){
			
			crustType.setCrustSelection(crustType.getPan());
			
			return crustType.getCrustSelection();
		}
		else if(thin.isSelected()){
			
			crustType.setCrustSelection(crustType.getThin());
			
			return crustType.getCrustSelection();
					
		}
		else
			
			return(0);
	}
	
}
