package org.example;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        if (numbers.endsWith(",\n") || numbers.endsWith("\n,")) {
            throw new IllegalArgumentException("Некоректний ввід: введення не повинно закінчуватися на ',\\n' або '\\n,'");
        }

        // Перевірка на два роздільники підряд
        if (numbers.contains(",,") || numbers.contains(",\n") || numbers.contains("\n,")) {
            throw new IllegalArgumentException("Некоректний ввід: введення не повинно містити два роздільники підряд");
        }

        numbers = numbers.replace("\n", ",");

        String[] numberArray = numbers.split(",");

        int sum = 0;
        for (String numberStr : numberArray) {
            if (!numberStr.trim().isEmpty()) {
                sum += Integer.parseInt(numberStr.trim());
            }
        }

        return sum;
    }

}

