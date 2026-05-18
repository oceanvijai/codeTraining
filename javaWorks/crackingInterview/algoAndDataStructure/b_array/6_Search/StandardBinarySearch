public class StandardBinarySearch{

  /**
     This will be the standard binary search code.

    varaibles and convepts
    1. Search space - The space within which are a searching for an answer
    2. start - start of the Search space - Inclusive - potential answer
    3. end - end of the Search space - Inclusive - potential answer
    4. mid - appx mid between start and end
    5. ans - Possible answer until the search is completed

    Note the below one will do the following,
    If arr=[1,2,3,4,4,4,9,9], searchItem = 4, result = 3; //giving us the lowest index of the search element
    If arr=[1,2,3,4,4,4,9,9], searchItem = 5, result = 6; //giving us the index where this element can be inserted the 

    Also, the intution behind different binary search code is based on the below points
      intution 1. When you eleminate either left or right sides of the array, we need to think the eleminated part is or is not part of the ans
          ex: When we assign mid+1 or m-1 or mid to the low and high pointer make sure the ans is not excluded
      intution 2. We need to return the left or right as ans, based on which one will be at the position when the last time the while loop condition was valid
        ex: When we say while(low < high) or while(low <= high), see where the execution will happen when this condidtion was true the last time and pick accordingly

  **/

  // Variation 1
  public static int binarySearchIndex(int searchItem, int[] arr){
        int n = arr.length-1;
        int start= 0, end = n, ans = n;// depends

        while(start <= end){ 
            int mid = start + (end-start/2);
            if(arr[mid] >= searchItem){ // Change this to '>' for upper bound
                ans = mid; // possible answer since it has "="
                end = mid-1;
            }else{
                start = mid+1;
            }
        }

        return ans;
    }

  // analysis
  // Intution 1 analysis: When we exclude the right/left half we might have seen the answer in the if block, so we save it in ans
  // Intution 2 analysis: The last time when start <= end, the possible senarios are
  //                      1. We found the element in its lowest index and start == end, so the if block will get executed
  //                      2. We didnt find that element, we are at an index which is just greater than the searched one. Here also, if gets executed


  // varation 2
  private static int binarySearch(int[] nums, int searchNum){
        int low = 0, high = nums.length - 1;
        while(low <= high){
            int mid = low + (high-low/2);
            if(nums[mid] >= searchNum){  // Change this to '>' for upper bound
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return low; // possible answer since it remains in place when the condtion low <= high was true last time
 }


  // analysis
  // Intution 1 analysis: When we exclude the right/left half we might have seen the answer. In that case intution #2 will play the role
  // Intution 2 analysis: The last time when low <= high, the possible senarios are
  //                      1. We found the element in its lowest index and start == end, so the if block will get executed, so the low remains in the coorect postion
  //                      2. We didnt find that element, we are at an index which is just greater and start == end. Here also, if gets executed, so the low remains in the coorect postion


  // variation 3

  private int binarySearch(int[] arr, int target) {
    int low = 0, high = arr.length;
    while (low < high) {
        int mid = (low + high) / 2;
        if (arr[mid] < target) {  
            low = mid + 1;        // move right
        } else {
            high = mid;           // move left
        }
    }
    return low; // index of first element >= target
}

 // analysis
  // Intution 1 analysis: When we exclude the right, we make sure the mid is included since it might be a possible answer since the else incudes the "==" secnario
  //                      When we exclude the left, we dont include mid in the next computation  
  // Intution 2 analysis: The last time when low < high, the possible senarios are
  //                      1. We found the element in its lowest index and high == low+1, then mid = (low + high) / 2 will give low's index. So the if block gets executed. So the 
  //                         low moves to the next index which is the ans
  //                      2. We didnt find that element, So low is at index where num[low] < target & high is at index where num[high] just greater than target, and high = low + 1
  //                         , so when mid is calculated, the low moves to the high position where low == high

  



}
