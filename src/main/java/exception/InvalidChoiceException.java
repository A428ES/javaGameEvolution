package exception;

public class InvalidChoiceException extends IllegalArgumentException {
    public InvalidChoiceException(String message) {
        super(message);
    }
}
