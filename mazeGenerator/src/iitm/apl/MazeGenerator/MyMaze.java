package iitm.apl.MazeGenerator;
/*
 * Author : Abhiram R : CS10B060
 * 
 * */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class MyMaze extends Maze
{
	public int rows, cols;
	List<Wall> AList;
	public MyMaze(int rows, int cols) 
	{
		super(rows, cols);
		this.rows = rows;
		this.cols = cols;
	}

	@Override
	public void generate(Recorder recorder) 
	{
		/* Initializing the disjoint set and the Wall list */
		DisjointSet[] set = new DisjointSet[rows*cols + 1];
		AList = new ArrayList<Wall>();
		int numberOfWalls = rows * (cols - 1) + (rows - 1) * cols;
		
		addWallsToList();	
		
		for( int i = 1; i <= rows * cols; i++ )
		{
			set[i] = new DisjointSet(i);
		}
		
		/* Now generate random number between 1 and numberOfWalls, put numberOfWalls-- , if the two points of the wall do not belong 
		 * to the same set, then remove that wall, and join those two to one set */
		Random rand = new Random();
		while(numberOfWalls > 0 )
		{
			int random = rand.nextInt(numberOfWalls);
			Wall wall = AList.get(random);
			/* Suppose the two reps are the same */
			if(set[getCellOne(wall)].findSet() != set[getCellTwo(wall)].findSet())
			{		
				breakWall(getCellOne(wall), getCellTwo(wall));
				recorder.takeSnap(this);
				set[getCellOne(wall)].Union(set[getCellTwo(wall)]);
			}
			AList.remove(random);
			numberOfWalls--;
		}
	}

	private void addWallsToList()
	{	
		/* Adding all the vertical walls */
		for(int i = 0; i < rows; i++ )
		{
			for(int j = 0; j < cols-1; j++)
			{
				AList.add(new Wall(new Point(i,j), new Point(i,j+1)));
			}
		}
		/* Adding all the horizontal walls */
		for(int j = 0; j < cols; j++ )
		{
			for(int i = 0; i < rows-1; i++ )
			{
				AList.add(new Wall(new Point(i,j), new Point(i+1,j)));
			}
		}
		
	}
	private int getCellOne(Wall wall)
	{
		return wall.point_1.row * cols + wall.point_1.col + 1;
	}
	private int getCellTwo(Wall wall)
	{
		return wall.point_2.row * cols + wall.point_2.col + 1;
	}
}
class Wall
{
	/* Two points define a wall */
	Point point_1, point_2;
	Wall(Point x, Point y)
	{
		this.point_1 = x;
		this.point_2 = y;
	}
}
class Point
{
	int row,col;
	Point(int x, int y)
	{
		this.row = x;
		this.col = y;
	}
}