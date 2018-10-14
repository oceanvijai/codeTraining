public class MultiStack {
    /**
     * SetOfStacks. push () and SetOfStacks. pop() should behave identically to a
     * single stack (that is, pop ( ) should return the same values as it would if
     * there were just a single stack).
     * 
     * FOLLOW UP Implement a function popAt (int index) which performs a pop
     * operation on a specific substack.
     */

    /**
     * This is like if the limit of the one stack reached we add another stack and
     * start adding to it
     * 
     */

    // Use an array list of stack
    ArrayList<Stack> stacks = new ArrayList<Stack>();

    // ....

    // Follow up: Implement popAt(int index)
    // This is tricky because if we remove from stack 1,
    // we need to shift elements accross the other ones

    // supose the stack implementation has a pointer to both the top and and the
    // bottom
    // we can do the following

    // get the stack we need to pop
    // remove the top item
    // call the same method, this time asking it to remove from bottom of the upper
    // stack and return it.
    // then add it to the top of the current stack
    // This step need to recursive

    public int popAt(int index) {
        return leftShift(index, true); // true only in this case
    }

    public int leftShift(int index, boolean removeTop) {
        Stack stack = stacks.get(index);
        int returnItem;
        if (removeTop) { // true only in the first case
            returnItem = stack.pop();
        } else {
            returnItem = stack.removeFromBottom();
            if (stack.isEmpty()) {
                // remove it from the list
            } else {
                // get the bottom of the upper stack
                int val = leftShift(index + 1, false);
                stack.push(val);
            }
        }
        return returnItem;
    }

}