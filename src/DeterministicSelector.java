public class DeterministicSelector {

    public static int comparisons = 0;
    public static int maxDepth = 0;

    public int select(int[] arr, int k) {
        if (arr.length == 0 || k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Invalid array or k");
        }
        comparisons = 0;
        maxDepth = 0;
        return select(arr, 0, arr.length - 1, k, 0);
    }

    private int select(int[] arr, int lo, int hi, int k, int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }

        if (lo == hi) {
            return arr[lo];
        }

        int pivotValue = medianOfMedians(arr, lo, hi, depth);
        int p = partition(arr, lo, hi, pivotValue);

        if (k == p) {
            return arr[p];
        } else if (k < p) {
            return select(arr, lo, p - 1, k, depth + 1);
        } else {
            return select(arr, p + 1, hi, k, depth + 1);
        }
    }

    private int medianOfMedians(int[] arr, int lo, int hi, int depth) {
        int n = hi - lo + 1;

        if (n <= 5) {
            insertionSort(arr, lo, hi);
            return arr[lo + n / 2];
        }

        int numGroups = (n + 4) / 5;
        for (int i = 0; i < numGroups; i++) {
            int groupLo = lo + i * 5;
            int groupHi = Math.min(groupLo + 4, hi);
            insertionSort(arr, groupLo, groupHi);
            int medianIndex = groupLo + (groupHi - groupLo) / 2;
            swap(arr, lo + i, medianIndex);
        }

        return select(arr, lo, lo + numGroups - 1, lo + numGroups / 2, depth + 1);
    }

    private int partition(int[] arr, int lo, int hi, int pivotValue) {
        int pivotIndex = lo;
        for (int i = lo; i <= hi; i++) {
            if (arr[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }
        swap(arr, pivotIndex, hi);

        int i = lo;
        for (int j = lo; j < hi; j++) {
            comparisons++;
            if (arr[j] < pivotValue) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, hi);
        return i;
    }

    private void insertionSort(int[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= lo && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
