public class ReverseLinkedList2 {
    // Reverse a linked list from position m to n. Do it in one-pass.
    /**
     * Input: 1->2->3->4->5->NULL, m = 2, n = 4
     * 
     * Output: 1->4->3->2->5->NULL
     */

    // Do it like the bubble sort, just bubble the current one forward till n-m
    // times

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