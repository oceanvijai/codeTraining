public class KthSmallestPairs {
    /**
     * You are given two integer arrays nums1 and nums2 sorted in ascending order
     * and an integer k.
     * 
     * Define a pair (u,v) which consists of one element from the first array and
     * one element from the second array.
     * 
     * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
     */

    /**
     * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
     * 
     * Output: [[1,2],[1,4],[1,6]] Explanation: The first 3 pairs are returned from
     * the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     */

    /**
     * Approach : O(k logk)
     * 
     * We first take the k elements in the first array (important) and pair it with
     * the first elemets of array2
     * 
     * Then we also keep note of what was the index in array2 used for paring Now we
     * keep k elements in the heap
     * 
     * Then we start polling and keep paring the reset of the eleemnts from array2
     */

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> ret = new ArrayList<>();
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0)
            return ret;
        int len1 = nums1.length, len2 = nums2.length;

        PriorityQueue<Pair> q = new PriorityQueue(k, new CompPair());
        for (int i = 0; i < nums1.length && i < k; i++) { // only need first k number in nums1 to start
            q.offer(new Pair(0, nums1[i], nums2[0]));
        }
        for (int i = 1; i <= k && !q.isEmpty(); i++) { // get the first k sums
            Pair p = q.poll();
            ret.add(p.pair);
            if (p.idx < len2 - 1) { // get to next value in nums2
                int next = p.idx + 1;
                q.offer(new Pair(next, p.pair[0], nums2[next]));
            }
        }
        return ret;
    }

    class Pair {
        int[] pair;
        int idx; // current index to nums2
        long sum;

        Pair(int idx, int n1, int n2) {
            this.idx = idx;
            pair = new int[] { n1, n2 };
            sum = (long) n1 + (long) n2;
        }
    }

    class CompPair implements Comparator<Pair> {
        public int compare(Pair p1, Pair p2) {
            return Long.compare(p1.sum, p2.sum);
        }
    }
}