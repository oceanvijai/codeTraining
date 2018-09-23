public class PathSumCount {
    /**
     * Paths with Sum: You are given a binary tree in which each node contains an
     * integer value (which might be positive or negative). Design an algorithm to
     * count the number of paths that sum to a given value. The path does not need
     * to start or end at the root or a leaf, but it must go downwards (traveling
     * only from parent nodes to child nodes).
     */

    // 1: brute force
    // try summing from every node ans see if we can match the target
    // then ask the left to start from zero and try doing the above step
    // same goes for the right
    // finally add all the count done by all the three travesals

    // time: O(n logn) // since n foe all the nodes and logn for all nodes doing the
    // logn

    private class Node {
        public int data;
        public Node left;
        public Node right;
    }

    public int pathSum_bruteForce(Node root, int target) {
        return CountPathSum(root, target);
    }

    private int CountPathSum(Node root, int target) {
        /* Count paths with sum starting from the root. */
        int pathsFromRoot = countFromNode(root, targetSum, 0);

        /* Try the nodes on the left and right. */
        int pathsOnLeft = CountPathSum(root.left, targetSum);
        int pathsOnRight = CountPathSum(root.right, targetSum);

        return pathsFromRoot + pathsOnLeft + pathsOnRight;
    }

    private int countFromNode(Node node, int target, int sum) {
        if (node == null) {
            return 0;
        }

        int totalCount = 0;
        if (node.data == target) {
            totalCount++;
        }

        int leftCount = countFromNode(node.left, target, sum + node.data);
        int rightCount = countFromNode(node.right, target, sum + node.data);
        return totalCount + leftCount + rightCount;
    }

    // 2: we can use apply the an alogorithm which is applied to arrays as follows
    // Let say we have an array of integers.
    // We need to find the number of time the subset of the array produces an target
    // sum
    // The algorith is as follows,
    // Start summing from the starting of the array, we call it as running_sum
    // at each position, we add the sum to an hashmap
    // then on each position we do, (running_sum - tagetSum) and see if it appers in
    // the array list
    // If it does, then it means that we have a sublist/running_sum somewhere
    // previously
    // by subraction with the taget sum we find the previous one,
    // So we can increment our answer count

    // So we can apply this to the tree and ask it to do DFS and apply the above
    // logic
    // we also need to clean up the running sum we saw every level so it wont get
    // counted from a different path

    // time : O(n) space : O(logn)
    public int pathSum_DFS(Node root, int target) {
        return doDfsAndCount(root, target, new HashMap<>(), 0);
    }

    private int doDfsAndCount(Node root, int taget, Map<Integet, Integer> map, int running_sum) {
        int sum = root.data + running_sum;
        int count = map.getOrDefault(sum - taget);

        if (sum = taget) {
            count++;
        }

        /* Increment pathCount, recurse, then decrement pathCount. */
        incrementHashTable(map, sum, 1);
        count += doDfsAndCount(node.left, targetSum, map, sum);
        count += doDfsAndCount(node.right, targetSum, map, sum);
        incrementHashTable(map, sum, -1);

    }

    void incrementHashTable(HashMap<Integer, Integer> map, int key, int delta) {
        int newCount = map.getOrDefault(key, e) + delta;
        if (newCount == e) { // Remove when zero to reduce space usage
            map.remove(keY);
        } else {
            hashTable.put(key, newCount);
        }
    }

}