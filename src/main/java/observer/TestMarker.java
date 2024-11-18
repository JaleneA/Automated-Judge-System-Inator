package observer;

/**
 * This interface defines methods for marking individual tests as passed or
 * failed, calculating the number of passed and failed tests, displaying the
 * results, and resetting the test marking state. Any class that implements this
 * interface must provide the functionality to handle these operations, enabling
 * flexible grading mechanisms.
 *
 * @author jalenearmstrong
 * @see <a href="https://refactoring.guru/design-patterns/observer">Observer
 * Design Pattern</a>
 * @see TestGradingObserver
 */
public interface TestMarker {

    // -- ABSTRACT METHODS --
    /**
     * Calculates the total number of tests that passed.
     *
     * @return the number of tests that passed.
     */
    int calculatePassedTests();

    /**
     * Calculates the total number of tests that failed.
     *
     * @return the number of tests that failed.
     */
    int calculateFailedTests();

    /**
     * Displays the summary of the test results, including the number of passed
     * and failed tests.
     */
    void displayResults();

    /**
     * Marks a single test as either passed or failed.
     *
     * @param testResult {@code true} if the test passed, {@code false} if the
     * test failed.
     */
    void markTest(boolean testResult);

    /**
     * Resets the test marking state, clearing the number of passed and failed
     * tests. This method prepares the marker for a fresh grading session.
     */
    void reset();
}
