package by.victor.jwd.parser.functions;

import by.victor.jwd.entity.Text;
import by.victor.jwd.parser.RequestFunction;

import java.util.List;

public class LongestPalindromeFunction implements RequestFunction {
    @Override
    public String apply(Text textObject, String params) {
        List<String> sentences = textObject.toStringList();
        String longest_palindrome = "";
        for (String sentence : sentences) {
            String current_palindrome = findTheLongestPalindrome(sentence);
            if (current_palindrome.length() > longest_palindrome.length()) {
                longest_palindrome = current_palindrome;
            }
        }
        return findTheLongestPalindrome(textObject.getTextForm());
    }

    private static String findTheLongestPalindrome(String str){

        String longestPalindrome = String.valueOf(str.charAt(0));
        for (int i = 0; i < str.length() - 1; i++) {
            String returnedPalindrome = findLongestPalindromeWithParams(str, i, i);
            if (returnedPalindrome.length() > longestPalindrome.length()) {
                longestPalindrome = returnedPalindrome;
            }
            returnedPalindrome = findLongestPalindromeWithParams(str, i, i + 1);
            if (returnedPalindrome.length() > longestPalindrome.length()) {
                longestPalindrome = returnedPalindrome;
            }
        }
        return longestPalindrome;
    }

    private static String findLongestPalindromeWithParams(String str, int left, int right) {
        if (left > right)
            return null;

        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return str.substring(left + 1, right);
    }
}
