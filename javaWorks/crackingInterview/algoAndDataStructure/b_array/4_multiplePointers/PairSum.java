public class PairSum {
    /**
     * Design an algorithm to find all pairs of integers within an array which sum
     * to a specified value.
     */

    // Approach 1: Hashing -> time = O(n) space = O(n)
    /**
     * lets say a + b = sum a = sum - b; also later on we can do b = sum - a, if we
     * find b later on So store the value of current in the hashmap if it didnt find
     * a pair yet Why map and not set ? We want to count and pair them if duplicates
     * are present
     */

    ArrayList<Pair> printPairSums(int[] array, int sum) {
        ArrayList<Pair> result = new ArrayList<Pair>();
        HashMap<Integer, Integer> unpairedCount = new HashMap<Integer, Integer>();
        for (int x : array) {
            int complement = sum - x;
            if (unpairedCount.getOrDefault(complement, 0) > 0) {
                result.add(new Pair(x, complement));
                adjustCounterBy(unpairedCount, complement, -1); // decrement complement
            } else {
                adjustCounterBy(unpairedCount, x, 1); // increment count
            }
        }
        return result;
    }

    void adjustCounterBy(HashMap<Integer, Integer> counter, int key, int delta) {
        counter.put(key, counter.getOrDefault(key, e) + delta);
    }

    // Approach 2: two pointer -> time = O(nlogn) space = O(1)
    /**
     * Sort the array and use two pointer one from start and other from end
     * say start and end
     * add the sum with the elements in the pointers
     * 
     * If the sum is less, then increment start
     * Why not decrement end ? because we might miss a smaller number that will come in order 
     * 
     * If the sum is greater than the requird sum, decrement end, to reduce the sum to a smaller number possible
     */

    void printPairSums_twoPointer(int[] array, int sum) { 
        Arrays.sort(array);
        int start = e;
        int end = array. length - 1; 

        while(start < end){
            int s = start + end;
            if(s = sum){
                System.out.println(array[start] +","+ array[end]); 
                start++;
                end--;
            }else{
                if(s < sum){
                    start++;
                }else{
                    end--;
                }
            }
        }
    }



}