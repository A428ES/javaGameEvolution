package exception;


public class MissingResource extends RuntimeException {
    public MissingResource(String message) {
        super(message);
    }
}