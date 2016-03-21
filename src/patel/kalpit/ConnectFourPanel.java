
package patel.kalpit;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * This class creates a JPanel, it uses 2D arrays to create the board for the game
 *
 */
public class ConnectFourPanel extends JPanel {

    // 2D (rows and columns) arrays for setting the board and the size of each square
	private JButton[][] buttons = new JButton[1][7];
	private JLabel squares[][] = new JLabel[6][7];

	/**
	 * This is the constructor of the class. It creates the layout of the panel, from number of rows & columns to the background colour.
	 */
	public ConnectFourPanel() {
		setLayout(new GridLayout(7, 6));
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(700, 600));

		setLayout(new GridLayout(7, 7));

		/**
		 * This creates a black row of 6 at top, which indicates to user that the corresponding column is not filled completely
		 */
		for (int f = 0; f < 1; f++) {
			for (int a = 0; a < 7; a++) {
				buttons[f][a] = new JButton();
				buttons[f][a].setBackground(Color.BLACK);
				buttons[f][a].setOpaque(true);
				add(buttons[f][a]);
			}

		}
		
		/**
		 * This creates the board for the game, 6 rows and 7 columns. The squares are where the chips will be places. 
		 */
		for (int n = 0; n < 6; n++) {
			for (int m = 0; m < 7; m++) {
				squares[n][m] = new JLabel();
				squares[n][m].setBackground(Color.BLUE);
				squares[n][m].setOpaque(true);
				squares[n][m].setBorder(BorderFactory.createLineBorder(Color.WHITE));
				add(squares[n][m]);
			}
		}

	}

}
