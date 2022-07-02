package myTest;
import myAdapter.*;

public class TestSet
{
	public static void main(String[] argv)
	{
		//change to interface types
		HSet s = null;
		HCollection c = null;
		HIterator iter2 = null;
		String[] vals = {"pippo", "pippo", "pluto", "paperino", "ciccio", "qui"};

		if(argv.length < 6)
			argv = vals;

		//change to actual adapters
		s = new SetAdapter();
		c = new ListAdapter();

		System.out.println("Test Set");

		try
		{
			System.out.print(s.size() + " ");
			iterate(s.iterator());
			for(int i=0;i<argv.length;i++)
			{
				System.out.println(argv[i] + " " + s.add(argv[i]));
				System.out.print(s.size() + " ");
				iterate(s.iterator());
			}
			if(s.size() == argv.length-1)
				System.out.println("\n*** set correctly refuses clones ***\n");
			else
				System.out.println("\n*** set wronlgy accepts clones ***\n");
				
	
			System.out.println("Set.toString()? " + s);
	
			iter2 = s.iterator();
			while(iter2.hasNext())
			{
				iter2.next();
				iter2.remove();
				System.out.print(s.size() + " ");
				iterate(s.iterator());
			}
			if(c.size() == 0)
				System.out.println("\n*** set iterator removal works ***\n");
			else
				System.out.println("\n*** set iterator removal fails ***\n");
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		System.out.println("Test cwCollection");


		System.out.print(c.size() + " ");
		iterate(c.iterator());
		for(int i=0;i<argv.length;i++)
		{
			System.out.println(argv[i] + " " + c.add(argv[i]));
			System.out.print(c.size() + " ");
			iterate(c.iterator());
		}

		if(c.size() == argv.length-1)
			System.out.println("\n*** collection refuses clones ***\n");
		else
			System.out.println("\n*** collection accepts clones ***\n");
			

		System.out.println("Collection.toString()? " + c);

		iter2 = c.iterator();
		while(iter2.hasNext())
		{
			iter2.next();
			iter2.remove();
			System.out.print(c.size() + " ");
			iterate(c.iterator());
		}

		if(c.size() == 0)
			System.out.println("\n*** collection iterator removal works ***\n");
		else
			System.out.println("\n*** collection iterator removal fails ***\n");
	}

	public static void iterate(HIterator iter)
	{
		System.out.print("{");
		while(iter.hasNext())
		{
			System.out.print(iter.next() + "; ");
		}
		System.out.println("}");
	}
}
