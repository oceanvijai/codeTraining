public class MaximumBinaryTree {
    /**
     * Given an integer array with no duplicates. A maximum tree building on this
     * array is defined as follow:
     * 
     * The root is the maximum number in the array. The left subtree is the maximum
     * tree constructed from left part subarray divided by the maximum number. The
     * right subtree is the maximum tree constructed from right part subarray
     * divided by the maximum number. Construct the maximum tree by the given array
     * and output the root node of this tree.
     */


    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> stack = new LinkedList<>();
        for(int i = 0; i < nums.length; i++) {
            TreeNode curr = new TreeNode(nums[i]);
            while(!stack.isEmpty() && stack.peek().val < nums[i]) {
                curr.left = stack.pop();
            }
            if(!stack.isEmpty()) {
                stack.peek().right = curr;
            }
            stack.push(curr);
        }
        
        return stack.isEmpty() ? null : stack.removeLast();
    }
}
