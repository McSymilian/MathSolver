package org.simplecalc.exception;


import org.simplecalc.operator.function.AdvancedFunctionType;
import org.simplecalc.operator.function.SimpleFunctionType;

public class FunctionException extends MathException{
    public FunctionException() {
        super();
    }

    public FunctionException(String message) {
        super(message);
    }

    public FunctionException(SimpleFunctionType functionType, Integer argsNumber) {
        super(functionType.name() + " doesnt support given number of arguments: " + argsNumber);
    }

    public FunctionException(AdvancedFunctionType functionType, Integer argsNumber) {
        super(functionType.name() + " doesnt support given number of arguments: " + argsNumber);
    }

    public FunctionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FunctionException(Throwable cause) {
        super(cause);
    }
}
