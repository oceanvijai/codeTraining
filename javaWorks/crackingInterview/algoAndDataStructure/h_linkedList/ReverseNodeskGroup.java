public class ReverseNodeskGroup {

    /**
     * Given a linked list, reverse the nodes of a linked list k at a time and
     * return its modified list.
     * 
     * k is a positive integer and is less than or equal to the length of the linked
     * list. If the number of nodes is not a multiple of k then left-out nodes in
     * the end should remain as it is.
     * 
     */

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode current = dummy;

        while (current != null) {
            ListNode h = current.next;
            ListNode tail = getNextTail(h, k);

            if (tail == null) {
                break;
            } else {
                // break the tail link
                ListNode t = tail;
                tail = tail.next;
                t.next = null;
            }

            current.next = reverse(h);
            h.next = tail;
            current = h;
        }

        return dummy.next;
    }

    private ListNode getNextTail(ListNode head, int k) {
        int count = 1;

        while (head != null && count != k) {
            count++;
            head = head.next;
        }

        // System.out.println("new Tail found:"+head.val);
        return head;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = head;
        ListNode current = head.next;

        while (current != null) {
            ListNode tmp = current.next;
            current.next = prev;

            prev = current;
            current = tmp;
        }

        return prev;
    }
}