package myAdapter;

import java.util.Objects;
import java.util.Enumeration;

/**
 *  The Hashtable class behaves like the Hashtable class from
 *  the version of Java Micro Edition CLDC 1.1. The last one has
 *  fewer methods than the standard Java's one.
 * 
 *  @author Giacomo Calabria
 *  @see java.util.Hashtable
 */

public class Hashtable {
    java.util.Hashtable hashtable;

    public Hashtable(){
        hashtable = new java.util.Hashtable();
    }

    public Hashtable(int initialCapacity){
        hashtable = new java.util.Hashtable(initialCapacity);
    }

    public Hashtable(int initialCapacity, float loadFactor){
        hashtable = new java.util.Hashtable(initialCapacity, loadFactor);
    }

    public Hashtable(HMap t){
        hashtable = new java.util.Hashtable(t);
    }

    public void clear(){
        hashtable.clear();
    }

    public Object clone(){
        return hashtable.clone();
    }

    public boolean contains(Object value){
        return hashtable.contains(value);
    }

    public boolean containsKey(Object key){
        return hashtable.containsKey(key);
    }

    public boolean containsValue(Object value){
        return hashtable.containsValue(value);
    }

    public Enumeration elements(){
        return hashtable.elements();
    }

    public HSet entrySet(){
        return hashtable.entrySet();
    }

    public boolean equals(Object o){
        return hashtable.equals(o);
    }

    public Object get(Object key){
        return hashtable.get(key);
    }

    public int hashCode(){
        return hashtable.hashCode();
    }

    public boolean isEmpty(){
        return hashtable.isEmpty();
    }
    
    public Enumeration keys(){
        return hashtable.keys();
    }

    public HSet keySet(){
        return hashtable.keySet();
    }

    public Object put(Object key, Object value){
        return hashtable.put(key, value);
    }

    public void putAll(HMap t){
        hashtable.putAll(t);
    }

    public Object remove(Object key){
        return hashtable.remove(key);
    }

    public int size(){
        return hashtable.size();
    }

    public String toString(){
        return hashtable.toString();
    }

    public HCollection values(){
        return hashtable.values();
    }
}
