public class zigzagLevelOrder {

    public List<List<Integer>> zigzagOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> ans = new ArrayList<>();

        List<Integer> oddLevel;
        Stack<TreeNode> oddStack = new Stack<>();

        List<Integer> evenLevel;
        Stack<TreeNode> evenStack = new Stack<>();

        oddStack.push(root);

        while (!oddStack.isEmpty() || !evenStack.isEmpty()) {

            oddLevel = new ArrayList<Integer>();
            while (!oddStack.isEmpty()) {
                TreeNode n = oddStack.pop();
                oddLevel.add(n.val);
                if (n.left != null) {
                    evenStack.push(n.left);
                }
                if (n.right != null) {
                    evenStack.push(n.right);
                }
            }

            if (oddLevel.size() > 0) {
                ans.add(oddLevel);
            }

            evenLevel = new ArrayList<Integer>();
            while (!evenStack.isEmpty()) {
                TreeNode n = evenStack.pop();
                evenLevel.add(n.val);
                if (n.right != null) {
                    oddStack.push(n.right);
                }
                if (n.left != null) {
                    oddStack.push(n.left);
                }
            }

            if (evenLevel.size() > 0) {
                ans.add(evenLevel);
            }

        }

        return ans;
    }

    // simpler code

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> ans = new ArrayList<>();

        List<Integer> oddLevel;
        Stack<TreeNode> oddStack = new Stack<>();

        List<Integer> evenLevel;
        Stack<TreeNode> evenStack = new Stack<>();

        oddStack.push(root);

        while (!oddStack.isEmpty() || !evenStack.isEmpty()) {

            oddLevel = new ArrayList<Integer>();
            while (!oddStack.isEmpty()) {
                TreeNode n = oddStack.pop();
                oddLevel.add(n.val);
                if (n.left != null) {
                    evenStack.push(n.left);
                }
                if (n.right != null) {
                    evenStack.push(n.right);
                }
            }

            if (oddLevel.size() > 0) {
                ans.add(oddLevel);
            }

            evenLevel = new ArrayList<Integer>();
            while (!evenStack.isEmpty()) {
                TreeNode n = evenStack.pop();
                evenLevel.add(n.val);
                if (n.right != null) {
                    oddStack.push(n.right);
                }
                if (n.left != null) {
                    oddStack.push(n.left);
                }
            }

            if (evenLevel.size() > 0) {
                ans.add(evenLevel);
            }

        }

        return ans;
    }

}