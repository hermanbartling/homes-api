package se.difo.hemnetapi.core.exception;

public class ApiValidationException extends RuntimeException {

    public ApiValidationException(String message) {
        super(message);
    }
}
