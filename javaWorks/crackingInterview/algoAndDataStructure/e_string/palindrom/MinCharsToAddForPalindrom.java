public class MinCharsToAddForPalindrom {

    public int solve(String A) {
        String copyString = new StringBuilder(A).reverse().toString();
        int i = 0;
        int count = 0;
        while (true) {
            if (isPanlindrome(copyString.substring(0, i) + A)) {
                break;
            }
            count++;
            i++;
        }

        return count;

    }

    private boolean isPanlindrome(String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }
}