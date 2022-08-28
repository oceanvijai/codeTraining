public class CountInvertedIndex {

  /**
    Let A be an array of integers. Call the pair of indices (i, j) inverted if i < j and A[i] > A[j].
    For example, if A - (4,1,2,3), count = 3
    
    
    Brute force is straight forward. Two loops and compare with n^2 time.
    Not to reduce N^2, we can try someting like the merge sort does
    
    break everyrange into two halfs. Perform a inversion count on each half. 
    Sort in desc order and merge the two half and send it to the higher level
    
    Now, when doing inversion count, since the result of two halfs is sorted, try and count only accross the two 
    sorted arrays, since the inversion count winthin the array is count when going below in the recursion.
    
    Same time and space for the merge sort: O(nlogn)
    
  **/




  public static void main(String []args){
        int[] a = new int[]{7,1,3,4,5,6};
        solve(a,0,a.length-1);
        System.out.println("ans: "+count);
     }
     
     private static List<Integer> solve(int[] a, int start, int end){
         List<Integer> arr = new ArrayList<>();
         // base case
         if(start == end){
             arr.add(a[start]);
             return arr;
         }
         // break the current range into two
         int mid = (start+end) / 2;
         // Doing it from the last so its easy to visualize
         List<Integer> arr1 = solve(a, mid+1, end);
         List<Integer> arr2 = solve(a, start, mid);

         // count inverted & sort the two arrays
         int i = 0, j = 0;
         while(i < arr1.size() && j < arr2.size()){
             if(arr2.get(j) > arr1.get(i)  ){
                arr.add(arr2.get(j));
                 count += arr1.size() - i;
                 j++;
             }else{
                 arr.add(arr1.get(i));
                 i++;
             }
         }
         if(i < arr1.size()){
             while(i < arr1.size()){
                 arr.add(arr1.get(i));
                 i++;
             }
         }else if(j < arr2.size()){
             while(j < arr2.size()){
                 arr.add(arr2.get(j));
                 j++;
             }
         }

         // return the sorted array result
         return arr;
     }



}
