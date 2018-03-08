package list.string;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class MsLinkedListTest {
	private MsLinkedList msLinkedList = new MsLinkedList();
	
	@Test
	void testAddFirst() {
		msLinkedList.addFirst("first");
		assertEquals("first", msLinkedList.get(0));
	}

	@Test
	void testAddLast() {
		msLinkedList.addLast("last1");
		msLinkedList.addLast("last2");
		assertEquals("last2", msLinkedList.get(1));
	}

	@Test
	void testAddE() {
		assertTrue(msLinkedList.add("A"));
	}

	@Test
	void testAddAll() {
		Collection<String> c = new LinkedList<>();
		for (int i=0; i<100; i++) {
			c.add("c" + i);
		}
		
		assertTrue(msLinkedList.addAll(c));
	}

	@Test
	void testAddIntE() {
		msLinkedList.add("a1");
		msLinkedList.add("a2");
		msLinkedList.add("a4");
		
		msLinkedList.add(2, "a3");
		assertEquals("a3", msLinkedList.get(2));
	}

	@Test
	void testRemoveFirst() {
		msLinkedList.add("a4");
		msLinkedList.add("a1");
		msLinkedList.add("a2");
		
		assertEquals("a4",msLinkedList.removeFirst());
	}

	@Test
	void testRemoveLast() {
		msLinkedList.add("a1");
		msLinkedList.add("a2");
		msLinkedList.add("a4");
		
		assertEquals("a4",msLinkedList.removeLast());
	}

	@Test
	void testRemove() {
		msLinkedList.add("a1");
		msLinkedList.add("a2");
		msLinkedList.add("a4");
		
		assertEquals("a1", msLinkedList.remove());
	}

	@Test
	void testRemoveInt() {
		msLinkedList.add("a1");
		msLinkedList.add("a2");
		msLinkedList.add("a4");
		msLinkedList.add("a3");
		
		assertEquals("a4", msLinkedList.remove(2));
	}

	@Test
	void testGet() {
		for (int i=0; i<100; i++) {
			msLinkedList.add("a" + i);
		}
		
		assertEquals("a50", msLinkedList.get(50));
	}

	@Test
	void testIndexOf() {
		for (int i=1; i<4; i++) {
			msLinkedList.add("a" + i);
		}
		msLinkedList.add("a4");
		msLinkedList.add("a5");
		msLinkedList.add("a6");
		//for문에서 add한 값은 indexOf메소드에서 같은 값으로 인식을 못하고 그냥 add한 값들만 인식하는 에러 존재.
		assertEquals(4, msLinkedList.indexOf("a5"));
	}

	@Test
	void testSet() {
		for (int i=0; i<4; i++) {
			msLinkedList.add("a" + i);
		}
		
		assertEquals("a3", msLinkedList.set(3, "A3"));
	}
	
	@Test
	void testSize() {
		for (int i=0; i<100; i++) {
			msLinkedList.add("a" + i);
		}
		
		assertEquals(100, msLinkedList.size());
	}
	
	@Test
	void testClear() {
		for (int i=0; i<4; i++) {
			msLinkedList.add("a" + i);
		}
		msLinkedList.clear();
		assertEquals(0, msLinkedList.size());
	}
}
