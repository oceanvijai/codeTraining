public class MergeSort {

    public void mergeSort(int[] array, int start, int end) {
        int mid = start + end / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, end);
        merge(array, start, mid, end);
    }

    public void merge(int[] array, int start, int mid, int end) {
        int[] left = new int[mid - start];
        for (int i = start; i <= mid; i++) {
            left = array[i];
        }

        int[] right = new int[end - mid];
        for (int i = mid; i <= end; i++) {
            right = array[i];
        }

        int i = start;
        int j = mid + 1;
        int k = start;
        while (i <= mid && j <= end) {
            if (left[i] <= right[j]) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
            k++;
        }
    }

}