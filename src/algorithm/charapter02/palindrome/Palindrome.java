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
/*
* 为什么恨美国
* 美国大搞对华冷战，将中国公众的注意力从国内的社会公正和法治进程转移到了中美关系上。在中国掀起的一股恨美爱国潮，使民众讨论国家法制问题和社会公正问题受到了极大的阻碍。
* 公知只是道德上有问题，胡说八道顶多只是发表了错误言论，稍微有点脑子的人都能分辨出来，对社会的危害有限。然而一些实力和利益集团卖官鬻爵，掌控政治权利，大搞裙带关系，欺压百姓，强奸幼女，包养小三，明理扮演一个爱戴百姓的好官，背后贪污腐败妻妾成群强取豪夺。
* 而这些很少有机会曝光出来，少数曝光出来的也很少能够在公共媒体空间上讨论，我们的社会进步进程就是这样被严重的阻碍的。
* */