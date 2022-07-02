package myAdapter;

import java.util.Map;

public interface HMap{
    //QUERY OPERATIONS

    int size();

    boolean isEmpty();
    
    boolean containsKey(Object key);

    boolean containsValue(Object value);

    Object get(Object key);

    HSet entrySet();

    HSet keySet();

    HCollection values();

    //MODIFICATION OPERATIONS

    Object put(Object key, Object value);

    Object remove (Object key);

    //COMPARISION AND HASHING

    boolean equals(Object o);

    int hashCode();

    //BULK OPERATIONS

    void putAll(HMap t);

    void clear();
 
}
