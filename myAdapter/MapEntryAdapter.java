package myAdapter;

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
 *  @see MapAdapter
 */
public class MapEntryAdapter implements HMap.HEntry{

    private Object key;
    private Object value = null;

    /**
     * Constructor with only key and value is default null
     * @param akey
     */

    public MapEntryAdapter(Object akey){
        this.key = akey;
    }

    /**
     * Constructor with key and value argument
     * @param akey - key to be assigned to the entry
     * @param avalue - value to be assigned to the entry
     */
    public MapEntryAdapter(Object akey, Object avalue){
        this.value = avalue;
        this.key = akey;
    }

    /**
     * Returns the value corresponding to this entry.  If the mapping
     * has been removed from the backing map (by the iterator's
     * {@code remove} operation), the results of this call are undefined.
     *
     * @return the value corresponding to this entry
     * 
     */
    public Object getValue(){
        return this.value;
    }

    /**
     * Returns the key corresponding to this entry.
     *
     * @return the key corresponding to this entry
     */
    public Object getKey(){
        return this.key;
    }

    /**
     * Replaces the value corresponding to this entry with the specified
     * value (optional operation).  (Writes through to the map.)  The
     * behavior of this call is undefined if the mapping has already been
     * removed from the map (by the iterator's {@code remove} operation).
     *
     * @param value new value to be stored in this entry
     * @return old value corresponding to the entry
     */

    public Object setValue(Object value){
        Object oldValue = getValue();
        this.value = value;
        return oldValue;
    }

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
    public boolean equals(Object o){
        if( !(o instanceof MapEntryAdapter))
            return false;

        MapEntryAdapter e = (MapEntryAdapter) o;
        
        return (this.getKey()==null ?
                e.getKey()==null : this.getKey().equals(e.getKey()))  &&
                (this.getValue()==null ?
                e.getValue()==null : this.getValue().equals(e.getValue()));
    }

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
    public int hashCode(){
        return (getKey()==null ? 0 : getKey().hashCode()) ^ (getValue()==null ? 0 : getValue().hashCode());
    }

    /** 
     *  Returns a string representation of the object.
     *
     *  @implSpec
     *  The {@code toString} method for class {@code MapEntryAdapter}
     *  returns a string consisting of the toString result of the key,
     *  the equals-sign charachter `{@code =}'  and the toString result
     *  of the value. 
     *  <p> In other words, this method returns a string equal to the
     *  value of:
     *  <blockquote>
     *  <pre>
     *  key.toString() + "=" + value.toString()
     *  </pre></blockquote>
     * 
     *  If value is null is not represented value.toString() but only key.toString()
     *  @return  a string representation of the object. 
     */

    @Override
    public String toString(){
        if(value == null)
            return key.toString();
        return key.toString() + "=" + value.toString();
    }
}
