package javastory.budget.util;

public class NoSuchCashBookException extends RuntimeException {
	//
	private static final long serialVersionUID = 9151435541549342709L;
	
	public NoSuchCashBookException(String message) {
		super(message);
	}
}
