package stack.string;

public class MsStack<E> {
	//
	private static final int DEFAULT_LENGTH_VALUE = 10;
	private String[] repo;
	private int length;
	private int top;
	
	public MsStack() {
		//
		top = 0;
		length = DEFAULT_LENGTH_VALUE;
		repo = new String[length];
	}
	
	public MsStack(int length) {
		//
		top = 0;
		this.length = length;
		repo = new String[length];
	}
	
	public void push(String item) {
		//
		if (top < length) {			
			repo[top++] = (String) item;
		} else {
			System.err.println("MsStack is full.");
		}
	}
	
	public String pop() {
		//
		top--;
		String item = repo[top];
		if (item == null) {
			return null;
		}
		repo[top] = null;
		return item;
	}
	
	public boolean empty() {
		//
		if (repo[0] == null) {
			return true;
		}
		return false;
	}
	
	public String peek() {
		//
		return repo[top-1];
	}
	
	public int search(String item) {
		//
		int distance = 0;
		for (String s : repo) {
			distance++;
			if (s.equals(item)) {
				break;
			}
		}
		
		return repo.length - distance;
	}

	public int getLength() {
		return length;
	}
}