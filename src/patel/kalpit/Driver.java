package patel.kalpit;

import javax.swing.*;

/**
 * 
 * @author Kalpit Patel 
 * Mr. Hutchison 
 * ISC3U1-01 
 * March, 18th/2016 
 * This program creates a Connect Four game that enables the user to play against the computer as well as play against another user (multiplayer)
 */
public class Driver {

	public static void main(String[] args) {

		JFrame window = new JFrame("Connect Four");

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(new MainMenu());
		window.pack();
		window.setSize(750, 650);
		window.setVisible(true);

	}

}