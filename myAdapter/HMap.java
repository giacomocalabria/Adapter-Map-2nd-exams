package myAdapter;

/**
 *  A Map is an object that maps keys to values. A map cannot contain duplicate
 *  keys; each key can map to at most one value.
 * 
 *  <p>
 *  This interface takes the place of the Dictionary class, which was a totally
 *  abstract class rather than an interface.
 * 
 *  <p>
 *  The Map interface provides three collection views, which allow a map's contents
 *  to be viewed as a set of keys, collection of values, or set of key-value mappings.
 *  The order of a map is defined as the order in which the iterators on the map's
 *  collection views return their elements. Some map implementations, like the
 *  TreeMap class, make specific guarantees as to their order;
 *  others, like the HashMap class, do not.
 * 
 *  <p>
 *  Note: great care must be exercised if mutable objects are used as map keys.
 *  The behavior of a map is not specified if the value of an object is changed
 *  in a manner that affects equals comparisons while the object is a key in the map.
 *  A special case of this prohibition is that it is not permissible for a map to
 *  contain itself as a key. While it is permissible for a map to contain itself as
 *  a value, extreme caution is advised: the equals and hashCode methods are no longer
 *  well defined on a such a map.
 * 
 *  <p>
 *  All general-purpose map implementation classes should provide two "standard"
 *  constructors: a void (no arguments) constructor which creates an empty map,
 *  and a constructor with a single argument of type Map, which creates a new map
 *  with the same key-value mappings as its argument. In effect, the latter constructor
 *  allows the user to copy any map, producing an equivalent map of the desired class.
 *  There is no way to enforce this recommendation (as interfaces cannot contain constructors)
 *  but all of the general-purpose map implementations in the SDK comply.
 * 
 *  <p>
 *  The "destructive" methods contained in this interface, that is, the methods that
 *  modify the map on which they operate, are specified to throw UnsupportedOperationException
 *  if this map does not support the operation. If this is the case, these methods
 *  may, but are not required to, throw an UnsupportedOperationException if the
 *  invocation would have no effect on the map. For example, invoking the putAll(Map)
 *  method on an unmodifiable map may, but is not required to, throw the exception if
 *  the map whose mappings are to be "superimposed" is empty.
 * 
 *  <p>
 *  Some map implementations have restrictions on the keys and values they may contain.
 *  For example, some implementations prohibit null keys and values, and some have
 *  restrictions on the types of their keys. Attempting to insert an ineligible key
 *  or value throws an unchecked exception, typically NullPointerException or ClassCastException. 
 *  Attempting to query the presence of an ineligible key or value may throw an exception,
 *  or it may simply return false; some implementations will exhibit the former behavior
 *  and some will exhibit the latter. More generally, attempting an operation on an
 *  ineligible key or value whose completion would not result in the insertion of
 *  an ineligible element into the map may throw an exception or it may succeed, at
 *  the option of the implementation. Such exceptions are marked as "optional" in
 *  the specification for this interface.
 * 
 *  @author Giacomo Calabria
 */

public interface HMap{
    //QUERY OPERATIONS

    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of key-value mappings in this map
     */
    int size();

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings
     */
    boolean isEmpty();
    
    /**
     * Returns {@code true} if this map contains a mapping for the specified
     * key.  More formally, returns {@code true} if and only if
     * this map contains a mapping for a key {@code k} such that
     * {@code Objects.equals(key, k)}.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified
     *         key
     *  @throws NullPointerException if the specified key is null (this map does not permit null key).
     */
    boolean containsKey(Object key);

    /**
     * Returns {@code true} if this map maps one or more keys to the
     * specified value.  More formally, returns {@code true} if and only if
     * this map contains at least one mapping to a value {@code v} such that
     * {@code Objects.equals(value, v)}.  This operation
     * will probably require time linear in the map size for most
     * implementations of the {@code Map} interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return {@code true} if this map maps one or more keys to the
     *         specified value
     *  @throws NullPointerException if the specified value is null (this map does not permit null values).
     */
    boolean containsValue(Object value);

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that
     * {@code Objects.equals(key, k)},
     * then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>If this map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the map
     * contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to {@code null}.  The {@link #containsKey
     * containsKey} operation may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     * @throws NullPointerException if the specified key is null (this map does not permit null key).
     */
    Object get(Object key);

    //MODIFICATION OPERATIONS

    /**
     * Associates the specified value with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.  (A map
     * {@code m} is said to contain a mapping for a key {@code k} if and only
     * if {@link #containsKey(Object) m.containsKey(k)} would return
     * {@code true}.)
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with {@code key},
     *         if the implementation supports {@code null} values.)
     * @throws NullPointerException if the specified key or value is null
     *         (this map does not permit null key).
     */
    Object put(Object key, Object value);

    /**
     * Removes the mapping for a key from this map if it is present
     * (optional operation).   More formally, if this map contains a mapping
     * from key {@code k} to value {@code v} such that
     * {@code Objects.equals(key, k)}, that mapping
     * is removed.  (The map can contain at most one such mapping.)
     *
     * <p>Returns the value to which this map previously associated the key,
     * or {@code null} if the map contained no mapping for the key.
     *
     * <p>If this map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the map
     * contained no mapping for the key; it's also possible that the map
     * explicitly mapped the key to {@code null}.
     *
     * <p>The map will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     * @throws NullPointerException if the specified key is null
     *         (this map does not permit null key).
     */
    Object remove (Object key);

    //BULK OPERATIONS

    /**
     * Copies all of the mappings from the specified map to this map
     * (optional operation).  The effect of this call is equivalent to that
     * of calling {@link #put(Object,Object) put(k, v)} on this map once
     * for each mapping from key {@code k} to value {@code v} in the
     * specified map.  The behavior of this operation is undefined if the
     * specified map is modified while the operation is in progress.
     *
     * @param m mappings to be stored in this map
     *  @throws NullPointerException the specified map is null, or if this map
     *  does not permit null keys or values, and the specified
     *  map contains null keys or values. (this map does not permit null key).
     */
    void putAll(HMap t);

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    void clear();

    /**
     * Returns a {@link HSet} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own {@code remove} operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * {@code HIterator.remove}, {@code HSet.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear}
     * operations.  It does not support the {@code add} or {@code addAll}
     * operations.
     *
     * @return a set view of the keys contained in this map
     */
    HSet keySet();

    /**
     * Returns a {@link HCollection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own {@code remove} operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the {@code HIterator.remove},
     * {@code HCollection.remove}, {@code removeAll},
     * {@code retainAll} and {@code clear} operations.  It does not
     * support the {@code add} or {@code addAll} operations.
     *
     * @return a collection view of the values contained in this map
     */
    HCollection values();

    /**
     * Returns a {@link HSet} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own {@code remove} operation, or through the
     * {@code setValue} operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the {@code HIterator.remove},
     * {@code HSet.remove}, {@code removeAll}, {@code retainAll} and
     * {@code clear} operations.  It does not support the
     * {@code add} or {@code addAll} operations.
     *
     * @return a set view of the mappings contained in this map
     */
    HSet entrySet();
    
    //COMPARISION AND HASHING

    /**
     * Compares the specified object with this map for equality.  Returns
     * {@code true} if the given object is also a map and the two maps
     * represent the same mappings.  More formally, two maps {@code m1} and
     * {@code m2} represent the same mappings if
     * {@code m1.entrySet().equals(m2.entrySet())}.  This ensures that the
     * {@code equals} method works properly across different implementations
     * of the {@code Map} interface.
     *
     * @param o object to be compared for equality with this map
     * @return {@code true} if the specified object is equal to this map
     *  @see Hashtable
     *  @see Object.hashCode()
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this map.  The hash code of a map is
     * defined to be the sum of the hash codes of each entry in the map's
     * {@code entrySet()} view.  This ensures that {@code m1.equals(m2)}
     * implies that {@code m1.hashCode()==m2.hashCode()} for any two maps
     * {@code m1} and {@code m2}, as required by the general contract of
     * {@link Object#hashCode}.
     *
     * @return the hash code value for this map
     *  @see equals(Object)
     *  @see Map.Entry.hashCode()
     *  @see Object.hashCode()
     *  @see Object.equals(Object)
     */
    int hashCode();

    /**
     *  A map entry (key-value pair). The Map.entrySet method returns a collection-view
     *  of the map, whose elements are of this class. The only way to obtain a reference
     *  to a map entry is from the iterator of this collection-view. These Map.Entry
     *  objects are valid only for the duration of the iteration; more formally, the
     *  behavior of a map entry is undefined if the backing map has been modified after
     *  the entry was returned by the iterator, except through the iterator's own remove
     *  operation, or through the setValue operation on a map entry returned by the iterator.
     * 
     *  @author Giacomo Calabria
     *  @see Map.entrySet()
     */
    public interface HEntry {

        /**
         * Compares the specified object with this entry for equality.
         * Returns {@code true} if the given object is also a map entry and
         * the two entries represent the same mapping.  More formally, two
         * entries {@code e1} and {@code e2} represent the same mapping
         * if<pre>
         *     (e1.getKey()==null ?
         *      e2.getKey()==null : e1.getKey().equals(e2.getKey()))  &amp;&amp;
         *     (e1.getValue()==null ?
         *      e2.getValue()==null : e1.getValue().equals(e2.getValue()))
         * </pre>
         * This ensures that the {@code equals} method works properly across
         * different implementations of the {@code Map.Entry} interface.
         *
         * @param o object to be compared for equality with this map entry
         * @return {@code true} if the specified object is equal to this map
         *         entry
         */
        public boolean equals(Object o);

        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry
         */
        public Object getKey();

        /**
         * Returns the value corresponding to this entry.  If the mapping
         * has been removed from the backing map (by the iterator's
         * {@code remove} operation), the results of this call are undefined.
         *
         * @return the value corresponding to this entry
         * 
         */
        public Object getValue();

        /**
         * Returns the hash code value for this map entry.  The hash code
         * of a map entry {@code e} is defined to be: <pre>
         *     (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
         *     (e.getValue()==null ? 0 : e.getValue().hashCode())
         * </pre>
         * This ensures that {@code e1.equals(e2)} implies that
         * {@code e1.hashCode()==e2.hashCode()} for any two Entries
         * {@code e1} and {@code e2}, as required by the general
         * contract of {@code Object.hashCode}.
         *
         * @return the hash code value for this map entry
         * @see Object#hashCode()
         * @see Object#equals(Object)
         * @see #equals(Object)
         */
        public int hashCode();

        /**
         * Replaces the value corresponding to this entry with the specified
         * value (optional operation).  (Writes through to the map.)  The
         * behavior of this call is undefined if the mapping has already been
         * removed from the map (by the iterator's {@code remove} operation).
         *
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         */
        public Object setValue(Object value);
    }
 
}
