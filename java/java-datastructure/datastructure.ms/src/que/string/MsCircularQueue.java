package que.string;

public class MsCircularQueue<E> {
	//
	private static final int DEFAULT_LENGTH_VALUE = 10;
	private String[] repo;
	private int length;
	private int head;
	private int tail;
	
	public MsCircularQueue() {
		//
		this(DEFAULT_LENGTH_VALUE);
	}
	
	public MsCircularQueue(int length) {
		this.length = length;
		repo = new String[length];
		head = 0; 
		tail = 0;
	}
	
	//Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions, returning true upon success and throwing an IllegalStateException if no space is currently available.
	public boolean	add(String element) {
		//
		try {
			if (tail == length) {
				tail = 0;
//				System.out.println("tail changed to 0");
			}
			if (repo[tail] != null) {
				throw new IllegalStateException("no space is currently available");
			}
			repo[tail++] = element;
			return true;
		} catch (IllegalStateException e1) {
			System.out.println(e1.getMessage());
			return false;
		}
	}
	
	//Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
	public String peek() {
		//
		if (repo[head] == null) {
			return null;
		}
		return repo[head];
	}
	
	//Retrieves and removes the head of this queue, or returns null if this queue is empty.
	public String poll() {
		//
		String item = repo[head];
		if (item == null) {
			return null;
		}
		repo[head++] = null;
		if (head == length) {
			head = 0;
		}
		return item;
	}
	
	public int getLength() {
		//
		return length;
	}
}