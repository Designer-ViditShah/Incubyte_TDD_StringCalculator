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
            String[] numberSplit = inputString.split(",|\n");
            int sum = 0;
            for(int i = 0; i < numberSplit.length; i++){
                sum += convertStringToInteger(numberSplit[i]);
            }
            return sum;
        }
    }

    private static int convertStringToInteger(String convertMeToInt) {
        return Integer.parseInt(convertMeToInt);
    }

    private static boolean checkIfStringEmpty(String inputString) {
        return inputString.isEmpty();
    }
}
