package exception;

public class ExitGameException extends IllegalArgumentException {
    public ExitGameException(String message) {
        super(message);
    }
}
