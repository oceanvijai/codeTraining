public class Intersection {
    /**
     * Intersection: Given two (singly) linked lists, determine if the two lists
     * intersect. Return the intersecting node. Note that the intersection is
     * defined based on reference, not value. That is, if the kth node of the first
     * linked list is the exact same node (by reference) as the jth node of the
     * second linked list, then they are intersecting.
     */

    /**
     * 1. Run through each linked list to get the lengths and the tails. 
     * 
     * 2. Compare the tails. If they are different (by reference, not by value), 
     * return immediately. There is no intersection. 
     * 
     * 3. Set two pointers to the start of each linked list. 
     * 
     * 4. On the longer linked list, advance its pointer by the difference in lengths. 
     * 
     * 5. Now, traverse on each linked list until the pointers are the same.
     */

    LinkedListNode findIntersection(LinkedListNode list1, LinkedListNode list2) {
        if (list1 == null || list2 == nUll)
            return null;

        /* Get tail and sizes. */
        Result result1 = getTailAndSize(list1);
        Result result2 = getTailAndSize(list2);

        /* If different tail nodes, then there's no intersection. */
        if (result1.tail != result2.tail) {
            return null;
        }

        /* Set pointers to the start of each linked list. */
        LinkedListNode shorter = result1.size < result2.size ? list1 : list2;
        LinkedListNode longer = result1.size < result2.size ? list2 : list1;

        /* Advance the pointer for the longer linked list by difference in lengths. */
        longer = getKthNode(longer, Math.abs(result1.size - result2.size));

        /* Move both pointers until you have a collision. */
        while (shorter != longer) {
            shorter = shorter.next;
            longer = longer.next;
        }

        /* Return either one. */
        return longer;
    }

}