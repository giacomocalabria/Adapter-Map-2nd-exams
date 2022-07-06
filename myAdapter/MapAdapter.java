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
        modificationCount ++;
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

        if(value == null)
            return null;

        modificationCount ++; // If this was modified increments modidification count
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
        
        //if (t.isEmpty()) return;

        HSet ks = t.entrySet();
        HIterator i = ks.iterator();
        while(i.hasNext()){
            HEntry me = (HEntry) i.next();
			table.put(me.getKey(),me.getValue());
		} 
        modificationCount ++;
    }

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    public void clear(){
        modificationCount ++;
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
        /*HSet ks = new SetAdapter();

        for (Enumeration e = table.keys() ; e.hasMoreElements() ;) {
            HEntry me = (HEntry) e.nextElement();
			Object key = me.getKey();
			ks.add(key);
        }

        return ks;*/

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
        /*HCollection vc = new CollectionAdapter();
        for (Enumeration e = table.elements() ; e.hasMoreElements() ;) {
            vc.add(((HEntry) e.nextElement()).getValue());;
        }
        return vc;*/

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
        /*HSet es = new SetAdapter();
        for (Enumeration e = table.keys() ; e.hasMoreElements() ;){
            Object tmp = e.nextElement();
            MapEntryAdapter me = new MapEntryAdapter(tmp);
            me.setValue(table.get(tmp));
            es.add(me);
        }
        return es;*/

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
     * @return the hash code value for this map
     *  @see equals(Object)
     *  @see Map.Entry.hashCode()
     *  @see Object.hashCode()
     *  @see Object.equals(Object)
     */
    public int hashCode(){
        return table.hashCode();
    }

    private class SubEntrySetAdapter implements HSet{
        private Hashtable table;

        public SubEntrySetAdapter(Hashtable table){
            this.table = table;
        }

        public int size() {
            return table.size();
        }

        public boolean isEmpty() {
            return table.isEmpty();
        }
    
        public boolean contains(Object obj){
            if(obj == null)
                throw new NullPointerException();

            if(! (obj instanceof HEntry)){
                return false;
            }

            HEntry em = (HEntry) obj;

            return (table.contains(em.getValue()) && table.containsKey(em.getKey()));
        }
    
        public HIterator iterator() {
            return new SubEntrySetAdapterIterator(table);
        }
    
        public Object[] toArray() {
            Object[] arr = new Object[size()];
            int i = 0;
            for (Enumeration e = table.keys() ; e.hasMoreElements() ;){
                Object key = e.nextElement();

                MapEntryAdapter me = new MapEntryAdapter(key);
                me.setValue(table.get(key));

                arr[i] = me;
                i++;
            }
            return arr;
        }
    
        public Object[] toArray(Object[] arrayTarget) {
            int i = 0;
            for (Enumeration e = table.keys() ; e.hasMoreElements() ;){
                Object key = e.nextElement();

                MapEntryAdapter me = new MapEntryAdapter(key);
                me.setValue(table.get(key));

                arrayTarget[i] = me;
                i++;
            }
            return arrayTarget;
        }
    
        public boolean add(Object obj) {
            return false;
        }
    
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
    
        public boolean containsAll(HCollection coll) {
            HIterator i = coll.iterator();
            while(i.hasNext()){
                Object tmp = i.next();
                if(!table.contains(tmp))
                    return false;
            }
            return true;
        }
    
        public boolean addAll(HCollection coll) {
            return false;
        }
    
        public boolean removeAll(HCollection coll) {
            HIterator i = coll.iterator();
            boolean hasRemAll = false;
            while(i.hasNext()){
                //hasRemAll = table.remove(i.next());
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
            table.clear();
        }
    
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
    
        public int hashCode(){
            return table.hashCode();
        }
    }

    private class SubValuesCollectionAdapter implements HCollection{
        private Hashtable table;

        public SubValuesCollectionAdapter(Hashtable table){
            this.table = table;
        }

        public int size() {
            return table.size();
        }

        public boolean isEmpty() {
            return table.isEmpty();
        }
    
        public boolean contains(Object obj){
            if(obj == null)
                throw new NullPointerException();
            return table.contains(obj);
        }
    
        public HIterator iterator() {
            return new SubValuesCollectionAdapterIterator(table);
        }
    
        public Object[] toArray() {
            Object[] arr = new Object[size()];
            int i = 0;
            for (Enumeration e = table.keys() ; e.hasMoreElements() ;){
                Object key = e.nextElement();
                arr[i] = table.get(key);
                i++;
            }
            return arr;
        }
    
        public Object[] toArray(Object[] arrayTarget) {
            if(arrayTarget == null)
                throw new NullPointerException();
            if (arrayTarget.length < size())
                throw new IllegalArgumentException();
            int i = 0;
            for (Enumeration e = table.keys() ; e.hasMoreElements() ;){
                Object key = e.nextElement();
                arrayTarget[i] = table.get(key);
                i++;
            }
            return arrayTarget;
        }
    
        public boolean add(Object obj) throws UnsupportedOperationException{
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
    
        public boolean containsAll(HCollection coll) {
            HIterator i = coll.iterator();
            while(i.hasNext()){
                Object tmp = i.next();
                if(!table.contains(tmp))
                    return false;
            }
            return true;
        }
    
        public boolean addAll(HCollection coll) throws UnsupportedOperationException{
            throw new UnsupportedOperationException();
        }
    
        public boolean removeAll(HCollection coll) {
            HIterator i = coll.iterator();
            boolean hasRemAll = false;
            while(i.hasNext()){
                if(table.remove(((HEntry) i.next()).getKey()) != null)
                    hasRemAll = true;
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
            table.clear();
        }

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
    
        public int hashCode(){
            return table.hashCode();
        }
    }

    private class SubKeySetAdapter implements HSet{
        private Hashtable table;

        public SubKeySetAdapter(Hashtable table){
            this.table = table;
        }

        public int size() {
            return table.size();
        }

        public boolean isEmpty() {
            return table.isEmpty();
        }
    
        public boolean contains(Object obj){
            if(obj == null)
                throw new NullPointerException();
            if(! (obj instanceof HEntry)){
                return false;
            }
            
            return table.containsKey(obj);
        }
    
        public HIterator iterator() {
            return new SubKeySetAdapterIterator(table);
        }
    
        public Object[] toArray() {
            Object[] arr = new Object[size()];
            int i = 0;
            for (Enumeration e = table.keys() ; e.hasMoreElements() ;){
                arr[i] = e.nextElement();
                i++;
            }
            return arr;
        }
    
        public Object[] toArray(Object[] arrayTarget) {
            int i = 0;
            for (Enumeration e = table.keys() ; e.hasMoreElements() ;){
                arrayTarget[i] = e.nextElement();
                i++;
            }
            return arrayTarget;
        }
    
        public boolean add(Object obj) {
            return false;
        }
    
        public boolean remove(Object obj){
            if(obj == null)
                throw new NullPointerException();

            if(! (obj instanceof HEntry)){
                return false;
            }

            if (table.remove(obj) == null)
                return false;

            return true;
        }
    
        public boolean containsAll(HCollection coll) {
            HIterator i = coll.iterator();
            while(i.hasNext()){
                Object tmp = i.next();
                if(!table.contains(tmp))
                    return false;
            }
            return true;
        }
    
        public boolean addAll(HCollection coll) {
            return false;
        }
    
        public boolean removeAll(HCollection coll) {
            HIterator i = coll.iterator();
            boolean hasRemAll = false;
            while(i.hasNext()){
                //hasRemAll = tabletable.removeElement(i.next());
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
            table.clear();
        }
    
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

        public int hashCode(){
            return table.hashCode();
        }
    }

    private class SubValuesCollectionAdapterIterator implements HIterator{
        
        Enumeration e;
        Enumeration k;

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
            k.nextElement();
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
            if(!hasNext()){
                throw new IllegalStateException();
            }
            e.nextElement();
            Object key = k.nextElement();
            table.remove(key);

            /*for(Enumeration k = table.keys(); k.hasMoreElements()){
                Object key = k.nextElement();
                if(table.get(key).equals(tmp))
                    table.remove(key);
                    return;
            }*/
        }
    }

    private class SubKeySetAdapterIterator implements HIterator{
        
        Enumeration e;

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
            if(!hasNext()){
                throw new IllegalStateException();
            }
            Object tmp = e.nextElement();

            table.remove(tmp);
        }
    }

    private class SubEntrySetAdapterIterator implements HIterator{
        
        Enumeration e;
        Enumeration k;

        public SubEntrySetAdapterIterator(Hashtable table){
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
            Object key = k.nextElement();
            Object value = e.nextElement();

            HEntry em = new MapEntryAdapter(key);
            em.setValue(value);

            return em;
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
            if(!hasNext()){
                throw new IllegalStateException();
            }

            e.nextElement();
            Object key = k.nextElement();

            table.remove(key);
        }
    }

}
