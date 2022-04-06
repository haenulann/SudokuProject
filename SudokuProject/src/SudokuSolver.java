
public class SudokuSolver {

	// constant for size of the grid
	private static final int GRID_SIZE = 9;

	public static void main(String[] args) {

		int[][] board = { 
			 { 0, 0, 0, 0, 0, 0, 6, 1, 8 }, 
			 { 9, 0, 0, 0, 6, 7, 3, 0, 0 }, 
			 { 0, 6, 0, 2, 0, 0, 0, 0, 7 },
			 { 0, 5, 0, 7, 4, 9, 0, 3, 0 }, 
			 { 0, 7, 9, 3, 0, 8, 1, 4, 0 }, 
			 { 0, 3, 0, 6, 1, 2, 0, 7, 0 },
			 { 7, 0, 0, 0, 0, 5, 0, 6, 0 }, 
			 { 0, 0, 3, 1, 2, 0, 0, 0, 4 }, 
			 { 6, 8, 4, 0, 0, 0, 0, 0, 0 } 
							
		};
		
		System.out.println();
		printBoard(board);

		if (solveBoard(board)) {
			System.out.println();
			System.out.println("Solved successfully! :)");
			System.out.println();
			
		} else {
			System.out.println("Unsolvable :(");
			System.out.println();
		}
		printBoard(board);

	}

	private static void printBoard(int[][] board) {
	    for (int row = 0; row < GRID_SIZE; row++) {
	      if (row % 3 == 0 && row != 0) {
	        System.out.println("-----------");
	      }
	      for (int column = 0; column < GRID_SIZE; column++) {
	        if (column % 3 == 0 && column != 0) {
	          System.out.print("|");
	        }
	        System.out.print(board[row][column]);
	      }
	      System.out.println();
	    }
	  }

	// helper method #1: to see if the number already exists in the row
	private static boolean isNumInRow(int[][] board, int num, int row) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[row][i] == num) {
				return true;
			}
		}
		return false;
	}

	// helper method #2: to see if the number already exists in the column
	private static boolean isNumInColumn(int[][] board, int num, int column) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[i][column] == num) {
				return true;
			}
		}
		return false;
	}

	// helper method #3: checking if number exists elsewhere
	private static boolean isNumInBoard(int[][] board, int num, int row, int column) {
		int localBoardRow = row - row % 3;
		int localBoardColumn = column - column % 3;

		for (int i = localBoardRow; i < localBoardRow + 3; i++) {
			for (int j = localBoardColumn; j < localBoardColumn + 3; j++) {
				if (board[i][j] == num) {
					return true;
				}
			}
		}
		return false;
	}
	
	// helper method #4: checks all 3 methods
	private static boolean isValidPlacement(int[][] board, int num, int row, int column) {
		return !isNumInRow(board, num, row) &&
			   !isNumInColumn(board, num, column) &&
			   !isNumInBoard(board, num, row, column);
		
	}
	
	
	private static boolean solveBoard(int[][] board) {
		//loop through the entire grid
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int column = 0; column < GRID_SIZE; column++) {
				//look for a space that has not been set yet
				if (board[row][column] == 0) {
					//loop through each number (1-9) and tries them
					for (int numToTry = 1; numToTry <= GRID_SIZE; numToTry++) {
						if (isValidPlacement(board, numToTry, row, column)) {
							board[row][column] = numToTry;
							
							if (solveBoard(board)) {
								return true;
							} else { 
								board[row][column] = 0; 
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
}
