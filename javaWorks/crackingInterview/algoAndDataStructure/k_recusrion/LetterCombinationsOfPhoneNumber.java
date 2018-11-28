public class LetterCombinationsOfPhoneNumber {

    /**
     * Given a string containing digits from 2-9 inclusive, return all possible
     * letter combinations that the number could represent.
     * 
     * A mapping of digit to letters (just like on the telephone buttons) is given
     * below. Note that 1 does not map to any letters.
     * 
     * Input: "23" 
     * 
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     */

    List<String> ans = new ArrayList<>();
    Map<Character, List<Character>> numPad = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<String>();
        }
        setup();
        solve(digits, 0, new StringBuilder(""));
        return ans;
    }

    private void solve(String digits, int digitIndex, StringBuilder sb) {
        if (digitIndex == digits.length()) {
            ans.add(sb.toString());
            return;
        }

        if (numPad.get(digits.charAt(digitIndex)) != null) {
            for (char c : numPad.get(digits.charAt(digitIndex))) {
                sb.append(c);
                solve(digits, digitIndex + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

    }

    private void setup() {
        List<Character> c = new ArrayList<>();
        c.add('a');
        c.add('b');
        c.add('c');
        numPad.put('2', c);

        c = new ArrayList<>();
        c.add('d');
        c.add('e');
        c.add('f');
        numPad.put('3', c);

        c = new ArrayList<>();
        c.add('g');
        c.add('h');
        c.add('i');
        numPad.put('4', c);

        c = new ArrayList<>();
        c.add('j');
        c.add('k');
        c.add('l');
        numPad.put('5', c);

        c = new ArrayList<>();
        c.add('m');
        c.add('n');
        c.add('o');
        numPad.put('6', c);

        c = new ArrayList<>();
        c.add('p');
        c.add('q');
        c.add('r');
        c.add('s');
        numPad.put('7', c);

        c = new ArrayList<>();
        c.add('t');
        c.add('u');
        c.add('v');
        numPad.put('8', c);

        c = new ArrayList<>();
        c.add('w');
        c.add('x');
        c.add('y');
        c.add('z');
        numPad.put('9', c);
    }
}