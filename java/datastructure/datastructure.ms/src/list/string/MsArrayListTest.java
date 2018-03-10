package list.string;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

class MsArrayListTest {
	
	MsArrayList msList = new MsArrayList();
	List<String> arrayList = new ArrayList<>();
	
	@Test
	void testAdd() {
		msList.add("abc");
		
		assertTrue(msList.add("abc"));
	}
	
	@Test
	void testGet() {
		msList.add("a");
		
		assertEquals("a", msList.get(0));
	}

	@Test
	void testAddAll() {
		for (int i=0; i<100; i++) {
			arrayList.add("s" + i);
		}
		
		assertTrue(msList.addAll(arrayList));
		assertEquals(100, msList.size());
	}

	@Test
	void testClear() {
		for (int i=0; i<5; i++) {
			msList.add("a" + i);
		}
		msList.clear();
		
		boolean compareResult = true;
		
		for (int i=0; i<5; i++) {
			if (msList.get(i) == null) {
				compareResult &= true;
			} else {
				compareResult &= false;
			}
		}
		
		assertTrue(compareResult);
		assertNotNull(msList);
	}

	@Test
	void testIndexOf() {
		String a = "a";
		int index = -1;
		
		msList.add(a);
		msList.add("b");
		msList.add("a");
		
		index = msList.indexOf(a);
		
		assertEquals(0, index);
	}

	@Test
	void testIsEmpty() {
		assertTrue(msList.isEmpty());
	}
	
	@Test
	void testLastIndexOf() {
		String a = "a";
		int index = -1;
		
		msList.add(a);	//index :0
		msList.add("b");	//index :1
		msList.add("a");//index :2
		
		index = msList.lastIndexOf(a);
		
		assertEquals(2, index);
	}

	@Test
	void testRemoveInt() {
		msList.add("a");
		msList.add("b");
		msList.add("c");
		
		assertEquals("b",msList.remove(1));
	}

	@Test
	void testRemoveObject() {
		msList.add("a");
		msList.add("b");
		msList.add("c");
		
		assertTrue(msList.remove("c"));
	}

	@Test
	void testSize() {
		for (int i=0; i<3; i++) {
			msList.add("size" + i);
		}
		assertEquals(3, msList.size());
	}

	@Test
	void testRemoveAll() {
		for (int i=0; i<3; i++) {
			msList.add("d" + i);
		}
		arrayList.add("d1");
		arrayList.add("d2");
		
		Collection<String> collection = new ArrayList<>();
		collection.add("d0");
		
		assertTrue(msList.removeAll(collection));
	
		boolean compareResult = true;
		for (int i=0; i<arrayList.size(); i++) {
			compareResult &= msList.get(i).equals(arrayList.get(i));
			
		}
		
		assertTrue(compareResult);
	}

}
