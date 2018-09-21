public class KthNodeInList {

    // Return Kth to Last: Implement an algorithm to find the kth to last
    // element of a singly linked list.

    /**
     * Doing this with size of list known is simple Or we can first find the length
     * and then do it
     */

    // 2: recursive solution
    // This takes time O(n) and space O(n)

    int printKthToLast(LinkedListNode head, int k) {
        if (head == null) {
            return 0;
        }
        int index = printKthToLast(head.next, k) + l;
        if (index == k) {
            System.out.println(k + "th to last node is " + head.data);
        }
        return index;
    }

    // 3: better solution is use two pointers
    // one starts at the head and other starts after k nodes
    // move both of them in same pace, and when fast hits the end, we are done

    LinkedListNode nthToLast(LinkedListNode head, int k) {
        LinkedListNode p1 = head;
        LinkedListNode p2 = head;

        /* Move pi k nodes into the list. */
        for (int i = 0; i < k; i++) {
            if (p1 == nUll)
                return null; // Out of bounds
            p1 = pl.next;
        }

        /*
         * Move them at the same pace. When p1 hits the end, p2 will be at the right
         * element .
         */
        while (p1 != nUll) {
            pl = pl.next;
            p2 = p2.next;
        }
        return p2;
    }

}