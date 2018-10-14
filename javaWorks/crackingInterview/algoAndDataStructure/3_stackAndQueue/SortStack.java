public class SortStack<T extends Comparable<T>> {
    /**
     * Write a program to sort a stack such that the smallest items are on the top.
     * You can use an additional temporary stack, but you may not copy the elements
     * into any other data structure (such as an array). The stack supports the
     * following operations: push, pop, peek, and isEmpty.
     */

    void sort(Stack<T> s) {
        Stack<T> r = new Stack<T>();
        while (!s.isEmpty()) { // first copy elements to r with smallest at the bottom
            T t = s.pop();
            while (!r.isEmpty() && t.compareTo(r.peek()) > 0) {
                s.push(r.pop());
            }
            r.push(t);

        }

        // Then copy things back to s now in sorted order
        while (!r.isEmpty()) {
            s.push(r.pop());
        }

    }
}