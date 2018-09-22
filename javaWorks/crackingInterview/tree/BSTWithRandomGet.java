import java.util.Random;

public class BSTWithRandomGet {
    /**
     * Random Node: You are implementing a binary search tree class from scratch,
     * which, in addition to insert, find, and delete, has a method getRandomNode()
     * which returns a random node from the tree. All nodes should be equally likely
     * to be chosen. Design and implement an algorithm for get Ra ndomNode, and
     * explain how you would implement the rest of the methods.
     */

    // 1: We can have an array interanlly and pick from that randomly, when get
    // getRandomNode is called
    // problem is we need to keep that array in sync with the tree duing insert and
    // delete

    // 2: we can label the nodes from 1 to N and when getRandomNode is called,
    // we generate a random label get get it from the tree
    // Again we need to update the label nodes everytime we insert or delete

    // Also we have these general problems
    // 1. what is the tree id not balanced properly, which is all cases
    // we then have to better in picking up the random nodes

    // 3: So we have a trick
    // Let say we have the size (number of nodes) under each node, then we can do
    // this,
    // get the random number starting from 1 to size, then decide which way to go
    // LEFT or RIGHT
    // based on the size values of left and right node and pick the node with that
    // random number

    Node root = null;

    private class Node {
        public int data;
        public Node left;
        public Node right;
        public int size;

        public Node(int data) {
            this.data = data;
        }

        public Node getIthNode(int i){
            if(i < size){
                return this.left.getIthNode(i);
            }else if(i == size){
                return this;
            }else{
                return this.right.getIthNode(i));
            }
        }

        public void insertlnOrder(int d) {
            if (d <= data) {
                if (left == nUll) {
                    left = new Node(d);
                } else {
                    left.insertlnOrder(d);
                }
            } else {
                if (right == nUll) {
                    right = new Node(d);
                } else {
                    right.insertlnOrder(d);
                }
            }
            size++;
        }
    }

    public Node getRandomNode() {
        if (root == null) {
            return null;
        }
        Random r = new Random();
        int randomIndex = r.nextInt(root.size);
        return root.getIthNode(randomIndex);
    }

    public void Insert(int data) {
        // do the noraml insert logic and finally update the size for every node
        root.insertlnOrder(data);
    }
}