import java.util.Random;

public class ClosestPairTest {

    private static final Random RANDOM = new Random(99);

    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;

        int[] sizes = {2, 3, 5, 10, 50, 200, 1000, 2000};

        for (int n : sizes) {
            Point[] points = generatePoints(n);
            ClosestPairSolver solver = new ClosestPairSolver();

            double fast = solver.closestPair(points.clone());
            double brute = solver.bruteForceFull(points.clone());

            boolean ok = Math.abs(fast - brute) < 1e-9;
            if (ok) {
                passed++;
            } else {
                failed++;
                System.out.println("FAIL  n=" + n + " fast=" + fast + " brute=" + brute);
            }
        }

        System.out.println("ClosestPairTest: " + passed + " passed, " + failed + " failed");
    }

    private static Point[] generatePoints(int n) {
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(RANDOM.nextDouble() * 1000, RANDOM.nextDouble() * 1000);
        }
        return points;
    }
}
