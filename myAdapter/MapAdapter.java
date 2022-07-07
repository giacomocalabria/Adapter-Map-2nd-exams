package myAdapter;

import java.util.Enumeration;

/**
 *  MapAdapter adapts the {@link Hashtable} class from Java CLDC 1.1 to the
 *  {@link HMap} interface. Due to this adaptee this implementation of the map
 *  have a restrictions on the keys and values they contain. In fact null keys
 *  or values are forbidden. 
 * 
 *  <p>Therefore it's an Adapter design pattern.
 *  
 *  @version 1.0
 *  @author Giacomo Calabria
 *  @see {@link HMap}
 */

public class MapAdapter implements HMap{


    // MapAdapter is a Object Adapter
    private Hashtable table;

    /**
     * Default constructor with no arguments.
     */
    public MapAdapter(){
        table = new Hashtable(); 
    }

    // QUERY OPERATIONS

    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of key-value mappings in this map
     */
    public int size(){
        return table.size();
    }

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings
     */
    public boolean isEmpty(){
        return table.isEmpty();
    }

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
    public boolean containsKey(Object key) throws NullPointerException{
        return table.containsKey(key);
    }

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
    public boolean containsValue(Object value) throws NullPointerException{
        return table.contains(value);
    }

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
    public Object get(Object key) throws NullPointerException{
        return table.get(key);
    }

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
    public Object put(Object key, Object value) throws NullPointerException{
        return table.put(key, value);
    }

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
    public Object remove(Object key)  throws NullPointerException{
        Object value = table.remove(key); //null if not removed, valid otherwise
            
        return value;
    }

    // BULK OPERATIONS

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
    public void putAll(HMap t) throws NullPointerException{
        if (t == null)
            throw new NullPointerException();
        
        if (t.isEmpty()) return;

        HSet ks = t.entrySet();
        HIterator i = ks.iterator();
        while(i.hasNext()){
            HEntry me = (HEntry) i.next();
			table.put(me.getKey(),me.getValue());
		}
    }

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    public void clear(){
        table.clear();
    }

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
    public HSet keySet(){
        return new SubKeySetAdapter(table);
    }

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
    public HCollection values(){
        return new SubValuesCollectionAdapter(table);
    }

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
    public HSet entrySet(){
        return new SubEntrySetAdapter(table);
    }

    // COMPARISION AND HASHING
    
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
    public boolean equals(Object o){
        if (!(o instanceof HMap))
            return false;

        HMap map = (HMap) o;

        if(this.isEmpty() && map.isEmpty())
            return true;

        if (map.size() != this.size())
            return false;

		HSet s1 = map.entrySet();
		HSet s2 = entrySet();
        
		return s1.equals(s2);
    }

    /**
     * Returns the hash code value for this map.  The hash code of a map is
     * defined to be the sum of the hash codes of each entry in the map's
     * {@code entrySet()} view.  This ensures that {@code m1.equals(m2)}
     * implies that {@code m1.hashCode()==m2.hashCode()} for any two maps
     * {@code m1} and {@code m2}, as required by the general contract of
     * {@link Object#hashCode}.
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

    /**
     *  SubEntrySetAdapter is a private class used in order to give to Map's entrySet() method
     *  a {@link HSet} view. 
     * 
     *  @version 1.0
     *  @author Giacomo Calabria
     *  @see {@link HSet}
     *  @see {@link HMap.entrySet()}
     */

    private class SubEntrySetAdapter implements HSet{
        private Hashtable table;

        public SubEntrySetAdapter(Hashtable table){
            this.table = table;
        }

        /**
         * Returns the number of elements in this set (its cardinality).  If this
         * set contains more than {@code Integer.MAX_VALUE} elements, returns
         * {@code Integer.MAX_VALUE}.
         *
         * @return the number of elements in this set (its cardinality)
         */
        public int size() {
            return table.size();
        }

        /**
         * Returns {@code true} if this set contains no elements.
         *
         * @return {@code true} if this set contains no elements
         */
        public boolean isEmpty() {
            return table.isEmpty();
        }
    
        /**
         * Returns {@code true} if this set contains the specified element.
         * More formally, returns {@code true} if and only if this set
         * contains an element {@code e} such that
         * {@code Objects.equals(o, e)}.
         *
         * @param o element whose presence in this set is to be tested
         * @return {@code true} if this set contains the specified element
         * @throws NullPointerException if the specified element is null
         */
        public boolean contains(Object obj){
            if(obj == null)
                throw new NullPointerException();

            if(! (obj instanceof HEntry)){
                return false;
            }

            HEntry em = (HEntry) obj;

            if(!(table.containsKey(em.getKey()) && table.contains(em.getValue())))
                return false;
            
            if(!table.get(em.getKey()).equals(em.getValue()))
                return false;
            
            return true;
        }
    
        /**
         * Returns an iterator over the elements in this set.  There are no
         * guarantees concerning the order in which the elements are returned
         * (unless this set is an instance of some class that provides a
         * guarantee).
         *
         * @return an {@code HIterator} over the elements in this set
         */
        public HIterator iterator() {
            return new SubEntrySetAdapterIterator(table);
        }
        
        /**
         * Returns an array containing all of the elements in this set. If the
         * set makes any guarantees as to what order its elements are returned by
         * its iterator, this method must return the elements in the same order.
         * <p>
         *
         * The returned array will be "safe" in that no references to it are maintained
         * by this set. (In other words, this method must allocate a new array
         * even if this set is backed by an array). The caller is thus free to
         * modify the returned array.
         * <p>
         *
         * This method acts as bridge between array-based and set-based APIs.
         *
         * @return an array containing all of the elements in this set
         */
        public Object[] toArray() {
            Object[] arr = new Object[size()];
            int i = 0;
            for (Enumeration e = table.keys() ; e.hasMoreElements() ;i++){
                Object key = e.nextElement();

                MapEntryAdapter me = new MapEntryAdapter(key);
                me.setValue(table.get(key));

                arr[i] = me;
            }
            return arr;
        }
    
        /**
         * Returns an array containing all of the elements in this set; the
         * runtime type of the returned array is that of the specified array. If the
         * set fits in the specified array, it is returned therein. Otherwise, a
         * new array is allocated with the runtime type of the specified array and the
         * size of this collection.
         * <p>
         *
         * If this collection fits in the specified array with room to spare (i.e., the
         * array has more elements than this collection), the element in the array
         * immediately following the end of the collection is set to null. This
         * is useful in determining the length of this collection <i>only</i> if the
         * caller knows that this collection does not contain any null
         * elements.)
         * <p>
         *
         * If this collection makes any guarantees as to what order its elements are
         * returned by its iterator, this method must return the elements in the same
         * order.
         * <p>
         *
         * Like the toArray method, this method acts as bridge between
         * array-based and collection-based APIs. Further, this method allows precise
         * control over the runtime type of the output array, and may, under certain
         * circumstances, be used to save allocation costs
         * <p>
         *
         * Suppose l is a List known to contain only strings. The
         * following code can be used to dump the list into a newly allocated array of
         * String:
         *
         * <pre>
         * String[] x = (String[]) v.toArray(new String[0]);
         * </pre>
         * <p>
         *
         * <p>Note that {@code toArray(new Object[0])} is identical in function to
         * {@code toArray()}.
         *
         * @param arrayTarget the array into which the elements of this collection are to be
         *                      stored, if it is big enough; otherwise, a new array of the same
         *                      runtime type is allocated for this purpose.
         * @return an array containing all of the elements in this collection
         *
         * @throws NullPointerException if the specified array is null.
         * @throws IllegalArgumentException if the specified array's lenght
         *          is less then this set lenght
         */
        public Object[] toArray(Object[] arrayTarget) {
            if(arrayTarget == null)
                throw new NullPointerException();
            if (arrayTarget.length < this.size())
                throw new IllegalArgumentException();
            
            int i = 0;
            for (Enumeration e = table.keys() ; e.hasMoreElements() ; i++){
                Object key = e.nextElement();

                MapEntryAdapter me = new MapEntryAdapter(key);
                me.setValue(table.get(key));

                arrayTarget[i] = me;
            }
            return arrayTarget;
        }

        /**
         * This method is unsupported for entrySet()
         * @throws UnsupportedOperationException as the operation is not supported
         */
    
        public boolean add(Object obj) throws UnsupportedOperationException{
            throw new UnsupportedOperationException();
        }

        /**
         * This method is unsupported for entrySet()
         * @throws UnsupportedOperationException as the operation is not supported
         */
        public boolean addAll(HCollection coll) throws UnsupportedOperationException{
            throw new UnsupportedOperationException();
        }

    
        /**
         * Removes a single instance of the specified element from this
         * set, if it is present (optional operation).  More formally,
         * removes an element {@code e} such that
         * {@code Objects.equals(o, e)}, if
         * this set contains one or more such elements.  Returns
         * {@code true} if this set contained the specified element (or
         * equivalently, if this set changed as a result of the call).
         *
         * @param o element to be removed from this set, if present
         * @return {@code true} if an element was removed as a result of this call
         * @throws NullPointerException if the specified object is {@code null}
         */
        public boolean remove(Object obj){
            if(obj == null)
                throw new NullPointerException();

            if(! (obj instanceof HEntry)){
                return false;
            }

            HEntry em = (HEntry) obj;

            if (table.remove(em.getKey()) == null)
                return false;
            return true;
        }
    
        /**
         * Returns {@code true} if this set contains all of the elements
         * in the specified collection.
         *
         * @param  c collection to be checked for containment in this set
         * @return {@code true} if this set contains all of the elements
         *         in the specified collection
         * @throws NullPointerException if the specified collection is null.
         * @see    #contains(Object)
         */
        public boolean containsAll(HCollection coll) {
            if(coll == null)
                throw new NullPointerException();

            HIterator i = coll.iterator();
            while(i.hasNext()){
                Object tmp = i.next();
                if(!table.contains(tmp))
                    return false;
            }
            return true;
        }
    
        /**
         * Removes all of this set's elements that are also contained in the
         * specified collection (optional operation).  After this call returns,
         * this set will contain no elements in common with the specified
         * collection.
         *
         * @param c collection containing elements to be removed from this set
         * @return {@code true} if this set changed as a result of the
         *         call
         *
         * @throws NullPointerException if the specified collection is null.
         * @see #remove(Object)
         * @see #contains(Object)
         */
        public boolean removeAll(HCollection coll) {
            if(coll == null)
                throw new NullPointerException();

            HIterator i = coll.iterator();
            boolean hasRemOne = false;
            while(i.hasNext()){
                Object el = i.next();
                if(remove(el))
                    hasRemOne = true;
            }
            return hasRemOne;
        }
        /**
         * Retains only the elements in this set that are contained in the
         * specified collection (optional operation).  In other words, removes from
         * this set all of its elements that are not contained in the
         * specified collection.
         *
         * @param c collection containing elements to be retained in this set
         * @return {@code true} if this set changed as a result of the call
         *
         * @throws NullPointerException if the specified collection is null.
         * @see #remove(Object)
         * @see #contains(Object)
         */
    
        public boolean retainAll(HCollection coll) {
            if(coll == null)
                throw new NullPointerException();

            boolean hasRetOne = false;
            HIterator i = this.iterator();
            while(i.hasNext()){
                Object tmp = i.next();
                if(! coll.contains(tmp)){
                    i.remove(); //remove(tmp);
                    hasRetOne = true;
                }
            }
            return hasRetOne;
        }
    
        /**
         * Removes all of the elements from this set.
         * The set will be empty after this method returns.
         *
         */
        public void clear(){
            table.clear();
        }

        /**
         * Compares the specified object with this set for equality. <p>
         *
         * While the {@code Collection} interface adds no stipulations to the
         * general contract for the {@code Object.equals}, programmers who
         * implement the {@code Collection} interface "directly" (in other words,
         * create a class that is a {@code Collection} but is not a {@code Set}
         * or a {@code List}) must exercise care if they choose to override the
         * {@code Object.equals}.  It is not necessary to do so, and the simplest
         * course of action is to rely on {@code Object}'s implementation, but
         * the implementor may wish to implement a "value comparison" in place of
         * the default "reference comparison."  (The {@code List} and
         * {@code Set} interfaces mandate such value comparisons.)<p>
         *
         * The general contract for the {@code Object.equals} method states that
         * equals must be symmetric (in other words, {@code a.equals(b)} if and
         * only if {@code b.equals(a)}).  The contracts for {@code List.equals}
         * and {@code Set.equals} state that lists are only equal to other lists,
         * and sets to other sets.  Thus, a custom {@code equals} method for a
         * set class that implements neither the {@code List} nor
         * {@code Set} interface must return {@code false} when this set
         * is compared to any list or set.  (By the same logic, it is not possible
         * to write a class that correctly implements both the {@code Set} and
         * {@code List} interfaces.)
         *
         * @param o object to be compared for equality with this set
         * @return {@code true} if the specified object is equal to this
         * set
         *
         * @see Object#equals(Object)
         * @see Set#equals(Object)
         * @see List#equals(Object)
         */
        public boolean equals(Object o){
            if(! (o instanceof SubEntrySetAdapter)){
                return false;
            }
            SubEntrySetAdapter sesa = (SubEntrySetAdapter) o;

            if(this.size() != sesa.size())
                return false;

            HIterator thisIterator = this.iterator();
            HIterator sesaIterator = sesa.iterator();

            while(thisIterator.hasNext() && sesaIterator.hasNext()){
                if(!thisIterator.next().equals(sesaIterator.next()))
                    return false;
            }

            return true;
        }

        /**
         * Returns the hash code value for this cset.  While the
         * {@code HSet} interface adds no stipulations to the general
         * contract for the {@code Object.hashCode} method, programmers should
         * take note that any class that overrides the {@code Object.equals}
         * method must also override the {@code Object.hashCode} method in order
         * to satisfy the general contract for the {@code Object.hashCode} method.
         * In particular, {@code c1.equals(c2)} implies that
         * {@code c1.hashCode()==c2.hashCode()}.
         *
         * @return the hash code value for this set
         *
         * @see Object#hashCode()
         * @see Object#equals(Object)
         */
        public int hashCode(){
            return table.hashCode();
        }

        @Override
        public String toString(){
            String str = "[";
            HIterator iter = iterator();
            while (iter.hasNext()){
                str += iter.next();
                if (iter.hasNext())
                    str += ", ";
            }
            str += "]";
            return str;
        }

        private class SubEntrySetAdapterIterator implements HIterator{
            Enumeration k;
    
            boolean next = false;
    
            Object keyToBeRemoved = null;
    
            public SubEntrySetAdapterIterator(Hashtable table){
                k = table.keys();
            }
    
    
            /**
             * Returns {@code true} if the iteration has more elements.
             * (In other words, returns {@code true} if {@link #next} would
             * return an element rather than throwing an exception.)
             *
             * @return {@code true} if the iteration has more elements
             */
            public boolean hasNext() {
                return k.hasMoreElements();
            }
    
            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration
             * @throws NoSuchElementException if the iteration has no more elements
             */
            public Object next() {
                Object key = k.nextElement();
                next = true;
    
                HEntry em = new MapEntryAdapter(key);
                em.setValue(table.get(key));
    
                keyToBeRemoved = key;
                return em;
            }
    
            /**
             * Removes from the underlying set the last element returned
             * by this iterator (optional operation).  This method can be called
             * only once per call to {@link #next}.
             * <p>
             * The behavior of an iterator is unspecified if the underlying set
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
                table.remove(keyToBeRemoved);
            }
        }
    }

    /**
     *  SubValuesCollectionAdapter is a private class used in order to give to Map's values() 
     *  method a {@link HCollection} view. 
     * 
     *  @version 1.0
     *  @author Giacomo Calabria
     *  @see {@link HCollection}
     *  @see {@link HMap.values()}
     */

    private class SubValuesCollectionAdapter implements HCollection{
        private Hashtable table;

        public SubValuesCollectionAdapter(Hashtable table){
            this.table = table;
        }

        /**
         * Returns the number of elements in this collection.  If this collection
         * contains more than {@code Integer.MAX_VALUE} elements, returns
         * {@code Integer.MAX_VALUE}.
         *
         * @return the number of elements in this collection
         */
        public int size() {
            return table.size();
        }

        /**
         * Returns {@code true} if this collection contains no elements.
         *
         * @return {@code true} if this collection contains no elements
         */
        public boolean isEmpty() {
            return table.isEmpty();
        }
    
        /**
         * Returns {@code true} if this collection contains the specified element.
         * More formally, returns {@code true} if and only if this collection
         * contains at least one element {@code e} such that
         * {@code Objects.equals(o, e)}.
         *
         * @param o element whose presence in this collection is to be tested
         * @return {@code true} if this collection contains the specified
         *         element
         * @throws NullPointerException if the specified object is null
         */
        public boolean contains(Object obj){
            if(obj == null)
                throw new NullPointerException();
            return table.contains(obj);
        }

        /**
         * Returns an iterator over the elements in this collection.  There are no
         * guarantees concerning the order in which the elements are returned
         * (unless this collection is an instance of some class that provides a
         * guarantee).
         *
         * @return an {@code HIterator} over the elements in this collection
         */
        public HIterator iterator() {
            return new SubValuesCollectionAdapterIterator(table);
        }
    
        /**
         * Returns an array containing all of the elements in this collection. If the
         * collection makes any guarantees as to what order its elements are returned by
         * its iterator, this method must return the elements in the same order.
         * <p>
         *
         * The returned array will be "safe" in that no references to it are maintained
         * by this collection. (In other words, this method must allocate a new array
         * even if this collection is backed by an array). The caller is thus free to
         * modify the returned array.
         * <p>
         *
         * This method acts as bridge between array-based and collection-based APIs.
         *
         * @return an array containing all of the elements in this collection
         */
        public Object[] toArray() {
            Object[] arr = new Object[size()];
            int i = 0;
            for (Enumeration e = table.elements() ; e.hasMoreElements() ; i++){
                arr[i] = e.nextElement();
            }
            return arr;
        }

        /**
         * Returns an array containing all of the elements in this collection; the
         * runtime type of the returned array is that of the specified array. If the
         * collection fits in the specified array, it is returned therein. Otherwise, a
         * new array is allocated with the runtime type of the specified array and the
         * size of this collection.
         * <p>
         *
         * If this collection fits in the specified array with room to spare (i.e., the
         * array has more elements than this collection), the element in the array
         * immediately following the end of the collection is set to null. This
         * is useful in determining the length of this collection <i>only</i> if the
         * caller knows that this collection does not contain any null
         * elements.)
         * <p>
         *
         * If this collection makes any guarantees as to what order its elements are
         * returned by its iterator, this method must return the elements in the same
         * order.
         * <p>
         *
         * Like the toArray method, this method acts as bridge between
         * array-based and collection-based APIs. Further, this method allows precise
         * control over the runtime type of the output array, and may, under certain
         * circumstances, be used to save allocation costs
         * <p>
         *
         * Suppose l is a List known to contain only strings. The
         * following code can be used to dump the list into a newly allocated array of
         * String:
         *
         * <pre>
         * String[] x = (String[]) v.toArray(new String[0]);
         * </pre>
         * <p>
         *
         * <p>Note that {@code toArray(new Object[0])} is identical in function to
         * {@code toArray()}.
         *
         * @param arrayTarget the array into which the elements of this collection are to be
         *                      stored, if it is big enough; otherwise, a new array of the same
         *                      runtime type is allocated for this purpose.
         * @return an array containing all of the elements in this collection
         *
         * @throws NullPointerException if the specified array is null.
         * @throws IllegalArgumentException if the specified array's lenght
         *          is less then this collection lenght
         */
        public Object[] toArray(Object[] arrayTarget) {
            if(arrayTarget == null)
                throw new NullPointerException();
            if (arrayTarget.length < size())
                throw new IllegalArgumentException();

            int i = 0;
            for (Enumeration e = table.elements() ; e.hasMoreElements() ; i++){
                arrayTarget[i] = e.nextElement();
            }
            return arrayTarget;
        }

        /**
         * This method is unsupported
         * @throws UnsupportedOperationException as the operation is not supported
         */
    
        public boolean add(Object obj) throws UnsupportedOperationException{
            throw new UnsupportedOperationException();
        }
        
        /**
         * This method is unsupported
         * @throws UnsupportedOperationException as the operation is not supported
         */
        public boolean addAll(HCollection coll) throws UnsupportedOperationException{
            throw new UnsupportedOperationException();
        }
    
        /**
         * Removes a single instance of the specified element from this collection, if
         * it is present (optional operation). More formally, removes an element e such
         * that (o==null ? e==null : o.equals(e)), if this collection contains one or
         * more such elements. Returns true if this collection contained the specified
         * element (or equivalently, if this collection changed as a result of the
         * call).
         *
         * @param obj element to be removed from this collection, if present.
         * @return true if this collection changed as a result of the call
         * @throws NullPointerException if the specified object is null.
         */
        public boolean remove(Object obj){
            if(obj == null)
                throw new NullPointerException();

            for (Enumeration keys = table.keys(); keys.hasMoreElements();){
                Object key = keys.nextElement();
                if (table.get(key).equals(obj)){
                    table.remove(key);
                    return true;
                }
            }
            return false;
        }
    
        /**
         * Returns {@code true} if this collection contains all of the elements
         * in the specified collection.
         *
         * @param  c collection to be checked for containment in this collection
         * @return {@code true} if this collection contains all of the elements
         *         in the specified collection
         * @throws NullPointerException if the specified collection is null.
         * @see    #contains(Object)
         */
        public boolean containsAll(HCollection coll) {
            if(coll == null)
                throw new NullPointerException();

            HIterator i = coll.iterator();
            while(i.hasNext()){
                Object tmp = i.next();
                if(!table.contains(tmp))
                    return false;
            }
            return true;
        }
        
  
        /**
         * Removes all of this collection's elements that are also contained in the
         * specified collection (optional operation).  After this call returns,
         * this collection will contain no elements in common with the specified
         * collection.
         *
         * @param c collection containing elements to be removed from this collection
         * @return {@code true} if this collection changed as a result of the
         *         call
         *
         * @throws NullPointerException if the specified collection is null.
         * @see #remove(Object)
         * @see #contains(Object)
         */
        public boolean removeAll(HCollection coll) {
            if(coll == null)
                throw new NullPointerException();

            HIterator i = coll.iterator();
            boolean hasRemOne = false;
            while(i.hasNext()){
                Object el = i.next();
                if(remove(el))
                    hasRemOne = true;
            }
            return hasRemOne;                    
        }
    
        /**
         * Retains only the elements in this collection that are contained in the
         * specified collection (optional operation).  In other words, removes from
         * this collection all of its elements that are not contained in the
         * specified collection.
         *
         * @param c collection containing elements to be retained in this collection
         * @return {@code true} if this collection changed as a result of the call
         *
         * @throws NullPointerException if the specified collection is null.
         * @see #remove(Object)
         * @see #contains(Object)
         */
        public boolean retainAll(HCollection coll) {
            if(coll == null)
                throw new NullPointerException();

            boolean hasRetOne = false;
            HIterator i = this.iterator();
            while(i.hasNext()){
                Object tmp = i.next();
                if(! coll.contains(tmp)){
                    i.remove(); //remove(tmp);
                    hasRetOne = true;
                }
            }
            return hasRetOne;
        }
    
        /**
         * Removes all of the elements from this collection.
         * The collection will be empty after this method returns.
         *
         */
        public void clear(){
            table.clear();
        }

        /**
         * Compares the specified object with this collection for equality. <p>
         *
         * While the {@code Collection} interface adds no stipulations to the
         * general contract for the {@code Object.equals}, programmers who
         * implement the {@code Collection} interface "directly" (in other words,
         * create a class that is a {@code Collection} but is not a {@code Set}
         * or a {@code List}) must exercise care if they choose to override the
         * {@code Object.equals}.  It is not necessary to do so, and the simplest
         * course of action is to rely on {@code Object}'s implementation, but
         * the implementor may wish to implement a "value comparison" in place of
         * the default "reference comparison."  (The {@code List} and
         * {@code Set} interfaces mandate such value comparisons.)<p>
         *
         * The general contract for the {@code Object.equals} method states that
         * equals must be symmetric (in other words, {@code a.equals(b)} if and
         * only if {@code b.equals(a)}).  The contracts for {@code List.equals}
         * and {@code Set.equals} state that lists are only equal to other lists,
         * and sets to other sets.  Thus, a custom {@code equals} method for a
         * collection class that implements neither the {@code List} nor
         * {@code Set} interface must return {@code false} when this collection
         * is compared to any list or set.  (By the same logic, it is not possible
         * to write a class that correctly implements both the {@code Set} and
         * {@code List} interfaces.)
         *
         * @param o object to be compared for equality with this collection
         * @return {@code true} if the specified object is equal to this
         * collection
         *
         * @see Object#equals(Object)
         * @see Set#equals(Object)
         * @see List#equals(Object)
         */
        public boolean equals(Object o){
            if(! (o instanceof SubValuesCollectionAdapter)){
                return false;
            }
            SubValuesCollectionAdapter sesa = (SubValuesCollectionAdapter) o;

            if(this.size() != sesa.size())
                return false;

            HIterator thisIterator = this.iterator();
            HIterator sesaIterator = sesa.iterator();

            while(thisIterator.hasNext() && sesaIterator.hasNext()){
                if(!thisIterator.next().equals(sesaIterator.next()))
                    return false;
            }
            
            return true;
        }

        /**
         * Returns the hash code value for this collection.  While the
         * {@code HCollection} interface adds no stipulations to the general
         * contract for the {@code Object.hashCode} method, programmers should
         * take note that any class that overrides the {@code Object.equals}
         * method must also override the {@code Object.hashCode} method in order
         * to satisfy the general contract for the {@code Object.hashCode} method.
         * In particular, {@code c1.equals(c2)} implies that
         * {@code c1.hashCode()==c2.hashCode()}.
         *
         * @return the hash code value for this collection
         *
         * @see Object#hashCode()
         * @see Object#equals(Object)
         */
        public int hashCode(){
            return table.hashCode();
        }

        @Override
        public String toString(){
            String str = "[";
            HIterator iter = iterator();
            while (iter.hasNext()){
                str += iter.next();
                if (iter.hasNext())
                    str += ", ";
            }
            str += "]";
            return str;
        }

        private class SubValuesCollectionAdapterIterator implements HIterator{
        
            Enumeration e;
            Enumeration k;
        
            boolean next = false;
    
            Object keyToBeRemoved = null;
    
            public SubValuesCollectionAdapterIterator(Hashtable table){
                e = table.elements();
                k = table.keys();
            }
    
    
            /**
             * Returns {@code true} if the iteration has more elements.
             * (In other words, returns {@code true} if {@link #next} would
             * return an element rather than throwing an exception.)
             *
             * @return {@code true} if the iteration has more elements
             */
            public boolean hasNext() {
                return e.hasMoreElements() && k.hasMoreElements();
            }
    
            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration
             * @throws NoSuchElementException if the iteration has no more elements
             */
            public Object next() {
                keyToBeRemoved = k.nextElement();
                next = true;
                return e.nextElement();
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
                table.remove(keyToBeRemoved);
            }
        }
    }

    /**
     *  SubKeySetAdapter is a private class used in order to give to Map's keySet() method
     *  a {@link HSet} view. 
     * 
     *  @version 1.0
     *  @author Giacomo Calabria
     *  @see {@link HSet}
     *  @see {@link HMap.keySet()}
     */

    private class SubKeySetAdapter implements HSet{
        private Hashtable table;

        public SubKeySetAdapter(Hashtable table){
            this.table = table;
        }

        /**
         * Returns the number of elements in this set (its cardinality).  If this
         * set contains more than {@code Integer.MAX_VALUE} elements, returns
         * {@code Integer.MAX_VALUE}.
         *
         * @return the number of elements in this set (its cardinality)
         */

        public int size() {
            return table.size();
        }

        /**
         * Returns {@code true} if this set contains no elements.
         *
         * @return {@code true} if this set contains no elements
         */
        public boolean isEmpty() {
            return table.isEmpty();
        }

        /**
         * Returns {@code true} if this set contains the specified element.
         * More formally, returns {@code true} if and only if this set
         * contains an element {@code e} such that
         * {@code Objects.equals(o, e)}.
         *
         * @param o element whose presence in this set is to be tested
         * @return {@code true} if this set contains the specified element
         * @throws NullPointerException if the specified element is null
         */
    
        public boolean contains(Object obj){
            if(obj == null)
                throw new NullPointerException();
            
            return table.containsKey(obj);
        }

        /**
         * Returns an iterator over the elements in this set.  There are no
         * guarantees concerning the order in which the elements are returned
         * (unless this set is an instance of some class that provides a
         * guarantee).
         *
         * @return an {@code HIterator} over the elements in this set
         */
    
        public HIterator iterator() {
            return new SubKeySetAdapterIterator(table);
        }

        /**
         * Returns an array containing all of the elements in this set. If the
         * set makes any guarantees as to what order its elements are returned by
         * its iterator, this method must return the elements in the same order.
         * <p>
         *
         * The returned array will be "safe" in that no references to it are maintained
         * by this set. (In other words, this method must allocate a new array
         * even if this set is backed by an array). The caller is thus free to
         * modify the returned array.
         * <p>
         *
         * This method acts as bridge between array-based and set-based APIs.
         *
         * @return an array containing all of the elements in this set
         */
    
        public Object[] toArray() {
            Object[] arr = new Object[size()];
            int i = 0;
            for (Enumeration e = table.keys() ; e.hasMoreElements() ;i++){
                arr[i] = e.nextElement();
            }
            return arr;
        }

        /**
         * Returns an array containing all of the elements in this set; the
         * runtime type of the returned array is that of the specified array. If the
         * set fits in the specified array, it is returned therein. Otherwise, a
         * new array is allocated with the runtime type of the specified array and the
         * size of this set.
         * <p>
         *
         * If this set fits in the specified array with room to spare (i.e., the
         * array has more elements than this set), the element in the array
         * immediately following the end of the collection is set to null. This
         * is useful in determining the length of this collection <i>only</i> if the
         * caller knows that this collection does not contain any null
         * elements.)
         * <p>  
         *
         * If this collection makes any guarantees as to what order its elements are
         * returned by its iterator, this method must return the elements in the same
         * order.
         * <p>
         *
         * Like the toArray method, this method acts as bridge between
         * array-based and collection-based APIs. Further, this method allows precise
         * control over the runtime type of the output array, and may, under certain
         * circumstances, be used to save allocation costs
         * <p>
         *
         * Suppose l is a List known to contain only strings. The
         * following code can be used to dump the list into a newly allocated array of
         * String:
         *
         * <pre>
         * String[] x = (String[]) v.toArray(new String[0]);
         * </pre>
         * <p>
         *
         * <p>Note that {@code toArray(new Object[0])} is identical in function to
         * {@code toArray()}.
         *
         * @param arrayTarget the array into which the elements of this collection are to be
         *                      stored, if it is big enough; otherwise, a new array of the same
         *                      runtime type is allocated for this purpose.
         * @return an array containing all of the elements in this collection
         *
         * @throws NullPointerException if the specified array is null.
         * @throws IllegalArgumentException if the specified array's lenght
         *          is less then this set lenght
         */
    
        public Object[] toArray(Object[] arrayTarget) {
            if(arrayTarget == null)
                throw new NullPointerException();
            if (arrayTarget.length < size())
                throw new IllegalArgumentException();
                
            int i = 0;
            for (Enumeration e = table.keys() ; e.hasMoreElements() ; i++){
                arrayTarget[i] = e.nextElement();
            }
            return arrayTarget;
        }

        /**
         * This method is unsupported
         * @throws UnsupportedOperationException as the operation is not supported
         */
    
        public boolean add(Object obj) throws UnsupportedOperationException{
            throw new UnsupportedOperationException();
        }
        
        /**
         * This method is unsupported
         * @throws UnsupportedOperationException as the operation is not supported
         */
        public boolean addAll(HCollection coll) throws UnsupportedOperationException{
            throw new UnsupportedOperationException();
        }
    
        /**
         * Removes a single instance of the specified element from this
         * set, if it is present (optional operation).  More formally,
         * removes an element {@code e} such that
         * {@code Objects.equals(o, e)}, if
         * this set contains one or more such elements.  Returns
         * {@code true} if this set contained the specified element (or
         * equivalently, if this set changed as a result of the call).
         *
         * @param o element to be removed from this set, if present
         * @return {@code true} if an element was removed as a result of this call
         * @throws NullPointerException if the specified object is null.
         */
        public boolean remove(Object obj){
            if(obj == null)
                throw new NullPointerException();

            if (table.remove(obj) == null)
                return false;
            return true;
        }

        /**
         * Returns {@code true} if this set contains all of the elements
         * in the specified collection.
         *
         * @param  c collection to be checked for containment in this set
         * @return {@code true} if this set contains all of the elements
         *         in the specified collection
         * @throws NullPointerException if the specified collection is null.
         * @see    #contains(Object)
         */
        public boolean containsAll(HCollection coll) {
            if(coll == null)
                throw new NullPointerException();

            HIterator i = coll.iterator();
            while(i.hasNext()){
                Object tmp = i.next();
                if(!table.contains(tmp))
                    return false;
            }
            return true;
        }

        

        /**
         * Removes all of this set's elements that are also contained in the
         * specified collection (optional operation).  After this call returns,
         * this set will contain no elements in common with the specified
         * collection.
         *
         * @param c collection containing elements to be removed from this set
         * @return {@code true} if this set changed as a result of the
         *         call
         *
         * @throws NullPointerException if the specified collection is null.
         * @see #remove(Object)
         * @see #contains(Object)
         */
        public boolean removeAll(HCollection coll){
            if(coll == null)
                throw new NullPointerException();

            HIterator i = coll.iterator();
            boolean hasRemOne = false;
            while(i.hasNext()){
                Object el = i.next();
                if(remove(el))
                    hasRemOne = true;
            }
            return hasRemOne;
        }

        /**
         * Retains only the elements in this set that are contained in the
         * specified collection (optional operation).  In other words, removes from
         * this set all of its elements that are not contained in the
         * specified collection.
         *
         * @param c collection containing elements to be retained in this set
         * @return {@code true} if this set changed as a result of the call
         *
         * @throws NullPointerException if the specified collection is null.
         * @see #remove(Object)
         * @see #contains(Object)
         */
    
        public boolean retainAll(HCollection coll) {
            if(coll == null)
                throw new NullPointerException();

            boolean hasRetOne = false;
            HIterator i = this.iterator();
            while(i.hasNext()){
                Object tmp = i.next();
                if(! coll.contains(tmp)){
                    i.remove(); //remove(tmp);
                    hasRetOne = true;
                }
            }
            return hasRetOne;
        }

        /**
         * Removes all of the elements from this set.
         * The set will be empty after this method returns.
         *
         */
        public void clear(){
            table.clear();
        }

        /**
         * Compares the specified object with this set for equality. <p>
         *
         * While the {@code Collection} interface adds no stipulations to the
         * general contract for the {@code Object.equals}, programmers who
         * implement the {@code Collection} interface "directly" (in other words,
         * create a class that is a {@code Collection} but is not a {@code Set}
         * or a {@code List}) must exercise care if they choose to override the
         * {@code Object.equals}.  It is not necessary to do so, and the simplest
         * course of action is to rely on {@code Object}'s implementation, but
         * the implementor may wish to implement a "value comparison" in place of
         * the default "reference comparison."  (The {@code List} and
         * {@code Set} interfaces mandate such value comparisons.)<p>
         *
         * The general contract for the {@code Object.equals} method states that
         * equals must be symmetric (in other words, {@code a.equals(b)} if and
         * only if {@code b.equals(a)}).  The contracts for {@code List.equals}
         * and {@code Set.equals} state that lists are only equal to other lists,
         * and sets to other sets.  Thus, a custom {@code equals} method for a
         * collection class that implements neither the {@code List} nor
         * {@code Set} interface must return {@code false} when this collection
         * is compared to any list or set.  (By the same logic, it is not possible
         * to write a class that correctly implements both the {@code Set} and
         * {@code List} interfaces.)
         *
         * @param o object to be compared for equality with this set
         * @return {@code true} if the specified object is equal to this
         * set
         *
         * @see Object#equals(Object)
         * @see Set#equals(Object)
         * @see List#equals(Object)
         */
    
        public boolean equals(Object o){
            if(! (o instanceof SubKeySetAdapter)){
                return false;
            }
            SubKeySetAdapter sesa = (SubKeySetAdapter) o;

            if(this.size() != sesa.size())
                return false;

            HIterator thisIterator = this.iterator();
            HIterator sesaIterator = sesa.iterator();

            while(thisIterator.hasNext() && sesaIterator.hasNext()){
                if(!thisIterator.next().equals(sesaIterator.next()))
                    return false;
            }
            
            return true;
        }

        /**
         * Returns the hash code value for this set.  While the
         * {@code HSet} interface adds no stipulations to the general
         * contract for the {@code Object.hashCode} method, programmers should
         * take note that any class that overrides the {@code Object.equals}
         * method must also override the {@code Object.hashCode} method in order
         * to satisfy the general contract for the {@code Object.hashCode} method.
         * In particular, {@code c1.equals(c2)} implies that
         * {@code c1.hashCode()==c2.hashCode()}.
         *
         * @return the hash code value for this set
         *
         * @see Object#hashCode()
         * @see Object#equals(Object)
         */
        public int hashCode(){
            return table.hashCode();
        }

        @Override
        public String toString(){
            String str = "[";
            HIterator iter = iterator();
            while (iter.hasNext()){
                str += iter.next();
                if (iter.hasNext())
                    str += ", ";
            }
            str += "]";
            return str;
        }

        private class SubKeySetAdapterIterator implements HIterator{
        
            Enumeration e;
    
            boolean next = false;
    
            Object keyToBeRemoved = null;
    
            public SubKeySetAdapterIterator(Hashtable table){
                e = table.keys();
            }
    
            /**
             * Returns {@code true} if the iteration has more elements.
             * (In other words, returns {@code true} if {@link #next} would
             * return an element rather than throwing an exception.)
             *
             * @return {@code true} if the iteration has more elements
             */
            public boolean hasNext() {
                return e.hasMoreElements();
            }
    
            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration
             * @throws NoSuchElementException if the iteration has no more elements
             */
            public Object next() {
                next = true;
                keyToBeRemoved = e.nextElement();
                return keyToBeRemoved;
            }
    
            /**
             * Removes from the underlying set the last element returned
             * by this iterator (optional operation).  This method can be called
             * only once per call to {@link #next}.
             * <p>
             * The behavior of an iterator is unspecified if the underlying set
             * is modified while the iteration is in progress in any way other than by
             * calling this method, unless an overriding class has specified a
             * concurrent modification policy.
             *
             */
            public void remove() {
                if(!next){
                    throw new IllegalStateException();
                }
                table.remove(keyToBeRemoved);
                next = false;
            }
        }
    }


}
