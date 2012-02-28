import java.util.Scanner;


public class NQueens 
{
	static int[] position;
	static int[] work;
	static int n;
	public static void main(String[] args) 
	{
		
		Scanner jin = new Scanner(System.in);
		n = jin.nextInt();
		
		position = new int[n+1];
		work = new int[n+1];
		
		for(int i = 1; i <= n; i++ )
		{
			position[i] = 0;
			work[i] = 1;
		}
		
		int k = jin.nextInt();
		
		int row, col;
		
		for( int i = 1; i <= k; i++ )
		{
			row = jin.nextInt();
			col = jin.nextInt();
			position[row] = col;
			work[row] = 0; // 0 means need not be worked on
		}
		
		boolean reverse = false;
		int i = 1;
		while(true)//while(i<=n)
		{
			if( i == 0 ) break;
			if( i == n+1 )
			{
				for( int l = 1; l <= n; l ++ )
				{
					System.out.print(chessPosition(l, position[l])+" ");
				}
				System.out.println();
				i--;
				reverse = true;
				continue;
			}
			if( work[i] == 1 )
			{
				//System.out.println("i = "+i);
				if(nextPosition(i) == 0)
				{
					position[i] = 0;
					i--;
					
					reverse = true;
					continue;
				}
				position[i] = nextPosition(i);
				//System.out.println(i+" "+position[i]+" works");
				i++;
				reverse = false;
				continue;
			}
			if( work[i] == 0 )
				{
					if( reverse == true )i--;
					if( reverse == false ) i++;
				}
			
		}
		/*for( int l = 1; l <= n; l ++ )
		{
			System.out.print(chessPosition(l, position[l])+" ");
		}*/
		

	}
	public static int nextPosition(int row)
	{
		for(int col = position[row]+1; col <= n; col ++)
		if(isAllowed(row,col) == true) return col;
		return 0;
		
		// no modifications are made to the original array
	}
	public static boolean isAllowed(int row, int col)
	{
		//System.out.println("Checked " + row + " " + col );
		boolean allowed = true;
		for(int i = row - 1, j= 1; i >= 1 ; i--,j++)
		{
			if(position[i] == 0) continue;
			if( (isValidCol(col-j) && position[i] == col - j) || (isValidCol(col+j)&&(position[i] == col + j)) || (position[i] == col)) return false;
		}
		for( int i = row + 1, j= 1; i <= n; i++,j++ )
		{
			if( position[i] == 0 ) continue;
			if( (isValidCol(col-j) && position[i] == col - j) || (isValidCol(col+j)&&(position[i] == col + j)) || (position[i] == col)) return false;
		}
		
		return allowed;
	}
	public static boolean isValidPosition(int row, int col)
	{
		if( 1 <= row && row <= n && 1 <= col && col <= n ) return true;
		return false;
	}
	public static boolean isValidCol(int col)
	{
		if( 1 <= col && col <= n ) return true;
		return false;
	}
	public static String chessPosition(int row, int col)
	{
		char x = (char)((int)'a' + row - 1);
		int y = col;
		String answer = Character.toString(x);
		StringBuffer ans = new StringBuffer(answer);
		ans.append(col);
		return ans.toString();
	}

}

