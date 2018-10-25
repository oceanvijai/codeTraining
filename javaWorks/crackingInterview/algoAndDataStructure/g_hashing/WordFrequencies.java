package CrackCode;

import java.util.HashMap;
import java.util.Map;

public class WordFrequencies {
    /**
     * Word Frequencies: Design a method to find the frequency of occurrences of any given word in a
     * book. What if we were running this algorithm multiple times?
     */

    /**
     * Since, we are gona do this multiple times, we can pre-process it for later use
     * <p>
     * Also, we can beet O(n) since, we have to atleast touch each word once
     */

    Map<String, Integer> dict = new HashMap<>();

    void setupDictionary(String[] book) {
        for (String word : book) {
            String w = word.toLowerCase();
            int count = dict.getOrDefault(w, 0);
            dict.put(w, count);
        }
    }

    int getFrequency(HashMap<String, Integer> table, String word) {
        if (table == null || word == null) return -1;
        word = word.toLowerCase();
        if (table.containsKey(word)) {
            return table.get(word);
        }
        return 0;
    }
}
