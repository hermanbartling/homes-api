package se.difo.hemnetapi.api.dto.common;

public class FailureRspDto {

    private String exception;
    private String message;


    public FailureRspDto(Exception e) {
        this.exception = e.getClass().getCanonicalName();
        this.message = e.getMessage();
    }

    public String getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }
}
