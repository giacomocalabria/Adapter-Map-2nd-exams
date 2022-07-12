package myAdapter;

import java.util.Enumeration;

/**
 *  The Hashtable class behaves like the Hashtable class from
 *  the version of Java Micro Edition CLDC 1.1. The last one has
 *  fewer methods than the standard Java's one.
 * 
 *  <p> Note that in Hashtable class of CLDC 1.1 null keys or values
 *  are forbidden.
 * 
 *  @author Giacomo Calabria
 *  @see java.util.Hashtable
 */

public class Hashtable{
    java.util.Hashtable table;

    /**
     * Constructs a new, empty hashtable with a default initial capacity (11)
     * and load factor (0.75).
     */
    public Hashtable(){
        table = new java.util.Hashtable();
    }

    /**
     * Constructs a new, empty hashtable with the specified initial capacity
     * and default load factor (0.75).
     *
     * @param     initialCapacity   the initial capacity of the hashtable.
     * @throws    IllegalArgumentException if the initial capacity is less
     *              than zero.
     */
    public Hashtable(int initialCapacity){
        table = new java.util.Hashtable(initialCapacity);
    }

    /**
     * Clears this hashtable so that it contains no keys
     */
    public void clear(){
        table.clear();
    }

    /**
     * Tests if some key maps into the specified value in this hashtable.
     * This operation is more expensive than the {@link #containsKey
     * containsKey} method.
     *
     * <p>Note that this method is identical in functionality to
     * {@link #containsValue containsValue}, (which is part of the
     * {@link HMap} interface in the collections framework).
     *
     * @param      value   a value to search for
     * @return     {@code true} if and only if some key maps to the
     *             {@code value} argument in this hashtable as
     *             determined by the {@code equals} method;
     *             {@code false} otherwise.
     * @throws     NullPointerException  if the value is {@code null}
     */
    public boolean contains(Object value){
        return table.contains(value);
    }

    /**
     * Tests if the specified object is a key in this hashtable.
     *
     * @param   key   possible key
     * @return  {@code true} if and only if the specified object
     *          is a key in this hashtable, as determined by the
     *          {@code equals} method; {@code false} otherwise.
     * @throws  NullPointerException  if the key is {@code null}
     * @see     #contains(Object)
     */
    public boolean containsKey(Object key){
        return table.containsKey(key);
    }

    /**
     * Returns an enumeration of the values in this hashtable.
     * Use the Enumeration methods on the returned object to fetch the elements
     * sequentially. If the hashtable is structurally modified while enumerating
     * over the values then the results of enumerating are undefined.
     *
     * @return  an enumeration of the values in this hashtable.
     * @see     java.util.Enumeration
     */
    public Enumeration elements(){
        return table.elements();
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key.equals(k))},
     * then this method returns {@code v}; otherwise it returns
     * {@code null}.  (There can be at most one such mapping.)
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     * @throws NullPointerException if the specified key is null
     * @see    #put(Object, Object)
     */
    public Object get(Object key){
        return table.get(key);
    }

    /**
     * Tests if this hashtable maps no keys to values.
     *
     * @return  {@code true} if this hashtable maps no keys to values;
     *          {@code false} otherwise.
     */
    public boolean isEmpty(){
        return table.isEmpty();
    }
    
    /**
     * Returns an enumeration of the keys in this hashtable.
     * Use the Enumeration methods on the returned object to fetch the keys
     * sequentially. If the hashtable is structurally modified while enumerating
     * over the keys then the results of enumerating are undefined.
     *
     * @return  an enumeration of the keys in this hashtable.
     * @see     Enumeration
     */
    public Enumeration keys(){
        return table.keys();
    }

    /**
     * Maps the specified {@code key} to the specified
     * {@code value} in this hashtable. Neither the key nor the
     * value can be {@code null}. <p>
     *
     * The value can be retrieved by calling the {@code get} method
     * with a key that is equal to the original key.
     *
     * @param      key     the hashtable key
     * @param      value   the value
     * @return     the previous value of the specified key in this hashtable,
     *             or {@code null} if it did not have one
     * @throws     NullPointerException  if the key or value is
     *               {@code null}
     * @see     #get(Object)
     */
    public Object put(Object key, Object value){
        return table.put(key, value);
    }

    /**
     * Removes the key (and its corresponding value) from this
     * hashtable. This method does nothing if the key is not in the hashtable.
     *
     * @param   key   the key that needs to be removed
     * @return  the value to which the key had been mapped in this hashtable,
     *          or {@code null} if the key did not have a mapping
     * @throws  NullPointerException  if the key is {@code null}
     */
    public Object remove(Object key){
        return table.remove(key);
    }

    /**
     * Returns the number of keys in this hashtable.
     *
     * @return  the number of keys in this hashtable.
     */
    public int size(){
        return table.size();
    }

    /**
     * Returns a string representation of this {@code Hashtable} object
     * in the form of a set of entries, enclosed in braces and separated
     * by the ASCII characters "<code> ,&nbsp;</code>" (comma and space). Each
     * entry is rendered as the key, an equals sign {@code =}, and the
     * associated element, where the {@code toString} method is used to
     * convert the key and element to strings.
     *
     * @return  a string representation of this hashtable
     */
    public String toString(){
        return table.toString();
    }
    
     /**
     * Compares the specified Object with this Map for equality,
     * as per the definition in the HMap interface.
     *
     * @param  o object to be compared for equality with this hashtable
     * @return true if the specified Object is equal to this Map
     */
    public boolean equals(Object o){
        return table.equals(o);
    }

    /**
     * Returns the hash code value for this Map as per the definition in the
     * Map interface.
     *
     * @see Map#hashCode()
     */
    public int hashCode(){
        return table.hashCode();
    }
 
}
