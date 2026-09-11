public class MergeSorter {

    public static int comparisons = 0;
    public static int maxDepth = 0;

    private static final int CUTOFF = 16;

    private int[] buffer;

    public void sort(int[] arr) {
        comparisons = 0;
        maxDepth = 0;
        buffer = new int[arr.length];
        sort(arr, 0, arr.length - 1, 0);
    }

    private void sort(int[] arr, int lo, int hi, int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }

        if (hi - lo <= CUTOFF) {
            insertionSort(arr, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(arr, lo, mid, depth + 1);
        sort(arr, mid + 1, hi, depth + 1);
        merge(arr, lo, mid, hi);
    }

    private void merge(int[] arr, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            buffer[k] = arr[k];
        }

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = buffer[j++];
            } else if (j > hi) {
                arr[k] = buffer[i++];
            } else {
                comparisons++;
                if (buffer[i] <= buffer[j]) {
                    arr[k] = buffer[i++];
                } else {
                    arr[k] = buffer[j++];
                }
            }
        }
    }

    private void insertionSort(int[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= lo) {
                comparisons++;
                if (arr[j] <= key) {
                    break;
                }
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
