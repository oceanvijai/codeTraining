public class CountSmaller{
  
  
  /**
    Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements to the right of nums[i].

    Example 1:

    Input: nums = [5,2,6,1]
    Output: [2,1,1,0]
    Explanation:
    To the right of 5 there are 2 smaller elements (2 and 1).
    To the right of 2 there is only 1 smaller element (1).
    To the right of 6 there is 1 smaller element (1).
    To the right of 1 there is 0 smaller element.


    Approach: For a segement tree and query from every number.
    But how to form the tree for this problem ?

    1. Get all unique values and sort them. This should be our search space and also the lower and upper bound of the tree
    2. The tree left node will its count, the middle nodes will have the count for a range like |3-6 count 3| 7-15 count 9| etc...
    2. The trick it to start from RIGHT to LEFT, so we can query the answer for the current element when we move backward
    3. When we find an element, first check the tree for answer.
    4. Then update its leaf and the middle nodes

  **/


  public List<Integer> countSmaller(int[] nums) {
        // get a copy with only distinct elements
        int[] distinctNums = Arrays.stream(nums).distinct().sorted().toArray();

        // Build segment tree with the distinctNums
        Segment root = buildTree(distinctNums, 0, distinctNums.length-1);
        int[] ans = new int[nums.length];

        // now try 2 things for each items from RIGHT to LEFT
        for(int i = nums.length-1; i >= 0; i--){
            int currentNum = nums[i];

            // 1. Query to see how many were 
            ans[i] = query(root, Integer.MIN_VALUE, currentNum-1);

            // 2. Update the presence of this current num
            update(root, currentNum);
        }

        return Arrays.stream(ans).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    private int query(Segment node, int startRange, int endRange){
        if(node == null){
            return 0;
        }

        // Outside range range
        if(endRange < node.startRange || startRange > node.endRange) return 0;

        // Inside current range
        // 1. Complete overlap
        if(startRange <= node.startRange && endRange >= node.endRange) return node.countSeenSoFar;


        // 2. Partail overlap
        return query(node.left, startRange, Math.min(node.endRange, endRange)) + 
                   query(node.right, Math.max(node.startRange, startRange), endRange);
    }


    private void update(Segment node, int currentNum){
        if(node == null){
            return;
        }

        // Leaf
        if(node.startRange == currentNum && node.endRange == currentNum){
            node.countSeenSoFar++;
            return;
        }

        // Out of range
        if(currentNum > node.endRange || currentNum < node.startRange){
            return;
        }

        // In range, only for internal middle nodes
        
        update(node.left, currentNum);
        update(node.right, currentNum);

        int newCount = 0;

        if(node.left!= null){
            newCount += node.left.countSeenSoFar;
        }
        if(node.right!= null){
            newCount += node.right.countSeenSoFar;
        }
        node.countSeenSoFar = newCount;
    }


    private Segment buildTree(int[] nums, int start, int end){
        if(start > end){
            return null;
        }
        if(start == end) return new Segment(nums[start], nums[end], 0);

        int mid = start + (end - start)/ 2;
        Segment left = buildTree(nums, start, mid);
        Segment right = buildTree(nums, mid+1, end);

        Segment node = new Segment(nums[start], nums[end], 0);
        node.left = left;
        node.right = right;

        return node;
    }



    private class Segment{
        int startRange;
        int endRange;
        int countSeenSoFar;

        Segment left;
        Segment right;


        public Segment(int startRange, int endRange, int countSeenSoFar){
            this.startRange = startRange;
            this.endRange = endRange;
            this.countSeenSoFar = countSeenSoFar;
        }
    }


  
}
