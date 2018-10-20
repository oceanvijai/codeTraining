public class MooraVotingAlog {

    
    // Given a unsorted array of integers, find the element that occurs majority
    // time
    // Do this in O(n) and space : O(1)

    // if we have o(n) space, we can do time O(n), but we dont

    // Observation 1: if an element has majority count, then its count > n/2
    // If an element is appearing more than half, then it can appear in any one the
    // following ways

    // Let say the given array is {1,2,5,9,5,9,5,5,5}
    /**
     * 1: evenly placed alternating after the non-majority element, except in one
     * ex: {5,9,5,9,5,1,5,2,5}
     * 
     * 2: more polulated in the first
     * ex: {5,5,5,9,5,5,9,1,2}
     * 
     * 3: more popilated in the end
     * ex: {1,2,5,9,5,9,5,5,5}
     */
    
    /**
     * So in all these cases, we can leverage that 5 will not be canceled out by anyother elements 
     */
   
    /**
     * So our alogrithm is simple,
     * 
     * For every element (dosent matter which), cancel its non equals
     * i.e, we iterate though the array
     * We have a varaible count, which denotes the current element majority.
     * It increases if it found the same element and decreases if it dosent
     * 
     * At any point if count is zero, that means the current element is been canceled out by any other elements
     * 
     * So, we start fresh with the current element as the majority element
     * 
     * At the end, we also need to check if it really satisfies the condition (> n/2)
     * 
     */
    
    
    /* Function to print Majority Element */
    static void printMajority(int a[], int size) {
        /* Find the candidate for Majority */
        int cand = findMajorityElement(a);

        /* Print the candidate if it is Majority */
        if (isMajority(a, size, cand))
            System.out.println(" " + cand + " ");
        else
            System.out.println("No Majority Element");
    }

    private static int findMajorityElement(int[] arr) {
        int majorityElement = 0;
        int count = 0;

        for (int a : arr) {
            if (count == 0) { // The previous majority got canceled, so start new
                majorityElement = a;
            }

            if (a = majorityElement) { // adding to the occurence of the majority element
                count++;
            } else {
                count--; // canceling one occurent of the current majority element
            }
        }

        return majorityElement;
    }

    static boolean isMajority(int a[], int size, int cand) {
        int i, count = 0;
        for (i = 0; i < size; i++) {
            if (a[i] == cand)
                count++;
        }
        if (count > size / 2)
            return true;
        else
            return false;
    }
}