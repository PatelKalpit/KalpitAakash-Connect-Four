package patel.kalpit;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class contains JPanel for Instructions, plus button which takes user
 * back to Main Menu screen when clicked
 * 
 *
 */
public class Instructions extends JPanel {
	final int FONT_SIZE = 24;
	JLabel title;
	JLabel stepOne;
	JLabel stepTwo;
	JLabel singlePlayer;
	JLabel twoPlayer;
	JButton back;

	ImageIcon titleImage;

	/**
	 * Constructor of Class
	 */
	public Instructions() {
		setBackground(Color.CYAN);
		setLayout(new GridLayout(7, 1));
		titleImage = new ImageIcon("InstructionsTitle.png");
		title = new JLabel();
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setIcon(titleImage);
		add(title);

		stepOne = new JLabel(" 1. Click on any black square to place chip in its corresponding column.");
		add(stepOne);
		stepTwo = new JLabel(
				" 2. Object of game is to get four chips in a row vertically, hortizontally, or diagonally before the other player.");
		add(stepTwo);
		singlePlayer = new JLabel(
				"                                                              Single Player: You (Yellow Chip) go first vs CPU (Red Chip) goes second ");
		add(singlePlayer);
		twoPlayer = new JLabel(
				"                 Two Player: Player 1 (Yellow Chip) goes first vs Player 2 (Red Chip) goes second. Turns will rotate after each game. ");
		add(twoPlayer);
		back = new JButton("Back to menu");
		back.addActionListener(new backmenu());
		back.setBackground(Color.white);
		add(back);
	}

	/**
	 * Detects if Back to Menu button is clicked, if clicked, takes user back to
	 * Main Menu Screen
	 * 
	 *
	 */
	private class backmenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == back) {
				JFrame backto = new JFrame("Connect Four");

				backto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				backto.getContentPane().add(new MainMenu());
				backto.pack();
				backto.setSize(750, 650);
				backto.setVisible(true);
			}
		}
	}
}