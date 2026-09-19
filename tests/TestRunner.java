public class TestRunner {

    public static void main(String[] args) throws Exception {
        System.out.println("=== SortingTest ===");
        runSafely(SortingTest.class);

        System.out.println();
        System.out.println("=== SelectTest ===");
        runSafely(SelectTest.class);

        System.out.println();
        System.out.println("=== ClosestPairTest ===");
        runSafely(ClosestPairTest.class);
    }

    private static void runSafely(Class<?> clazz) {
        try {
            clazz.getMethod("main", String[].class).invoke(null, (Object) new String[]{});
        } catch (Exception e) {
            System.out.println(clazz.getSimpleName() + " threw an exception:");
            e.printStackTrace();
        }
    }
}
