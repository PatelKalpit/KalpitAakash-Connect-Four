package patel.kalpit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * This class creates JPanel for Main Menu Screen, has three buttons --> Single
 * player, Multiplayer and Instructions
 *
 */

public class MainMenu extends JPanel {

	final int TITLE_SIZE = 50;
	final int FONT_SIZE = 30;
	
	JPanel buttonPanel, backGroundPanel;
	JButton onePlayer;
	JButton twoPlayer;
	JButton instructions;
	JLabel title;
	ImageIcon titleIcon;

	/**
	 * Constructor Of Class
	 */
	public MainMenu() {
		setLayout(new GridLayout(4, 1, 40, 40));
		setBackground(Color.CYAN);

		setFont(new Font("Arial", Font.BOLD, FONT_SIZE));

		titleIcon = new ImageIcon("connect4Logo.png");
		setFont(new Font("Arial", Font.BOLD, TITLE_SIZE));
		title = new JLabel();
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setIcon(titleIcon);
		add(title);

		onePlayer = new JButton("Single Player");
		onePlayer.addActionListener(new uservai());
		onePlayer.setBackground(Color.WHITE);
		add(onePlayer);

		twoPlayer = new JButton("Two Player");
		twoPlayer.addActionListener(new uservuser());
		twoPlayer.setBackground(Color.WHITE);
		add(twoPlayer);

		instructions = new JButton("Instructions");
		instructions.addActionListener(new instructions());
		instructions.setBackground(Color.WHITE);
		add(instructions);

	}

	/**
	 * Detects if Two Player button is clicked, if clicked, Multiplayer game is
	 * started with different JPanel (Board)
	 * 
	 *
	 */
	private class uservuser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == twoPlayer) {
				int gameType = 2;
				JFrame onevone = new JFrame("User vs User");

				onevone.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				onevone.getContentPane().add(new ConnectFourPanel(gameType));
				onevone.pack();
				onevone.setSize(750, 650);
				onevone.setVisible(true);
			}
		}
	}

	/**
	 * Detects if Single Player button is clicked, if clicked, User vs Ai game
	 * is started with different JPanel (Board)
	 */
	private class uservai implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == onePlayer) {
				int gameType = 1;
				JFrame onevCPU = new JFrame ("User vs CPU");
				
				onevCPU.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				onevCPU.getContentPane().add(new ConnectFourPanel(gameType));
				onevCPU.pack();
				onevCPU.setSize(750, 650);
				onevCPU.setVisible(true);
				
			}
		}
	}

	/**
	 * Detects if Instructions button is clicked, if clicked, new panel which
	 * states instructions is displayed
	 */
	private class instructions implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == instructions) {
				JFrame helping = new JFrame("INSTRUCTIONS");

				helping.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				helping.getContentPane().add(new Instructions());
				helping.pack();
				helping.setSize(750, 650);
				helping.setVisible(true);

			}
		}
	}


		}