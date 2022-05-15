package org.simplecalc.operator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.simplecalc.exception.MathException;

class BasicOperatorTest {
    //todo more test
    @Test
    public void powTest() throws MathException {
        Assertions.assertEquals(1, new BasicOperator("1^1").getResult());
        Assertions.assertEquals(1, new BasicOperator("1^0").getResult());
        Assertions.assertEquals(4, new BasicOperator("2^2").getResult());
        Assertions.assertEquals(1/4.0, new BasicOperator("0,5^2").getResult());
        Assertions.assertEquals(2, new BasicOperator("4^0,5").getResult());
    }
    @Test
    public void sqrTest() throws MathException {
        Assertions.assertEquals(2, new BasicOperator("2sqr4").getResult());
        Assertions.assertEquals(5, new BasicOperator("2sqr25").getResult());
        Assertions.assertEquals(5, new BasicOperator("3sqr125").getResult(), 0.0000001);
        Assertions.assertEquals(4, new BasicOperator("0,5sqr2").getResult());
        Assertions.assertEquals(4, new BasicOperator("1sqr4").getResult());
    }
}