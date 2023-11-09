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

        // Parse custom delimiters
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            if (delimiterIndex != -1) {
                String delimiterSection = numbers.substring(2, delimiterIndex);
                Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(delimiterSection);
                while (m.find()) {
                    delimiters.add(Pattern.quote(m.group(1)));
                }
                numbers = numbers.substring(delimiterIndex + 1);
            }
        }

        // Check if the input ends with any of the delimiters
        for (String delimiter : delimiters) {
            if (numbers.endsWith(delimiter)) {
                throw new IllegalArgumentException("Некоректний ввід: введення не повинно закінчуватися на роздільник");
            }
        }

        String regex = String.join("|", delimiters);
        String[] numberArray = numbers.split(regex);

        int sum = 0;
        List<Integer> negativeNumbers = new ArrayList<>();
        for (String numberStr : numberArray) {
            if (!numberStr.trim().isEmpty()) {
                int number = Integer.parseInt(numberStr.trim());
                if (number < 0) {
                    negativeNumbers.add(number);
                } else if (number <= 1000) {
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
