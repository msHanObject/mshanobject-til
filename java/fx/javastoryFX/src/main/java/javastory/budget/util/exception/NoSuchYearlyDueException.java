package javastory.budget.util.exception;

public class NoSuchYearlyDueException extends RuntimeException {
	//
	private static final long serialVersionUID = 6884166798550089271L;
	
	public NoSuchYearlyDueException(String message) {
		super(message);
	}
}
