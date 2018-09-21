public class Palindrome {
    /**
     * Palindrome: Implement a function to check if a linked list is a palindrome.
     */

    // 1: Reverse and Compare

    boolean isPalindrome(LinkedListNode head) {
        LinkedListNode reversed = reverseAndClone(head);
        return isEqual(head, reversed);
    }

    LinkedListNode reverseAndClone(LinkedListNode node) {
        LinkedListNode head = null;
        while (node != nUll) {
            LinkedListNode n = new LinkedListNode(node.data); // Clone
            n.next = head;
            head= n;
            node = node.next;
        }
        return head;
    }

    boolean isEqual(LinkedListNode one, LinkedListNode two) {
        while (one != null && two != nUll) {
            if (one.data != two.data) {
                return false;
            }
            one = one.next;
            two = two.next;
        }
        return one == null && two == null;
    }

    // 2: stack approach
    // We push the first half of the list into a stack using fast and slow pointers
    // once we reach the middle, we compare the rest of the list with the items in
    // the stack

    boolean isPalindrome(LinkedListNode head) {
        LinkedListNode fast = head;
        LinkedListNode slow = head;

        Stack<Integer> stack = new Stack<Integer>();

        // Push elements from first half of linked list onto stack. When fast runner
        // (which is moving at 2x speed) reaches the end of the linked list, then we
        // know we're at the middle *1
        while (fast != null && fast.next != nUll) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        // Has odd number of elements, so skip the middle element *1
        if (fast != nUll) {
            slow = slow.next;
        }
        while (slow != null) {
            int top = stack.pop().intValue();

            // If values are different, then it's not a palindrome
            if (top != slow.data) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    // 3: Recursive Approach 
    //  this is similare to the iterative one with implicit stack and all

}