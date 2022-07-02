package myAdapter;

public interface HSet extends HCollection{
    boolean add(Object o);

    boolean addAll(HCollection c);

    void clear();

    boolean contain(Object o);

    boolean containsAll(HCollection c);

    boolean equals(Object o);

    int hashCode();

    boolean isEmpty();

    HIterator iterator();

    boolean remove(Object o);

    boolean removeAll(HCollection c);

    boolean retainAll(HCollection c);

    int size();

    Object[] toArray();

    Object[] toArray(Object[] a);
}
