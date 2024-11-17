package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the {@link TestMarker} interface for marking test
 * results and notifying listeners.
 * <p>
 * This class tracks the number of passed and failed tests and provides methods
 * for calculating the test results, displaying the results, and resetting the
 * state. Additionally, it notifies registered listeners each time a test is
 * marked, allowing for the implementation of observer-based behavior.
 * <p>
 * This class can be used in grading systems that require test result tracking
 * and the notification of observers when a test is marked.
 *
 * @author jalenearmstrong
 * @see TestMarker
 * @see TestMarkingListener
 * @see <a href="https://refactoring.guru/design-patterns/observer">Observer
 * Design Pattern</a>
 */
public class TestMarking implements TestMarker {

    /**
     * The number of tests that passed.
     */
    private int passedTests = 0;

    /**
     * The number of tests that failed.
     */
    private int failedTests = 0;

    /**
     * A list of listeners that will be notified when a test is marked.
     */
    private List<TestMarkingListener> listeners = new ArrayList<>();

    /**
     * Adds a listener to be notified when a test is marked.
     *
     * @param listener the listener to be added.
     */
    public void addListener(TestMarkingListener listener) {
        listeners.add(listener);
    }

    /**
     * Marks a single test as passed or failed.
     *
     * @param testResult {@code true} if the test passed, {@code false} if the
     * test failed.
     */
    @Override
    public void markTest(boolean testResult) {
        if (testResult) {
            passedTests++;
        } else {
            failedTests++;
        }
        notifyListeners(testResult);
    }

    /**
     * Notifies all listeners that a test has been marked with the result.
     *
     * @param testResult the result of the test being marked.
     */
    private void notifyListeners(boolean testResult) {
        for (TestMarkingListener listener : listeners) {
            listener.onTestMarked(testResult);
        }
    }

    /**
     * Calculates the total number of tests that passed.
     *
     * @return the number of passed tests.
     */
    @Override
    public int calculatePassedTests() {
        return passedTests;
    }

    /**
     * Calculates the total number of tests that failed.
     *
     * @return the number of failed tests.
     */
    @Override
    public int calculateFailedTests() {
        return failedTests;
    }

    /**
     * Displays the summary of the test results, including the number of passed
     * and failed tests.
     */
    @Override
    public void displayResults() {
        System.out.println("Passed Tests: " + calculatePassedTests());
        System.out.println("Failed Tests: " + calculateFailedTests());
    }

    /**
     * Resets the test marking state, clearing the number of passed and failed
     * tests and removing all listeners.
     */
    @Override
    public void reset() {
        this.passedTests = 0;
        this.failedTests = 0;
        this.listeners.clear();
    }

    /**
     * Gets the list of listeners that are notified when a test is marked.
     *
     * @return the list of {@link TestMarkingListener} objects.
     */
    public List<TestMarkingListener> getListeners() {
        return listeners;
    }

    /**
     * Sets the list of listeners to be notified when a test is marked.
     *
     * @param listeners the list of {@link TestMarkingListener} objects to be
     * set.
     */
    public void setListeners(List<TestMarkingListener> listeners) {
        this.listeners = listeners;
    }
}
