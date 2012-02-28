import java.util.List;


public class bubbleSorter 
{
	public static <T extends Comparable<T>> void bubbleSort( List<T> lst, int start, int end )
	{
		int i;
		int n = end - start;

		do
		{
			for (i = start; i < end; i++)
			{
				if (lst.get(i).compareTo(lst.get(i+1)) > 0)
				{
					T buffer = lst.get(i);
					lst.set(i, lst.get(i+1)); 
					lst.set(i+1, buffer); 
				}
			}
			n--;
		}
		while ( n > 0 );
	}

}
