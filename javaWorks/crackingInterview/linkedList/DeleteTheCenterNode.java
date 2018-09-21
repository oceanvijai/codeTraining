public class DeleteTheCenterNode {

    /**
     * Delete Middle Node: Implement an algorithm to delete a node in the middle
     * (Le., any node but the first and last node, not necessarily the exact middle)
     * of a singly linked list, given only access to that node. EXAMPLE Input: the
     * node c from the linked list a - >b- >c - >d - >e- >f Result: nothing is
     * returned, but the new linked list looks like a->b->d->e->f
     */

    // Since we are not give access to the head node,
    // we can simply copy the values of the next node to the current node
    
    // Important : If the node to be deleted is the last one, we cant do it

    boolean deleteNode(LinkedListNode n) {
        if (n == null || n.next == nUll) {
            return false; // Failure
        }
        LinkedListNode next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;
    }
}
