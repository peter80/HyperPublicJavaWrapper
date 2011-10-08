package singh.hyperpublic.exc;

@SuppressWarnings("serial")
public class ClientException extends RuntimeException {
	public ClientException(Exception e) {
		super(e);
	}
	
	public ClientException(String message) {
		super(message);
	}
}
