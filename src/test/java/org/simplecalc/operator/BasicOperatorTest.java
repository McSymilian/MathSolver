package org.simplecalc.operator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.simplecalc.exception.MathException;

class BasicOperatorTest {
    @Test
    public void addTest() throws MathException {
        Assertions.assertEquals(2, new BasicOperator("1+1").getResult());
        Assertions.assertEquals(0, new BasicOperator("1+-1").getResult());
        Assertions.assertEquals(-4, new BasicOperator("-2+-2").getResult());
        Assertions.assertEquals(2.5, new BasicOperator("0,5+2").getResult());
        Assertions.assertEquals(-3.5, new BasicOperator("-4+0,5").getResult());
    }
    @Test
    public void subtractionTest() throws MathException {
        Assertions.assertEquals(0, new BasicOperator("1-1").getResult());
        Assertions.assertEquals(-1, new BasicOperator("-1-0").getResult());
        Assertions.assertEquals(0, new BasicOperator("-2--2").getResult());
        Assertions.assertEquals(-1.5, new BasicOperator("0,5-2").getResult());
        Assertions.assertEquals(4.5, new BasicOperator("4--0,5").getResult());
    }
    @Test
    public void divisionTest() throws MathException {
        Assertions.assertEquals(1, new BasicOperator("1/1").getResult());
        Assertions.assertEquals(0, new BasicOperator("0/1").getResult());
        Assertions.assertEquals(2.5/2, new BasicOperator("2.5/2").getResult());
        Assertions.assertEquals(0.5/-2, new BasicOperator("0,5/-2").getResult());
        Assertions.assertEquals(-4/-0.5, new BasicOperator("-4/-0,5").getResult());
    }
    @Test
    public void multiplyTest() throws MathException {
        Assertions.assertEquals(1, new BasicOperator("1*1").getResult());
        Assertions.assertEquals(0, new BasicOperator("1*0").getResult());
        Assertions.assertEquals(-4, new BasicOperator("2*-2").getResult());
        Assertions.assertEquals(-1, new BasicOperator("-0,5*2").getResult());
        Assertions.assertEquals(2, new BasicOperator("-4*-0,5").getResult());
    }
    @Test
    public void powerTest() throws MathException {
        Assertions.assertEquals(1, new BasicOperator("1^1").getResult());
        Assertions.assertEquals(1, new BasicOperator("1^0").getResult());
        Assertions.assertEquals(4, new BasicOperator("2^2").getResult());
        Assertions.assertEquals(1/4.0, new BasicOperator("0,5^2").getResult());
        Assertions.assertEquals(2, new BasicOperator("4^0,5").getResult());
    }
}