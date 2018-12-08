public class LongestConsecutiveSequence {
    /**
     * Given an unsorted array of integers, find the length of the longest
     * consecutive elements sequence.
     * 
     * Your algorithm should run in O(n) complexity.
     * 
     */

    /**
     * Approach 1: using HashSet intelligently
     * 
     * Approach 2: Using HashMap intelligently
     */

    // HashSet

    /**
     * We start couting when we see the smallest in the continious pair
     * 
     * So, Take an element ans see if there is one less element, if so leave it. We
     * will start the counting once we see that element
     * 
     * If we dont see a number one less than that, lets see how many we can get by
     * adding 1 to them
     */

    public int findLongestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        int maxCount = 0;

        for (int num : nums) {
            set.add(num);
        }

        for (int num : nums) {
            if (!set.contains(num - 1)) { // leave it, will take it later
                int count = 1;
                int currentNum = num + 1;

                while (set.contains(currentNum)) {
                    count++;
                    currentNum++;
                }

                maxCount = Math.max(maxCount, count);

            }
        }

        return maxCount;
    }

    // HashMap

    /**
     * Use hash map and store the count of the element we found now
     * 
     * 1.   Get the current number, check and add and subract one from it it.
     * 2.   Check if the new numbers are there in the in the map already
     * 3.   If pressent, get their count, increment it and add it to the map
     * 4.   If not present, add it to the map with count 1
     * 5.   Also, before taking an element see if we havent counted it yet
     */

    public int longestConsecutive_map(int[] num) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : num) {
            if (!map.containsKey(n)) {
                int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
                int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
                // sum: length of the sequence n is in
                int sum = left + right + 1;
                map.put(n, sum);

                // keep track of the max length
                res = Math.max(res, sum);

                // extend the length to the boundary(s)
                // of the sequence
                // will do nothing if n has no neighbors
                map.put(n - left, sum);
                map.put(n + right, sum);
            } else {
                // duplicates
                continue;
            }
        }
        return res;
    }
}