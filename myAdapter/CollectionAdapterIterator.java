package myAdapter;

public class CollectionAdapterIterator implements HIterator{

    Vector vec;
    int index;
    boolean next = false;

    public CollectionAdapterIterator(Vector v){
        vec = v;
    }

    public boolean hasNext() {
        return (index < vec.size());
    }

    public Object next() {
        if(index >= vec.size()){
			throw new java.util.NoSuchElementException();
		}
		next = true;
		return vec.elementAt(index++);
    }

    public void remove() {
        if(!next){
			throw new IllegalStateException();
		}
		next = false;
		vec.removeElementAt(--index);
    }
    
}
