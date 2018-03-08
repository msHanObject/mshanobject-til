package javastory.club.util.exception;

public class PostingDuplicationException extends RuntimeException{
	//
	private static final long serialVersionUID = 371232828020326656L;
	
	public PostingDuplicationException(String message) {
		super(message);
	}
}
