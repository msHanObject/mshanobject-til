package map.string;

import java.util.Map;
import java.util.Objects;

import javax.swing.tree.TreeNode;

public class MsHashMap<K, V> {
	//
	static final int DEFAULT_INITIAl_CAPACITY = 16;
	static final int MAXIMUM_CAPACITY = 10000;
	static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	int size;
	final float loadFactor;
	Node[] table;
	
	static class Node<K,V> implements Map.Entry<K, V>{
		//
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
        		//
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
        		//
        		return key;
        }
        
        public final V getValue() {
        		//
        		return value;
        	}
        
        public final String toString() {
        		//
        		return key + "=" + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
        		//
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
        
        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
	
	public MsHashMap() {
		//
		this.loadFactor = DEFAULT_LOAD_FACTOR;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		//
		return size == 0;
	}
	
	public V get(K key) {
		Node<K,V> element;
		element = getNode(hash(key), key);
		if (element == null) {
			return null;
		}
		return element.value;
	}
	
	final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

	static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
	
	public String put(String key, String value) {
		Node element = new Node(hash(key), key, value, new Node());
	}
}
