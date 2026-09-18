import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Assignment 1: Divide-and-Conquer Algorithm Analysis");
        System.out.println("Student: Sain Yermek, group SE-2503");
        System.out.println();

        demoMergeSort();
        demoQuickSort();
        demoSelect();
        demoClosestPair();

        System.out.println();
        System.out.println("Running full experiment suite, this can take a while for large n...");
        Experiment experiment = new Experiment();
        experiment.runAll("results/results.csv");
    }

    private static void demoMergeSort() {
        int[] arr = {9, 3, 7, 1, 5, 2, 8, 4, 6, 0};
        System.out.println("MergeSort input:  " + Arrays.toString(arr));
        new MergeSorter().sort(arr);
        System.out.println("MergeSort output: " + Arrays.toString(arr));
        System.out.println();
    }

    private static void demoQuickSort() {
        int[] arr = {9, 3, 7, 1, 5, 2, 8, 4, 6, 0};
        System.out.println("QuickSort input:  " + Arrays.toString(arr));
        new QuickSorter().sort(arr);
        System.out.println("QuickSort output: " + Arrays.toString(arr));
        System.out.println();
    }

    private static void demoSelect() {
        int[] arr = {9, 3, 7, 1, 5, 2, 8, 4, 6, 0};
        int k = arr.length / 2;
        System.out.println("DeterministicSelect input: " + Arrays.toString(arr) + " k=" + k);
        int result = new DeterministicSelector().select(arr, k);
        System.out.println("Value at rank k=" + k + " is " + result);
        System.out.println();
    }

    private static void demoClosestPair() {
        Point[] points = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(1, 1),
                new Point(50, 50),
                new Point(1, 2)
        };
        double result = new ClosestPairSolver().closestPair(points);
        System.out.println("ClosestPair distance for demo set: " + result);
        System.out.println();
    }
}
