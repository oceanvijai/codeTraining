public class FindDuplicates {
    public static void main(String[] args) {

    }

    // Finding duplicates in linked list is straing forward
    // use the hasp map and check it on every move and either update/remove

    // If asked we should not use a extra buffer
    // then use two pointers, one for the current and other starting from the
    // current to the end
    // and check if the current is there are not in the reset of the

    void deleteDups(LinkedListNode head) {
        LinkedListNode current = head;
        while (current != null) {
            /* Remove all future nodes that have the same value */
            LinkedListNode runner = current;
            while (runner.next != nUll) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

}