/**
 * This class creates the main frame, panels and the calculations for the different selections.  It then generates a new window to display the 
 * transaction info.
 * 
 * 
 * @author Wes's PC
 * @version 1.0
 * 
 * COP 3022 Project 6
 * File Name:  CrustPanel.java
 *
 */

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderCalculation extends JFrame implements ActionListener{
	
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static final double TAX = .075;
	
	private double transactionTotal = 0;
	private double subtotal = 0;
	private double taxTotal = 0;
	
	CrustPanel crust = new CrustPanel();
	ToppingsPanel toppings = new ToppingsPanel();
	DrinksPanel drinks = new DrinksPanel();
	
	JPanel buttonPanel;
	JFrame totalFrame;
	
	JButton calculateButtion;
	JButton exitButton;
	
	JTextArea totalText;
	JTextField welcomeText;
	
	/**
	 * Constructor that builds the main frame that displays all of the selection menus.
	 */
	
	public OrderCalculation(){
		
		super();
		setSize(WIDTH, HEIGHT);
		setTitle("Order Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.DARK_GRAY);
		setLayout(new BorderLayout());
		
		welcomeText = new JTextField(30);
		welcomeText.setFont(new Font("serif", Font.BOLD, 14));
		welcomeText.setText("Welcome to the Pizza Builder");
		welcomeText.setEditable(false);
		
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.setLayout(new FlowLayout());
		
		
		calculateButtion = new JButton("Calculate");
		calculateButtion.addActionListener(this);
		buttonPanel.add(calculateButtion);
		
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		buttonPanel.add(exitButton);
		
		add(welcomeText, BorderLayout.NORTH);
		add(crust, BorderLayout.WEST);
		add(toppings, BorderLayout.CENTER);
		add(drinks, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
		
		
		
	}

	/**
	 * This method calculates the total and displays it on a separate panel.
	 * It also contains the action events for each button on the panel(s).
	 */
	public void actionPerformed(ActionEvent e){
		
		String action = e.getActionCommand();
		
		if(action.equals("Calculate")){
			
			totalFrame = new JFrame();
			totalFrame.setSize(300, 200);
			totalFrame.setTitle("Transaction Total");
			totalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			totalFrame.setBackground(Color.GRAY);
			totalFrame.setLayout(new BorderLayout());
			totalFrame.setVisible(true);
			
			totalText = new JTextArea(3, 20);
			totalText.setBackground(new Color(0, 0, 0, 0));
			totalText.setFont(new Font("serif", Font.BOLD, 14));
			totalText.setEditable(false);
			
			JPanel totalPanel = new JPanel();
			totalPanel.add(totalText);
			totalFrame.add(totalPanel, BorderLayout.CENTER);
			
			
			JButton okButton = new JButton("OK");
			okButton.addActionListener(this);
			
			JPanel exitPanel = new JPanel();
			exitPanel.add(okButton);
			totalFrame.add(exitPanel, BorderLayout.SOUTH);
			
			
		
			
			subtotal = crust.getCrustSeletion() + toppings.getToppingsSelection() + drinks.getDrinkSelection();
			taxTotal = subtotal * TAX;
			transactionTotal = (subtotal * TAX) + subtotal;
			
			totalText.setText(String.format("Subtotal: $%.2f\n Tax: $%.2f\n Total: $%.2f", subtotal, taxTotal, transactionTotal));
			
		}
		else if(action.equals("Exit")){
		
			System.exit(0);
		}
		else if(action.equals("OK")){
			
			totalFrame.dispose();
		}
	}
}

