public class Stack<T> {
    private static class StackNode<T> {
        private StackNode<T> next;
        private T data;

        StackNode(T data) {
            this.data = data;
        }
    }

    private StackNode<T> head;

    public T pop() {
        if (head == null) {
            throw new Exception();
        }

        T i = head;
        head = head.next;
        return i;

    }

    public void push(T data) {
        StackNode<T> t = new StackNode(data);
        t.next = head;
        head = t;
    }

    public T peek() {
        if (head == null) {
            // throw exception
        }
        return head.data;
    }

    // is Empty
    // size etc etc
}