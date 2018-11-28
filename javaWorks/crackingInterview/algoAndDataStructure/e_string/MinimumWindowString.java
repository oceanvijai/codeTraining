public class MinimumWindowString {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        int[] count = new int[256];
        int[] countT = new int[256];
        for (int i = 0; i < t.length(); i++) {
            countT[t.charAt(i)]++;
        }
        int j = 0;
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            while (j < s.length() && !valid(count, countT)) {
                count[s.charAt(j)]++;
                j++;
            }
            if (valid(count, countT)) {
                if (ans.equals("") || ans.length() > j - i) {
                    ans = s.substring(i, j);
                }
            }
            count[s.charAt(i)]--;
            if (count[s.charAt(i)] < 0) {
                count[s.charAt(i)] = 0;
            }
        }
        return ans;
    }
    private boolean valid(int[] count, int[] countT) {
        for (int i = 0; i < 256; i++) {
            if (countT[i] != 0 && count[i] < countT[i]) {
                return false;
            }
        }
        return true;
    }
}