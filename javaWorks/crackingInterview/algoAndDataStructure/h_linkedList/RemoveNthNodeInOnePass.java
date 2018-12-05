public class RemoveNthNodeInOnePass {
    /**
     * Given a linked list, remove the n-th node from the end of list and return its
     * head.
     * 
     * Given linked list: 1->2->3->4->5, and n = 2.
     * 
     * After removing the second node from the end, the linked list becomes
     * 1->2->3->5.
     */

    /**
     * Approach: if we need to do it in one pass have two pointers Move the first
     * pointer n times Then move both the pointers in the same speed
     * 
     * when the first, pointer reaches the end, the second one -> next will be
     * pointing to the nth node
     * 
     * delete it
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        slow.next = head;

        // Move fast in front so that the gap between slow and fast becomes n
        for (int i = 1; i <= n + 1; i++) {
            fast = fast.next;
        }
        // Move fast to the end, maintaining the gap
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // Skip the desired node
        slow.next = slow.next.next;
        return start.next;

    }
}