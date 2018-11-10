public class ConstTreeFromInAndPreOrder {
    
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, null);
    }

    private TreeNode buildTree(int[] preorder, int preS, int preE, int[] inorder, int inS, int inE, String side) {

        if (preS == -1 || preS == preorder.length) {
            return null;
        }
        // [3,9,20,15,7]
        // [9,3,15,20,7]
        // System.out.println("preorder:"+ preS + ","+ preE);
        // System.out.println("inorder:"+ inS + ","+ inE);
        // System.out.println("side:"+ side);

        int rootVal = preorder[preS];
        TreeNode root = new TreeNode(rootVal);
        int index = findIndexInInorder(inS, inE, inorder, rootVal);

        // System.out.println("Index:"+ index);
        // System.out.println("---------------------");

        if (index == -1) {
            return null;
        }

        int leftSize = index - inS;
        TreeNode left = buildTree(preorder, preS + 1, preS + leftSize, inorder, inS, index - 1, "left");

        int rightSize = inE - index;
        TreeNode right = buildTree(preorder, preE - rightSize + 1, preE, inorder, index + 1, inE, "right");

        root.left = left;
        root.right = right;

        return root;
    }

    private int findIndexInInorder(int start, int end, int[] inorder, int val) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }
}