public class AddNumbers {

    /**
     * You are given two non-empty linked lists representing two non-negative
     * integers. The most significant digit comes first and each of their nodes
     * contain a single digit. Add the two numbers and return it as a linked list.
     * 
     * 
     * You may assume the two numbers do not contain any leading zero, except the
     * number 0 itself.
     * 
     * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4) 
     * 
     * Output: 7 -> 8 -> 0 -> 7
     */

    /**
     * Approach: Follow the steps
     * 
     * If the linked list are off the same size, do recursion, add them and return the carry
     * If they are of different size, then find the kth node where they are equal and do the same
     * 
     * then take the carry and again do recursion to add up the rest of the digits
     * 
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int l1Size = getSize(l1);
        int l2Size = getSize(l2);

        if (l1Size == l2Size) {
            int carry = getSumOfEqualsize(l1, l2);
            if (carry == 1) {
                ListNode node = new ListNode(1);
                node.next = ans;
                ans = node;
            }
        } else {
            ListNode longest = l1Size > l2Size ? l1 : l2;
            ListNode smallest = l1Size > l2Size ? l2 : l1;

            int k = Math.abs(l1Size - l2Size);
            ListNode KthNode = getKthNode(k, longest);
            int carry = getSumOfEqualsize(KthNode, smallest);
            carry = getSumWithCarryTill(longest, KthNode, carry);

            if (carry == 1) {
                ListNode n = new ListNode(1);
                n.next = ans;
                ans = n;
            }

        }

        return ans;
    }

    private int getSumWithCarryTill(ListNode head, ListNode kthNode, int carry) {
        if (head != kthNode) {
            int c = getSumWithCarryTill(head.next, kthNode, carry);
            int val = c + head.val;
            ListNode node = new ListNode(val % 10);
            node.next = ans;
            ans = node;
            return val / 10;
        } else {
            return carry;
        }
    }

    private int getSumOfEqualsize(ListNode l1, ListNode l2) {
        if (l1 != null) {
            // System.out.println("entring:" + l1.val + "," +l2.val );
            int carry = getSumOfEqualsize(l1.next, l2.next);
            int val = l1.val + l2.val + carry;
            // System.out.println("adding:" + l1.val + "," +l2.val );
            ListNode node = new ListNode(val % 10);
            node.next = ans;
            ans = node;
            return val / 10;
        } else {
            // System.out.println("end reached:" );
            return 0;
        }
    }

    private ListNode getKthNode(int k, ListNode longest) {
        ListNode c = longest;
        while (k != 0) {
            k--;
            c = c.next;
        }

        return c;
    }

    private int getSize(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;

    }

}