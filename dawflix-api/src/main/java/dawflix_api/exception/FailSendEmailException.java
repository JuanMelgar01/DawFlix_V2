package dawflix_api.exception;

public class FailSendEmailException extends RuntimeException {
    public FailSendEmailException(String message) {
        super(message);
    }
}
