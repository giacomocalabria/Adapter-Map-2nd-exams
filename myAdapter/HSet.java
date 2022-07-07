package myAdapter;

/**
 *  A collection that contains no duplicate elements. More formally, sets 
 *  contain no pair of elements e1 and e2 such that e1.equals(e2), and at most
 *  one null element. As implied by its name, this interface models the 
 *  mathematical set abstraction.
 *  
 *  <p>
 *  The Set interface places additional stipulations, beyond those inherited 
 *  from the Collection interface, on the contracts of all constructors and on
 *  the contracts of the add, equals and hashCode methods. Declarations for
 *  other inherited methods are also included here for convenience.
 *  
 *  <p>
 *  The additional stipulation on constructors is, not surprisingly, that 
 *  all constructors must create a set that contains no duplicate elements (as defined above).
 *  
 *  <p>
 *  Some set implementations have restrictions on the elements that they may 
 *  contain. For example, some implementations prohibit null elements, and some 
 *  have restrictions on the types of their elements. Attempting to add an 
 *  ineligible element throws an unchecked exception, typically NullPointerException 
 *  or ClassCastException. Attempting to query the presence of an ineligible element
 *  may throw an exception, or it may simply return false; some implementations
 *  will exhibit the former behavior and some will exhibit the latter. 
 *  More generally, attempting an operation on an ineligible element whose 
 *  completion would not result in the insertion of an ineligible element 
 *  into the set may throw an exception or it may succeed, at the option of
 *  the implementation. Such exceptions are marked as "optional" in the 
 *  specification for this interface.
 * 
 *  @author Giacomo Calabria
 *  @see HCollection
 */

public interface HSet extends HCollection{

    // Query Operations

    /**
     * Returns the number of elements in this set (its cardinality).  If this
     * set contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this set (its cardinality)
     */
    int size();

    /**
     * Returns {@code true} if this set contains no elements.
     *
     * @return {@code true} if this set contains no elements
     */
    boolean isEmpty();

    /**
     * Returns {@code true} if this set contains the specified element.
     * More formally, returns {@code true} if and only if this set
     * contains an element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this set is to be tested
     * @return {@code true} if this set contains the specified element
     * @throws NullPointerException if the specified element is null.
     * 
     */
    boolean contains(Object o);

    /**
     * Returns an iterator over the elements in this set.  The elements are
     * returned in no particular order (unless this set is an instance of some
     * class that provides a guarantee).
     *
     * @return an iterator over the elements in this set
     */
    HIterator iterator();

    /**
     * Returns an array containing all of the elements in this set.
     * If this set makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the
     * elements in the same order.
     *
     * <p>The returned array will be "safe" in that no references to it
     * are maintained by this set.  (In other words, this method must
     * allocate a new array even if this set is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all the elements in this set
     */
    Object[] toArray();

    /**
     * Returns an array containing all of the elements in this set; the
     * runtime type of the returned array is that of the specified array.
     * If the set fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the
     * specified array and the size of this set.
     *
     * <p>If this set fits in the specified array with room to spare
     * (i.e., the array has more elements than this set), the element in
     * the array immediately following the end of the set is set to
     * {@code null}.  (This is useful in determining the length of this
     * set <i>only</i> if the caller knows that this set does not contain
     * any null elements.)
     *
     * <p>If this set makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements
     * in the same order.
     *
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * <p>Suppose {@code x} is a set known to contain only strings.
     * The following code can be used to dump the set into a newly allocated
     * array of {@code String}:
     *
     * <pre>
     *     String[] y = x.toArray(new String[0]);</pre>
     *
     * Note that {@code toArray(new Object[0])} is identical in function to
     * {@code toArray()}.
     *
     * @param a the array into which the elements of this set are to be
     *        stored, if it is big enough; otherwise, a new array of the same
     *        runtime type is allocated for this purpose.
     * @return an array containing all the elements in this set
     * @throws NullPointerException if the specified array is null
     */
    Object[] toArray(Object[] arrayTarget);

    // Modification Operations

    /**
     * Adds the specified element to this set if it is not already present
     * (optional operation).  More formally, adds the specified element
     * {@code e} to this set if the set contains no element {@code e2}
     * such that
     * {@code Objects.equals(e, e2)}.
     * If this set already contains the element, the call leaves the set
     * unchanged and returns {@code false}.  In combination with the
     * restriction on constructors, this ensures that sets never contain
     * duplicate elements.
     *
     * <p>The stipulation above does not imply that sets must accept all
     * elements; sets may refuse to add any particular element, including
     * {@code null}, and throw an exception, as described in the
     * specification for {@link Collection#add Collection.add}.
     * Individual set implementations should clearly document any
     * restrictions on the elements that they may contain.
     *
     * @param e element to be added to this set
     * @return {@code true} if this set did not already contain the specified
     *         element
     * @throws UnsupportedOperationException if the {@code add} operation
     *         is not supported by this set
     * @throws NullPointerException if the specified element is null and this
     *         set does not permit null elements
     */
    boolean add(Object e);

    /**
     * Removes the specified element from this set if it is present
     * (optional operation).  More formally, removes an element {@code e}
     * such that
     * {@code Objects.equals(o, e)}, if
     * this set contains such an element.  Returns {@code true} if this set
     * contained the element (or equivalently, if this set changed as a
     * result of the call).  (This set will not contain the element once the
     * call returns.)
     *
     * @param o object to be removed from this set, if present
     * @return {@code true} if this set contained the specified element
     * @throws NullPointerException if the specified element is null and this
     *         set does not permit null elements
     * 
     */
    boolean remove(Object o);

    // Bulk Operations

    /**
     * Returns {@code true} if this set contains all of the elements of the
     * specified collection.  If the specified collection is also a set, this
     * method returns {@code true} if it is a <i>subset</i> of this set.
     *
     * @param  c collection to be checked for containment in this set
     * @return {@code true} if this set contains all of the elements of the
     *         specified collection
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements and this set does not permit null
     *         elements
     *         or if the specified collection is null
     * @see    #contains(Object)
     */
    boolean containsAll(HCollection coll);

    /**
     * Adds all of the elements in the specified collection to this set if
     * they're not already present (optional operation).  If the specified
     * collection is also a set, the {@code addAll} operation effectively
     * modifies this set so that its value is the <i>union</i> of the two
     * sets.  The behavior of this operation is undefined if the specified
     * collection is modified while the operation is in progress.
     *
     * @param  c collection containing elements to be added to this set
     * @return {@code true} if this set changed as a result of the call
     *
     * @throws UnsupportedOperationException if the {@code addAll} operation
     *         is not supported by this set
     * @throws NullPointerException if the specified collection contains one
     *         or more null elements and this set does not permit null
     *         elements, or if the specified collection is null
     * @see #add(Object)
     */
    boolean addAll(HCollection coll);

    /**
     * Retains only the elements in this set that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this set all of its elements that are not contained in the
     * specified collection.  If the specified collection is also a set, this
     * operation effectively modifies this set so that its value is the
     * <i>intersection</i> of the two sets.
     *
     * @param  c collection containing elements to be retained in this set
     * @return {@code true} if this set changed as a result of the call
     * @throws NullPointerException if this set contains a null element and the
     *         specified collection does not permit null elements
     *         or if the specified collection is null
     * @see #remove(Object)
     */
    boolean retainAll(HCollection coll);

    /**
     * Removes from this set all of its elements that are contained in the
     * specified collection (optional operation).  If the specified
     * collection is also a set, this operation effectively modifies this
     * set so that its value is the <i>asymmetric set difference</i> of
     * the two sets.
     *
     * @param  c collection containing elements to be removed from this set
     * @return {@code true} if this set changed as a result of the call
     * @throws NullPointerException if this set contains a null element or
     * if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean removeAll(HCollection coll);

    /**
     * Removes all of the elements from this set (optional operation).
     * The set will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the {@code clear} method
     *         is not supported by this set
     */
    void clear();


    // Comparison and hashing

    /**
     * Compares the specified object with this set for equality.  Returns
     * {@code true} if the specified object is also a set, the two sets
     * have the same size, and every member of the specified set is
     * contained in this set (or equivalently, every member of this set is
     * contained in the specified set).  This definition ensures that the
     * equals method works properly across different implementations of the
     * set interface.
     *
     * @param o object to be compared for equality with this set
     * @return {@code true} if the specified object is equal to this set
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this set.  The hash code of a set is
     * defined to be the sum of the hash codes of the elements in the set,
     * where the hash code of a {@code null} element is defined to be zero.
     * This ensures that {@code s1.equals(s2)} implies that
     * {@code s1.hashCode()==s2.hashCode()} for any two sets {@code s1}
     * and {@code s2}, as required by the general contract of
     * {@link Object#hashCode}.
     *
     * @return the hash code value for this set
     * @see Object#equals(Object)
     * @see Set#equals(Object)
     */
    int hashCode();
}
