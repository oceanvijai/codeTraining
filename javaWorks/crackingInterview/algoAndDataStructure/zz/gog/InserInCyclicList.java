public class InserInCyclicList {
    /**
     * Given a node from a cyclic linked list which is sorted in ascending order,
     * write a function to insert a value into the list such that it remains a
     * cyclic sorted list. The given node can be a reference to any single node in
     * the list, and may not be necessarily the smallest value in the cyclic list.
     * 
     * If there are multiple suitable places for insertion, you may choose any place
     * to insert the new value. After the insertion, the cyclic list should remain
     * sorted.
     * 
     * If the list is empty (i.e., given node is null), you should create a new
     * single cyclic list and return the reference to that single node. Otherwise,
     * you should return the original given node.
     * 
     * The following example may help you understand the problem better:
     */

    /*
     * // Definition for a Node. class Node { public int val; public Node next;
     * 
     * public Node() {}
     * 
     * public Node(int _val,Node _next) { val = _val; next = _next; } };
     */
    class Solution {
        public Node insert(Node head, int insertVal) {

            if (head == null) {
                head = new Node();
                head.val = insertVal;
                head.next = head;
                return head;
            }

            Node prev = head;
            Node current = head.next;
            boolean found = false;

            while (current != head) {
                if (insertVal == prev.val || prev.val < insertVal && current.val >= insertVal
                        || current.val < prev.val && insertVal > prev.val
                        || current.val < prev.val && insertVal < current.val) {
                    found = true;
                    Node newNode = new Node(insertVal, current);
                    prev.next = newNode;
                    break;
                }
                prev = current;
                current = current.next;
            }

            if (!found) {
                Node node = new Node(insertVal);
                prev.next = node;
                node.next = current;
            }

            return head;
        }
    }
}