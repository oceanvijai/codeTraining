public class FindMedianInDataStream {
    PriorityQueue<Integer> maxHeap; // for the first half
    PriorityQueue<Integer> minHeap; // for the second half

    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<Integer>((a, b) -> (b - a));
        minHeap = new PriorityQueue<Integer>();
    }

    public void addNum(int num) {
        if (maxHeap.size() == minHeap.size()) {
            if (minHeap.peek() != null && minHeap.peek() < num) {
                int valFromMinHeap = minHeap.poll();
                minHeap.offer(num);
                maxHeap.offer(valFromMinHeap);
            } else {
                maxHeap.offer(num);
            }
        } else {
            if (num < maxHeap.peek()) {
                int valFromMaxHeap = maxHeap.poll();
                maxHeap.offer(num);
                minHeap.offer(valFromMaxHeap);
            } else {
                minHeap.offer(num);
            }
        }
    }

    public double findMedian() {
        if (maxHeap.size() == 0) {
            return 0;
        } else if (minHeap.size() == maxHeap.size()) {
            return ((double) minHeap.peek() + (double) maxHeap.peek()) / 2;
        } else {
            return maxHeap.peek();
        }
    }
}