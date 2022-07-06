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
 */
public class MapEntryAdapter implements HMap.HEntry{

    private Object key;
    private Object value;

    public MapEntryAdapter(Object akey){
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

    public Object setValue(Object newValue){
        Object oldValue = getValue();
        this.value = newValue;
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
        return(this.key.equals(e.key) && this.value.equals(e.value));
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
        return (new String(key.toString() + ":" + value.toString())).hashCode();
    }
}
