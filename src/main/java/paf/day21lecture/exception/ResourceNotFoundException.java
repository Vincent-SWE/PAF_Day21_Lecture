package paf.day21lecture.exception;


public class ResourceNotFoundException extends RuntimeException{

    // We can write custom error messages.
    // We don't need to use the default error messages that will be thrown.
    
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}
