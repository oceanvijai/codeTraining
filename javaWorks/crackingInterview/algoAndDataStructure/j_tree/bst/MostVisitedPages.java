public class MostVisitedPages{
  
  
  /**
       Write a function to read the next line from a log file, and a function to find the k most visited pages, 
       where k is an input to the function. Optimize performance for the situation where calls to the two functions are interleaved. 
       You can assume the set of distinct pages is small enough to fit in RAM
  **/
  
  
  /**
    As a concrete example, suppose the log file ids appear in the following order: g,a,t,t,a,a,a,g,t,c,t,a,t,i.e., 
    there are four pages with ids a,c,g,t. After the first 10 lines have been read, 
    the most common page is a with a count of 4, and the next most common page is t with a count of 3.
  **/
  
  
  /**
    First we need to keep the current count of all the nodes
    Need to udpate the count on the next line read
    Find the top k pages from the list
    
    Solution 1: Brute force: HasMap all the pages and counts. Iterate over them to find the k most visited. Time O(n).

    Solution 2: 
    We can have an hashmap with the pages count and a tree map to store the count & pages
    When we add a page we update both maps
    To get the top K, do head
  
  **/
  



}
