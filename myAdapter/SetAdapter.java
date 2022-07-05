package myAdapter;

/**
 * 
 * @author Giacomo Calabria
 */

public class SetAdapter extends CollectionAdapter implements HSet{
	public boolean add(Object o){
		if(contains(o)){
			return false;
		}
		return super.add(o);
	}

	public boolean addAll(HCollection c){
		boolean hasAddAll = false;
		HIterator i = c.iterator();
		while(i.hasNext()){
			hasAddAll = add(i.next());
			
		}
		return hasAddAll;
	}
}
