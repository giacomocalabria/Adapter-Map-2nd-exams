import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TestCollection {
	
	/**
	 * @safe.precondition
	 * @safe.postcondition
	 * @safe.summary
	 */

	MyCollection head = new CollectionAdapter();
	boolean h = head.add("Collection Adapter");
	
	MyCollection param = new CollectionAdapter();
	boolean aa = param.add("aaa");
	boolean bb = param.add("bbb");
	boolean cc = param.add("ccc");
	
	MyCollection intersect = new CollectionAdapter();
	boolean aaa = intersect.add("aaa");
	boolean bbb = intersect.add("bbb");
	boolean ccc = intersect.add("ccc");
	
	MyCollection ct = new CollectionAdapter();
	boolean first = ct.add("Collection Adapter");
	boolean a = ct.add("aaa");
	boolean b = ct.add("bbb");
	boolean c = ct.add("ccc");
	
	@Test
	/**
	 * @safe.precondition stato attuale di ct
	 * @safe.postcondition ct modificato con aggiunta di un elemento
	 * @safe.summary aggiungo un elemento al ct utilizzando il metodo add(Object o)
	 */
	public void add_o() {
		boolean ris = ct.add("add(Object o) funziona");
		assertTrue(ris);
	}
	
	@Test
	/**
	 * @safe.precondition stato attuale di ct
	 * @safe.postcondition ct modificato con aggiunta da parte di un'altra Collection
	 * @safe.summary aggiunta in ct degli elementi da parte di un'altra Collection
	 */
	public void addAll_c() {
		boolean ris = ct.addAll(param);
		assertTrue(ris);
	}
	
	@Test
	/**
	 * @safe.precondition ct contiene almeno un elemento
	 * @safe.postcondition non ci sono piu' value in ct
	 * @safe.summary svuotare il ct e testare se e' vuoto
	 */
	public void clear() {
		ct.clear();
		assertTrue(ct.isEmpty());
	}
	
	@Test
	/**
	 * @safe.precondition ct contiene l'emento da verificare
	 * @safe.postcondition ris e' true
	 * @safe.summary controllo se c'e' l'elemento in ct
	 */
	public void contains_o() {
		boolean ris = ct.contains("Collection Adapter");
		assertTrue(ris);
	}
	
	@Test
	/**
	 * @safe.precondition ct contiene tutti gli elementi presenti nella Collection c
	 * @safe.postcondition ris e' true
	 * @safe.summary test metodo containsAll(Collection c) e verifico che gli elementi di c siano presenti in ct
	 */
	public void containsAll_c() {
		boolean ris = ct.containsAll(param);
		assertTrue(ris);
	}
	
	@Test
	/**
	 * @safe.precondition due oggetti uguali
	 * @safe.postcondition ris vale true
	 * @safe.summary test metodo equals(Object o) e controllo se sono uguali
	 */
	public void equals_o() {
		MyCollection temp = new CollectionAdapter();
		temp.add("Collection Adapter");
		temp.add("aaa");
		temp.add("bbb");
		temp.add("ccc");
		
		boolean ris = ct.equals(temp);
		assertTrue(ris);
	}
	
	@Test
	/**
	 * @safe.precondition
	 * @safe.postcondition
	 * @safe.summary
	 */
	public void hash_Code() {
		
	}
	
	@Test
	/**
	 * @safe.precondition ct contiene un elemento
	 * @safe.postcondition ct vuoto, ris vale true
	 * @safe.summary test metodo isEmpty() e controllo che ct sia vuoto attraverso il metodo size()
	 */
	public void is_empty() {
		ct.clear();
		boolean ris = ct.isEmpty();
		assertTrue(ris);
	}
	
	@Test
	/**
	 * @safe.precondition
	 * @safe.postcondition
	 * @safe.summary
	 */
	public void iterator() {
		
	}
	
	@Test
	/**
	 * @safe.precondition ct con almeno un elemento
	 * @safe.postcondition ct senza l'elemento o
	 * @safe.summary test remove(Object o), rimuovo da ct l'elemento o e verifico che la dimensione di ct sia cambiata
	 */
	public void remove_o() {
		Object o = "Collection Adapter";
		boolean ris = ct.remove(o);
		assertTrue(ris);
	}
	
	@Test
	/**
	 * @safe.precondition ct con almeno un elemento
	 * @safe.postcondition ct senza gli elementi di Collection c
	 * @safe.summary test removeAll(Collection c), rimuovo da ct tutti gli elementi di c
	 */
	public void remove_all_c() {
		boolean ris = ct.removeAll(param);
		assertTrue(ris);
	}
	
	@Test
	/**
	 * @safe.precondition ct con almeno un elemento
	 * @safe.postcondition ct intersecato a Collection c
	 * @safe.summary modifica ct facendogli contenere alla fine solo gli elementi presenti anche in c
	 */
	public void retain_all_c() {
		boolean ris = ct.retainAll(param);
		assertTrue(ris);
	}
	
	
	@Test
	/**
	 * @safe.precondition ct con almeno un elemento
	 * @safe.postcondition size = 4
	 * @safe.summary verifico se size e' uguale a 4
	 */
	public void size() {
		int size = ct.size();
		assertEquals(4, size);
	}
	
	@Test
	/**
	 * @safe.precondition head con almeno un elemento
	 * @safe.postcondition temp che e' un array contenete gli elementi di head
	 * @safe.summary verifico che temp contenga gli elementi di head
	 */
	public void to_array() {
		Object[] temp = head.toArray();
		boolean size = (temp.length == 1);
		boolean content = (temp[0].equals("Collection Adapter"));
		assertTrue(size && content);
	}
	
	@Test
	/**
	 * @safe.precondition head con almeno un elemento, a array di destinazione
	 * @safe.postcondition a contiene gli elementi di head
	 * @safe.summary verifico che head cotanga gli elementi di head, facendo il confronto con la sua dimensione e il suo contenuto
	 */
	public void to_array_a() {
		Object[] a = new Object[10];
		a = head.toArray(a);
		boolean size = (a.length == 1);
		boolean content = (a[0].equals("Collection Adapter"));
		assertTrue(size && content);
	}
}
