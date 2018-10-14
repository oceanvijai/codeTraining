public class PeaksAndValley {
    /**
     * In an array of integers, a "peak" is an element which is greater than or
     * equal to the adjacent integers and a "valley" is an element which is less
     * than or equal to the adjacent integers.
     * 
     * For example, in the array {S, 8, 6, 2, 3, 4, 6}, {8, 6} are peaks and {S, 2}
     * are valleys. Given an array of integers, sort the array into an alternating
     * sequence of peaks and valleys.
     */

    // Approach 1: We sort the array , then we
    // iterate over the array jumping every two elements and swaping them with
    // previous element

    // time nlogn for sorting

    void sortValleyPeak(int[] array) {
        Arrays.sort(array);
        for (int i = 1; i < array.length; i += 2) {
            swap(array, i - 1, i);
        }
    }

    void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    // Approach to without sorting
    // here we lets say we have 2 elements 1, 5, 9
    // we can rearrange them like 1,9,5 or 5,1,9 both will present the peek and
    // value
    // But if you see, the swaping the middle with the max in the tree gives an
    // formation which can be useful

    // So start iterating the array and again jump two elements at the time
    // now we consider 3 elements, current, current -1, current +1
    // we swap the current with the one which is greater than in the three

    // time O(n)

    void sortvalleyPeak(int[] array) {
        for (int i = 1; i < array.length; i += 2) {
            int biggestlndex = maxlndex(array, i - 1, i, i + 1);
            if (i != biggestlndex) {
                swap(array, i, biggestlndex);
            }
        }
    }

    int maxlndex(int[] array, int a, int b, int c) {
        int len = array.length;
        int aValue = a >= 0 && a < len ? array[a] : Integer.MIN_VALUE;
        int bValue = b >= 0 && b < len ? array[b] : Integer.MIN_VALUE;
        int cValue = c >= 0 && c < len ? array[c] : Integer.MIN_VALUE;

        int max = Math.max(aValue, Math.max(bValue, cValue));
        if (aValue == max)
            return a;
        else if (bValue == max)
            return b;
        else
            return c;
    }
}