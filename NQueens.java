/*
   Charles Daniel
   July 1, 2002
   COMP 440
   N-Queens Problem: Takes an positive integer N from the command line
   and prints out a solution of N queens placed on a NxN chess board
   such that no queens can capture another.
*/

public class NQueens {

        // queenArray=chessboard, where each array element=column
	// and the value at each element=row
	private int[] queenArray;

	private int N;                  //size of board & # of queens
	private boolean success;        //flags if a solution is found
	private int currCol, currRow;   //used internally
	
	private boolean TestPiece() {
	// This method checks to see that if a queen is placed at the currRow
	// and currCol, that it will not capture any existing queen.
	// Existing queens (columns to the left of currCol) are assumed to
	// already be non-capturing themselves.
	// This method returns false if the currRow and currCol can be captured
	// by another queen, otherwise it returns true.
	
		for(int i=currCol-1; i>=0; i--) {
			if( (queenArray[i] == currRow) ||
			    (queenArray[i] == (currRow+(currCol-i))) ||
			    (queenArray[i] == (currRow-(currCol-i))) ) {
				return false;
			}
		}

		return true;
	}

	public void PrintBoard() {
	// This method simply prints out the current state of the
	// chess board. Note that success==true when there is a valid
	// configuration present in the queenArray.

		System.out.println();
		if(success==true) {
			for(int col=0; col<N; col++) {
				for(int row=0; row<N; row++) {
					if(queenArray[col] == row) {
						System.out.print("Q");
					}
					else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
		}
		else {
			System.out.println("No solution possible.");
		}
	}
	
	public boolean FindSolution() {
	// This method uses backtracking to place N queens on the
	// NxN chess board.
	
		System.out.print("Thinking");
		currRow=queenArray[currCol];

		// Infinite loop, but we'll return from the function
		// if currCol gets to N (past the rightmost column)
		// (ie. a solution was found)
		// or falls below 0 (past the leftmost column)
		// (ie. no solutions were found).
		for( ; ; ) {
			if(TestPiece() == true) {
				System.out.print(".");
				queenArray[currCol] = currRow;
				currCol++;
				if(currCol >= N) {
					success=true;
					return success;
				}
				currRow = queenArray[currCol];
			}
			else { // advance
				currRow++;
				while(currRow >= N) { //backtrack
					System.out.print("\u0008");
					queenArray[currCol] = 0;
					currCol--;
					if(currCol < 0) {
						success=false;
						return success;
					}
					currRow = queenArray[currCol]+1;
				}
			}
		}
	}
	
	public NQueens(int N) {
	// This constructor takes in a positive integer N and creates
	// an array of N elements (the columns) which hold values that
	// correspond to the row at that column.
	
		this.N = N;
		queenArray = new int[N];

		FindSolution();
		PrintBoard();
		queenArray=null;
	}

	public static void PrintUsage() {
		System.out.println("Usage:\tNQueens N\n\tWhere N is a positive integer that specifies the size\n\tof the chess board (NxN) and the number of\n\tqueens to place on that chess board.");
	}
	
	public static void main(String[] args) {
		// Splash screen.
		System.out.println("N-Queens Problem");
		System.out.println("AUTHOR: Charles Daniel (csd117@psu.edu)");
		System.out.println("LICENSE: PUBLIC DOMAIN FREEWARE\n");

		// Get input from command line.
		if(args.length != 1) {
			PrintUsage();
			return;
		}

		// Some basic error checking.
		int N=Integer.parseInt(args[0]);
		if(N <= 0) {
			System.out.println("ERROR: N must be a positive integer\n");
			PrintUsage();
			return;
		}

		// Create an instance of NQueens, note that the
		// constructor actually solves the problem and prints
		// the results.
		NQueens nq = new NQueens(N);

	}

}
