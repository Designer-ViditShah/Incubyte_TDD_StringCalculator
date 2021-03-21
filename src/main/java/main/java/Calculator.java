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
Pattern and Matcher helps to deal with the escape and the dangling characters that are used in the regular expression
Few dangling characters are * [ \ etc.
 */
    private static int countAddMethodCalls = 0;
    private static String deliminators = ",|\\n";
    private static Pattern customDelimitersValidator = Pattern.compile("//(\\[(\\D+)])+\n.*");
    private static Matcher customDelimitersMatcher;

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
                if(allDelimiter.startsWith("[")){
                    List<String> numberList = getNumbersFromDelimiters(inputString,customDelimiterStringNumbers);
                    checkForNegativeNumbers(numberList);
                    return sumArray(numberList);
                }
                else {return AddHelper(customDelimiterStringNumbers,allDelimiter);}
            }
            else{ return AddHelper(inputString,deliminators); }
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
            if(convertStringToInteger(number)<=1000 && convertStringToInteger(number) >= 0)
                ans += Integer.parseInt(number);
            else if (convertStringToInteger(number)<0) listOfNegativeNumbers += number + " ";
        }
        if(listOfNegativeNumbers.length()>0)
            throw new IllegalArgumentException("negative not allowed " + listOfNegativeNumbers);
        return ans;
    }
    private static List<String> getNumbersFromDelimiters(String string, String numberString) {
        String delimiters = ",|\n";
        if (canTheseMatchWithTheRegexPattern(string)) {
            String customDelimiters = customDelimitersMatcher.group(1);
//            System.out.println("CustomDelimiters "+customDelimiters);

            String convertToRegExFormat = customDelimiters.replaceFirst("\\[", "")
                    .replaceAll("\\[", "|")
                    .replaceAll("]", "");
            delimiters += "|" + Pattern.compile("\\|").
                    splitAsStream(convertToRegExFormat).
                    map(Pattern::quote).
                    collect(Collectors.joining("|"));
        }
        return asList(numberString.split(delimiters));
    }
    /*
        Sum of the array can be done in two ways
        For case 1
            This method includes the concept to sum values in the List using filter and map function and sum function
        For case 2
            This method simply traverse the numbers and add them into a variable and return that variable
        Note that in both the case sum will be printed
    */
    private static int sumArray(List<String> numbersList) {
        return numbersList.stream()
                .filter(s -> Integer.parseInt(s) <= 1000)
                .mapToInt(Integer::parseInt)
                .sum();
    }
    /*
    canTheseMatchWithTheRegexPattern method
        returns true if the delimiter string matches the Pattern regex string mentioned above
        returns false if the delimiter string does not matches the Pattern regex string mentioned above
 */
    private static boolean canTheseMatchWithTheRegexPattern(String string) {
        customDelimitersMatcher = customDelimitersValidator.matcher(string);
        return customDelimitersMatcher.matches();
    }
    /*
    Negative values are handled in two ways
    For case if string delimiter starts with [
        This method includes the concept to filter out the List using filter and map function for the negative values
    For case if string delimiter does not starts with [
        This method simply traverse the numbers after splitting and add the number to the stringNegativeFound
    Note that in both the case all the negative values have been printed
 */
    private static void checkForNegativeNumbers(List<String> numbersList) {
        String negatives = numbersList.stream()
                .filter(s -> s.contains("-"))
                .collect(Collectors.joining(","));
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }
    }
    // This method is called to count number of times the add function has been called
    private static int GetCalledCount() { return countAddMethodCalls; }
}