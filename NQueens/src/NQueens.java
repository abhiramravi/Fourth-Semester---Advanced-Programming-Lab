import java.util.Scanner;


public class NQueens 
{

	/**
	 * Read 'N' from terminal and generate a valid arrangement of N queens on an NxN chess board.
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO: Get N from terminal
		Scanner jin = new Scanner( System.in );
		n = jin.nextInt();
		position = new int[n];
		for( int i = 0; i < n; i++ )
		{
			position[i] = -1;
		}
		// TODO: Find placement of N Queens 
		placeQueens(n);
		for( int i = 0; i < n; i++ )
		{
			System.out.print(positionString(i, position[i]) + " ");
		}
		// TODO: Print the chess positions (e.g. b4) of the pieces
		//printBoard(position);
		
	}
	private static int n;
	private static int[] position;
	/**
	 * Find a valid arrangement of n queens on a nxn chess-board.
	 * Hint: Use a recursive solution
	 * @param n
	 * @return
	 */
	private static int[] placeQueens(int n)
	{
		for( int i = 0; i < n; i++ )
		{
			//printBoard(position);
			if( placeQueens( i, n ) == false )  
			{	
				position[i] = -1; 	/* reset the position if no such next position is possible*/
				i -= 2; 			/*after the i++ it effectively becomes i-- */
			}
			if( i == -2 ) 
			{
				System.out.println("Impossible!");
				return null;
			}
		}
		return position;
		
		
	}

	/**
	 * Place queen number 'row' so as not to conflict with any of the previous positions
	 * Return false if no such placement is possible   
	 * @param positions
	 * @param row
	 * @return 
	 */

	private static boolean placeQueens( int row, int n )
	{
		boolean check = false;
		for( int i = position[row]+1; i < n; i++ )
		{
			if( isPossible( row, i, n ) == true ) 
			{
				position[row] = i;
				check = true;
				break;
			}
		}
		if(check == false) position[row] = -1;
		return check;
	}
	private static boolean isPossible( int row, int i, int n )
	{
		/* first check along the verticals */
		for( int j = 0; j < row; j++ )
		{
			if( position[j] == i ) return false;
		}
		/* check along the diagonals */
		for( int j = 0; j < row; j++ )
		{
			if( i-j-1 >= 0 ) if( position[row-j-1] == i-j-1 ) return false;
			if( i+j+1 < n ) if( position[row-j-1] == i+j+1 ) return false;
		}
		return true;
	}

	/**
	 * Print the position of a piece in i-th row and j-th column in chess format. 
	 * @param i
	 * @param j
	 * @return
	 */
	private static String positionString(int i, int j) 
	{
		String position = "";
		switch(j)
		{
		case 0 : position = position + "a"; break;
		case 1 : position = position + "b"; break;
		case 2 : position = position + "c"; break;
		case 3 : position = position + "d"; break;
		case 4 : position = position + "e"; break;
		case 5 : position = position + "f"; break;
		case 6 : position = position + "g"; break;
		case 7 : position = position + "h"; break;
		case 8 : position = position + "i"; break;
		case 9 : position = position + "j"; break;
		case 10: position = position + "k"; break;
		case 11: position = position + "l"; break;
		case 12: position = position + "m"; break;
		case 13: position = position + "n"; break;
		case 14: position = position + "o"; break;
		case 15: position = position + "p"; break;
		case 16: position = position + "q"; break;
		case 17: position = position + "r"; break;
		case 18: position = position + "s"; break;
		case 19: position = position + "t"; break;
		case 20: position = position + "u"; break;
		case 21: position = position + "v"; break;
		case 22: position = position + "w"; break;
		case 23: position = position + "x"; break;
		case 24: position = position + "y"; break;
		case 25: position = position + "z"; break;
		default : break;
		}
		position = position + Integer.toString(i+1);
		return position;
	}
	
	/**
	 * Helper function that prints the position of all queens given the array
	 * @param positions
	 */
	
	private static void printBoard( int[] positions ) 
	{
		int n = positions.length;
		
		System.err.print( "XX|" );
		for( int j = 0; j<n; j++ ) 
		{
			System.err.format( "%c|", 'a'+j);
		}
		System.err.println();
		for( int i = 0; i<n; i++ ) 
		{		
			System.err.format( "%2d|", i+1);
			for( int j = 0; j<n; j++ ) 
			{
				if( j == positions[i] )
					System.err.print( "Q|" );
				else
					System.err.print( " |" );
			}
			System.err.println();
		}
	}
}
