package myAdapter;

import java.util.Enumeration;

/**
 *  MapAdapter adapts the Hashtable class from Java CLDC 1.1 to the
 *  HMap interface. Therefore it's an Adapter design
 *  pattern. 
 * 
 *  @version 1.0
 *  @author Giacomo Calabria
 *  @see HMap
 */

public class MapAdapter implements HMap{


    // MapAdapter is a Object Adapter
    private Hashtable table;

    // Counts the number of modifications
    protected int modificationCount;

    /**
     * Default constructor with no arguments.
     */
    public MapAdapter(){
        modificationCount = 0;
        table = new Hashtable();
    }

    // QUERY OPERATIONS

    /**
     * Returns the number of elements in this map. If this map contains more than
     * Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     *
     * @return the number of elements in this map.
     */
    public int size(){
        return table.size();
    }

    /**
     * Returns true if this map contains no elements.
     *
     * @return true if this map contains no elements.
     */
    public boolean isEmpty(){
        return table.isEmpty();
    }

    /**
     *  Returns true if this map contains a mapping for the specified key. 
     *  More formally, returns true if and only if this map contains at a mapping for a key k such 
     *  that (key==null ? k==null : key.equals(k)). (There can be at most one such mapping.)
     *  @param key key whose presence in this map is to be tested
     *  @return true if this map contains a mapping for the specified key
     */
    public boolean containsKey(Object key){
        return table.containsKey(key);
    }

    /**
     *  Returns true if this map maps one or more keys to the specified value. 
     *  More formally, returns true if and only if this map contains at least one mapping to a value v such 
     *  that (value==null ? v==null : value.equals(v)). This operation will 
     *  probably require time linear in the map size for most implementations of the Map interface.
     *  @param value value whose presence in this map is to be tested
     *  @return true if this map maps one or more keys to the specified value.
     */
    public boolean containsValue(Object value){
        for (Enumeration e = table.elements() ; e.hasMoreElements() ;) {
            if (e.nextElement().equals(value))
                return true;
        }
        return false;
    }

    /**
     *  Returns the value to which this map maps the specified key. Returns null if
     *  the map contains no mapping for this key. A return value of null does not
     *  necessarily indicate that the map contains no mapping for the key; it's also
     *  possible that the map explicitly maps the key to null.
     *  The containsKey operation may be used to distinguish these two cases.
     * 
     * <p>
     *  More formally, if this map contains a mapping from a key k to a value v such
     *  that (key==null ? k==null : key.equals(k)), then this method returns v;
     *  otherwise it returns null. (There can be at most one such mapping.)
     * 
     *  @param key key whose associated value is to be returned.
     *  @return the value to which this map maps the specified key, or null if the
     *  map contains no mapping for this key.
     */
    public Object get(Object key){
        return table.get(key);
    }

    //MODIFICATION OPERATIONS

    /** 
     *  Associates the specified value with the specified key in this map (optional operation).
     *  If the map previously contained a mapping for this key, the old value is
     *  replaced by the specified value. (A map m is said to contain a mapping for
     *  a key k if and only if m.containsKey(k) would return true.))
     * 
     *  @param key key with which the specified value is to be associated.
     *  @param value value to be associated with the specified key.
     *  @return previous value associated with specified key, or null if there
     *          was no mapping for key. A null return can also indicate that the
     *          map previously associated null with the specified key, if the
     *          implementation supports null values.
     */
    public Object put(Object key, Object value){
        modificationCount ++;
        return table.put(key, value);
    }

    /**
     *  Removes the mapping for this key from this map if it is present (optional operation).
     *  More formally, if this map contains a mapping from key k to value v such
     *  that (key==null ? k==null : key.equals(k)), that mapping is removed.
     *  (The map can contain at most one such mapping.)
     * 
     * <p>
     *  Returns the value to which the map previously associated the key, or null
     *  if the map contained no mapping for this key. (A null return can also
     *  indicate that the map previously associated null with the specified key
     *  if the implementation supports null values.) The map will not contain
     *  a mapping for the specified key once the call returns.
     * 
     *  @param key key whose mapping is to be removed from the map.
     *  @return previous value associated with specified key, or null if there was no mapping for key.
     */
    public Object remove(Object key){
        Object value = table.remove(key); //null if not removed, valid otherwise

        if(value == null)
            return null;

        modificationCount ++; // If this was modified increments modidification count
        return value;
    }

    // BULK OPERATIONS

    /**
     *  Copies all of the mappings from the specified map to this map (optional operation).
     *  The effect of this call is equivalent to that of calling put(k, v) on this
     *  map once for each mapping from key k to value v in the specified map.
     *  The behavior of this operation is unspecified if the specified map is
     *  modified while the operation is in progress.
     * 
     *  @param t Mapping to be stored in this map.
     *  @throws NullPointerException the specified map is null, or if this map does not permit null keys or values, and the specified
     *  map contains null keys or values.
     */
    public void putAll(HMap t){
        if (t == null)
            throw new NullPointerException();

        HSet ks = t.keySet();

        for (HIterator i = ks.iterator(); i.hasNext();) {
            Object key = i.next();
			table.put(key,get(key));
		}
        modificationCount ++;
    }

    /**
     *  Removes all mappings from this map (optional operation).
     */
    public void clear(){
        modificationCount ++;
        table.clear();
    }

    /**
     *  Returns a set view of the keys contained in this map. The set is backed
     *  by the map, so changes to the map are reflected in the set, and vice-versa.
     *  If the map is modified while an iteration over the set is in progress,
     *  the results of the iteration are undefined. The set supports element
     *  removal, which removes the corresponding mapping from the map, via the
     *  Iterator.remove, Set.remove, removeAll retainAll, and clear operations.
     *  It does not support the add or addAll operations.
     * 
     *  @return a set view of the keys contained in this map
     */
    public HSet keySet(){
        HSet ks = new SetAdapter();

        for (Enumeration e = table.keys() ; e.hasMoreElements() ;) {
            ks.add(e.nextElement());
        }

        return ks;
    }

    /**
     *  Returns a collection view of the values contained in this map. The collection
     *  is backed by the map, so changes to the map are reflected in the collection,
     *  and vice-versa. If the map is modified while an iteration over the collection
     *  is in progress, the results of the iteration are undefined. The collection
     *  supports element removal, which removes the corresponding mapping from the
     *  map, via the Iterator.remove, Collection.remove, removeAll, retainAll
     *  and clear operations. It does not support the add or addAll operations.
     * 
     *  @return a collection view of the values contained in this map
     */
    public HCollection values(){
        HCollection vc = new HCollection();

        for (Enumeration e = table.elements() ; e.hasMoreElements() ;) {
            vc.add(e.nextElement());
        }

        return vc;
    }

    /**
     * Returns a set view of the mappings contained in this map. Each element
     * in the returned set is a Map.Entry. The set is backed by the map, so changes
     * to the map are reflected in the set, and vice-versa. If the map is modified
     * while an iteration over the set is in progress, the results of the iteration
     * are undefined. The set supports element removal, which removes the corresponding
     * mapping from the map, via the Iterator.remove, Set.remove, removeAll,
     * retainAll and clear operations. It does not support the add or addAll operations.
     * 
     *  @return a set view of the mappings contained in this map.
     */
    public HSet entrySet(){
        HSet es = new SetAdapter();
        for (Enumeration e = table.keys() ; e.hasMoreElements() ;){
            Object key = e.nextElement();
            EntryAdapter en = new EntryAdapter(key,get(key));
            es.add(en);
        }
        return es;

    }

    // COMPARISION AND HASHING
    
    /**
     *  Compares the specified object with this map for equality. Returns true
     *  if the given object is also a map and the two Maps represent the same
     *  mappings. More formally, two maps t1 and t2 represent the same mappings
     *  if t1.entrySet().equals(t2.entrySet()). This ensures that the equals
     *  method works properly across different implementations of the Map interface.
     * 
     *  @param o object to be compared fot equality for this map
     *  @return true if the specified object is equal to this map
     *  @see Hashtable
     *  @see Object.hashCode()
     */
    public boolean equals(Object o){
        return table.equals(o);
    }

    /**
     *  Returns the hash code value for this map. The hash code of a map is defined
     *  to be the sum of the hashCodes of each entry in the map's entrySet view.
     *  This ensures that t1.equals(t2) implies that t1.hashCode()==t2.hashCode()
     *  for any two maps t1 and t2, as required by the general contract of Object.hashCode.
     * 
     *  @return the hash code value for this map
     *  @see equals(Object)
     *  @see Map.Entry.hashCode()
     *  @see Object.hashCode()
     *  @see Object.equals(Object)
     */
    public int hashCode(){
        return table.hashCode();
    }

    private class EntryAdapter implements HEntry{

        private Object key;

        private Object value;

        public EntryAdapter(){
            this(null,null);
        }

        public EntryAdapter(Object akey, Object avalue){
            key = akey;
            value = avalue;
        }

        public Object getValue(){
            return value;
        }

        public Object getKey(){
            return key;
        }

        /**
         *   Replaces the value corresponding to this entry with the specified value
         *   (optional operation).() Writes through to the map.) The behavior of this
         *   call is undefined if the mapping has already been removed from the map
         *   (by the iterator's remove operation).
         * 
         *   @param value new value to be stored in this entry
         *   @return old value corresponging to the entry
         */

        public Object setValue(Object newValue){
            Object oldValue = getValue();
            value = newValue;
            return oldValue;
        }

    }

    private class SetAdapter implements HSet{

    }
}
