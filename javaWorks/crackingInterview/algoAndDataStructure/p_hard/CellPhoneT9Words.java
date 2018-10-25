package CrackCode;

import java.util.ArrayList;

public class CellPhoneT9Words {


    /**
     * On old cell phones, users typed on a numeric keypad and the phone would provide a list of words
     * that matched these numbers. Each digit mapped to a set of 0 - 4 letters.
     *
     * Implement an algorithm to return a list of matching words, given a sequence of digits.
     *
     * You are provided a list of valid words
     * (provided in whatever data structure you'd like). The mapping is shown in the diagram below:
     */

    /**
     * EXAMPLE
     * Input: 8733
     *
     * Output: tree, used
     */

    /**
     * Brute force is straight forward
     *
     * we take all the characters for a number press, then combine with the next set of characters
     * for the next character and then at the final stage, see if you have formed a valid word
     *
     * this, is doing a kind of DFS
     */

    /**
     * We can optimize this DFS by using a trie which will validate at each level if we need to go to the next level or not
     * <p>
     * This is much better since we only get valid words at the end
     * <p>
     * For this, we should have pre-processed the dict to a trie
     * <p>
     * O(wc) for the trie, where w is the # of words and c is the count of the characters
     * O(nc) where n is the number of digits and c the characters associate with each digit
     */


    ArrayList<String> getValidT9Words(String number, Trie trie) {
        ArrayList<String> results = new ArrayList<String>();
        getValidWords(number, 0, "", trie.getRoot(), results);
        return results;

    }

    // this is the DFS part
    void getValidWords(String number, int index, String prefix, TrieNode trieNode, ArrayList<String> results) {
        // If it's a complete word, print it.
        if (index == number.length()) {
            if (trieNode.terminates()) { // Is complete wo rd
                results.add(prefix);
            }
        }

        // Get characters that match this digit
        char digit = number.charAt(index);
        char[] letters = getT9Chars(digit);


        // Go through all remaining options.
        if (letters != null) {
            for (char letter : letters) {
                TrieNode child = trieNode.getChild(letter);
                if (child != null) {
                    getValidWords(number, index++, prefix + letter, child, results);
                }
            }
        }

    }

    // This will get the characters associate with the digit
    char[] getT9Chars(int digit) {
        return new char[4];
    }

    private abstract class Trie {
        abstract TrieNode getRoot();
    }

    private abstract class TrieNode {
        abstract Trie getRoot();

        abstract boolean terminates();

        abstract TrieNode getChild(char c);
    }


    /**
     * Another very good optimization we can make is
     * Instead of converting the given digits to the corresponding list of valid strings
     *
     * we can make a map of digits and the corresponding list of valid words by pre-processing the dict
     *
     * we can convert every word in the dictionary into a number sequence based on their characters and
     * map them in a hash map and also group them
     *
     * this way, we can do O(1) when a search is made
     *
     * Time: O(nw) , where n is the number of words in the dict and w is the number of characters per letter
     */

}
