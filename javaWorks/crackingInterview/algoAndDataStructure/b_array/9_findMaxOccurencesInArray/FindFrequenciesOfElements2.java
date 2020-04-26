public class FindFrequenciesOfElements2{

	/**
     * Count frequencies of all elements in array in O(1) extra space and O(n) time
     */

    /**
     * Input: arr[] = {2, 3, 3, 2, 5}
     * 
     * Output: Below are frequencies of all elements
     * 
     * 1 -> 0
     * 
     * 2 -> 2
     * 
     * 3 -> 2
     * 
     * 4 -> 0
     * 
     * 5 -> 1
     */


    // This is approach 2
    // encode each index with its value and it count

    // Let n = arr.length +1;
    // get original value = arr[i] % n
    // get count = arr[i] / n;

    // both = (arr[i] % n) + n;

    void printfrequency(int arr[], int n)  
    { 
        // Subtract 1 from every element so that the elements 
        // become in range from 0 to n-1 
        for (int j = 0; j < n; j++) 
            arr[j] = arr[j] - 1; 
  
        // Use every element arr[i] as index and add 'n' to 
        // element present at arr[i]%n to keep track of count of 
        // occurrences of arr[i] 
        for (int i = 0; i < n; i++) 
            arr[arr[i] % n] = arr[arr[i] % n] + n;  // this is incrementing the count
  
        // To print counts, simply print the number of times n 
        // was added at index corresponding to every element 
        for (int i = 0; i < n; i++) 
            System.out.println(i + 1 + "->" + arr[i] / n); // this is getting only the count out.
    } 
	
}