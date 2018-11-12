public class Atoi {
    public int myAtoi(String str) {

        int sign = 1;

        int i = 0;

        while (i < str.length()) {
            char c = str.charAt(i);
            // System.out.println(c);
            if (c == ' ') {
                i++;
            } else if (c == '-') {
                sign = -1;
                i++;
                break;
            } else if (c == '+') {
                i++;
                break;
            } else if (Character.isLetter(c)) {
                return 0;
            } else {
                break;
            }
        }

        long ans = 0;
        while (i < str.length()) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                int num = Character.getNumericValue(c);
                ans = ans * 10 + num;
            } else {
                break;
            }
            i++;
            // System.out.println(ans);
            if (sign < 0 && ans > Integer.MAX_VALUE) {
                return Integer.MIN_VALUE;
            } else if (ans > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
        }

        // System.out.println("dsfds"+Integer.MIN_VALUE);
        return (int) (ans * sign);

    }
}