package org.example;

import java.util.ArrayList;
import java.util.List;

public final class Tokenizer {

    private Tokenizer() {
    }

    public static List<Token> tokenize(String expression) {
        List<Token> tokens = new ArrayList<>();
        int i = 0;
        while (i < expression.length()) {
            char ch = expression.charAt(i);

            if (Character.isWhitespace(ch)) {
                i++;
                continue;
            }

            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder number = new StringBuilder();
                while (i < expression.length()) {
                    char c = expression.charAt(i);
                    if (Character.isDigit(c) || c == '.') {
                        number.append(c);
                        i++;
                    } else {
                        break;
                    }
                }
                tokens.add(new Token(TokenType.NUMBER, number.toString()));
                continue;
            }

            if ("+-*/^".indexOf(ch) >= 0) {
                tokens.add(new Token(TokenType.OPERATOR, String.valueOf(ch)));
                i++;
                continue;
            }

            if (ch == '(') {
                tokens.add(new Token(TokenType.LEFT_PAREN, "("));
                i++;
                continue;
            }

            if (ch == ')') {
                tokens.add(new Token(TokenType.RIGHT_PAREN, ")"));
                i++;
                continue;
            }

            throw new IllegalArgumentException("Unexpected character: " + ch);
        }
        return tokens;
    }
}
