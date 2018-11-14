public class CharSearch {

    // We have the following question under this type
    /**
     * 1. Find the first non repeating character in a string
     * 
     * 2. Find the kth non repeating character in a string
     * 
     * 3. Find repeated character present first in a string, meaning the char which
     * comes first and repeats in the future
     * 
     * 4. First non-repeating character in a stream
     * 
     */

    /**
     * 1. Find the first non repeating character in a string Ex: geeksforgeeks , ans
     * = f (other chars are o,r etc)
     * 
     * We can do this in constant space. by creating a charMapArray and updating the
     * respective index But the problem here is, we dont know which character came
     * first in the given input string
     * 
     * So we can do two thing, 1. Again traverse the string and find which char has
     * the least one 2. we maintain another array, storing the index of the chracter
     * seen first
     */

    private int getIndexForChar(char c) {
        return ((int) c - 'a');
    }

    public int firstUniqChar(String s) {
        int[] charsCount = new int[26];
        int[] charsIndex = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int index = getIndexForChar(s.charAt(i));
            charsCount[index] = charsCount[index] + 1;
            if (charsIndex[index] == 0) {
                charsIndex[index] = i;
            }
        }

        int minIndex = s.length() + 1;
        for (int i = 0; i < 26; i++) {
            if (charsCount[i] == 1) {
                minIndex = Math.min(minIndex, charsIndex[i]);
            }
        }

        return minIndex == s.length() + 1 ? -1 : minIndex;
    }

    /**
     * 2. Find the kth non repeating character in a string
     * 
     * This is also achieved as above, with 2 variation
     * 
     * 1. If we find the charsCount greater than 1, put a value greater than
     * s.length()
     * 
     * 2. Now sort the charsIndex and get the kth one. Also, fill the charsIndex
     * initially with larger number
     */

    /**
     * 3. Find repeated character present first in a string, meaning the char which
     * comes first and repeats in the future
     * 
     * Again a variation of the above, only this time we are looking for the
     * repeated char
     */

    /**
     * 4. First non-repeating character in a stream
     * https://www.geeksforgeeks.org/find-first-non-repeating-character-stream-characters/
     * 
     * We have to approach here like LRU cache
     * 
     * Use Doubly linked list and addition array to find out which one is the ans at any time 
     * by looking at the head of linked list
     * And update it when the modification occurs
     */

}