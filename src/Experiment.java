import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Experiment {

    private static final Random RANDOM = new Random(42);
    private static final int[] SIZES = {1000, 10000, 100000};
    private static final String[] TYPES = {"random", "sorted", "reverse", "duplicates"};

    public void runAll(String csvPath) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(csvPath));
        writer.println("algorithm,inputType,n,timeNanos,maxDepth,comparisons,extraMetric");

        for (int n : SIZES) {
            for (String type : TYPES) {
                runMergeSort(writer, type, n);
                runQuickSort(writer, type, n);
            }
        }

        for (int n : SIZES) {
            runSelect(writer, n);
        }

        int[] closestPairSizes = {200, 2000, 20000, 100000};
        for (int n : closestPairSizes) {
            runClosestPair(writer, n);
        }

        writer.close();
        System.out.println("Results written to " + csvPath);
    }

    private void runMergeSort(PrintWriter writer, String type, int n) {
        int[] arr = generateArray(type, n);
        MergeSorter sorter = new MergeSorter();

        long start = System.nanoTime();
        sorter.sort(arr);
        long end = System.nanoTime();

        checkSorted(arr);
        writer.println("MergeSort," + type + "," + n + "," + (end - start) + ","
                + MergeSorter.maxDepth + "," + MergeSorter.comparisons + ",0");
    }

    private void runQuickSort(PrintWriter writer, String type, int n) {
        int[] arr = generateArray(type, n);
        QuickSorter sorter = new QuickSorter();

        long start = System.nanoTime();
        sorter.sort(arr);
        long end = System.nanoTime();

        checkSorted(arr);
        writer.println("QuickSort," + type + "," + n + "," + (end - start) + ","
                + QuickSorter.maxDepth + "," + QuickSorter.comparisons + "," + QuickSorter.swaps);
    }

    private void runSelect(PrintWriter writer, int n) {
        int[] arr = generateArray("random", n);
        int k = n / 2;
        DeterministicSelector selector = new DeterministicSelector();

        long start = System.nanoTime();
        selector.select(arr, k);
        long end = System.nanoTime();

        writer.println("DeterministicSelect,random," + n + "," + (end - start) + ","
                + DeterministicSelector.maxDepth + "," + DeterministicSelector.comparisons + ",0");
    }

    private void runClosestPair(PrintWriter writer, int n) {
        Point[] points = generatePoints(n);
        ClosestPairSolver solver = new ClosestPairSolver();

        long start = System.nanoTime();
        solver.closestPair(points);
        long end = System.nanoTime();

        writer.println("ClosestPair,random," + n + "," + (end - start) + ","
                + ClosestPairSolver.maxDepth + ",0," + ClosestPairSolver.distanceChecks);
    }

    private int[] generateArray(String type, int n) {
        int[] arr = new int[n];
        switch (type) {
            case "random":
                for (int i = 0; i < n; i++) {
                    arr[i] = RANDOM.nextInt(1000000);
                }
                break;
            case "sorted":
                for (int i = 0; i < n; i++) {
                    arr[i] = i;
                }
                break;
            case "reverse":
                for (int i = 0; i < n; i++) {
                    arr[i] = n - i;
                }
                break;
            case "duplicates":
                for (int i = 0; i < n; i++) {
                    arr[i] = RANDOM.nextInt(10);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
        return arr;
    }

    private Point[] generatePoints(int n) {
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(RANDOM.nextDouble() * 100000, RANDOM.nextDouble() * 100000);
        }
        return points;
    }

    private void checkSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                throw new IllegalStateException("Array is not sorted at index " + i);
            }
        }
    }
}
