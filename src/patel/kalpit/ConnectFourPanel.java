package patel.kalpit;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * This class creates a JPanel, it uses 2D arrays to create the board for the
 * game
 *
 */
public class ConnectFourPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	final int ROW = 6, COL = 7;

	private JButton[] buttons = new JButton[COL];
	private JLabel squares[][] = new JLabel[ROW][COL];
	int counter = 0;
	Color color = null;
	int[] index = new int[COL];
	char[][] board = new char[ROW][COL];
	final int FONT_SIZE = 100;

	int winnerChip;
	int turnRemaining;
	int gameType; // 1 = singlePlayer , 2 = multiPlayer;

	ComputerAI compAI;

	/**
	 * This is the constructor of the class. It creates the layout of the panel,
	 * from number of rows & columns to the background colour.
	 */
	public ConnectFourPanel(int gameType) {
		this.gameType = gameType;
		setLayout(new GridLayout(COL, ROW));
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(700, 600));

		setLayout(new GridLayout(7, 7, 10, 10));

		/**
		 * This creates a black row of 6 at top, which indicates to user that
		 * the corresponding column is not filled completely
		 */

		for (int a = 0; a < COL; a++) {
			buttons[a] = new JButton();
			buttons[a].setBackground(Color.BLACK);
			buttons[a].setOpaque(true);
			buttons[a].addActionListener(new ClickListener());
			buttons[a].setBorder(BorderFactory.createLineBorder(Color.RED));
			add(buttons[a]);
		}

		/**
		 * This creates the board for the game, 6 rows and 7 columns. The
		 * squares are where the chips will be places.
		 */
		for (int n = 0; n < ROW; n++) {
			for (int m = 0; m < COL; m++) {
				squares[n][m] = new JLabel();
				squares[n][m].setBackground(Color.BLUE);
				squares[n][m].setOpaque(true);
				squares[n][m].setBorder(BorderFactory.createLineBorder(Color.WHITE));
				squares[n][m].setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
				squares[n][m].setHorizontalAlignment(SwingConstants.CENTER);
				squares[n][m].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				add(squares[n][m]);
			}
		}

		for (int i = 0; i < COL; i++) {
			index[i] = ROW;
		}
		board();

		compAI = new ComputerAI(ROW, COL);
	}

	/**
	 * Detects if user clicks on black square to place chip in corresponding
	 * column, if clicked, it changes colour of square in its corresponding
	 * column depending on which player's turn it is Counter++ is used to make
	 * sure that after one player is gone, it is other player's turn Turn-- is
	 * used to check tie, for each turn taken, it reduces and once it reaches 0,
	 * it means the game is tied
	 */
	private class ClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == buttons[0]) {
				if (gameType == 2) {
					Color color = turnSwitcher(0);
					squares[index[0]][0].setBackground(color);
					updateBoard(color, index[0], 0);
					counter++;
					turnRemaining--;
				} else if (gameType == 1) {
					compMode(0);
				}

			}
			if (e.getSource() == buttons[1]) {
				if (gameType == 2) {
					Color color = turnSwitcher(1);
					squares[index[1]][1].setBackground(color);
					updateBoard(color, index[1], 1);
					counter++;
					turnRemaining--;
				} else if (gameType == 1) {
					compMode(1);
				}
			}
			if (e.getSource() == buttons[2]) {
				if (gameType == 2) {
					Color color = turnSwitcher(2);
					squares[index[2]][2].setBackground(color);
					updateBoard(color, index[2], 2);
					counter++;
					turnRemaining--;
				} else if (gameType == 1) {
					compMode(2);
				}
			}
			if (e.getSource() == buttons[3]) {
				if (gameType == 2) {
					Color color = turnSwitcher(3);
					squares[index[3]][3].setBackground(color);
					updateBoard(color, index[3], 3);
					counter++;
					turnRemaining--;
				} else if (gameType == 1) {
					compMode(3);
				}
			}

			if (e.getSource() == buttons[4]) {
				if (gameType == 2) {
					Color color = turnSwitcher(4);
					squares[index[4]][4].setBackground(color);
					updateBoard(color, index[4], 4);
					counter++;
					turnRemaining--;
				} else if (gameType == 1) {
					compMode(4);
				}
			}
			if (e.getSource() == buttons[5]) {
				if (gameType == 2) {
					Color color = turnSwitcher(5);
					squares[index[5]][5].setBackground(color);
					updateBoard(color, index[5], 5);
					counter++;
					turnRemaining--;
				} else if (gameType == 1) {
					compMode(5);
				}
			}
			if (e.getSource() == buttons[6]) {
				if (gameType == 2) {
					Color color = turnSwitcher(6);
					squares[index[6]][6].setBackground(color);
					updateBoard(color, index[6], 6);
					counter++;
					turnRemaining--;
				} else if (gameType == 1) {
					compMode(6);
				}
			}
			for (int n = 0; n < 6; n++) {
				for (int m = 0; m < 7; m++) {
				}
			}

			if (gameType == 1) {
				for (int n = 0; n < ROW; n++) {
					for (int m = 0; m < COL; m++) {
						board[n][m] = compAI.update(n, m);
					}
				}
			}
			if (turnRemaining == 0) {
				tie();
			} else {
				if (win()) {
					winnerFound();
				}
			}
		}

	}

	/**
	 * Game Set up for single player. Sets colours for user and Computer as well
	 * as places chip for CPU automatically after user has gone
	 * 
	 * @param col
	 */
	private void compMode(int col) {
		int colPlayer, rowPlayer, colComp, rowComp;
		Color player = Color.yellow;
		Color comp = Color.red;

		colPlayer = col;
		rowPlayer = compAI.playerMove(colPlayer);
		squares[rowPlayer][colPlayer].setBackground(player);

		/**
		 * Automatically places chip for computer (This is where the turn of AI takes place)
		 */
		compAI.compMove();
		colComp = compAI.getColOfAITurn();
		rowComp = compAI.getRowOfAITurn();
		if (!compAI.isColumnFull(colComp)) {
			squares[rowComp][colComp].setBackground(comp);
		}
		if (compAI.isColumnFull(col)) {
			buttons[col].setEnabled(false);
			buttons[col].setBackground(Color.WHITE);
		}
	}

	/**
	 * Makes another board which corresponds game board, this board is used to
	 * check winner
	 */
	private void board() {
		for (int n = 0; n < ROW; n++) {
			for (int m = 0; m < COL; m++) {
				board[n][m] = 'X';
			}
		}
		turnRemaining = 42;
	}

	/**
	 * Sets colour for each player, Yellow for Player 1, Red for Player 2
	 * 
	 * @param color
	 * @param i
	 * @param y
	 */
	public void updateBoard(Color color, int i, int y) {
		if (color == Color.yellow) {
			board[i][y] = '1';

		} else if (color == Color.red) {
			board[i][y] = '2';
		}
	}

	/**
	 * Changes turn for player, also if column is full, disables button which
	 * corresponds to the column, making it known to users the column is full
	 * 
	 * @param i
	 * @return
	 */
	public Color turnSwitcher(int i) {
		if ((counter % 2) == 0) {
			color = Color.yellow;
		} else {
			color = Color.red;
		}
		index[i] -= 1;

		if (index[i] < 1) {
			buttons[i].setEnabled(false);
			buttons[i].setBackground(Color.WHITE);
		}
		return color;
	}

	/**
	 * States winner and stops game once winner is found
	 */
	public void winnerFound() {
		for (int n = 0; n < 6; n++) {
			for (int m = 0; m < 7; m++) {
				squares[n][m].setEnabled(false);
			}
		}
		for (int a = 0; a < 7; a++) {
			buttons[a].setEnabled(false);
		}
		if (winnerChip != 67) {
			int result = JOptionPane.showConfirmDialog(null,
					"  Player " + (winnerChip - 48) + " won! Do you want to start a new game?", null,
					JOptionPane.YES_NO_OPTION);

			if (result == JOptionPane.YES_OPTION) {
				gameReset();
			} else if (result == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(null, " Click Ok to return to Main Menu");

				JFrame backToMenu = new JFrame("Main Menu");

				backToMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				backToMenu.getContentPane().add(new MainMenu());
				backToMenu.pack();
				backToMenu.setSize(750, 650);
				backToMenu.setVisible(true);
			}
		}if (winnerChip == 67) {
			int result = JOptionPane.showConfirmDialog(null, " You lost! Do you want to start a new game?", null, JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				gameReset();
			} else if (result == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(null, " Click Ok to return to Main Menu");

				JFrame backToMenu = new JFrame("Main Menu");

				backToMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				backToMenu.getContentPane().add(new MainMenu());
				backToMenu.pack();
				backToMenu.setSize(750, 650);
				backToMenu.setVisible(true);
			}
		}
	}

	/**
	 * Checks for Winner using board which was made above, checks for winner
	 * vertically, horizontally and diagonally
	 * 
	 * @return
	 */
	public boolean win() {
		for (int n = 0; n < 3; n++) {
			for (int m = 0; m < 7; m++) {
				if (board[n][m] != 'X') {
					if (board[n + 1][m] == board[n][m] && board[n + 2][m] == board[n][m]
							&& board[n + 3][m] == board[n][m]) {
						squares[n][m].setText("X");
						squares[n + 1][m].setText("X");
						squares[n + 2][m].setText("X");
						squares[n + 3][m].setText("X");

						winnerChip = board[n][m];

						return true;
					}
				}
			}
		}
		for (int n = 0; n < 6; n++) {
			for (int m = 0; m < 4; m++) {
				if (board[n][m] != 'X') {
					if (board[n][m + 1] == board[n][m] && board[n][m + 2] == board[n][m]
							&& board[n][m + 3] == board[n][m]) {
						squares[n][m].setText("X");
						squares[n][m + 1].setText("X");
						squares[n][m + 2].setText("X");
						squares[n][m + 3].setText("X");
						winnerChip = board[n][m];

						return true;
					}
				}
			}
		}
		for (int n = 0; n < 3; n++) {
			for (int m = 0; m < 4; m++) {
				if (board[n][m] != 'X') {
					if (board[n + 1][m + 1] == board[n][m] && board[n + 2][m + 2] == board[n][m]
							&& board[n + 3][m + 3] == board[n][m]) {
						squares[n][m].setText("X");
						squares[n + 1][m + 1].setText("X");
						squares[n + 2][m + 2].setText("X");
						squares[n + 3][m + 3].setText("X");

						winnerChip = board[n][m];
						return true;
					}
				}
			}
		}
		for (int n = 3; n < 6; n++) {
			for (int m = 0; m < 4; m++) {
				if (board[n][m] != 'X') {
					if (board[n - 1][m + 1] == board[n][m] && board[n - 2][m + 2] == board[n][m]
							&& board[n - 3][m + 3] == board[n][m]) {
						squares[n][m].setText("X");
						squares[n - 1][m + 1].setText("X");
						squares[n - 2][m + 2].setText("X");
						squares[n - 3][m + 3].setText("X");

						winnerChip = board[n][m];
						return true;
					}
				}
			}
		}
		return false;

	}

	/**
	 * If game is tied, lets players know game is tied and asks user if they
	 * want to play again or return back to Main Menu
	 */
	public void tie() {
		for (int n = 0; n < 6; n++) {
			for (int m = 0; m < 7; m++) {
				squares[n][m].setEnabled(false);
			}
		}
		for (int a = 0; a < 7; a++) {
			buttons[a].setEnabled(false);
		}
		int result = JOptionPane.showConfirmDialog(null,
				"    TIE GAME! No winner Found! Do you want to start a new game?", null, JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.YES_OPTION) {
			gameReset();
		} else if (result == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, " Click Ok to return to Main Menu");

			JFrame backToMenu = new JFrame("Main Menu");

			backToMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			backToMenu.getContentPane().add(new MainMenu());
			backToMenu.pack();
			backToMenu.setSize(750, 650);
			backToMenu.setVisible(true);
		}

	}

	/**
	 * Used for when game is reset again
	 */
	public void gameReset() {
		for (int n = 0; n < 6; n++) {
			for (int m = 0; m < 7; m++) {
				squares[n][m].setText("");
				squares[n][m].setBackground(Color.BLUE);
				squares[n][m].setEnabled(true);
			}
		}
		for (int a = 0; a < 7; a++) {
			buttons[a].setEnabled(true);
			buttons[a].setBackground(Color.BLACK);

		}
		if (gameType == 2) {
			board();
			for (int i = 0; i < 7; i++) {
				index[i] = 6;
			}

		} else if (gameType == 1) {
			compAI.setUpArray(ROW, COL);
		}
	}

}