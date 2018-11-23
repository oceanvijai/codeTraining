public class PhoneDirectory {

    /**
     * Design a Phone Directory which supports the following operations:
     * 
     * get: Provide a number which is not assigned to anyone. check: Check if a
     * number is available or not. release: Recycle or release a number.
     */

    LinkedList<Integer> freePool;
    Set<Integer> reservedPoll;
    int maxNumbers;
    int currentIndex;

    /**
     * Initialize your data structure here
     * 
     * @param maxNumbers - The maximum numbers that can be stored in the phone
     *                   directory.
     */
    public PhoneDirectory(int maxNumbers) {
        this.maxNumbers = maxNumbers;
        this.freePool = new LinkedList<>();
        this.reservedPoll = new HashSet<>();
        this.currentIndex = 0;
    }

    /**
     * Provide a number which is not assigned to anyone.
     * 
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        if (!freePool.isEmpty()) {
            Integer val = freePool.pollFirst();
            reservedPoll.add(val);
            return val;
        } else if (currentIndex < maxNumbers) {
            int val = new Integer(currentIndex);
            currentIndex++;
            reservedPoll.add(val);
            return val;
        } else {
            return -1;
        }
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        if (number > maxNumbers || reservedPoll.contains(new Integer(number))) {
            return false;
        }

        return true;
    }

    /** Recycle or release a number. */
    public void release(int number) {
        int val = new Integer(number);
        if (reservedPoll.contains(val)) {
            reservedPoll.remove(val);
            freePool.addLast(val);
        }
    }
}