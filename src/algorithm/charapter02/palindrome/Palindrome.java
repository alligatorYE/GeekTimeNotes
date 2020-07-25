package algorithm.charapter02.palindrome;

public class Palindrome {
    public boolean isPalindrome(String str) {
        //1.filter NonNumber and NonChar;   2.reverse and compare
        String filteredStr = filterNonNumberAndNonChar(str);

        return reverseString(filteredStr).equalsIgnoreCase(filteredStr);
    }

    private String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private String filterNonNumberAndNonChar(String str) {
        return str.replaceAll("[^A-Za-z0-9]","");
    }
}
