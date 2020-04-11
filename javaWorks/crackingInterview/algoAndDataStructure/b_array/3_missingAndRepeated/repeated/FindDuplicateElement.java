public class FindDuplicateElement {
    // Given an array nums containing n + 1 integers where each integer is between 1
    // and n (inclusive), prove that at least one duplicate number must exist.
    // Assume that there is only one duplicate number, find the duplicate one.


    // https://medium.com/solvingalgo/solving-algorithmic-problems-find-a-duplicate-in-an-array-3d9edad5ad41
    
    /**
     * You must not modify the array (assume the array is read only).
     * 
     * You must use only constant, O(1) extra space.
     * 
     * Your runtime complexity should be less than O(n2).
     * 
     * There is only one duplicate number in the array, but it could be repeated
     * more than once.
     */

    /**
     * Count the frequency in a seperate array or hashmap and get the duplicates out
     * But this is O(n) space
     */

    /**
     * cant use bit wise and equation since, the element is repearting multiple
     * times
     */

    /**
     * We can use sorting to get the result in nlogn using RADIX
     * 
     */

    /**
     * there is a binary search solution as follows with nlogn
     * https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/496/discuss/72844/Two-Solutions-(with-explanation):-O(nlog(n))-and-O(n)-time-O(1)-space-without-changing-the-input-array
     * 
     * Go to the mid, and count the number of elements less than mid in the entire array
     * If the count is more than mid index, then one element is duplicated on the left
     * So do the search in the left, otherwise do it on the right
     * 
     * Do this until the search space is two numbers
     * 
     */

    public static int findDuplicate(int[] nums) {
        if (nums.length == 0 || nums == null)
            return 0;
        int low = 1, high = nums.length - 1, mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid)
                    count++;
            }
            if (count > mid)
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }

    /**
     * So let try the marking 
     */

    void marker(int arr[], int size) {
        int i;
        System.out.println("The repeating elements are : ");

        for (i = 0; i < size; i++) {
            if (arr[Math.abs(arr[i])] >= 0)
                arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])];
            else
                System.out.print(Math.abs(arr[i]) + " ");
        }
    }

    /**
     * but since we are not allowed to modify the input array, we can try runner
     * techinque now
     * 
     * This is the Floyd cycle detection algorithm in linked list
     * The steps are
     * 
     * 1.   Get a node inside the cycle
     * 2.   Get the length of th cycle
     * 3.   Get the starting point of the cycle
     * Usually this is the equaltion, 
     * 
     * m - where the cycle start
     * l - length of the loop
     * k - where the slow and fast pointers meet in the loop
     * x - number of times the slow has traversed the loop before meeting with fast
     * y - number of time the fast has traversed the loop before meeting the slow
     * 
     * Also, the fast moved twices as fast as the slow
     * 
     * so the equation of distance travelled is,
     *  (ditance travelled by slow) * 2 = (distance travelled by the fast)
     *  (m + l * x + k) 2 = (m + l * y + k)
     *  
     *  So to solve this problem, we have to prove that the point where they meet is the length 
     * of the loop, i.e m = k (where k is where they meet)
     * 
     *  So if we can see, the slow reaches k with distance, m + (l - k) 
     * then the fast which is moving twice as fast as slow reachs k at, m + l + (l - k)
     *  so, (m + l - k) * 2 = m + l + l - k
     *       2 m + 2 l - 2k = m + 2l - k => m = k
     * 
     * so there are two intutions to be noted here
     *  1.  The meeting point of the slow and fast is the length of the loop
     *  2.  The start of the cycle is where the duplicate number, this you can see by taking any 
     *      array with duplicates and for a linked list using its index and values
     */

    /*
     * Complexity - Time O(n) 
     * Space - O(1) 
     */


    public int findDuplicate_runner(int[] nums) {
        
        int slow = nums[0];
        int fast = nums[nums[0]];
        
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        
        // So until here we found the m at k
        
        slow = 0;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        
        
        return slow;
    }


// interview bit 
    /* Why does this work?
    First figure out why there will be a cycle when traversing the way we are.
    Also, why will the duplicate element be at the start of the cycle?
    slow moves 1 step at a time, while fast moves two steps at a time.
    Variables:
    x is the distance from the start of list to the start of cycle.
    y is the distance from the start of cycle to where the slow and fast meet.
    z is the disctance from the meeting point to the start of the cycle.
    distance travelled by fast = x + n(y + z) + y  (n is an integer >= 1)
    Also, distance travelled by fast = 2 * distance travelled by slow.
    Distance travelled by slow = x + y
    2(x + y) = x + n(y + z) + y
    x + y = n(y + z) 
    x = (n - 1)(y + z) + z
    Now when we reset slow to 0 and make it travel x distance, fast will travel 
    (n - 1) times around the circle + the distance z. Since fast was already at distance y
    when we started moving them again it will now reach the start of the cycle. 
    */



}