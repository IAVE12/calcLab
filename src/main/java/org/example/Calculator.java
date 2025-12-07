package org.example;


import java.util.List;

public final class Calculator {

    private Calculator() {
    }

    public static List<Token> toRpn(String expression) {
        List<Token> tokens = Tokenizer.tokenize(expression);
        return RpnConverter.toRpn(tokens);
    }

    public static double calculate(String expression) {
        List<Token> rpn = toRpn(expression);
        return RpnEvaluator.evaluate(rpn);
    }
}
