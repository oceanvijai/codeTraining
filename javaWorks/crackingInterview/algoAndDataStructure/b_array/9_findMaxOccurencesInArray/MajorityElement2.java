public class MajorityElement2 {
    /**
     * Given an integer array of size n, find all elements that appear more than ⌊
     * n/3 ⌋ times.
     * 
     * The algorithm should run in linear time and in O(1) space.
     */

    /**
     * If we dont have the constraint of O(1) space, we could have done it with a
     * HashMap
     * 
     * Since, we cant use hash map we can do a modification of Moora Voting Alog
     * 
     * Observations:
     * 
     * 1. At a max we can have only two element in the array of n elements, having
     * count MORE than n/3
     * 
     * 2. In Moor's algo, to find we element with more than n/2 occurences, we need
     * to do n/2 cancelations Similarly, if we cancel out n/3 elements, and get two
     * max elements, we migth get our answer
     * 
     * 3. To do n/3 cancelation, we can have have two majorityElements and two
     * counters On every step, we can cancel, either two elements or four elements
     * which are not matching
     * 
     * 4. Finally, we can verify our majorities
     */

    public List<Integer> majorityElement(int[] nums) {
        int majorityElement1 = -1;
        int majorityElement2 = -1;

        int count1 = 0;
        int count2 = 0;

        for (int i : nums) {

            if (majorityElement1 == i) {
                count1++;
            } else if (majorityElement2 == i) {
                count2++;
            } else if (count1 == 0) {
                majorityElement1 = i;
                count1 = 1;
            } else if (count2 == 0) {
                majorityElement2 = i;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int i : nums) {
            if (majorityElement1 == i) {
                count1++;
            }

            if (majorityElement2 == i) {
                count2++;
            }
        }

        List<Integer> ans = new ArrayList<>();
        if (count1 > nums.length / 3) {
            ans.add(majorityElement1);
        }

        if (count2 > nums.length / 3) {
            ans.add(majorityElement2);
        }

        return ans;
    }
}