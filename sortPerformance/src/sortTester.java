import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
public class sortTester 
{
	static int N = 10;
	final static int stringlength = 25;
	final static int averager = 30;
	
	static File file = new File("values.txt");

	static PrintWriter jout ;

	public static void main(String[] args) throws FileNotFoundException 
	{
		jout = new PrintWriter(file);
		do
		{
		/* ArrayList */
		/* Integer sort */
		List<Integer> lst = new ArrayList<Integer>();
		Random rand = new Random();
		for( int i = 0; i < N; i++ )
		{
			lst.add(rand.nextInt(Integer.MAX_VALUE));
		}
		List<Integer> newLst = new ArrayList<Integer>();
		newLst.addAll(lst);
		//bubbleSortTester(newLst, 0);
		
		newLst.clear();
		newLst.addAll(lst);
		mergeSortTester(newLst, 0);
		
		newLst.clear();
		newLst.addAll(lst);
		insertionSortTester(newLst, 0);
		
		/* String sort */
		List<String> lst1 = new ArrayList<String>();
		for( int i = 0; i < N; i++ )
		{
			lst1.add(randomString());
		}
		List<String> newLst1 = new ArrayList<String>();
		newLst1.addAll(lst1);
		
		//bubbleSortTester(newLst1, 0);
		
		newLst1.clear();
		newLst1.addAll(lst1);
		mergeSortTester(newLst1, 0);
		
		newLst1.clear();
		newLst1.addAll(lst1);
		insertionSortTester(newLst1, 0);
		
		/* Linked List*/
		List<Integer> llst = new LinkedList<Integer>();
		for( int i = 0; i < N; i++ )
		{
			llst.add(rand.nextInt(Integer.MAX_VALUE));
		}
		newLst.clear();
		newLst.addAll(llst);
		//bubbleSortTester(newLst, 1);
		
		newLst.clear();
		newLst.addAll(llst);
		mergeSortTester(newLst, 1);
		
		newLst.clear();
		newLst.addAll(llst);
		insertionSortTester(newLst, 1);
		
		/* String sort */
		List<String> llst1 = new LinkedList<String>();
		for( int i = 0; i < N; i++ )
		{
			llst1.add(randomString());
		}
		newLst1.clear();
		newLst1.addAll(llst1);
		//bubbleSortTester(newLst1, 1);
		
		newLst1.clear();
		newLst1.addAll(llst1);
		mergeSortTester(newLst1, 1);
		
		newLst1.clear();
		newLst1.addAll(llst1);
		insertionSortTester(newLst1, 1);
		jout.println("");
		N*=5;
		System.out.println(N);
		}while(N <= 700000);
		
		
		jout.close();
		
		System.out.println("done");
	
	}
	public static String randomString()
	{
		Random rand = new Random();
		int size;

		StringBuilder str = new StringBuilder();
		/* Ensures that the string is not empty */
		do
		{
			size = rand.nextInt(25);
		}
		while( size == 0 );
		/* appends random characters to the string*/
		for( int i = 0 ; i < size ; i ++ )
		{
			str.append((char)(rand.nextInt(26) + 'a'));
		}
		return str.toString();
	}
	public static <T extends Comparable<T>> void bubbleSortTester(List<T> lst, int al)
	{
		/* Bubble Sort Testing */
		List<T> backup;
		if( al == 0 ) 
		{
			backup = new ArrayList<T>();
			backup.addAll(lst);
		}
		else
		{
			backup = new LinkedList<T>();
			backup.addAll(lst);
		}
		double averageTime = 0 ; //the time is measured in milliseconds
		
		for( int k = 0; k < averager; k++ )
		{
			double start = System.nanoTime();
			bubbleSorter.bubbleSort(backup, 0, backup.size()-1);
			double end = System.nanoTime();
			averageTime += (end - start)/1000000;	
			backup.clear();
			backup.addAll(lst);
		}
		averageTime = averageTime/averager;
		jout.print(averageTime + " ");
	}
	public static <T extends Comparable<T>> void mergeSortTester(List<T> lst, int al)
	{
		List<T> backup;
		if( al == 0 ) 
		{
			backup = new ArrayList<T>();
			backup.addAll(lst);
		}
		else
		{
			backup = new LinkedList<T>();
			backup.addAll(lst);
		}
		double averageTime = 0 ; //the time is measured in milliseconds
		
		for( int k = 0; k < averager; k++ )
		{
			double start = System.nanoTime();
			mergeSorter.mergeSort(lst, 0, lst.size()-1);
			double end = System.nanoTime();
			averageTime += (end - start)/1000000;	
			backup.clear();
			backup.addAll(lst);
		}
		averageTime = averageTime/averager;
		jout.print(averageTime + " ");
	}
	public static <T extends Comparable<T>> void insertionSortTester(List<T> lst, int al)
	{
		List<T> backup;
		if( al == 0 ) 
		{
			backup = new ArrayList<T>();
			backup.addAll(lst);
		}
		else
		{
			backup = new LinkedList<T>();
			backup.addAll(lst);
		}
		double averageTime = 0 ; //the time is measured in milliseconds
		for( int k = 0; k < averager; k++ )
		{
			double start = System.nanoTime();
			insertionSorter.insertionSort(lst, 0, lst.size()-1);
			double end = System.nanoTime();
			averageTime += (end - start)/1000000;	
			backup.clear();
			backup.addAll(lst);
		}
		averageTime = averageTime/averager;
		jout.print(averageTime + " ");
	}
	

}
