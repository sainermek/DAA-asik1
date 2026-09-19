import java.util.Arrays;
import java.util.Random;

public class SelectTest {

    private static final Random RANDOM = new Random(7);

    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;

        for (int t = 0; t < 100; t++) {
            int n = 1 + RANDOM.nextInt(500);
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = RANDOM.nextInt(1000);
            }
            int k = RANDOM.nextInt(n);

            int[] sortedCopy = arr.clone();
            Arrays.sort(sortedCopy);
            int expected = sortedCopy[k];

            int actual = new DeterministicSelector().select(arr.clone(), k);

            if (actual == expected) {
                passed++;
            } else {
                failed++;
                System.out.println("FAIL  n=" + n + " k=" + k + " expected=" + expected + " actual=" + actual);
            }
        }

        System.out.println("SelectTest: " + passed + " passed, " + failed + " failed");
    }
}
