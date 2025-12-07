package org.example;

import java.util.List;
import java.util.Scanner;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("RPN калькулятор");
        System.out.println("Поддерживаемые операции: + - * / ^ и скобки ()");
        System.out.println("Введите выражение или 'exit' чтобы выйти.");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if (line == null) {
                break;
            }
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            if ("exit".equalsIgnoreCase(line)) {
                System.out.println("Выход.");
                break;
            }

            try {
                // Шаг 1: покажем токены
                List<Token> tokens = Tokenizer.tokenize(line);
                System.out.println("Токены: " + tokens);

                // Шаг 2: покажем ОПН
                List<Token> rpn = RpnConverter.toRpn(tokens);
                System.out.print("ОПН: ");
                for (Token t : rpn) {
                    System.out.print(t.getValue() + " ");
                }
                System.out.println();

                // Шаг 3: посчитаем результат
                double result = RpnEvaluator.evaluate(rpn);
                System.out.println("Результат: " + result);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
