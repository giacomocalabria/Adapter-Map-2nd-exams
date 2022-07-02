package myAdapter;

/**
 *  MapAdapter adapts the Hashtable class from Java CLDC 1.1 to the
 *  HMap and HCollection interface. Therefore it's an Adapter design
 *  pattern. 
 * 
 *  @version 1.0
 *  @author Giacomo Calabria
 *  @see HMap
 *  @see HCollection
 */

public class MapAdapter implements HMap{
    private Hashtable table;

    public MapAdapter(){
        table = new Hashtable();
    }

    public void clear(){
        table.clear();
    }

    public boolean containsKey(Object key){
        return table.containsKey(key);
    }

    public boolean containsValue(Object value){
        return table.containsValue(value);
    }

    public HSet entrySet(){
        return table.entrySet();
    }

    public boolean equals(Object o){
        return table.equals(o);
    }

    public Object get(Object key){
        return table.get(key);
    }

    public int hashCode(){
        return table.hashCode();
    }

    public boolean isEmpty(){
        return table.isEmpty();
    }

    public HSet keySet(){
        return table.keySet();
    }

    public Object put(Object key, Object value){
        return table.put(key, value);
    }

    public void putAll(HMap t){
        table.putAll(t);
    }

    public Object remove(Object key){
        return table.remove(key);
    }

    public int size(){
        return table.size();
    }

    public HCollection values(){
        return table.values();
    }

}
