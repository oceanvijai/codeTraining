public class Partition {
    /**
     * Write code to partition a linked list around a value x, such that all nodes
     * less than x come before all nodes greater than or equal to x. If x is
     * contained within the list, the values of x only need to be after the elements
     * less than x (see below). The partition element x can appear anywhere in the
     * "right partition"; it does not need to appear between the left and right
     * partitions.
     */

    /**
     * EXAMPLE Input: 3 -> 5 -> 8 -> 5 -> 113 -> 2 -> 1 [partition = 5] Output: 3 ->
     * 1 -> 2 -> 113 -> 5 -> 5 -> 8
     */

    // Meaning partition the list based on x, all elements lesser than x is to its
    // left
    // and all greater than x is to its right

    // 1: first foward we can create two list and then add elements to it based on x
    // value
    // then finally merge it

    // Unlike the example shown at the top, our solution will be a stable one

    public ListNode partition(ListNode A, int B) {

        ListNode first = new ListNode(0);
        ListNode firstCurrent = first;
        ListNode second = new ListNode(0);
        ListNode secondCurrent = second;

        while (A != null) {
            if (A.val < B) {
                firstCurrent.next = new ListNode(A.val);
                A = A.next;
                firstCurrent = firstCurrent.next;
            } else {
                secondCurrent.next = new ListNode(A.val);
                A = A.next;
                secondCurrent = secondCurrent.next;
            }

        }

        ListNode ans = first.next;
        if (ans == null) {
            ans = second.next;
        } else {
            firstCurrent.next = second.next;
        }

        return ans;

    }
}