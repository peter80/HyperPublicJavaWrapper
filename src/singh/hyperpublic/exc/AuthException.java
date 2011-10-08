package singh.hyperpublic.exc;

@SuppressWarnings("serial")
public class AuthException extends RuntimeException {
	public AuthException(String message){
		super(message);
	}
}
