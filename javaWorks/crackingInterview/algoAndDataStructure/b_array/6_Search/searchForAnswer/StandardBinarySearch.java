public class StandardBinarySearch{

  /**
     This will be the standard binary search code.

    varaibles and convepts
    1. Search space - The space within which are a searching for an answer
    2. start - start of the Search space - Inclusive - potential answer
    3. end - end of the Search space - Inclusive - potential answer
    4. mid - appx mid between start and end
    5. ans - Possible answer until the search is completed

  **/


  public void int binarySearchIndex(int searchItem, int[] arr){
      int n = arr.length-1;
      int start= 0, mid = n, ans = n;// depends

      while(start <= end){
        int mid = (start + (end-start/2));
        if(arr[mid] >= searchItem){
            ans = mid; // possible answer since it has "="
            end = mid-1;
        }else{
          start = mid+1;
        }
      }

      return arr[ans];
  }  

  

  
}
