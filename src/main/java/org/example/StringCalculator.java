package org.example;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        String[] numberArray = numbers.split(",");
        if (numberArray.length > 2) {
            throw new IllegalArgumentException("Up to 2 numbers separated by comma are allowed");
        }
        int sum = 0;
        for (String numberStr : numberArray) {
            sum += Integer.parseInt(numberStr);
        }
        return sum;
    }
}
