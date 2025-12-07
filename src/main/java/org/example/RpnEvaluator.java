package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public final class RpnEvaluator {

    private RpnEvaluator() {
    }

    public static double evaluate(List<Token> rpnTokens) {
        Deque<Double> stack = new ArrayDeque<>();

        for (Token token : rpnTokens) {
            if (token.getType() == TokenType.NUMBER) {
                stack.push(Double.parseDouble(token.getValue()));
            } else if (token.getType() == TokenType.OPERATOR) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Not enough operands for operator " + token.getValue());
                }
                double b = stack.pop();
                double a = stack.pop();
                double result;
                switch (token.getValue()) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        if (b == 0.0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        result = a / b;
                        break;
                    case "^":
                        result = Math.pow(a, b);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operator: " + token.getValue());
                }
                stack.push(result);
            } else {
                throw new IllegalStateException("Unexpected token in RPN: " + token);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid RPN expression, stack size = " + stack.size());
        }

        return stack.pop();
    }
}
