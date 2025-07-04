class SegmentTree {

    Segement segmentTree;

    public SegmentTree(int[] nums) {
        initilize(nums);
    }

    public void update(int index, int val) {
        updateTree(segmentTree, index, val);
    }

    public int sumRange(int left, int right) {
        return findSum(segmentTree, left, right);
    }

    private int findSum(Segement node, int start, int end) {
        if (node == null) {
            return 0;
        }

        // Adjust range for this segemnt
        start = Math.max(node.start,start);
        end = Math.min(node.end,end);

        // exact overlap
        if(node.start == start && node.end == end){
            return node.value;
        }

        // no overlap
        if(node.end < start || node.start > end){
            return 0;
        }

        // Partial overlap
        int ans = 0;
        ans += findSum(node.left, start, end);
        ans += findSum(node.right, start, end);
        return ans;
    }

    private void updateTree(Segement node, int index, int val) {
        // out of range
        if (node == null || (node.end < index || node.start > index)) {
            return;
        }
        if (index == node.start && node.start == node.end) {
            node.value = val;
            return;
        }

        // Recalculate segment value
        updateTree(node.left, index, val);
        updateTree(node.right, index, val);

        node.value = node.left != null ? node.left.value : 0;
        node.value += node.right != null ? node.right.value : 0;
    }


    private void initilize(int[] nums) {
        segmentTree = buildTree(0, nums.length - 1, nums);
    }

    private Segement buildTree(int rangeLow, int rangeHigh, int[] nums) {
        if (rangeHigh == rangeLow) {
            return new Segement(rangeLow, rangeHigh, nums[rangeHigh]);
        }

        int mid = (rangeHigh + rangeLow) / 2;
        // Build left
        Segement left = buildTree(rangeLow, mid, nums);
        // Build right
        Segement right = buildTree(mid + 1, rangeHigh, nums);

        Segement node = new Segement(rangeLow, rangeHigh, left.value + right.value);
        node.left = left;
        node.right = right;

        return node;
    }

    private class Segement {
        public Segement(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        int start, end, value;
        Segement left, right;
    }
}
