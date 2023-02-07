public class ThreeOrderedBSTNodes{

  /**
    Write a program which takes two nodes in a BST and a third node, the "middle" node and determine
    
    if one of the two nodes is a proper ancestor and the other a proper descendant of the middle.
    
    A proper ancestor of a node is an ancestor that is not equal to the node; a proper descendant is defined similarly
  **/
  
  /**
    Note that any one of the two nodes could have been either an ancestor or descendant.
    
    So by brute force, we first start search from each given node and try and find the middle node. 
    But its imperative that one of the search is gona fail.
    And then proceed from middle to the other node.
    Time in O(height) 
    
    
    We can make this a little efficient by doing the search from both node paralelly.
    This was we make one iteration to find the middle from any one of the node.
    Once its done, searh for the other which havent reached middle.
  **/




}
