public class SerializeAndDesezBST {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        StringBuilder sb = new StringBuilder("-1");

        doPreOrderSerialize(root, sb);

        System.out.println(sb.toString());

        return sb.toString();
    }

    private void doPreOrderSerialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }

        sb.append("," + root.val);
        doPreOrderSerialize(root.left, sb);
        doPreOrderSerialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        String[] vals = data.split(",");
        if (vals.length <= 1) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(vals[1]));

        for (int i = 2; i < vals.length; i++) {
            InsertToBT(root, vals[i]);
        }

        return root;
    }

    private void InsertToBT(TreeNode root, String sVal) {
        if (root == null) {
            return;
        }

        Integer val = Integer.parseInt(sVal);

        if (val < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
                return;
            } else {
                InsertToBT(root.left, val.toString());
            }
        }

        if (val > root.val) {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                InsertToBT(root.right, val.toString());
            }
        }
    }
}