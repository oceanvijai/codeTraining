public class LongestSubstringWithoutRepeatingCharacters {
    // Given a string, find the length of the longest substring without repeating
    // characters.

    public int lengthOfLongestSubstring(String s) {

        if (s.length() == 0) {
            return 0;
        }

        int i = 0;
        int j = 0;

        int maxlength = 1;
        Set<Character> set = new HashSet<>();
        while (j < s.length()) {
            char c = s.charAt(j);
            if (!set.contains(c)) {
                set.add(c);
            } else {
                while (set.contains(c)) {
                    char cx = s.charAt(i);
                    set.remove(cx);
                    i++;
                }

                set.add(c);
            }

            j++;

            if (j - i > maxlength) {
                maxlength = j - i;
            }

        }

        return maxlength;
    }
}