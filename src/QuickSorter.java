import java.util.Random;

public class QuickSorter {

    public static int comparisons = 0;
    public static int swaps = 0;
    public static int maxDepth = 0;

    private static final Random RANDOM = new Random();

    public void sort(int[] arr) {
        comparisons = 0;
        swaps = 0;
        maxDepth = 0;
        sort(arr, 0, arr.length - 1, 0);
    }

    private void sort(int[] arr, int lo, int hi, int depth) {
        while (lo < hi) {
            if (depth > maxDepth) {
                maxDepth = depth;
            }

            int p = partition(arr, lo, hi);

            if (p - lo < hi - p) {
                sort(arr, lo, p - 1, depth + 1);
                lo = p + 1;
            } else {
                sort(arr, p + 1, hi, depth + 1);
                hi = p - 1;
            }
        }
    }

    private int partition(int[] arr, int lo, int hi) {
        int pivotIndex = lo + RANDOM.nextInt(hi - lo + 1);
        swap(arr, pivotIndex, hi);
        int pivot = arr[hi];

        int i = lo;
        for (int j = lo; j < hi; j++) {
            comparisons++;
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, hi);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        swaps++;
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
