package td7.api.exceptions;

public class ErrorResponse {

    public final String error;
    public final String message;

    public ErrorResponse(String error, String description) {
        this.error = error;
        this.message = description;
    }
}
