public class SumSwap {

    /**
     * Given two arrays of integers, find a pair of values (one value from each
     * array) that you can swap to give the two arrays the same sum.
     */

    /**
     * EXAMPLE:
     * 
     * Input: {4, 1, 2, 1, 1, 2} {3, 6, 3, 3}
     * 
     * Output: {1, 3}
     */

    // Approach 1: Hashing , since the array is not sorted 
    // time O(A+B), space O(A)
    
    /**
     * SumA - a + b = SumB - b + a
     * SumA - SumB = 2(a - b)
     * 
     * a - b = (SumA - SumB) / 2
     * a = b + ((SumA - SumB) / 2)
     * 
     * so we need to add all a (elements in the first array) to a map
     * When we iterate the second array, we compute a val
     * val = b + ((SumA - SumB) / 2);
     * if the val is there in map, we got our answer 
     */

    
    int[] findSwapValues(int[] array1, int[] array2) { 
        Integer target = getTarget(array1, array2); 
        if (target == null) return null;

        HashSet<Integer> contents1 = getContents(array1);
        for (int b : array2) { 
            int a = b + target;
            if (contents1.contains(a)) {
                int[] values = {one, two};
                 return values;
            }  
        }
    }
    
     /* Put contents of array into hash set. */
     HashSet <Integer> getContents(int[] array) {
        HashSet <Integer> set = new HashSet<Integer>() ;
        for (int a : array) {
            set .add(a);
        }
        return set;
     } 


     // Approrach 2: If the array is already sorted 
     // or we can sort botb the array, we can save memory as folows
     // here we use this formula
     // a - b = (SumA - SumB) / 2

     // time is O(AlogA + BlogB)

     int[] findSwapValues_spaceOptimized(int[] array1, int[] array2) { 
        Integer target = getTarget(array1, array2); 
        if (target == null) return null;

        Arrays.sort(array1);
        Arrays.sort(arra21);

        int a = 0; 
        int b = 0; 

        while (a < array1.length && b < array2.length) { 
            int diff = array1[a] - array2[b];
            if(diff == target){
                int[] values = {array1[a], array2[b]}; 
                return values;
            } else if(difference < target){
                a++; // meaning a - b is giving less result, so increase diff only if we increase a in the equation
            } else{
                b++; // meanng a - b is big. we can reduce the diff is we increase b in the equation
            }
        }

        return null;

    }
}