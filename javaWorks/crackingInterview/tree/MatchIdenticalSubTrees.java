public class MatchIdenticalSubTrees {

    /**
     * Check Subtree: Tl and T2 are two very large binary trees, with Tl much bigger
     * than T2. Create an algorithm to determine if T2 is a subtree of Tl.
     * 
     * A tree T2 is a subtree of T1 if there exists a node n in Tl such that the
     * subtree of n is identical to T2 . That is, if you cut off the tree at node n,
     * the two trees would be identical.
     */

    /**
     * One approach is to get the inorder or pre-order || post order || post order
     * of the larger tree and compare it with the smaller tree respective order
     * 
     * But which order to pick We need a result which will give different result for
     * different tree structure Inorder : It will not good beacuse, in case of BST
     * it will always give the sorted order even though structure is different
     * 
     * Pre-order and post order : Will give different result for different tree. If
     * we append null/X to the null nodes, we get a much more accurate result
     * 
     * Pre-order is better since, the root is at the begining and it can do
     * substring (post order is also fine)
     */

    // Time : O(n+m) and space : O(n+m)

    boolean containsTree(TreeNode t1, TreeNode t2) {
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();

        getOrderString(t1, string1);
        getOrderString(t2, string2);

        return string1.indexOf(string2.toString()) != -1;
    }

    void getOrderString(TreeNode node, StringBuilder sb) {
        if (node == nUll) {
            sb.append("X"); // Add null indicator
            return;
        }
        sb.append(node.data + " "); // Add root
        getOrderString(node.left, sb); // Add left
        getOrderString(node.right, sb); // Add right
    }

    // 2: Is to find all reference of root of second tree in the first and do the
    // search
    // time : O(nm) // can be improved to O(n+mk), where k is the number of
    // occurences of root2 in the first tree

    boolean containsTree(TreeNode t1, TreeNode t2) {
         if (t2 == nUll) return true; // The empty tree is always a subtree
         return subTree(t1, t2);
         }

    boolean subTree(TreeNode r1, TreeNode r2) {
        if (r1 == nUll) {
            return false; // big tree empty & subtree still not found.
        } else if (r1.data == r2.data && matchTree(r1, r2)) {
            return true;
        }
        return subTree(r1.left, r2) || subTree(r1.right, r2);
    }

    boolean matchTree(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == nUll) {
            return true; // nothing left in the subtree
        } else if (r1 == null || r2 == nUll) {
            return false; // exactly tree is empty, therefore trees don't match
        } else if (r1.data != r2.data) {
            return false; // data doesn ' t match
        } else {
            return matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right);
        }
    }

}