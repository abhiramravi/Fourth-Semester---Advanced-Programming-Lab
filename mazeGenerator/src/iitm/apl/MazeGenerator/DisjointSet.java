package iitm.apl.MazeGenerator;

public class DisjointSet 
{
	/* Declare variables required to implement disjoint set*/
	private DisjointSet parent;
	private int value;
	
	DisjointSet(int value)
	{
		this.parent = this;
		this.value = value;
	}
	/* findSet() implemented with path compression */
	DisjointSet findSet()
	{
		if(this.parent == this) return this;
		else
		{
			parent = parent.findSet();
			return this.parent;
		}
	}
	
	void Union(DisjointSet S)
	{	
		DisjointSet repOfThis = this.findSet();
		DisjointSet repOfThat = S.findSet();
		repOfThat.parent = repOfThis;
	}
}
