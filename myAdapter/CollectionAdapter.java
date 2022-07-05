package myAdapter;

public class CollectionAdapter implements HCollection{

    private Vector vec;

    public CollectionAdapter(){
        vec = new Vector();
    }

    public int size() {
        return vec.size();
    }

    public boolean isEmpty() {
        return vec.isEmpty();
    }

    public boolean contains(Object obj){
        return vec.contains(obj);
    }

    public HIterator iterator() {
        return new CollectionAdapterIterator(vec);
    }

    public Object[] toArray() {
        Object[] arr = new Object[size()];
        for(int i = 0; i < arr.length; i++){
            arr[i] = vec.elementAt(i);
        }
        return arr;
    }

    public Object[] toArray(Object[] arrayTarget) {
        return toArray();
    }

    public boolean add(Object obj) {
        vec.addElement(obj);
        return true;
    }

    public boolean remove(Object obj){
        return vec.removeElement(obj);
    }

    public boolean containsAll(HCollection coll) {
        HIterator i = coll.iterator();
        while(i.hasNext()){
            Object tmp = i.next();
            if(!vec.contains(tmp))
                return false;
        }
        return true;
    }

    public boolean addAll(HCollection coll) {
        HIterator i = coll.iterator();
        while(i.hasNext()){
            Object tmp = i.next();
            vec.addElement(tmp);
        }
        return true;
    }

    public boolean removeAll(HCollection coll) {
        HIterator i = coll.iterator();
		boolean hasRemAll = false;
		while(i.hasNext()){
			hasRemAll = vec.removeElement(i.next());
		}
		return hasRemAll;
    }

    public boolean retainAll(HCollection coll) {
        boolean hasRetAll = false;
		HIterator i = this.iterator();
		while(i.hasNext()){
			Object tmp = i.next();
			if(! coll.contains(tmp)){
				i.remove();
				hasRetAll = true;
			}
		}
		return hasRetAll;
    }

    public void clear(){
		vec.removeAllElements();
	}

    public boolean equals(Object o){
		if(! (o instanceof CollectionAdapter)){
			return false;
		}
		CollectionAdapter ca = (CollectionAdapter) o;
		return vec.equals(ca.vec);
	}

	public int hashCode(){
		return vec.hashCode();
	}
    
}
