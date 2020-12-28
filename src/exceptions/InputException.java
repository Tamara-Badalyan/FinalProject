package exceptions;

public class InputException extends RuntimeException {
    public InputException() {

    }

    @Override
    public String toString() {
        return "Invalid input";
    }
}
