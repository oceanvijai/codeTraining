public class MinimumWindowSubstring {

    // Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).


    public String minWindow(String s, String t) {

        Map<Character, Integer> validCountmap = new HashMap<>(); // This is a reference for validity
        int tLength = t.length();
        // Setup up valid constraints
        for (int i = 0; i < tLength; i++) {
            validCountmap.put(t.charAt(i), validCountmap.getOrDefault(t.charAt(i), 0) + 1);
        }

        // Now iterate the main string
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int count = 0;

        int minLeft = 0;
        int minLength = s.length();
        boolean found = false;

        while (right < s.length()) {
            char c = s.charAt(right);
            if (validCountmap.containsKey(c)) {
                map.put(c, map.getOrDefault(c, 0) + 1);
                // "+validCountmap.get(c));
                if (map.get(c) <= validCountmap.get(c)) { // we are yet to rach validity
                    count++;
                }
            }

            // when validity is achieved, shink from left until invalid
            while (count == tLength && left <= right) {
                if (found == false) {
                    found = true;
                }
                if (right - left < minLength) {
                    minLeft = left;
                    minLength = right - left;
                }

                char lc = s.charAt(left);
                if (validCountmap.containsKey(lc)) {
                    map.put(lc, map.get(lc) - 1);
                    if (map.get(lc) < validCountmap.get(lc)) { // we are yet to rach validity
                        count--;
                    }
                }

                left++;
            }

            right++;
        }

        if (found == false) {
            return "";
        }

        return s.substring(minLeft, minLeft + minLength + 1);
    }
}