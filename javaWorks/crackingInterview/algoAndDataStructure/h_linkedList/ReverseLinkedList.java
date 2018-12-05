public class ReverseLinkedList {
    /**
     * Reverse a linked list from position m to n. Do it in one-pass.
     * 
     * Input: 1->2->3->4->5->NULL, m = 2, n = 4
     * 
     * Output: 1->4->3->2->5->NULL
     */

    /**
     * Approach
     * 
     * first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, current = 2, next = 4
     * 
     * second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, current = 2, next = 5
     * (finish)
     */

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null)
            return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // make a pointer pre as a marker for the node before reversing
        ListNode pre = dummy;
        for (int i = 0; i < m - 1; i++)
            pre = pre.next;

        ListNode current = pre.next; // This is gona remain the same, since its gona move inwards always
        ListNode next = current.next; // This one always points next of the current

        for (int i = 0; i < n - m; i++) {
            current.next = next.next;
            next.next = pre.next;
            pre.next = next;
            next = current.next; // reset the next to the current next
        }

        return dummy.next;

    }
}