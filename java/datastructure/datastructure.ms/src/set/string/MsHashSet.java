package set.string;

import java.util.HashMap;

public class MsHashSet {
	//
	private transient HashMap<String,String> map;
	
    // Dummy value to associate with an Object in the backing Map
    private static final String PRESENT = new String();
    
	public MsHashSet() {
		//
		map = new HashMap<>();
	}
	
//	Adds the specified element to this set if it is not already present.
	public boolean	add(String e) {
		boolean notExist = false;
		if (map.put(e, PRESENT)==null) {
			notExist = true;
		}
		return notExist;
	}
	
//	Removes all of the elements from this set.
	public void	clear() {
		map.clear();
	}
	
//	Returns true if this set contains the specified element.
	public boolean	contains(String e) {
		return map.containsKey(e);
	}
	
//	Returns true if this set contains no elements.
	public boolean	isEmpty() {
		return map.isEmpty();
	}
	
//	Removes the specified element from this set if it is present.
	public boolean	remove(String e) {
		boolean exist = false;
		if (map.remove(e)==PRESENT) {
			exist = true;
		}
		return exist;
	}

	//	Returns the number of elements in this set (its cardinality).
	public int	size() {
		return map.size();
	}
}