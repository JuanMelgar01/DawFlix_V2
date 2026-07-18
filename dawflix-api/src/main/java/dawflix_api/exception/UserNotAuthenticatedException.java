package dawflix_api.exception;

public class UserNotAuthenticatedException extends RuntimeException {

    public UserNotAuthenticatedException(String message){
        super(message);
    }
    
}
