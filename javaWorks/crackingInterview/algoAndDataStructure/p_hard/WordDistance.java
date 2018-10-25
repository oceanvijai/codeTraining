public class WordDistance {
    /**
     * You have a large text file containing words. Given any two words, find the
     * shortest distance (in terms of number of words) between them in the file.
     * 
     * If the operation will be repeated many times for the same file (but different
     * pairs of words), can you optimize your solution?
     */

    /**
     * Approach: We can have a map storing each word and their list of locations in
     * the file
     * 
     * like word1 -> 1, 10, 15, 17 etc 
     *      word2 -> 2, 22, 50 etc 
     *      word3 -> 3, 5, 19 etc
     * 
     * Now if we are asked two words shortest distance, we can get those two list
     * and try to get the min distance by using two pinter technique
     * 
     * like moving the least pointer to make the diff less
     * 
     * 
     * So this takes, O(n) for the mapping and O(A+B) for the two pointer, where A
     * is the number of occurences of word1 and B for word2
     */
}