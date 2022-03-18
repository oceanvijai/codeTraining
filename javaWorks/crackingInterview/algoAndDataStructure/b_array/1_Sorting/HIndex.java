 public class  HIndex{
 	/**
		Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

		"A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

 	**/


 	/**
	
	Input: citations = [3,0,6,1,5]
	Output: 3 
	Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had 
	             received 3, 0, 6, 1, 5 citations respectively. 
	             Since the researcher has 3 papers with at least 3 citations each and the remaining 
	             two with no more than 3 citations each, her h-index is 3.

 	**/


 	// Approch 1: sort the array and check if the index value is greater or equal to the value in the index. Straight forward
 	// O(nlogn)


 	// Approach 2: count and store

 	// Any value greater than the max index (length of array) is considered equal to the value of max index
 	// So we create a tmp array and for every element in the input array, we increment the value in the index 
 	// location. If the value is greater than index location, then we increment the last index value

 	// Then we iterate from last and add up the count and compare it with the index


 	public int hIndex(int[] citations) {
        int[] counts = new int[citations.length + 2];
        for (int i = citations.length - 1; i >= 0; i--)  
            counts[Math.min(citations[i], citations.length)]++;
        int h;
        for (h = citations.length; h > 0; h--) {
            counts[h] += counts[h + 1];
            if (counts[h] >= h)  break;
        }
        return h;
    }


 }