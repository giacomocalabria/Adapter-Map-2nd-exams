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

    public Hashtable(java.util.Map t){
        hashtable = new java.util.Hashtable(t);
    }

    
}
