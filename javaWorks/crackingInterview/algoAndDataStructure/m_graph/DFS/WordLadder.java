public class WordLadder {
    /**
     * Given two words (beginWord and endWord), and a dictionary's word list, find
     * the length of shortest transformation sequence from beginWord to endWord,
     * such that:
     * 
     * Only one letter can be changed at a time. Each transformed word must exist in
     * the word list. Note that beginWord is not a transformed word.
     */

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Since we only need to find the levels, we can do bi directional search
        Set<String> beginSet = new HashSet<String>();
        Set<String> endSet = new HashSet<String>();

        int level = 1;
        int strLen = beginWord.length();
        HashSet<String> visited = new HashSet<String>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        // Start the search
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<String>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();
                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return level + 1;
                        }

                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }

            }

            beginSet = temp;
            level++;
        }

        return 0;
    }
}