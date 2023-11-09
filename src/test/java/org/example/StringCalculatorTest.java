package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    @Test
    public void testAddEmptyString() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void testAddOneNumber() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(1, calculator.add("1"));
    }

    @Test
    public void testAddTwoNumbers() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("1,2"));
    }
    @Test
    public void testAddMultipleNumbers() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1,2,3"));
    }
    @Test
    public void testAddWithMixedNumbers() {
        StringCalculator calculator = new StringCalculator();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1,-2,3,-1,3")
        );
        assertEquals("Недозволені від’ємні числа: [-2, -1]", exception.getMessage());
    }
    @Test
    public void testAddWithNewline() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    public void testAddWithInvalidInput() {
        StringCalculator calculator = new StringCalculator();
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("1,\n");
        });
    }
    @Test
    public void testtestWithDelimiterInput() {
        StringCalculator calculator = new StringCalculator();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("//[*]\n1*2*-3")
        );
        assertEquals("Недозволені від’ємні числа: [-3]", exception.getMessage());
    }

    @Test
    public void testAddWithNegativeNumbers() {
        StringCalculator calculator = new StringCalculator();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1,-2,3,-4")
        );
        assertEquals("Недозволені від’ємні числа: [-2, -4]", exception.getMessage());
    }

    @Test
    public void testAddWithNumbersGreaterThan1000() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(2, calculator.add("1001,2"));
    }
    @Test
    public void testAddWithNumbersGreaterThan1000_2() {
        StringCalculator calculator = new StringCalculator();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1001,2,-999")
        );
        assertEquals("Недозволені від’ємні числа: [-999]", exception.getMessage());
    }
    @Test
    public void testLongCustomDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("//[***]\n1***2***3"));
    }
    @Test
    public void testLongCustomDelimiter_2() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(10, calculator.add("//[*****]\n1*****2*****3,2\n2"));
    }
    @Test
    public void testLongCustomDelimiterNegative() {
        StringCalculator calculator = new StringCalculator();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("//[*****]\n1*****-2*****3,2\n2")
        );
        assertEquals("Недозволені від’ємні числа: [-2]", exception.getMessage());
    }
    @Test
    public void testLongCustomDelimiterSeveralNegative() {
        StringCalculator calculator = new StringCalculator();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("//[*****][******][*]\n1******2*-3,2\n2*3*****2,5")
        );
        assertEquals("Недозволені від’ємні числа: [-3]", exception.getMessage());
    }
    @Test
    public void testLongCustomDelimiterSeveral() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(20, calculator.add("//[*****][******][*]\n1******2*3,2\n2*3*****2,5"));
    }
    @Test
    public void testLongCustomDelimiterSeveral_2() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(30, calculator.add("//[*****][******][*][~]\n1******2*3,2\n2*3*****2,5~4******2*****4"));
    }

}
