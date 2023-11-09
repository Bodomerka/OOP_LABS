package org.example;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        List<String> delimiters = new ArrayList<>();
        delimiters.add(",");
        delimiters.add("\n");
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            if (delimiterIndex != -1) {
                String customDelimiter = numbers.substring(2, delimiterIndex);
                delimiters.add(customDelimiter);
                numbers = numbers.substring(delimiterIndex + 1);
            }
        }
        for (String delimiter : delimiters) {
            numbers = numbers.replace(delimiter, ",");
        }

        // Перевірка на два роздільники підряд
        if (numbers.contains(",,") || numbers.contains(",\n") || numbers.contains("\n,")) {
            throw new IllegalArgumentException("Некоректний ввід: введення не повинно містити два роздільники підряд");
        }

        if (numbers.endsWith(",\n") || numbers.endsWith("\n,") || numbers.endsWith(",")) {
            throw new IllegalArgumentException("Некоректний ввід: введення не повинно закінчуватися на ',\\n', '\\n,' або ','");
        }

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


