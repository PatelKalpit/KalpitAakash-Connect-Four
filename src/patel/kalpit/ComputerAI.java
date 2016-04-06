package patel.kalpit;

import javax.swing.*;

/**
 * This class decides move for AI and also for player in Single Player mode
 * 
 * @author 324996115
 *
 */

public class ComputerAI extends JPanel {
	char[][] board;
	int[] index = new int[7];

	int rowAI, colAI;

	/**
	 * Constructor of AI
	 * 
	 * @param rowOfBoard
	 * @param colOfBoard
	 */
	public ComputerAI(int rowOfBoard, int colOfBoard) {
		board = new char[rowOfBoard][colOfBoard];
		setUpArray(rowOfBoard, colOfBoard);

	}

	/**
	 * Sets up another board corresponding Connect Four Panel, uses this to
	 * check for winner in different method
	 * 
	 * @param row
	 * @param col
	 */
	public void setUpArray(int row, int col) {
		for (int n = 0; n < row; n++) {
			for (int m = 0; m < col; m++) {
				board[n][m] = 'X';
			}
		}
		for (int i = 0; i < 7; i++) {
			index[i] = 6;
		}
	}

	/**
	 * Gets random column for AI to play chip
	 * 
	 * @return
	 */
	public int getRandomCol() {
		int randomCol = (int) (Math.random() * 6);
		return randomCol;
	}

	/**
	 * Find location and update board
	 */
	public void compMove() {
		boolean isDone = false;
		int colValue;
		while (!isDone){	
			colValue = getRandomCol();
			if (!isColumnFull(colValue)) {
				isDone=true;
				colAI=colValue;
				rowAI = getRow(colAI);
				
			}
			
		}
		
		board[rowAI][colAI] = 'C'; // C - for computer

	}

	/**
	 * Returns row Value for AI move
	 * 
	 * @return
	 */
	public int getRowOfAITurn() {
		return rowAI;
	}

	/**
	 * Returns column Value for AI move
	 * 
	 * @return
	 */
	public int getColOfAITurn() {
		return colAI;
	}

	/**
	 * Gets player's column value after their turn and returns row value
	 * 
	 * @param col
	 * @return
	 */
	public int playerMove(int col) {
		int row = getRow(col);
		board[row][col] = '1';

		return row;
	}

	/**
	 * Returns index value to corresponding column to place chip in correct row
	 * for next turn
	 * 
	 * @param col
	 * @return
	 */
	private int getRow(int col) {
		index[col] -= 1;
		return index[col];
	}

	/**
	 * Returns values of row and column to update board in ConnectFourPanel
	 * class
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public char update(int row, int col) {
		return board[row][col];
	}

	/**
	 * Checks if column is filled or not
	 * 
	 * @param col
	 * @return
	 */
	public boolean isColumnFull(int col) {
		if (index[col] < 1) {
			return true;
		} else
			return false;
	}
}