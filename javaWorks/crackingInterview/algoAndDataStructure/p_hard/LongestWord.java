package CrackCode;

import java.util.HashMap;
import java.util.Map;

public class LongestWord {
    /**
     * Given a list of words, write a program to find the longest word made of other words
     * in the list.
     */

    /**
     * Techniques used - recursion and DP using map
     */

    /**
     * Approach
     *
     * At first it looks like if we sort all the words based on their length
     * Then which ever we find the first valid to this constraint will be our answer
     *
     * Now, we can have to break every word into a substring which is present in the existing list
     * of words
     * So, we can put all the words in a map to get all the words, kind of dictionary
     *
     * Then we can take every word and break it and see if we are able to find only valid words
     * forming this entire word
     *
     * One optimization we can do here is
     *
     * If we find a word to be invalid, we can avoid it being searched again
     */


    String printLongestWord(String arr[]) {
        // Step1 : sort arr by its length

        // Step2 : put all the sorted element in the map
        Map<String, Boolean> map = new HashMap<>(); // the boolean is for the memorization purpose
        // we default all values to true

        // step 3: lets iterate over them and find a valid word

        for (String str : arr) {
            if (isValid(str, map, false)) { // Is partial is passed false saying this is a full word
                return str;
            }
        }

        return null;
    }

    private boolean isValid(String word, Map<String, Boolean> map, boolean isPartial) {
        if (map.containsKey(word) && map.get(word) == false) {
            return false; // meaning we have see this already and marked it as invalid
        }

        if (isPartial == true && map.containsKey(word) && map.get(word) == true) {
            return map.get(word); // If we know this already
        }

        for (int i = 0; i < word.length(); i++) {
            String left = word.substring(0, i);
            if (map.containsKey(left) && map.get(left) == true) {
                String right = word.substring(i);
                if (isValid(right, map, true)) {
                    map.put(right, true);
                    return true;
                }
            }
        }

        map.put(word, false); // to remember this work is no use in parsing
        return false;
    }
}
