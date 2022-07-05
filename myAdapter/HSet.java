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
 */

public interface HSet extends HCollection{
    
}
