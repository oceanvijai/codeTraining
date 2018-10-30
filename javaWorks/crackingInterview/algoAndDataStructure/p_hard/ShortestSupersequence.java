public class ShortestSupersequence {

    /**
     * You are given two arrays, one shorter (with all distinct elements) and one
     * longer.
     * 
     * Find the shortest subarray in the longer array that contains all the elements
     * in the shorter array. The items can appear in any order
     */

    /**
     * EXAMPLE
     * 
     * Input: {1, 5, 9} and {7, 5,9,8,2,1,3, 5, 7, 9, 1,1,5,8,8,9, 7}
     * 
     * Output: [7, 18] ( 5, 7, 9, 1)
     * 
     */

    // We have two solution for this
    // DP and Multiple pointers (with min heap)

    /**
     * DP
     * 
     * If we see the array {7, 5,9,8,2,1,3, 5, 7, 9, 1,1,5,8,8,9, 7} At every point
     * we need to know the disance of every other required elements Most preferably
     * the farest one present in the expceted list
     * 
     * So we can create a DP table and fill the index of the very element in the reverse
     * i,e fill from reverse where 1 was seen before, then in the next row again from reverse
     * 
     * so it will look like this
     * 
     * 
     *      7 | 5 | 9 | 8 | 2 | 1 | 3 | 5 | 7 |  9 | 1 | 1 | 5 | 8 | 8 | 9 | 7 |
     * 1->  5 | 5 | 5 | 5 | 5 | 5 | 10| 10| 10| 10 | 10| 11| x | x | x | x | x |
     * 5->  1 | 1 | 7 | 7 | 7 | 7 | 7 | 7 | 12| 12 | 12| 12| 12| x | x | x | x |
     * 9->  2 | 2 | 2 | 9 | 9 | 9 | 9 | 9 | 9 | 9  | 15| 15| 15| 15| 15| 15| x |
     * 
     * Now if you look at the table, we need to find the max and min in each column
     * then the difference is the best distance they can make
     * 
     * steps:
     * 
     * 1.   Create the index for every element in the smaller array - O(nm) n - elements in big and m - for small
     * 2.   Get the min and max in every column and fill it in the last or another 1d array with difference - O(nm)
     * 3.   Get the smalledt in the distance array - O(n)
     * 
     */

    /**
     * Multiple pointers method
     * 
     * 
     */

    /**
     * Now we if see the above DP table, we dont need all the index of where each element is present
     * We only need where they are present
     * like,
     * 
     * 1 -> {5, 10, 11}
     * 2 -> {1, 7, 12}
     * 3 -> {2, 3, 9, 15}
     * 
     * So we can put multiple pointers and get a min and the max to get the distance
     * 
     * Steps:
     * 1    Create a map of elements and its indexs in the bigger array - O(n)
     * 2    Get the min and the max in the map - O(nm) , sice it iterate over m items in n items in the big one
     * 3    Also remove the min at each iteration
     * 
     * Now, we can optimize the time we need to look for the min.
     * This can be done by putting the heads of the list (linkedlist) to a min heap
     * this will give us both the head of the smallest element and the key of the min head
     * 
     * So we can take the min list, remove it and put it back to the min heap and the map
     * 
     * So, this will take,
     * 
     * O(n log m), log m for the heap procesing
     */
}