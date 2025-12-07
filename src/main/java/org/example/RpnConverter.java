package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public final class RpnConverter {

    private RpnConverter() {
    }

    public static List<Token> toRpn(List<Token> infixTokens) {
        List<Token> output = new ArrayList<>();
        Deque<Token> stack = new ArrayDeque<>();

        for (Token token : infixTokens) {
            switch (token.getType()) {
                case NUMBER:
                    output.add(token);
                    break;
                case OPERATOR:
                    while (!stack.isEmpty()
                            && stack.peek().getType() == TokenType.OPERATOR
                            && shouldPopBefore(token.getValue(), stack.peek().getValue())) {
                        output.add(stack.pop());
                    }
                    stack.push(token);
                    break;
                case LEFT_PAREN:
                    stack.push(token);
                    break;
                case RIGHT_PAREN:
                    while (!stack.isEmpty()
                            && stack.peek().getType() != TokenType.LEFT_PAREN) {
                        output.add(stack.pop());
                    }
                    if (stack.isEmpty() || stack.peek().getType() != TokenType.LEFT_PAREN) {
                        throw new IllegalArgumentException("Mismatched parentheses");
                    }
                    stack.pop(); // remove '('
                    break;
                default:
                    throw new IllegalStateException("Unexpected token type: " + token.getType());
            }
        }

        while (!stack.isEmpty()) {
            Token t = stack.pop();
            if (t.getType() == TokenType.LEFT_PAREN || t.getType() == TokenType.RIGHT_PAREN) {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            output.add(t);
        }

        return output;
    }

    private static boolean shouldPopBefore(String currentOp, String stackOp) {
        int p1 = precedence(currentOp);
        int p2 = precedence(stackOp);
        if (isLeftAssociative(currentOp)) {
            return p1 <= p2;
        } else {
            return p1 < p2;
        }
    }

    private static boolean isLeftAssociative(String op) {
        return !("^".equals(op));
    }

    private static int precedence



            (String op) {
        switch (op) {
            case "+":
            case "-":
                return 1; // низший
            case "*":
            case "/":
                return 2;
            case "^":
                return 3; // высший
            default:
                throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }
}
