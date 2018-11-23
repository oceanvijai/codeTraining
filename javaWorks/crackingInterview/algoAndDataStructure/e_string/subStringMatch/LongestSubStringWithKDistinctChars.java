public class LongestSubStringWithKDistinctChars {
    /**
     * Given a string, find the length of the longest substring T that contains at
     * most k distinct characters.
     * 
     * 
     */

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLenght = 0;

        int i = 0;
        int j = 0;
        int n = s.length();

        while (j < s.length()) {

            char c = s.charAt(j);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }

            if (map.size() <= k) {
                maxLenght = Math.max(maxLenght, j - i + 1);
            }

            if (map.size() > k) {

                while (map.size() > k && i < j) {
                    char cx = s.charAt(i);
                    map.put(cx, map.get(cx) - 1);
                    if (map.get(cx) == 0) {
                        map.remove(cx);
                    }
                    i++;
                }
            }

            j++;
        }

        return maxLenght;
    }
}