package patel.kalpit;


import javax.swing.JFrame;


/**
 * 
 * @author Kalpit Patel
 * Mr. Hutchison - ISC4U1-01
 * March, 18th/2016
 * This program creates a Connect Four game that enables the user to play against the computer as well as play against a real opponent (multiplayer)
 */
public class Driver {

	public static void main(String[] args) {

		JFrame window = new JFrame("Connect Four");

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(new ConnectFourPanel());
		window.pack();
		window.setSize(750, 650);
		window.setVisible(true);
	}
	
}