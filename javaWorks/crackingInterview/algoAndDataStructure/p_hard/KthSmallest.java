public class KthSmallest {

    /**
     * Design an algorithm to find the smallest K numbers in an array.
     */

    /**
     * Approach 1: sort the array and find the kth index element
     * 
     * O(nlogn), not interested
     */

    /**
     * Approach 2: Push it into a max heap of size k and remove or add based next
     * elements in the arry.
     * 
     * Finally return the head
     * 
     * O(n logk) good, but can do better
     */

    // Max heap solution

    int[] smallestK(int[] array, int k) {
        if (k <= 0 || k > array.length) {
            throw new IllegalArgumentException();
        }

        PriorityQueue<Integer> heap = getKMaxHeap(array, k);
        return heapTolntArray(heap);
    }

    /* Create max heap of smallest k elements. */
    PriorityQueue<Integer> getKMaxHeap(int[] array, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new MaxHeapComparator());

        for (int a : array) {
            if (heap.size() < k) { // If space remaining
                heap.add(a);
            } else if (a < heap.peek()) { // If full and top is small
                heap.poll(); // remove highest
                heap.add(a); // insert new element
            }
        }
        return heap;
    }

    // Selection rank

    /**
     * Approach 3: Selection rank, use a variation of quick sort
     * 
     * have a pivot push all elements to the right of the pivot if less then move
     * left or right of the until the pivot is the in the position of k meaning, all
     * other elements less than k are to its left
     * 
     * O(n) since, we work on only one partition at a times
     * 
     * 
     * 1. Pick a random element in the array and use it as a "pivot:' Partition
     * elements around the pivot, keeping track of the number of elements on the
     * left side of the partition.
     * 
     * 2. If there are exactly i elements on the left, then you just return the
     * biggest element on the left.
     * 
     * 3. If the left side is bigger than i, repeat the algorithm on just the left
     * part of the array.
     * 
     * 4. If the left side is smaller than i, repeat the algorithm on the right, but
     * look for the element with rank i - leftSize.
     */

    int[] smallestK_selectionRank(int[] array, int k) {
        if (k <= 0 || k > array.length) {
            throw new IllegalArgumentException();

            int[] smallest = new int[k];
            int count = a;
            for (int a : array) {
                if (a <= threshold) {
                    smallest[count] = a;
                    count++;
                }
            }
            return smallest;
        }
    }

    /* Get element with rank. or k */
    int rank(int[] array, int rank) {
        return rank(array, 0, array.length - 1, rank);
    }

    /* Get element with rank between left and right indices. */
    int rank(int[] array, int left, int right, int rank) {
        int pivotIndex = randomlntlnRange(left, right);

        int partitionIndex = partition(array, left, right, pivotIndex);
        int leftArraySize = partitionIndex - left;

        if (rank == leftArraySize) {
            return max(array, left, right);
        } else if (leftArraySize > rank) {
            return rank(array, left, partitionIndex, rank);
        } else {
            return rank(array, partitionIndex + 1, right, rank);
        }
    }

    /* Get random integer within range J inclusive. */
    int randomlntlnRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max + 1 - min) + min;
    }

    private int partition(int[] array, int start, int end, int pivotIndex) {
        int pivotElement = array[pivotIndex];

        int i = start;
        int p = start;
        while (i <= end) {
            if (array[i] <= pivotElement) {
                swap(arr, p, i);
                p++;
            }
            i++;
        }
        swap(arr, p, pivotIndex);
        return p;
    }

    void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    int max(int[] array, int left, int right) {
        int max = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            max = Math.max(array[i], max);
        }
        return maX;
    }

}