package main.java;

public class Calculator {
    private static String deliminators = ",|\n";
    private static int countAddMethodCalls = 0;
    public static int Add(String inputString) {
        countAddMethodCalls++;
//        GetCalledCount();
        if(checkIfStringEmpty(inputString)){
            return 0;
        }
        if(inputString.length() == 1){
            return Integer.parseInt(inputString);
        }
        else{
            if(inputString.startsWith("//")){
                deliminators = Character.toString(inputString.charAt(2));
                String customDelimiterStringNumbers = inputString.substring(4);
                return AddHelper(customDelimiterStringNumbers, deliminators);
            }
            String[] numberSplit = inputString.split(deliminators);
            int sum = 0;
            for (String addMe : numberSplit) {
                sum += convertStringToInteger(addMe.trim());
            }
            return sum;
        }
    }

    private static int GetCalledCount() {
        return countAddMethodCalls;
    }

    private static int AddHelper(String customDelimiterStringNumbers, String delimiters) {
        int ans = 0;
        String[] arrayString = customDelimiterStringNumbers.split(delimiters);
        for(String number : arrayString){
            ans += Integer.parseInt(number);
        }
        return ans;
    }

    private static int convertStringToInteger(String convertMeToInt) {
        if(convertMeToInt.isEmpty()){
            return 0;
        }
        return Integer.parseInt(convertMeToInt);
    }

    private static boolean checkIfStringEmpty(String inputString) {
        return inputString.isEmpty();
    }

    class customExceptionNegative extends Exception{

    }
}
