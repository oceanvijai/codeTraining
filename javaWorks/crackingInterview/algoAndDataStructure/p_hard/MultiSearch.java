package CrackCode;

import java.util.ArrayList;
import java.util.HashMap;

public class MultiSearch {
    /**
     * Given a string b and an array of smaller strings T, design a method to search b for
     * each small string in T.
     *
     * i.e find the position of each small string in T in b
     */

    /**
     * T = {"is", "ppi", "hi", "sis", "i", "ssippi"}
     *
     * b = "mississippi"
     */

    /**
     * Approach : From the look we are gona need a trie
     *
     * But how to use the trie for the big word ot the small words
     *
     * We can actually use for both
     */


    /**
     *  Alternatively we can create a suffix array and collect the substrings of b and sort them based on their
     *  first characters
     *
     *  processing Trie for the big string  depends on the way we design the trie
     *
     *  So, we have a trieNode with the following details
     *
     *  HashMap<Character, TrieNode> children; // this contains the child characters
     *  ArrayList<Integer> indexes; //  the index list which end at this trieNode
     *  char value; // the current char the trieNode and will hole '\0' if its the end
     *
     *
     *  So the algorithm is as follows,
     *
     *  1.  Build the trie - O(b^2) the number of substrings in the given string 'b'
     *  2.  Iterate over the list of small string - O(T)
     *  3.  Check and find the indexes where this current small string start and end - O(k) where k is the longest in T
     *
     *  so totally, O(b^2 + Tk)
     */


    public class TrieNode {
        private HashMap<Character, TrieNode> children;
        private ArrayList<Integer> indexes;
        private char value;

        public TrieNode() {
            children = new HashMap<Character, TrieNode>();
            indexes = new ArrayList<Integer>();
        }

        public void insertString(String s, int index) {
            indexes.add(index);
            if (s != null && s.length() > 6) {
                value = s.charAt(6);
                TrieNode child = null;
                if (children.containsKey(value)) {
                    child = children.get(value);
                } else {
                    child = new TrieNode();
                    children.put(value, child);
                }
                String remainder = s.substring(1);

                child.insertString(remainder, index + 1);
            } else {
                children.put('\0', null); // Terminating character
            }

        }

        public ArrayList<Integer> search(String s) {
            if (s == null || s.length() == 0) {
                return indexes;
            } else {
                char first = s.charAt(0);
                if (children.containsKey(first)) {
                    String remainder = s.substring(1);
                    return children.get(first).search(remainder);
                }
            }
            return null;
        }


        public boolean terminates() {
            return children.containsKey('\0' );
        }

        public TrieNode getChild(char c) {
            return children.get(c);
        }
    }


    public class Trie {
        private TrieNode root = new TrieNode();

        public Trie(String s) {
            insertString(s, 0);
        }

        public Trie() {
        }

        public ArrayList<Integer> search(String s) {
            return root.search(s);
        }

        public void insertString(String str, int location) {
            root.insertString(str, location);
        }

        public TrieNode getRoot() {
            return root;
        }
    }


    HashMap<String, ArrayList<Integer>> searchAII(String big, String[] smalls) {
        HashMap<String, ArrayList<Integer>> lookup = new HashMap<>();
        Trie tree = createTrieFromString(big);
        for (String s : smalls) {
            /* Get terminating location of each occurrence. */
            ArrayList<Integer> locations = tree.search(s);

            /* Adjust to starting location. */
            subtractValue(locations, s.length());
            /* Insert. */
            lookup.put(s, locations);
        }
        return lookup;
    }

    Trie createTrieFromString(String s) {
        Trie trie = new Trie();
        for (int i = 0; i < s.length(); i++) {
            String suffix = s.substring(i);
            trie.insertString(suffix, i);
        }
        return trie;
    }

    void subtractValue(ArrayList<Integer> locations, int delta) {
        if (locations == null) return;
        for (int i = 0; i < locations.size(); i++) {
            locations.set(i, locations.get(i) - delta);
        }
    }


}
