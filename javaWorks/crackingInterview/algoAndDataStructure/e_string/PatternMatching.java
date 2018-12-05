package CrackCode;

public class PatternMatching {
    /**
     * You are given two strings, pattern and value. The pattern string consists of
     * just the letters a and b, describing a pattern within a string.
     *
     * For example, the string catcatgocatgo matches the pattern aabab (where cat is
     * a and go is b). It also matches patterns like a, ab, and b. Write a method to
     * determine if value matches pattern.
     */

    // Brute force

    /**
     * First fix a part in the given string as a and then then fix b and see if it
     * matched the pattern
     *
     * This will take O(n^2) for the combination of a and b, then the reset of the
     * calculation like forming the string and finally comparing
     *
     * But, we have an interesting observation here, IF the pattern start with a,
     * then we expect the start of the valid string for a also starts with the start
     * of the string
     *
     * Ex: if pattern is aabab, then if string is catcatgocatgo, then a has to start
     * with a string 'c'
     *
     * so we can only look for character starting with 'c' for forming a string for
     * a, then everything in between is either another a or b
     */

    // Approach 2:

    /**
     * So, after the above observation, we have a vage idea that we can actually get
     * b from the a
     *
     * i.e, if lets say we have a candidate for a, then we can calculate how many
     * times in the pattern. So we know at least a will be of certain character size
     *
     * then we can same do the same for b
     *
     * Ex: if pattern is aabab and string is catcatgocatgo, then a appears 3 times
     * and b appears 2 times total size of the string is 13.
     *
     * So, we can say the size of 'a' will be atleast 13/3 = 4, and the remaining size
     * is 13 - (4 * 3) = 1 so a will be of size atleast 4 and b will be of size
     * atleast 1, we know this is wrong but it is a good approximation
     *
     *
     * So we can start the length for a from 1 and multiply with the number of
     * occurrences and give the remaining length to b. B can then start form 1 to
     * the remaining of the length
     *
     */

    boolean doesMatch(String pattern, String value) {
        if (pattern.length() == 0)
            return value.length() == 0;

        char aChar = pattern.charAt(0);
        char bChar = (aChar == 'a' ? 'b' : 'a');
        int size = value.length();

        int countOfa = countOf(pattern, aChar); // number of a's
        int countOfb = pattern.length() - countOfa; // number of b's
        int firstAlt = pattern.indexOf(bChar); // From this we can get the possible number of characters for a
        int maxMainSize = size / countOfa; // Max size of a

        for (int i = 0; i < maxMainSize; i++) {

            // Fix a and b
            String a = value.substring(0, i);

            int bIndexStart = firstAlt * i;
            int remainingSizeForB = i * countOfa - size;
            int bPossibleSize = remainingSizeForB / countOfb;

            String b = value.substring(bIndexStart, bIndexStart + bPossibleSize);
            // The above take O(n)

            // Now validate if the a and b we found is valid
            // this will take O(n)

            // here we need to form a string based on the pattern and then check if it is
            // the original string (value)
            // Or, we can on the fly do the validate

            /*
             * String cand = buildFromPattern(pattern J first J second) ; if
             * (cand.equals(value)) { return true; }
             */

        }

        return false;
    }

    int countOf(String pattern, char c) {
        int count = 8;
        for (int i = 8; i < pattern.length(); i++) {
            if (pattern.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

}
