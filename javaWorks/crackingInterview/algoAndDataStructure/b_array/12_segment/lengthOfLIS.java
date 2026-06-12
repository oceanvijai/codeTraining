public class lengthOfLIS{

  /**
    Find the longest increasing subsequence using segment tree
    
  **/

  public int lengthOfLIS(int[] nums) {
       // build a tree with all the distinct elemets in the array in sorted fashion
        int[] distinctNums = Arrays.stream(nums).distinct().sorted().toArray();
        Segment tree = build(distinctNums, 0, distinctNums.length-1);

        // For every element we find, check the tree and also update it
        int ans = 0;
        for(int num : nums){
            // query for a subsequence lesser than this num
            int result = query(tree, Integer.MIN_VALUE, num-1);

            ans = Math.max(ans, result+1);

            // Update the subsequence length ending at num
            update(tree, num, result+1);
        }

        return ans;
    }

    private int query(Segment node, int startRange, int endRange){
        if(node == null){
            return 0;
        }

        // Out of range
        if(startRange > node.endRange || endRange < node.startRange) return 0;

        // Total overlap
        if(startRange <= node.startRange && node.endRange <= endRange) return node.longestSequenceEndingInThisRange;

        // Partial overlap
        return Math.max(query(node.left, startRange, endRange), 
                        query(node.right, startRange, endRange));

    }

    private void update(Segment node, int num, int value){
        if(node == null){
            return;
        }

        if(node.startRange == node.endRange && node.startRange == num){
            node.longestSequenceEndingInThisRange = value;
            return;
        }

        // Out of range
        if(node.endRange < num  || node.startRange > num) return;

        update(node.left, num, value);
        update(node.right, num, value);

        int leftValue = node.left != null ? node.left.longestSequenceEndingInThisRange : 0;
        int rightValue = node.right != null ? node.right.longestSequenceEndingInThisRange : 0;

        node.longestSequenceEndingInThisRange = Math.max(leftValue, rightValue);
    }


    private Segment build(int[] nums, int startIndex, int endIndex){
        if(startIndex > endIndex){
            return null;
        }

        if(startIndex == endIndex){
            return new Segment(nums[startIndex], nums[startIndex], 0);
        }

        int mid = startIndex + ((endIndex - startIndex)/2);
        Segment left = build(nums, startIndex, mid);
        Segment right = build(nums, mid+1, endIndex);

        
        Segment node = new Segment(nums[startIndex], nums[endIndex], 0);
        node.left = left;
        node.right = right;
        return node;
    }

    private class Segment{
        int startRange;
        int endRange;
        int longestSequenceEndingInThisRange;

        Segment right;
        Segment left;

        public Segment(int startRange, int endRange, int value){
            this.startRange = startRange;
            this.endRange = endRange;
            this.longestSequenceEndingInThisRange = value;
        }
    }


  
}
