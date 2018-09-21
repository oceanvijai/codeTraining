public class QueueByStack {
    // 1: Use stack 1 to push when a push is made
    // When a peek or pop is made, push all the elements to the Stack 2 and peek
    // from the stack 2
    // then push it back to S1, so new elemets are maintained in order in S1

    // This is fine, but the peek and pop is cotly every time

    // 2: So we can do one thing, let stack s1 always gets the new values
    // When a peek / pop is called, we push it to the S2 if S2 is empty
    // otherwisw we week from S2
    // When new element is added, they will be maintained in s1

    Stack<T> stackNewest, stackOldest;

    public QueueByStack() {
        stackNewest = new Stack<T>();
        stackOldest = new Stack<T>();
    }

    public int size() {
        return stackNewest.size() + stackOldest.size();
    }

    public void add(T value) {
        /* Push onto stackNewest, which always has the newest elements on top */
        stackNewest.push(value);
    }

    /*
     * Move elements from stackNewest into stackOldest. This is usually done so that
     * we can do operations on stackOldest.
     */

    private void shiftStacks() {
        if (stackOldest.isEmpty()) {
            while (!stackNewest.isEmpty()) {
                stackOldest.push(stackNewest.pop());
            }
        }
    }

    public T peek() {
        shiftStacks(); // Ensure stackOldest has the current elements
        return stackOldest.peek(); // retrieve the oldest item .
    }

    public T remove() {
        shiftStacks(); // Ensure stackOldest has the current elements
        return stackOldest.pop(); // pop the oldest item.
    }

}