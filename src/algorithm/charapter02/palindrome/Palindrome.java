package algorithm.charapter02.palindrome;

public class Palindrome {
    public boolean isPalindrome(String str) {
        //1.filter non-numeric and non-letter;   2.reverse and compare
        String filteredStr = filterNonNumericAndNonLetter(str);

        return reverseString(filteredStr).equalsIgnoreCase(filteredStr);
    }

    private String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private String filterNonNumericAndNonLetter(String str) {
        return str.replaceAll("[^A-Za-z0-9]","");
    }

    public static void main(String[] args) {
        String str = "adf er ,tt :ref ; da";
        Palindrome palindrome = new Palindrome();
        if (palindrome.isPalindrome(str)){
            System.out.println("YES! " + str + " is a palindrome.");
        }else {
            System.out.println("NO! " + str + " is not a palindrome.");
        }
    }
}
