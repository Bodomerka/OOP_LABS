package org.example;

public class Main {
    public static void main(String[] args) {
        StringCalculator calculator = new StringCalculator();

        String input = "//[***][*][**]\n1***2\n3,5,1001***1*1\n1**-1";

        try {
            int result = calculator.add(input);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Сталася помилка при обчисленні: " + e.getMessage());
        }
    }
}