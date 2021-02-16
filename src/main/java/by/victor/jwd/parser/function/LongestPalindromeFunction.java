package by.victor.jwd.parser.function;

import by.victor.jwd.entity.Text;
import by.victor.jwd.parser.RequestFunction;

import java.util.List;

public class LongestPalindromeFunction implements RequestFunction {

    @Override
    public String apply(Text textObject, String params) {
        List<String> sentences = textObject.toStringList();
        String longestPalindrome = "";

        for (String sentence : sentences) {
            String currentPalindrome = findTheLongestPalindrome(sentence);
            if (currentPalindrome.length() > longestPalindrome.length()) {
                longestPalindrome = currentPalindrome;
            }
        }

        return longestPalindrome;
    }

    private static String findTheLongestPalindrome(String stringForSearch){
        String longestPalindrome = String.valueOf(stringForSearch.charAt(0));
        for (int i = 0; i < stringForSearch.length() - 1; i++) {
            String currentPalindrome = findLongestPalindromeWithParams(stringForSearch, i, i);
            if (currentPalindrome.length() > longestPalindrome.length()) {
                longestPalindrome = currentPalindrome;
            }
            currentPalindrome = findLongestPalindromeWithParams(stringForSearch, i, i + 1);
            if (currentPalindrome.length() > longestPalindrome.length()) {
                longestPalindrome = currentPalindrome;
            }
        }
        return longestPalindrome;
    }

    private static String findLongestPalindromeWithParams(String stringForSearch, int left, int right) {
        while (left >= 0 && right < stringForSearch.length() && stringForSearch.charAt(left) == stringForSearch.charAt(right)) {
            left--;
            right++;
        }
        return stringForSearch.substring(left + 1, right);
    }
}
