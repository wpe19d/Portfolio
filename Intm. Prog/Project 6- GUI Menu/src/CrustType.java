/**
 * This class contains the crust types and price for each.
 * 
 * 
 * @author Wes's PC
 * @version 1.0
 * 
 * COP 3022 Project 6
 * File Name:  CrustType.java
 *
 */


public class CrustType {
	
	private double pan;
	private double thin;
	private double crustSelection;
	
	/**
	 * Constructor that initializes variables.
	 */
	
	public CrustType()
	{
		setPan(8.00);
		setThin(7.00);
		setCrustSelection(0);
		
	}

	/**
	 * Sets the crust selection.
	 * @param selection  The input crust type selection price
	 */
	public CrustType(double selection){
		
		setCrustSelection(selection);
	}
	
	/**
	 * Returns the crust selections
	 * @return
	 */
	public double getCrustSelection() 
	{
		
		return crustSelection;
		
	}
	
	/**
	 * Sets the crust selection.
	 * @param crustSelection  The input crust type selection price
	 */

	public void setCrustSelection(double crustSelection)
	{
		
		this.crustSelection = crustSelection;
		
	}

	/**
	 * returns the pan crust type
	 * @return
	 */
	public double getPan() {
		
		return pan;
		
	}

	/**
	 * sets the pan price
	 * @param pan  input pan price
	 */
	public void setPan(double pan) {
		
		this.pan = pan;
		
	}

	/**
	 * returns the thin curst price
	 * @return
	 */
	public double getThin() {
		
		return thin;
		
	}

	
	/**
	 * sets the this crust price
	 * @param thin
	 */
	public void setThin(double thin) {
		
		this.thin = thin;
		
	}

}
