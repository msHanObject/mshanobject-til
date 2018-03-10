package list.string;

import java.util.Collection;
import java.util.Iterator;

public class MsLinkedList {
	//
	private Node head;
	private Node tail;
	private int size;
	
	public MsLinkedList() {
		//
		size = 0;
	}
	
	private class Node {
		//
		private String data;
		private Node nextNode;
		
		public Node(String inputData) {
			//
			this.data = inputData;
			this.nextNode = null;
		}
		
		public String toString() {
			//
			return String.valueOf(this.data);
		}
	}
	
	//Inserts the specified element at the beginning of this list.
	public void addFirst(String inputData) {
		//
		Node newNode = new Node(inputData);
		newNode.nextNode = head;
		head = newNode;
		size++;
		
		if (head.nextNode == null) {
			tail = head;
		}
	}
	
	//Appends the specified element to the end of this list.
	public void addLast (String inputData) {
		//
		if (size == 0) {
			addFirst(inputData);
			return;
		}
		Node newNode = new Node(inputData);
		tail.nextNode = newNode;
		tail = newNode;
		size++;
	}
	
	//Appends the specified element to the end of this list.
	public boolean add (String inputData) {
		//
		try {
			this.addLast(inputData);
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator.
	public boolean addAll(Collection<String> c) {
		//
		boolean success = false;
		Iterator<String> cIter = c.iterator();
		while (cIter.hasNext()) {
			success = this.add(cIter.next());
		}
		return success;
	}

	private Node foundNode(int index) {
		//
		Node nodeFound = head;
		for (int i=0; i<index; i++) {
			nodeFound = nodeFound.nextNode; 
		}
		return nodeFound;
	}
	
	//Inserts the specified element at the specified position in this list.
	public void add(int index, String element) {
		//
		if (index == 0) {
			addFirst(element);
			return;
		}
		
		Node prevNode = this.foundNode(index-1);
		Node nextNode = prevNode.nextNode;
		Node newNode = new Node(element);
		prevNode.nextNode = newNode;
		newNode.nextNode = nextNode;
		size++;
		
		if (newNode.nextNode == null) {
			tail = newNode;
		}
	}
	
	//Removes and returns the first element from this list.
	public String removeFirst() {
		//
		Node firstNode = head;
		head = firstNode.nextNode;
		String returnData = firstNode.data;
		firstNode = null;
		size--;
		
		return returnData;
	}
	
	//Removes and returns the last element from this list.
	public String removeLast() {
		//
		Node lastNode = tail;
		tail = foundNode(size-1);
		String returnData = lastNode.data;
		lastNode = null;
		size--;
		
		return returnData;
	}
	
	//Retrieves and removes the head (first element) of this list.
	public String remove() {
		//
		return this.removeFirst();
	}
	
	//Removes the element at the specified position in this list.
	public String remove(int index) {
		//
		if (index == 0) {
			return removeFirst();
		}
		
		Node prevNode = this.foundNode(index-1);
		Node targetNode = prevNode.nextNode;
		prevNode.nextNode = targetNode.nextNode;
		String returnData = targetNode.data;
		
		if (targetNode == tail) {
			tail = prevNode;
		}
		
		targetNode = null;
		size--;
		
		return returnData;
	}
	
	//Returns the element at the specified position in this list.
	public String get(int index) {
		Node nodeFound = foundNode(index);
		return nodeFound.data;
	}
	
	//Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
	public int indexOf(Object o) {
		//
		Node node = head;
		int index = 0;
		
		while (node.data != o) {
			node = node.nextNode;
			index++;
			if (node == null) {
				return -1;
			}
		}
		return index;
	}
	
	//Replaces the element at the specified position in this list with the specified element.
	public String set(int index, String element) {
		//
		String prevElement;
		Node targetNode = this.foundNode(index);
		prevElement = targetNode.data;
		targetNode.data = element;
		
		return prevElement;
	}
	
	//Removes all of the elements from this list.
	public void clear() {
		//
		Node node = head;
		while (node.nextNode != null) {
			node = node.nextNode;
			this.remove();
		}
		this.remove();
	}
	
	public int size() {
		//
		return this.size;
	}
}