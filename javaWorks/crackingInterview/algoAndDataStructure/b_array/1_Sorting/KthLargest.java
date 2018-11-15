public class KthLargest {

    /**
     * First we can use a min heap and push only k elements in the heap
     * 
     * This is O(nlogn) which can be done by simply sorting the array and find the kth largest
     * 
     */


    public int findKthLargest_heap(int[] nums, int k) {

        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int val : nums) {
            pq.offer(val);
    
            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    /**
     * This is a selection rank / quick search alogithm
     * If we want the k the largest element
     * 
     * Push k elements larger than k at an index and find the min in the left
     */

    public int findKthLargest(int[] nums, int k) {

        int start = 0;
        int end = nums.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = partition(nums, start, end); // anything left of mid will greater than the mid
            if (mid == k) {
                return findMin(nums, mid - 1);
            } else if (mid < k) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return nums[mid];
    }

    private int partition(int[] nums, int start, int end) {
        int pivotElement = nums[end];
        int i = start;
        int p = start;
        while (i <= end) {
            if (nums[i] > pivotElement) {
                swap(nums, i, p);
                p++;
            }
            i++;
        }

        swap(nums, end, p);

        return p;

    }

    private void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int findMin(int[] nums, int end) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i <= end; i++) {
            min = Math.min(min, nums[i]);
        }

        return min;
    }
}