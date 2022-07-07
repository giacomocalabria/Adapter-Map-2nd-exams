package myAdapter;

/**
 * 
 * @author Giacomo Calabria
 */

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
        return toArray(); // ATTENZIONE
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

    /**
     * An iterator over a collection.  {@code Iterator} takes the place of
     * {@link Enumeration} in the Java Collections Framework.  Iterators
     * differ from enumerations in two ways:
     * <ul>
     *      <li> Iterators allow the caller to remove elements from the
     *           underlying collection during the iteration with well-defined
     *           semantics.
     *      <li> Method names have been improved.
     * </ul>
     * @author Giacomo Calabria
     */

    public class CollectionAdapterIterator implements HIterator{

        Vector vec;
        int index;
        boolean next = false;

        public CollectionAdapterIterator(Vector v){
            vec = v;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        public boolean hasNext() {
            return (index < vec.size());
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        public Object next() {
            if(index >= vec.size()){
                throw new java.util.NoSuchElementException();
            }
            next = true;
            return vec.elementAt(index++);
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.
         * <p>
         * The behavior of an iterator is unspecified if the underlying collection
         * is modified while the iteration is in progress in any way other than by
         * calling this method, unless an overriding class has specified a
         * concurrent modification policy.
         *
         */
        public void remove() {
            if(!next){
                throw new IllegalStateException();
            }
            next = false;
            vec.removeElementAt(--index);
        }
    
    }
    
}
