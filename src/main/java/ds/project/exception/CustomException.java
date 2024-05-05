package ds.project.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException{
    protected HttpStatus status;
    protected String message;

    @Override
    public String getMessage() {
        return message;
    }
}
