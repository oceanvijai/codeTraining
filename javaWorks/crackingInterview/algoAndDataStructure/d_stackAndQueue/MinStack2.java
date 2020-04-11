public class MinStack2 {

    // Min stack with O(n) and O(1)

    LinkedList<Long> stack;
    long min = -1L;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.addFirst(0L); // so when we top the last, we can add it to the min
            min = x;
        } else {
            long val = x - min; // this will be -ve if a new min comes
            stack.addFirst(val);
            if (x < min)
                min = x;
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        long val = stack.pollFirst();
        System.out.println("poped: " + val);
        // Now decide what will be the min
        if (stack.isEmpty()) {
            min = -1L;
        } else if (val < 0) {
            // we need to find the previous min
            // At this position, val = x - min; where min = previous min and x = current min
            // so, val = curMin - prevMin => prevMin = curMin - val
            min = min - val;
        }
        System.out.println("now Min: " + min);
    }

    public int top() {
        if (stack.isEmpty()) {
            return -1;
        }

        long val = stack.peekFirst();
        if (val < 0) { // where min appeared
            return (int) min;
        } else {
            long x = val + min; // previously, val = x - min, so now
            return (int) x; // min is at the top
        }
    }

    public int getMin() {
        return (int) min;
    }
}