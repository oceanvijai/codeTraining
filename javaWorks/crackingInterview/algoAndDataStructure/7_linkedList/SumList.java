public class SumList {
    /**
     * You have two numbers represented by a linked list, where each node contains a
     * single digit. The digits are stored in reverse order, such that the 1 's
     * digit is at the head of the list. Write a function that adds the two numbers
     * and returns the sum as a linked list.
     */

    /**
     * EXAMPLE Input: (7-> 1 -> 6) + (5 -> 9 -> 2) .Thatis,617 + 295. Output: 2 - >
     * 1 - > 9. That is, 912.
     */

    /**
     * FOLLOW UP Suppose the digits are stored in forward order. Repeat the above
     * problem. Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).Thatis,617 + 295. Output: 9 - >
     * 1 - > 2. That is, 912.
     */

    // 1: iterative solution
    public ListNode addTwoNumbers(ListNode A, ListNode B) {

        int carry = 0;

        ListNode head = new ListNode(0);
        ListNode ans = head;

        while (A != null && B != null) {

            ans.next = new ListNode(0);
            ans = ans.next;

            int val = carry + A.val + B.val;
            if (val >= 10) {
                ans.val = val % 10;
                carry = 1;
            } else {
                ans.val = val;
                carry = 0;
            }

            A = A.next;
            B = B.next;

        }

        ListNode r = null;
        if (A != null || B != null) {
            r = A != null ? A : B;
        }

        while (r != null) {
            // System.out.println(r.val);
            ans.next = new ListNode(0);
            ans = ans.next;
            int val = carry + r.val;
            if (val >= 10) {
                ans.val = val % 10;
                carry = 1;
            } else {
                ans.val = val;
                carry = 0;
            }

            // System.out.println(r.next.val);
            r = r.next;
        }

        if (carry != 0) {
            ans.next = new ListNode(0);
            ans = ans.next;
            ans.val = carry;
        }

        return head.next;

    }

    // 2: recursive
    LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        LinkedListNode result = new LinkedListNode();
        int value = carry;
        if (l1 != nUll) {
            value += l1.data;
        }
        if (l2 != null) {
            value += l2.data;
        }

        result.data = value % 10; /* Second digit of number */
        /* Re curse */
        if (l1 != null || l2 != nUll) {
            LinkedListNode more = addLists(ll == null ? null : l1.next, l2 == null ? null : l2.next,
                    value >= 18 ? 1 : 0);
            result.setNext(more);
        }
        return result;
    }

    // Now for the follow up

    // 1: reverse the lists and do the same as above
    // time: O(n1+n2)

    // 2: padd zeros to the front and do a recursive addition
    // padding zero to the front make sure, we have the same posistion in both the nodes
}