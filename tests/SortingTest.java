import java.util.Arrays;
import java.util.Random;

public class SortingTest {

    private static int passed = 0;
    private static int failed = 0;
    private static final Random RANDOM = new Random(123);

    public static void main(String[] args) {
        testCase("empty", new int[]{});
        testCase("singleElement", new int[]{5});
        testCase("sorted", makeSorted(1000));
        testCase("reverseSorted", makeReverse(1000));
        testCase("duplicates", makeDuplicates(1000));
        testCase("random", makeRandom(1000));
        testCase("randomLarge", makeRandom(50000));

        System.out.println();
        System.out.println("SortingTest: " + passed + " passed, " + failed + " failed");
    }

    private static void testCase(String name, int[] original) {
        int[] forMerge = original.clone();
        int[] forQuick = original.clone();
        int[] expected = original.clone();
        Arrays.sort(expected);

        new MergeSorter().sort(forMerge);
        check(name + " (MergeSort)", expected, forMerge);

        new QuickSorter().sort(forQuick);
        check(name + " (QuickSort)", expected, forQuick);
    }

    private static void check(String label, int[] expected, int[] actual) {
        boolean ok = Arrays.equals(expected, actual);
        if (ok) {
            passed++;
            System.out.println("PASS  " + label);
        } else {
            failed++;
            System.out.println("FAIL  " + label);
        }
    }

    private static int[] makeSorted(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private static int[] makeReverse(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = n - i;
        }
        return arr;
    }

    private static int[] makeDuplicates(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = RANDOM.nextInt(5);
        }
        return arr;
    }

    private static int[] makeRandom(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = RANDOM.nextInt(1000000);
        }
        return arr;
    }
}
