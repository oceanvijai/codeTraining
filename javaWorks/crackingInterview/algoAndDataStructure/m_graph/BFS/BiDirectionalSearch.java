import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BiDirectionalSearch {

    // You are given a undirected graph and you need to get from source to
    // destination

    // Ok lets use, BFS.

    // We can do BFS from both side to increase performance

    // But there are limitiations
    // Bidirection search is good if the The branching factor is exactly the same in
    // both directions.

    // Time complexity : O(b^d/2), b - branching factor, d - distance between source
    // and destination
    // Explanation: The time compexity of BFS is O(b^d)
    // Since we can find the destination in half way we get d/2.

    private void doBidirectionSearch(Node src, Node dest) {
        Queue<Node> queueA = new Queue<Node>();
        Queue<Node> queueB = new Queue<Node>();
        Set<Node> visitedA = new HashSet<Node>();
        Set<Node> visitedB = new HashSet<Node>();

        visitedA.add(a);
        visitedB.add(b);
        queueA.add(a);
        queueB.add(b);

        while (!queueA.isEmpty() && !queueB.isEmpty()) {
            Node ans = doBFSAndCheckForIntersetion(queueA, visitedA, visitedB);
            if (ans != null) {
                // do something like merge path
                break;
            }

            ans = doBFSAndCheckForIntersetion(queueB, visitedB, visitedA);
            if (ans != null) {
                // do something
                break;
            }
        }
    }

    private Node doBFSAndCheckForIntersetion(Queue<Node> queue, Set<Node> visitedA, Set<Node> visitedB) {
        // We only want to search one level at a time.
        int count = queue.size();

        for (int i = 0; i < count; i++) {
            Node path = queue.poll();

            if (visitedB.contains(path)) {
                return path;
            } else {
                visitedA.add(path);
            }

            for (Node f : node.adjacent) {
                queue.add(f);
            }

        }

        return null;
    }

    private static class Node<T> {
        public final T data;
        public final Set<Node> adjacent = new HashSet<Node>();

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        // returns if the node was added, false if already there
        public boolean addAdjacent(Node node) {
            return adjacent.add(node);
        }

        // returns true if any were added
        public boolean addAdjacents(Set<Node> nodes) {
            return adjacent.addAll(nodes);
        }
    }

}