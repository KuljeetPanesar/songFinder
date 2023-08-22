package kuljeet.panesar.songFinder.exception;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 4179601271376763616L;

	public UserAlreadyExistsException(String message) {
		super(message);
	}

	
}
