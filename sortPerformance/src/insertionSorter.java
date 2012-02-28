import java.util.List;


public class insertionSorter 
{
	public static <T extends Comparable<T>> void insertionSort( List<T> lst, int start, int end)
	{
		int j,i;
		T key;
	    for(j = start; j < end; j++)
	    {
	        key = lst.get(j);
	        i = j-1;
	        while(i>=0 && lst.get(i).compareTo(key) > 0)
	        {
	            lst.set(i+1, lst.get(i));
	            i--;
	        }
	        lst.set(i+1, key) ;
	    }
	}

}
