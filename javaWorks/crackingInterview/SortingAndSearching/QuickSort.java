public class QuickSort {

    private void QuickSort(int array[], int start, int end) {
        int pivot = partition(array, start, end);
        QuickSort(array, start, pivot);
        QuickSort(array, pivot + 1, end);
    }

    private int partition(int[] array, int start, int end) {
        int pivotElement = array[end];
        int pivotIndex = start;
        int x = start;
        while (x <= end) {
            if (array[pivotIndex] < pivotElement) {
                pivotIndex++;
                swap(array, x, pivotElement);
            }
            x++;
        }
        swap(array, pivotIndex, end);
        return pivotIndex;

    }

    private void swap(int[] array, int x, int y) {
        int t = array[x];
        array[x] = array[y];
        array[y] = t;
    }
}