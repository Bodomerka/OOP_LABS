package org.example;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        String[] numberArray = numbers.split(",");
        int sum = 0;

        for (String numberStr : numberArray) {
            try {
                int number = Integer.parseInt(numberStr.trim());
                sum += number;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Input string can contain only numbers separated by comma", e);
            }
        }
        return sum;
    }
}
