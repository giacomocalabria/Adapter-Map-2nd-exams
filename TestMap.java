import myAdapter.*;

public class TestMap
{
	public static void main(String[] argv)
	{
		int count = 0;
		HMap m = null;
		HSet s1 = null;
		HSet ks = null;
		HIterator iter = null;
		HCollection c = null;

		String[] args = {"pippo", "pluto", "qui", "ciccio", "gambatek"};

		m = new MapAdapter();

		if(argv.length < 5)
		{
			argv = args;
		} 

		for(int i=0;i<argv.length;i++)
		{
			m.put(argv[i], argv[i]);
		}

		try
		{
			int sm0, sm1, sm2, ss0, ss1, ss2;
			System.out.println("Test propagation from map to keyset");
			ks = m.keySet();
			System.out.println(m + " " + m.size());
			System.out.println(ks + " " + ks.size());
			sm0 = m.size();
			ss0 = ks.size();
			m.remove(argv[0]);
			sm1 = m.size();
			ss1 = ks.size();
			System.out.println("Entry removed: " + m + " " + m.size());
			System.out.println(ks + " " + ks.size());
			m.put(argv[0], argv[0]);
			sm2 = m.size();
			ss2 = ks.size();
			System.out.println("Entry restored: " + m + " " + m.size());
			System.out.println(ks + " " + ks.size());
			if(sm0 == ss0 && sm1 == ss1 && sm2 == ss2 && (sm0-sm1) == 1)
			{
				System.out.println("\n*** map propaga modifiche a keyset ***\n");
			}
			else
			{
				System.out.println(sm0 + " " + sm1 + " " + sm2 + " " + ss0 + " " + ss1 + " " + ss2);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		//Test toString
		System.out.println("Map.toString() ? " + m);

		//Test keySet and backing
		try
		{
			int sm0, sm1, sm2, ss0, ss1, ss2;
			System.out.println("Test keyset and backing");
			System.out.println(m + " " + m.size());
	
			s1 = m.keySet();
			sm0 = m.size();
			ss0 = s1.size();

			iter = s1.iterator();
			count = s1.size()+2;
			while(iter.hasNext()&&count-->=0)
			{
				Object k = iter.next();
				System.out.print("[" + k + "=" + m.get(k) + "]; ");
			}
			System.out.println("\n" + s1);
			s1.remove(argv[0]);

			sm1 = m.size();
			ss1 = s1.size();

			System.out.println(m + " " + m.size());
			iter = s1.iterator();
			count = s1.size()+2;
			while(iter.hasNext()&&count-->=0)
			{
				Object k = iter.next();
				System.out.print("[" + k + "=" + m.get(k) + "]; ");
			}
			System.out.println("\n" + s1);
	
			System.out.println("Inserisco nella mappa e controllo il set");
			m.put("carrozza", "carrozza");
			System.out.println(m + " " + m.size());
			iter = s1.iterator();
			count = s1.size()+2;
			while(iter.hasNext()&&count-->=0)
			{
				Object k = iter.next();
				System.out.print("[" + k + "=" + m.get(k) + "]; ");
			}
			System.out.println("\n" + s1);

			sm2 = m.size();
			ss2 = s1.size();

			s1.remove("carrozza");
			System.out.println("Removed carrozza from keyset");
			System.out.println("set size=" + s1.size() + "; map size=" + m.size());
			if(sm2 == m.size() || ss2 == s1.size() || s1.size() != m.size())
			{
				System.out.println("Removal from key set not working");
				System.out.println("Before removal " + sm2 + " " + ss2 + " after removal " + m.size() + " " + s1.size());

			}
			else
			{
				//System.out.println("Removal from key set is ok");

				if(sm0 == ss0 && sm1 == ss1 && sm2 == ss2 && (sm0-sm1) == 1)
				{
					System.out.println("\n*** keyset propaga modifiche a map ***\n");
				}
				else
				{
					System.out.println(sm0 + " " + sm1 + " " + sm2 + " " + ss0 + " " + ss1 + " " + ss2);
				}
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			System.out.println("Test emptying by keyset iterator");
			iter = s1.iterator();
			count = s1.size()+2;
			while(iter.hasNext() && count-- >= 0)
			{
				Object k = iter.next();
				iter.remove();
				System.out.print(k + " " + m.size() + "; ");
			}
			System.out.println("\n" + s1);
			if(m.size() == s1.size() && m.size() == 0)
			{
				System.out.println("\n*** keyset iterator removal works and propaga modifiche a map ***\n");
			}
			else
			{
				System.out.println("\n*** keyset iterator removal does not work ***\n");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}



		//reset m and test values
		try
		{
			int sm0, sm1, sm2, ss0, ss1, ss2;
			System.out.println("reset map content and test values");
			m.clear();
			System.out.println("Before " + m + " " + m.size());
			m.put(argv[0], argv[0]);
			for(int i=0;i<argv.length;i++)
			{
				m.put(argv[i], argv[i]);
			}
			if(m.size() != argv.length)
				System.out.println("\n*** map.put malfunction ***\n");

			System.out.println("after " + m + " " + m.size());
			c = m.values();

			sm0 = m.size();
			ss0 = c.size();

			iter = c.iterator();
			count = c.size() +2;
			while(iter.hasNext() && count-- >= 0)
				System.out.print(iter.next() + "; ");
			System.out.println("\n" + c);
			c.remove(argv[0]);

			sm1 = m.size();
			ss1 = c.size();

			System.out.println(m + " " + m.size());
			iter = c.iterator();
			count = c.size()+2;
			while(iter.hasNext()&&count-->= 0)
				System.out.print(iter.next() + "; ");
			System.out.println("\n" + c);

			sm2 = m.size();
			ss2 = c.size();

			if(sm0 == ss0 && sm1 == ss1 && sm2 == ss2 && (sm0-sm1) == 1)
			{
				System.out.println("\n*** values propaga modifiche a map ***\n");
			}
			else
			{
				System.out.println("\n*** values NON propaga modifiche a map ***\n");
				System.out.println(sm0 + " " + sm1 + " " + sm2 + " " + ss0 + " " + ss1 + " " + ss2);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			System.out.println("\nemptying by values iterator");
			iter = c.iterator();
			count = c.size()+2;
			while(iter.hasNext()&&count-->=0)
			{
				System.out.print(iter.next() + "; ");
				iter.remove();
			}
			System.out.println("\nmap " + m.size() + "; collection " + c.size());
			if(m.size() == c.size() && m.size() == 0)
			{
				System.out.println("\n*** values iterator removal works and propaga modifiche a map ***\n");
			}
			else
			{
				System.out.println("\n*** values iterator removal NON propaga modifiche a map ***\n");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}
