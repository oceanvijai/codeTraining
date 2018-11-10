public class WordSearch {

    List<String> ans = new ArrayList<>();
    boolean[][] visited; // we can ovoid this by using the board array and put # and change it back
                         // during backtrack

    public List<String> findWords(char[][] board, String[] words) {

        if (board.length == 0) {
            return new ArrayList<String>();
        }

        visited = new boolean[board.length][board[0].length];
        TriesNode root = new TriesNode('*');
        for (String s : words) {
            root.insertString(s);
        }
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.map.get(board[i][j]) != null) {
                    sb.append(board[i][j]);
                    solve(board, root.map.get(board[i][j]), sb, i, j);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }

        return ans;
    }

    private void solve(char[][] board, TriesNode trie, StringBuilder sb, int i, int j) {

        // if(trie.map.get(board[i][j]) == null){
        // return;
        // }

        if (visited[i][j] == true) {
            return;
        }

        visited[i][j] = true;

        // System.out.println(board[i][j]);
        // System.out.println(sb.toString());
        // System.out.println(trie.end);
        // System.out.println("----");

        if (trie.end == true) {
            if (ans.contains(sb.toString()) == false) {
                ans.add(sb.toString());
            }
        }

        // try top
        if (i - 1 >= 0 && trie.map.get(board[i - 1][j]) != null) {
            sb.append(board[i - 1][j]);
            solve(board, trie.map.get(board[i - 1][j]), sb, i - 1, j);
            sb.deleteCharAt(sb.length() - 1);
        }

        // try bottom
        if (i + 1 < board.length && trie.map.get(board[i + 1][j]) != null) {
            sb.append(board[i + 1][j]);
            solve(board, trie.map.get(board[i + 1][j]), sb, i + 1, j);
            sb.deleteCharAt(sb.length() - 1);
        }

        // try left
        if (j - 1 >= 0 && trie.map.get(board[i][j - 1]) != null) {
            sb.append(board[i][j - 1]);
            solve(board, trie.map.get(board[i][j - 1]), sb, i, j - 1);
            sb.deleteCharAt(sb.length() - 1);
        }

        // try right
        // trie.map.get(board[i][j+1]);
        if (j + 1 < board[0].length && trie.map.get(board[i][j + 1]) != null) {
            sb.append(board[i][j + 1]);
            solve(board, trie.map.get(board[i][j + 1]), sb, i, j + 1);
            sb.deleteCharAt(sb.length() - 1);
        }

        visited[i][j] = false;

    }

    private class TriesNode {
        char val;
        Map<Character, TriesNode> map;
        boolean end;

        public TriesNode(char c) {
            this.val = c;
            map = new HashMap<Character, TriesNode>();
        }

        public void insertString(String s) {

            if (s.length() == 0) {
                this.end = true;
                return;
            }

            // System.out.println("Trie:"+ s);

            // if(s.length() == 1){
            // //System.out.println("End at:"+ s);
            // this.end = true;
            // }

            char c = s.charAt(0);
            if (map.get(c) != null) {
                map.get(c).insertString(s.substring(1));
            } else {
                TriesNode n = new TriesNode(c);
                map.put(c, n);
                n.insertString(s.substring(1));
            }

        }
    }

}