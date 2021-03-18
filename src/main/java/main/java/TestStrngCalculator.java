package main.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestStrngCalculator {

    @Test
    public void returnsIfZeroLengthStringFound(){
        assertEquals(Calculator.Add(""),0);
    }

    @Test
    public void returnsIfOneLengthStringFound(){
        assertEquals(Calculator.Add("1"),1);
    }


    @Test
    public void returnsSumOfTwoNumbersCommaSeperated(){
        assertEquals(Calculator.Add("1,2"),3);
    }

    @Test
    public void returnsSumOfTwoNumbersNewLineSeperated(){
        assertEquals(Calculator.Add("1\n2"),3);
    }

}
