public class longestPalindrome {

    public String getLongestPalindrome(String s) {

        if (s.length() == 0) {
            return "";
        }

        int ans = 0;
        int ansStart = 0;
        int ansEnd = 0;

        int i = 0;
        while (i < s.length()) {

            int start = i;
            while (start >= 0 && s.charAt(i) == s.charAt(start)) {
                start--;
            }

            int end = i;
            while (end < s.length() && s.charAt(i) == s.charAt(end)) {
                end++;
            }

            while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
                start--;
                end++;
            }

            if (end - start > ans) {
                ans = end - start;
                ansStart = start + 1;
                ansEnd = end - 1;
            }

            i++;
        }

        return s.substring(ansStart, ansEnd + 1);
    }
}