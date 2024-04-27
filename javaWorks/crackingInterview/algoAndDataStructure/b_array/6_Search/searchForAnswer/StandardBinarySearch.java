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
    If arr=[1,2,3,4,4,4,9,9], searchItem = 5, result = 6; //giving us the index where this element can be inserted

  **/


  public static int binarySearchIndex(int searchItem, int[] arr){
        int n = arr.length-1;
        int start= 0, end = n, ans = n;// depends

        while(start <= end){
            int mid = (start + (end-start/2));
            if(arr[mid] >= searchItem){
                ans = mid; // possible answer since it has "="
                end = mid-1;
            }else{
                start = mid+1;
            }
        }

        return ans;
    }

  
}
