package myAdapter;

public interface HMap{
    //QUERY OPERATIONS

    int size();

    boolean isEmpty();
    
    boolean containsKey(Object key);

    boolean containsValue(Object value);

    Object get(Object key);

    //MODIFICATION OPERATIONS

    Object put(Object key, Object value);

    Object remove (Object key);

    //BULK OPERATIONS

    void putAll(HMap t);

    void clear();

    HSet keySet();

    HCollection values();

    HSet entrySet();
    
    //COMPARISION AND HASHING

    boolean equals(Object o);

    int hashCode();
 
}
