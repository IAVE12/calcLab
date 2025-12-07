package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class RpnEvaluatorTest {

    @Test
    void evaluateSimpleRpn() {
        List<Token> tokens = Tokenizer.tokenize("2+3*4");
        List<Token> rpn = RpnConverter.toRpn(tokens);
        double result = RpnEvaluator.evaluate(rpn);
        assertEquals(14.0, result, 1e-9);
    }

    @Test
    void evaluatePowerOperator() {
        List<Token> tokens = Tokenizer.tokenize("2^3^2"); // 2^(3^2)=2^9=512
        List<Token> rpn = RpnConverter.toRpn(tokens);
        double result = RpnEvaluator.evaluate(rpn);
        assertEquals(512.0, result, 1e-9);
    }

    @Test
    void divisionByZeroThrows() {
        List<Token> tokens = Tokenizer.tokenize("1/0");
        List<Token> rpn = RpnConverter.toRpn(tokens);
        assertThrows(ArithmeticException.class, () -> RpnEvaluator.evaluate(rpn));
    }
}
