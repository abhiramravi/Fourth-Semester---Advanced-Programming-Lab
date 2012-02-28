import java.util.ArrayList;
import java.util.List;


public class mergeSorter 
{
	public static <T extends Comparable<T>> void mergeSort( List<T> a, int p, int r)
	{
		if( p < r )
		{
			int q = ( p + r )/2;
			mergeSort(a, p, q);
			mergeSort(a, q+1, r);
			merge(a, p, q, r);
		}
	}
	public static <T extends Comparable<T>> void merge(List<T> lst, int p, int q, int r) 
	{
		int k = p, i = 0, j = 0;

		List<T> l = new ArrayList<T>();
		List<T> z = new ArrayList<T>();

		for (i = 0; k < q; k++, i++)
		{
			l.add(i, lst.get(k));
		}
		l.add(null);

		while( k < r )
		{
			z.add(j, lst.get(k));
			k++;
			j++;
		}
		z.add(null);

		k = p;
		i = 0;
		j = 0;
		while (k < r) 
		{
			if (l.get(i) != null) 
			{
				if (z.get(j) != null)
				{
					if (l.get(i).compareTo(z.get(j)) <= 0)
					{
						lst.set(k, l.get(i));
						k++;
						i++;
					} else 
					{
						lst.set(k, z.get(j));
						k++;
						j++;
					}
				} 
				else 
				{
					lst.set(k, l.get(i));
					k++;
					i++;
				}
			} 
			else 
			{
				lst.set(k, z.get(j));
				k++;
				j++;
			}

		}
	}

}
