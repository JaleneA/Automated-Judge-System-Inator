package observer;

/**
 * A listener interface for receiving notifications when a test has been marked.
 * <p>
 * Classes that implement this interface are notified whenever a test is marked
 * as either passed or failed.
 *
 * @author jalenearmstrong
 * @see <a href="https://refactoring.guru/design-patterns/observer">Observer
 * Design Pattern</a>
 * @see TestMarking
 */
public interface TestMarkingListener {

    // -- ABSTRACT METHODS --
    /**
     * Invoked when a test has been marked as either passed or failed.
     *
     * @param testResult {@code true} if the test passed, {@code false} if the
     * test failed.
     */
    void onTestMarked(boolean testResult);
}
