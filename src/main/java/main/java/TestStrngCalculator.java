package main.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStrngCalculator {

    @Test
    public void returnsIfZeroLengthStringFound() throws Exception {
        assertEquals(Calculator.Add(""),0);
    }

    @Test
    public void returnsIfOneLengthStringFound() throws Exception {
        assertEquals(Calculator.Add("1"),1);
    }

    @Test
    public void returnsSumOfTwoNumbersCommaSeperated() throws Exception {
        assertEquals(Calculator.Add("1,2"),3);
    }

    @Test
    public void returnsSumOfTwoNumbersNewLineSeperated() throws Exception {
        assertEquals(Calculator.Add("1\n2"),3);
    }

    @Test
    public void returnsSumOfMultipleNumbersWhichHasCustomDelimiterSeperated() throws Exception {
        assertEquals(Calculator.Add("//;\n1;2"),3);
    }


    @Test
    public void ignoreIfNumberIsGreaterThan1000() throws Exception {
        assertEquals(Calculator.Add("2,1001"),2);
    }

    @Test
    public void checkForSingleDelimiterDynamicSize() throws Exception {
        assertEquals(Calculator.Add("//[***]\n1***2***3"),6);
        assertEquals(Calculator.Add("//[ddd]\n1ddd2ddd3"),6);
    }

    @Test
    public void checkForMultipleDelimiterDynamicSize() throws Exception {
        assertEquals(Calculator.Add("//[***][$$$][%][;]\n1***2$$$3%4;5"),15);
    }

}
