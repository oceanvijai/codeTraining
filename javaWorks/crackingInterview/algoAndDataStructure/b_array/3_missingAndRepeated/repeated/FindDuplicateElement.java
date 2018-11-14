public class FindDuplicateElement {
    // Given an array nums containing n + 1 integers where each integer is between 1
    // and n (inclusive), prove that at least one duplicate number must exist.
    // Assume that there is only one duplicate number, find the duplicate one.

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

    /* class Solution(object):
    def findDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        low = 1
        high = len(nums)-1
        
        while low < high:
            mid = low+(high-low)/2
            count = 0
            for i in nums:
                if i <= mid:
                    count+=1
            if count <= mid:
                low = mid+1
            else:
                high = mid
        return low */

    /**
     * So let try the marking and runner techinques
     */

    void printRepeating(int arr[], int size) {
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
     */

     

}