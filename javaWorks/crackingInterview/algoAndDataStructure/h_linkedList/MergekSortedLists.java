public class MergekSortedLists {
    /**
     * Merge k sorted linked lists and return it as one sorted list. Analyze and
     * describe its complexity.
     * 
     * Input: [ 1->4->5, 1->3->4, 2->6 ]
     * 
     * Output: 1->1->2->3->4->4->5->6
     */

    private PriorityQueue<ListNode> heap;

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }

        heap = new PriorityQueue<ListNode>(lists.length, (ListNode x, ListNode y) -> {
            return x.val - y.val;
        });

        for (ListNode l : lists) {
            if (l != null) {
                heap.add(l);
            }
        }

        ListNode head = new ListNode(-1);
        ListNode current = head;
        ListNode min = getMin();

        while (min != null) {
            current.next = min;
            current = current.next;
            min = getMin();
        }

        return head.next;
    }

    private ListNode getMin() {
        if (heap.peek() != null) {
            ListNode min = heap.poll();
            if (min.next != null) {
                heap.add(min.next);
            }
            return min;
        }

        return null;
    }
}