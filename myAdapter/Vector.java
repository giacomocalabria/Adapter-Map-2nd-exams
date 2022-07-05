package myAdapter;

import java.util.Enumeration;
import java.util.Objects;

/**
 * The Vector class behaves like the Vector class from
 * the version of Java Micro Edition CLDC 1.1. The last one has
 * fewer methods than the standard Java's one.
 * 
 * <p> Important: in this Vector is forbidden null elements
 * 
 * @author Giacomo Calabria
 * @see ava.util.Vector
 */

public class Vector {

    java.util.Vector vector;

    public Vector() {
        vector = new java.util.Vector();
    }

    public Vector(int initialCapacity) {
        vector = new java.util.Vector(initialCapacity);
    }

    public Vector(int initialCapacity, int capacityIncrement) {
        vector = new java.util.Vector(initialCapacity, capacityIncrement);
    }

    public void addElement(Object obj) throws NullPointerException{
        if (obj == null)
            throw new NullPointerException();
        vector.addElement(obj);
    }

    public int capacity(){
        return vector.capacity();
    }

    public boolean contains(Object elem) throws NullPointerException{
        if (elem == null)
            throw new NullPointerException();
        return vector.contains(elem);
    }

    public void copyInto(Object[] anArray){
        vector.copyInto(anArray);
    }

    public Object elementAt(int index){
        return vector.elementAt(index);
    }

    public Enumeration elements(){
        return vector.elements();
    }

    public void ensureCapacity(int minCapacity){
        vector.ensureCapacity(minCapacity);
    }

    public Object firstElement(){
        return vector.firstElement();
    }

    public int indexOf(Object elem) throws NullPointerException{
        if (elem == null)
            throw new NullPointerException();
        return vector.indexOf(elem);
    }

    public int indexOf(Object elem, int index) throws NullPointerException{
        if (elem == null)
            throw new NullPointerException();
        return vector.indexOf(elem, index);
    }

    public void insertElementAt(Object obj, int index) throws NullPointerException{
        if (obj == null)
            throw new NullPointerException();
        vector.insertElementAt(obj, index);
    }

    public boolean isEmpty(){
        return vector.isEmpty();
    }

    public Object lastElement(){
        return vector.lastElement();
    }

    public int lastIndexOf(Object elem) throws NullPointerException{
        if (elem == null)
            throw new NullPointerException();
        return vector.lastIndexOf(elem);
    }

    public int lastIndexOf(Object elem, int index) throws NullPointerException{
        if (elem == null)
            throw new NullPointerException();
        return vector.lastIndexOf(elem, index);
    }

    public void removeAllElements(){
        vector.removeAllElements();
    }

    public boolean removeElement(Object obj) throws NullPointerException{
        if (obj == null)
            throw new NullPointerException();
        return vector.removeElement(obj);
    }

    public void removeElementAt(int index){
        vector.removeElementAt(index);
    }

    public void setElementAt(Object obj, int index) throws NullPointerException{
        if (obj == null)
            throw new NullPointerException();
        vector.setElementAt(obj, index);
    }

    public void setSize(int newSize){
        vector.setSize(newSize);
    }

    public int size(){
        return vector.size();
    }

    public String toString(){
        return vector.toString();
    }

    public void trimToSize(){
        vector.trimToSize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector1 = (Vector) o;
        return Objects.equals(vector, vector1.vector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vector);
    }
}
