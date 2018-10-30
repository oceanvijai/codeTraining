public class ContinuousMedian {
    /**
     * Numbers are randomly generated and passed to a method.
     * 
     * Write a program to find and maintain the median value as new values are
     * generated.
     */

    /**
     * Take two heaps, one max heap and another min heap
     * 
     * Put the elements in the heap and balance it
     * 
     * When an element comes, If the element is smaller than the current median, we
     * put it to the max heap Otherwise to the min heap
     * 
     * when the size becomes unequal, pick the head and shift it to the other heap
     */

    Comparator<Integer> maxHeapComparator, minHeapComparator;
    PriorityQueue<Integer> maxHeap, minHeap;

    void addNewNumber(int randomNumber) {
        /*
         * Note: addNewNumber maintains a condition that maxHeap.size() >=
         * minHeap.size()
         */
        if (maxHeap.size() == minHeap.size()) {
            if ((minHeap.peek() != null) && randomNumber > minHeap.peek()) {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(randomNumber);
            } else {
                maxHeap.offer(randomNumber);
            }
        } else {
            if (randomNumber < maxHeap.peek()) {
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(randomNumber);
            } else {
                minHeap.offer(randomNumber);
            }
        }
    }

    double getMedian() {
        /*
         * maxHeap is always at least as big as minHeap. So if maxHeap is empty, then
         * minHeap is also.
         */
        if (maxHeap.isEmpty()) {
            return 0;
        }
        if (maxHeap.size() == minHeap.size()) {
            return ((double) minHeap.peek() + (double) maxHeap.peek()) / 2;
        } else {
            /*
             * If maxHeap and minHeap are of different sizes, then maxHeap must have one
             * extra element. Return maxHeap's top element.
             */
            return maxHeap.peek();
        }
    }

}