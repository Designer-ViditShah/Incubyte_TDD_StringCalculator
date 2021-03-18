package main.java;

public class Calculator {
    public static int Add(String inputString) {
        if(inputString.isEmpty()){
            return 0;
        }
        else{
            return Integer.parseInt(inputString);
        }
    }
}
