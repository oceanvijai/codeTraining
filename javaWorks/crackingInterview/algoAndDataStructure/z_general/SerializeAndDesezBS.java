public class SerializeAndDesezBS {
    private static final String seperator = ",";
    private static final String NULL = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder("");
        doPreOderSerialize(root, sb);
        sb.deleteCharAt(0);
        return sb.toString();
    }

    private void doPreOderSerialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(seperator + NULL);
            return;
        }

        sb.append(seperator + root.val);

        doPreOderSerialize(root.left, sb);
        doPreOderSerialize(root.right, sb);

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(seperator)));
        return build(nodes);
    }

    private TreeNode build(LinkedList<String> nodes) {
        String node = nodes.pollFirst();
        if (node == null || node.equals(NULL)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(node));
        TreeNode left = build(nodes);
        TreeNode right = build(nodes);

        root.left = left;
        root.right = right;

        return root;
    }
}