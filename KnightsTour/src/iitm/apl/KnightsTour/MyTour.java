package iitm.apl.KnightsTour;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MyTour extends ChessBoard
{	
	public static void main(String[] args) 
	{
		initialise(new MyTour());
	}
	public List<Move> tour(Move m) 
	{
		makeMove(m);
		List<Move> knightsTour = new LinkedList<Move>();
		
		while(true)
		{
			Move[] reachableMoves = m.reachableMoves();
			
			Move minMove = reachableMoves[0];
			int min = 10;
			
			for( int i = 0 ; i < reachableMoves.length; i++ )
			{
				if( reachableMoves[i].reachableMoves().length < min )
				{
					min = reachableMoves[i].reachableMoves().length;
					minMove = reachableMoves[i];
				}
			}
			
			knightsTour.add(minMove);
			makeMove(minMove);
			m = minMove;
			if( minMove.reachableMoves().length == 0 ) break;
		}
		
		for(int i = 0; i < 8; i ++ )
			for(int j = 0; j < 8; j ++ )
				Move.moves[i][j] = 1;
		return knightsTour;
	}
}
