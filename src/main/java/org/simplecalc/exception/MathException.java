package org.simplecalc.exception;

import java.io.IOException;

public class MathException extends IOException {
    public MathException() {
        super("Syntax Error");
    }

    public MathException(String message) {
        super("Syntax Error: " + message);
    }

    public MathException(String message, Throwable cause) {
        super("Syntax Error: " + message, cause);
    }

    public MathException(Throwable cause) {
        super(cause);
    }
}
