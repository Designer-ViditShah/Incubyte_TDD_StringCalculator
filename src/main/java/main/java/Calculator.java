package main.java;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Calculator {
/*
countAddMethodCalls -> used to count number of times Add() function is called
deliminators        -> used as basic delimiter in case of string does not start with //
START_INDEX_OFFSET  -> In case the string starts with //[ so custom delimiter will always start from 3 index
startIndex          -> it gets updated for each [delimiter] in the string
Pattern and Matcher helps to deal with the escape and the dangling characters that are used in the regular expression
Few dangling characters are * [ \ etc.
 */
    private static int countAddMethodCalls = 0;
    private static String deliminators = ",|\\n";
    public static final int START_INDEX_OFFSET = 3;
    private static Pattern customDelimitersValidator = Pattern.compile("//(\\[(\\D+)])+\n.*");
    private static Matcher customDelimitersMatcher;
    private static int startIndex;
/*
    Add function is called with string as an argument
    Since incorrect argument's have been already dealt as mentioned in the question
    Add function will throw an exception only in case of negative values
    Case for empty string
    Case for length one string
    Case for 2 number separated with delimiter like , and \n
    Case to ignore if the number is greater than 1000
    Case for custom delimiter
        -> it may start with [
            = find the string after \n and save it to some memory
            = find the string after // and before \n and save it to memory with name delimiterString
            = In delimiterString
                ~ replace the first [ with empty string
                ~ replace all other [ with | symbol if it exits - this will take care for multiple delimiters in regex
                ~ replace all ] with empty string
                ~ using the Pattern compile We will compile the updated string and add each delimiter to deliminators

        -> it has only one custom delimiter and does not start with [
            = find the delimiter at the 2nd index starting from 0
            = split the string after \n based on the delimiter found
 */
    public static int Add(String inputString) throws Exception {
        countAddMethodCalls++;
        if(checkIfStringEmpty(inputString)){
            return 0;
        }
        if(inputString.length() == 1){
            return convertStringToInteger(inputString);
        }
        else{
            if(inputString.startsWith("//")){
                String allDelimiter = inputString.substring(2,inputString.indexOf("\n"));
                String customDelimiterStringNumbers = inputString.substring(inputString.indexOf("\n")+1);
                return AddHelper(customDelimiterStringNumbers,allDelimiter);
            }
            else{
                return AddHelper(inputString,deliminators);
            }
        }
    }

    private static boolean checkIfStringEmpty(String inputString) {
        return inputString.isEmpty();
    }
/*
    convertStringToInteger method simply converts the string value to integer value
    note: testcase have been made such that the string will only contain "0" to "9" in any order
 */
    private static int convertStringToInteger(String convertMeToInt) {
        return Integer.parseInt(convertMeToInt);
    }
    private static int AddHelper(String customDelimiterStringNumbers, String delimiters) throws Exception {
        int ans = 0;
        String listOfNegativeNumbers = "";
        if(delimiters.equals(",|\\n"))
            delimiters = ",|\n";
        String[] arrayString = customDelimiterStringNumbers.split(delimiters);
        for(String number : arrayString){
            if(convertStringToInteger(number)<=1000 && convertStringToInteger(number) >= 0) {
                ans += Integer.parseInt(number);
            }else if (convertStringToInteger(number)<0){
                listOfNegativeNumbers += number + " ";
            }
        }
        if(listOfNegativeNumbers.length()>0){
            throw new IllegalArgumentException("negative not allowed "+listOfNegativeNumbers);
        }
        return ans;
    }


}
