package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        List<String> delimiters = new ArrayList<>();
        delimiters.add(",");
        delimiters.add("\n");

        // Перевірка на наявність визначеного користувачем роздільника
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            if (delimiterIndex != -1) {
                String delimiterSection = numbers.substring(2, delimiterIndex);
                Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(delimiterSection);
                while (m.find()) {
                    delimiters.add(Pattern.quote(m.group(1))); // Додаємо усі роздільники між квадратними дужками
                }
                numbers = numbers.substring(delimiterIndex + 1); // Оновлюємо рядок чисел, видаляючи частину з роздільником
            }
        }

        // Перевірка на роздільники, які йдуть підряд
        for (String delimiter : delimiters) {
            if (numbers.contains(delimiter + delimiter)) {
                throw new IllegalArgumentException("Некоректний ввід: введення не повинно містити два роздільники підряд");
            }
        }

        // Заміна всіх роздільників на стандартний для спрощення подальшого розділення
        String regex = String.join("|", delimiters);
        String[] numberArray = numbers.split(regex); // Розділяємо вхідний рядок за допомогою всіх роздільників

        int sum = 0;
        List<Integer> negativeNumbers = new ArrayList<>();
        for (String numberStr : numberArray) {
            if (!numberStr.trim().isEmpty()) {
                int number = Integer.parseInt(numberStr.trim());
                if (number < 0) {
                    negativeNumbers.add(number);
                } else if (number <= 1000) { // Ігноруємо числа більше 1000
                    sum += number;
                }
            }
        }

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("Недозволені від’ємні числа: " + negativeNumbers.toString());
        }

        return sum;
    }
}
