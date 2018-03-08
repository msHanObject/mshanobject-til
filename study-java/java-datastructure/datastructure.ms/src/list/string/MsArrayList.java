package list.string;

import java.util.Collection;
import java.util.Iterator;

public class MsArrayList {
	//
	private static final int DEFAULT_MAX_CAPACITY= 100;
	private String[] elements;
	private int capacity;
	private int lastIndex;
	
	public MsArrayList() {
		//
		capacity = DEFAULT_MAX_CAPACITY;
		elements = new String[capacity];
		lastIndex = 0;
	}
	
//	Appends the specified element to the end of this list.
	public boolean	add(String str) {
		//
		if (checkFull(capacity)) {			
			increaseCapacity(lastIndex + 1);
		}
		elements[lastIndex++] = (String) str;
		
		return true;
	}
	
	private boolean checkFull(int limit) {
		if (lastIndex == limit) {
			return true;
		}
		return false;
	}
	
	private void increaseCapacity(int minSize) {
		// 
		capacity = minSize;
		String[] increasedelements = new String[capacity];
		for (int i =0; i<lastIndex; i++) {
			increasedelements[i] = elements[i];
		}
		elements = increasedelements;
	}
	
//	Returns the element at the specified position in this list.
	public String get(int index) {
		//
		return (String) elements[index];
	}

//	Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's Iterator.
	public boolean	addAll(Collection<String> c) {
		//
		boolean success = false;
		Iterator<String> collectionIter = c.iterator();
		while (collectionIter.hasNext()) {
			success = this.add((String) collectionIter.next());
		}
		return success;
	}
	
//	Removes all of the elements from this list.
	public void	clear() {
		//
		for (int i=0; i<lastIndex; i++) {
			elements[i] = null;
		}
	}
	
//	Returns true if this list contains the specified element.
	public boolean	contains(String str) {
		//
		for (String item : elements) {
			if( item.equals(str)) {
				return true;
			}
		}
		return false;
	}
	
//	Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
	public int	indexOf(String str) {
		//
		int index = 0;
		for (int i = 0; i<lastIndex; i++) {
			if (elements[i].equals(str)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
//	Returns true if this list contains no elements.
	public boolean	isEmpty() {
		//
		if (lastIndex == 0 && elements[lastIndex] == null) {
			return true;
		}
		return false;
	}
	
//	Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
	public int	lastIndexOf(String str) {
		//
		int index = 0;
		for (int i = 0; i<lastIndex; i++) {
			if (elements[i].equals(str)) {
				index = i;
			}
		}
		return index;
	}
	
//	Removes the element at the specified position in this list.
	public  String remove(int index) {
		//
		String element = (String) elements[index];
		this.shiftLeft(index);
		this.decreaseCapacity();

		return element;
	}
	
//	Removes the first occurrence of the specified element from this list, if it is present.
	public  boolean	remove(String str) {
		//
		boolean found = false;
		int position = 0;
		for (String item : elements) {
			if (str.equals(item)) {
				this.shiftLeft(position);
				this.decreaseCapacity();
				found = true;
				break;
			}
			position++;
		}
		return found;
	}
	
	private void shiftLeft(int position) {
		// 
		for(int i=position; i<lastIndex-1; i++) {
			elements[i] = elements[i+1];
		}
		elements[--lastIndex] = null;
	}

	private void decreaseCapacity() {
		//
		if (elements[lastIndex] != null && elements[lastIndex +1] != null) {
			return;
		}
		String[] newelements  = new String[lastIndex];
		for (int i=0; i<lastIndex; i++) {
			newelements[i] = elements[i];
		}
		elements = newelements;
	}

//	Removes from this list all of its elements that are contained in the specified collection.
	public  boolean	removeAll(Collection<String> c) {
		//
		boolean compareResult = false;
		Iterator<String> cIter = c.iterator();
		
		while (cIter.hasNext()) {
			String str = cIter.next();
			for (String item: elements) {		
				if (str.equals(item)) {
					compareResult |= this.remove(item);
				}
				compareResult |= false;
			}
		}
		
		return compareResult;
	}
	
//	Returns the number of elements in this list.
	public int size() {
		//
		return lastIndex;
	}
}