import java.util.HashSet;

public class Respace {
    /**
     * Re-Space: Oh, no! You have accidentally removed all spaces, punctuation, and
     * capitalization in a lengthy document. A sentence like "I reset the computer.
     * It still didn J t boot!" became"iresetthecomputeritstilldidntboot': You'll
     * deal with the punctuation and capitalization later; right now you need to
     * re-insert the spaces. Most of the words are in a dictionary but a few are
     * not.
     * 
     * Given a dictionary (a list of strings) and the document (a string), design an
     * algorithm to unconcatenate the document in a way that minimizes the number of
     * unrecognized characters.
     */

    /**
     * EXAMPLE Input: jesslookedjustliketimherbrother
     * 
     * Output: (jess) looked just like (tim) her brother (7 unrecognized characters)
     */

    /**
     * Approach: First we need to pick some chracters and see if they are valid from
     * dict It not get its count as the unrecognized characters
     * 
     * then ask recursively for the rest of the string and add the count it returns
     * Every time we recurse, make sure we update the min number of unrecognized
     * characters at that point
     * 
     * So we dont recalculate it again and again
     * 
     * Also, when we move to the next characters, make sure the number of
     * unrecognized chracters are less than the currently found one
     * 
     */

     // Time O(n^2) , since first level n times and each other level onces but another n times

    String bestSplit(HashSet<String> dictionary, String sentence) {
        ParseResult[] memo = new ParseResult[sentence.length()];
        ParseResult r = split(dictionary, sentence, 0);
        return r == null ? null : r.parsed;
    }

    ParseResult split(HashSet<String> dictionary, String sentence, int start, ParseResult[] memo) {
        if (start >= sentence.length()) {
            return new ParseResult(0, "");
        }
        if (memo[start] != null) {
            return memo[start];
        }

        int bestInvalidength = Integer.MAX_VALUE;
        String bestParsing = null; 
        for (int i = start; i <= sentence.length(); i++) {
            String stringSoFar = sentence.substring(start, i);
            int len = dictionary.contains(stringSoFar) ? 0 : stringSoFar.length();
            if (len < bestInvalidength) {
                /*
                 * Recurse, putting a space after this character. If this is better than the
                 * current best option, replace the best option.
                 */

                ParseResult result = split(dictionary, sentence, i + 1, memo);
                if (len + result.invalid < bestInvalidength) {
                    bestInvalid = len + result.invalid;
                    bestParsing = partial + " " + result.parsed;
                    if (bestInvalid == 0)
                        break; // Short circuit
                }
            }
        }

        memo[start] = new ParseResult(bestInvalidength, bestParsing);
        return memo[start];
    }

}