package observer;

/**
 * A singleton class that manages the shared instance of
 * {@link TestGradingObserver}.
 * <p>
 * This class provides access to a single {@link TestGradingObserver} instance
 * that can be used to store and manage test results for multiple grading
 * operations.
 *
 * @author jalenearmstrong
 * @see TestGradingObserver
 */
public class TestGradingManager {

    /**
     * The single shared instance of {@link TestGradingObserver}. This observer
     * is used to store and manage test results.
     */
    private static TestGradingObserver gradingObserver = new TestGradingObserver();

    /**
     * Returns the singleton instance of {@link TestGradingObserver}.
     *
     * @return the shared {@link TestGradingObserver} instance.
     */
    public static TestGradingObserver getGradingObserver() {
        return gradingObserver;
    }

    /**
     * Resets the singleton instance of {@link TestGradingObserver} by creating
     * a new instance. This is useful for clearing any stored results and
     * starting fresh.
     */
    public static void resetGradingObserver() {
        gradingObserver = new TestGradingObserver();
    }
}
