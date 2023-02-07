public class RangeLookup {
    
    /**
      Write a program that takes as input a BST and an interval and returns the BST keys that lie in the interval.
      
      If the interval is [5,10], then the function should return all nodes within this range.
    **/
  
  
    /**
        We may need to do inorder traversal and pick only those in the range.
        Time O(n)
    **/
    
    
    /**
        We can reduce some time by using the BST property. Starting from root node,
            1. If a node is lesser than intervalStart, We can SKIP its left
            2. If a node is greater than intervalEnd, we can SKIP its right
            3. If a node is within the interval, try picking from both sides
        
        Time: O(height) - to reach the node with interval start + O(intervalNodes) 
                
    **/
    
    /**
        The varient of this problem can ask for just the number of nodes in this interval.
        For such problems we can try and add more details in every node like,
            1. Parent info
            2. Size of its left & right tree
            3. etc
         We can do a much better traversal using the property of the BST together.
        
    **/
        
    

}
