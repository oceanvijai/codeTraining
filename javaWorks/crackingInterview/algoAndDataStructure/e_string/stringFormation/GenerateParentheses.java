public class GenerateParentheses {
    List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        solve(n, n, new StringBuilder(""));
        return ans;
    }

    private void solve(int o, int c, StringBuilder sb) {
        if (o == 0 && c == 0) {
            ans.add(sb.toString());
            return;
        }

        if (o > 0) {
            sb.append("(");
            solve(o - 1, c, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (c > o) {
            sb.append(")");
            solve(o, c - 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

    }
}