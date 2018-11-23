public class ReverseKGroupNodes {
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