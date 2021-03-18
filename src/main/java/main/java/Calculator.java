package main.java;

public class Calculator {
    public static int Add(String inputString) {
        if(checkIfStringEmpty(inputString)){
            return 0;
        }
        if(inputString.length() == 1){
            return Integer.parseInt(inputString);
        }
        else{

            return 0;
        }
    }

    private static boolean checkIfStringEmpty(String inputString) {
        return inputString.isEmpty();
    }
}
