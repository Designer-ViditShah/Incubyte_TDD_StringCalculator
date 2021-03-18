package main.java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestStrngCalculator {

    @Test
    public void returnsIfZeroLengthStringFound(){
        assertEquals(Calculator.Add(""),0);
    }
}
