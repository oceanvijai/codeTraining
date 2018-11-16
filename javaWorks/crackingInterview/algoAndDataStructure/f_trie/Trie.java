public class Trie {
    class Trie {

        TrieNode root = null;

        public Trie() {
            root = new TrieNode('*');
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            root.insertString(word);
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            int i = 0;
            TrieNode curr = root;
            while (i < word.length()) {
                curr = curr.map.get(word.charAt(i));
                if (curr == null) {
                    return false;
                }
                if (i == word.length() - 1 && curr.end) {
                    return true;
                }
                i++;
            }

            return false;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            int i = 0;
            TrieNode curr = root;
            while (i < prefix.length()) {
                curr = curr.map.get(prefix.charAt(i));
                if (curr == null) {
                    return false;
                }
                if (i == prefix.length() - 1) {
                    return true;
                }
                i++;
            }

            return false;
        }

        private class TrieNode {
            char val;
            Map<Character, TrieNode> map;
            boolean end;

            public TrieNode(char c) {
                this.val = c;
                map = new HashMap<Character, TrieNode>();

            }

            public void insertString(String s) {

                if (s.length() == 0) {
                    this.end = true;
                    return;
                }

                char c = s.charAt(0);
                if (map.get(c) != null) {
                    map.get(c).insertString(s.substring(1));
                } else {
                    TrieNode n = new TrieNode(c);
                    map.put(c, n);
                    n.insertString(s.substring(1));
                }
            }

        }
    }
}