/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


 /*

    Return the root node of a binary search tree that matches the given preorder traversal.

    (Recall that a binary search tree is a binary tree where for every node, 
    any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  
    Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

 */
class BSTFromPreOrder {
    int currentIndex = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = solution(preorder,Integer.MIN_VALUE, Integer.MAX_VALUE);
        return root;
    }
    
    private TreeNode solution(int[] preorder, int min, int max){
        if(currentIndex == preorder.length){
            return null;
        }
        
        TreeNode root = null;
        int val = preorder[currentIndex];
        
        if(val > min && val < max){
            root = new TreeNode(val);
            currentIndex = currentIndex+1;
            root.left = solution(preorder,min,val);
            root.right = solution(preorder,val,max);
        }
        
        return root;
        
    }
}