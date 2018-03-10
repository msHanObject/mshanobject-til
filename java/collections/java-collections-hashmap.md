The HashMap Class
---
The HashMap class uses a hashtable to implement the Map interface. This allows the execution time of basic operations, such as get() and put(), to remain constant even for large sets.

Following is the list of constructors supported by the HashMap class.

Sr.<br/>No.|Constructors and Description
---|---
1|**HashMap()**<br/>This constructors a default HashMap.
2|**HashMap(Map m)**<br/>This constructor initializes the hash map by using the elements of the given Map object **m**.
3|**HashMap(int capacity)**<br/>This constructor intitializes the capacity of the hash map to the given integer value, capacity.
4|**HashMap(int capacity, float fillRatio)**<br/>This constructor initializes both the capacity and fill ratio of the hash map by using its arguments.

Apart from the methods inherited from its parent classes, HashMap defines the following methods:
Sr.No.|Methods with Description
---|---
1|**void clear()**<br/>Removes all mappings from this map.
2|**Object cloen()**<br/>Returns a shallow copy of this HashMap instance: the keys and values themselves are not cloned.
3|**boolean containsKey(Object Key)**<br/>Returns true if this map contains a mapping for the specified key.
4|**boolean containsValue(Object value)**<br/>Returns true if this map maps one or more keys to the specified value.
5|**Set entrySet()**<br/>Returns a collection view of the mappings contained in this map.
6|**Object get(Object key)**<br/>Returns the value to which the specified key is mapped in this identity hash map, or null if the map contains no mapping for this key.
7|**boolean isEmpty()**<br/>Returns true if this map contains no key-value mappings.
8|**Set keySet()**<br/> Returns a set view of the keys contained in this map.
9|**Object put(Object kye, Object value)**<br/>Associates the specified value with the specified key in this map.
10|**putAll(Map m)**<br/>Copies all of the mappings from the specified map to this map. These mappings will replace any mappings that this map had for any of the keys currently in the specified map.
11|**Object remove(Object key)**<br/>Removes the mapping for this key from this map if present.
12|**int size()**<br/>Returns the number of key-value mappings in this map.
13|**Collection values()**<br/>Returns a collection view of the values contained in this map.

**Example**
HashMapDemo.java
