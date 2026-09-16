import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

public class ClosestPairSolver {

    public static int maxDepth = 0;
    public static long distanceChecks = 0;

    public double closestPair(Point[] points) {
        maxDepth = 0;
        distanceChecks = 0;

        Point[] byX = points.clone();
        Arrays.sort(byX, Comparator.comparingDouble(p -> p.x));
        Point[] byY = byX.clone();
        Arrays.sort(byY, Comparator.comparingDouble(p -> p.y));

        return solve(byX, byY, 0, points.length - 1, 0);
    }

    private double solve(Point[] byX, Point[] byY, int lo, int hi, int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }

        int n = hi - lo + 1;
        if (n <= 3) {
            return bruteForce(byX, lo, hi);
        }

        int mid = lo + (hi - lo) / 2;
        Point midPoint = byX[mid];

        Set<Point> leftSet = Collections.newSetFromMap(new IdentityHashMap<>());
        for (int i = lo; i <= mid; i++) {
            leftSet.add(byX[i]);
        }

        Point[] leftY = new Point[mid - lo + 1];
        Point[] rightY = new Point[hi - mid];
        int li = 0;
        int ri = 0;
        for (Point p : byY) {
            if (leftSet.contains(p)) {
                if (li < leftY.length) {
                    leftY[li++] = p;
                }
            } else {
                if (ri < rightY.length) {
                    rightY[ri++] = p;
                }
            }
        }

        double dl = solve(byX, leftY, lo, mid, depth + 1);
        double dr = solve(byX, rightY, mid + 1, hi, depth + 1);
        double d = Math.min(dl, dr);

        Point[] strip = new Point[n];
        int si = 0;
        for (Point p : byY) {
            if (Math.abs(p.x - midPoint.x) < d) {
                strip[si++] = p;
            }
        }

        for (int i = 0; i < si; i++) {
            for (int j = i + 1; j < si && (strip[j].y - strip[i].y) < d; j++) {
                distanceChecks++;
                double dist = strip[i].distanceTo(strip[j]);
                if (dist < d) {
                    d = dist;
                }
            }
        }

        return d;
    }

    private double bruteForce(Point[] points, int lo, int hi) {
        double min = Double.MAX_VALUE;
        for (int i = lo; i <= hi; i++) {
            for (int j = i + 1; j <= hi; j++) {
                distanceChecks++;
                double dist = points[i].distanceTo(points[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }

    public double bruteForceFull(Point[] points) {
        distanceChecks = 0;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                distanceChecks++;
                double dist = points[i].distanceTo(points[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }
}
