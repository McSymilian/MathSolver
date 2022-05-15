package org.simplecalc.exception;

public class ParenthesesException extends MathException {
    public ParenthesesException() {
        super();
    }

    public ParenthesesException(String message) {
        super(message);
    }

    public ParenthesesException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParenthesesException(Throwable cause) {
        super(cause);
    }
}
