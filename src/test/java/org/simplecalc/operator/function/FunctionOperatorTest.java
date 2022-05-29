package org.simplecalc.operator.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.simplecalc.exception.FunctionException;

class FunctionOperatorTest {

    @Test
    public void simpleTestSet() throws FunctionException {
        Assertions.assertEquals(1, new FunctionOperator("sin90").getResult());
        Assertions.assertEquals(2, new FunctionOperator("2surd4").getResult());
    }

}