package org.simplecalc.exception;


public class FunctionException extends MathException{
    public FunctionException() {
        super();
    }

    public FunctionException(String message) {
        super(message);
    }

    public FunctionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FunctionException(Throwable cause) {
        super(cause);
    }
}
