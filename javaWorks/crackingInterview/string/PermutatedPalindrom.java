public class PermutatedPalindrom {

    // Given a string, write a function to check if it is a permutation of
    // a palindrome. A palindrome is a word or phrase that is the same forwards and
    // backwards. A
    // permutation is a rearrangement of letters. The palindrome does not need to be
    // limited to just
    // dictionary words.

    // Example
    // Input: Tact Coa
    // Output: True (permutations:"taco cat'; "atco cta'; etc.)

    // For a string to be palindrom, we need same and equal number of characters on
    // both side
    // So count the number of strings and see if the characters are even numbers and
    // at max one character to be odd so can be present in the middle

    // 1: Straight forward, count all the characters in a string and put it in an
    // array or hash map
    // check if there is only one chracter with odd count
    // time : n, space : n

    // 2: another important observation is, we only need to see if the count is even
    // or odd.
    // we dont need to store the count
    // so we can use a bit map and flip the bit for the chracter
    // So in the final if we see 1 for that bit, then its count is odd, otherwise
    // even
    // So as per our need we need to know, finally only one bit should be 1

    // So, we lets say we start with 0000, then we flip every bit mapped to every
    // character
    // finally we fnd how many bits are set to 1

    public boolean check(String str) {
        int bitmap = 0;
        for (int i = 0; i < str.length(); i++) {
            bitmap = flipBit(bitmap, str.charAt(i));
        }
        return checkIfOnlyOneBitIsSet(bitmap);
    }

    /* Toggle the ith bit in the integer. */ 
    private int flipBit(int bitmap, int c) {
        int t = 1 << c;
        bitmap = bitmap ^ t;
        return t;
    }

    /* Check that exactly one bit is set by subtracting one from the integer and 
    ANDing it with the original integer. */
    private boolean checkIfOnlyOneBitIsSet(int bitmap) {
        return (bitmap & (bitmap - 1)) == 0;
    }

}