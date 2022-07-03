package myAdapter;

import java.util.Enumeration;

/**
 *  The Hashtable class behaves like the Hashtable class from
 *  the version of Java Micro Edition CLDC 1.1. The last one has
 *  fewer methods than the standard Java's one.
 * 
 *  @author Giacomo Calabria
 *  @see java.util.Hashtable
 */

public class Hashtable{
    java.util.Hashtable table;

    public Hashtable(){
        table = new java.util.Hashtable();
    }

    public Hashtable(int initialCapacity){
        table = new java.util.Hashtable(initialCapacity);
    }

    public void clear(){
        table.clear();
    }

    public boolean contains(Object value){
        return table.contains(value);
    }

    public boolean containsKey(Object key){
        return table.containsKey(key);
    }

    public Enumeration elements(){
        return table.elements();
    }

    public Object get(Object key){
        return table.get(key);
    }

    public boolean isEmpty(){
        return table.isEmpty();
    }
    
    public Enumeration keys(){
        return table.keys();
    }

    public Object put(Object key, Object value){
        return table.put(key, value);
    }

    public Object remove(Object key){
        return table.remove(key);
    }

    public int size(){
        return table.size();
    }

    public String toString(){
        return table.toString();
    }
    
    public boolean equals(Object o){
        return table.equals(o);
    }

    public int hashCode(){
        return table.hashCode();
    }
}
