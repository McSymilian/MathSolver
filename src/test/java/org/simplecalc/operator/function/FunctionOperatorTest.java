package org.simplecalc.operator.function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.simplecalc.exception.MathException;

class FunctionOperatorTest {

    @Test
    public void simpleTestSet() throws MathException {
        Assertions.assertEquals(1, new FunctionOperator("sin90").getResult());
        Assertions.assertEquals(2, new FunctionOperator("2surd4").getResult());
    }

}