package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the {@link TestMarkingListener} interface and is
 * responsible for tracking the results of tests marked as passed or failed. It
 * maintains a list of test results and provides methods to display or reset the
 * results. The observer also keeps count of the total passed and failed tests.
 * <p>
 * This class is used to monitor the progress and outcome of tests during
 * grading and provide insights into the overall performance of a student's
 * submission.
 *
 * @author jalenearmstrong
 * @see TestMarkingListener
 * @see TestResult
 */
public class TestGradingObserver implements TestMarkingListener {

    // -- INSTANCE VARIABLES --
    /**
     * A list of {@link TestResult} objects, each representing the result of a
     * specific test.
     */
    private List<TestResult> studentTestResults = new ArrayList<>();

    /**
     * The total number of tests that passed.
     */
    private int totalPassedTests = 0;

    /**
     * The total number of tests that failed.
     */
    private int totalFailedTests = 0;

    // -- BUSINESS LOGIC METHODS --
    /**
     * Displays the summary of test results, including the number of passed and
     * failed tests.
     */
    public void displayTestResults() {
        System.out.println("Displaying test results...");
        System.out.println("Total Passed Tests: " + totalPassedTests);
        System.out.println("Total Failed Tests: " + totalFailedTests);
        System.out.println("Test Results List Size: " + studentTestResults.size());
    }

    /**
     * Resets the observer by clearing the test results and resetting the
     * counters.
     */
    public void reset() {
        totalPassedTests = 0;
        totalFailedTests = 0;
        studentTestResults.clear();
    }

    /**
     * Stores the result of a specific test, including its class name and the
     * result.
     *
     * @param testClassName the name of the test class.
     * @param testResult {@code true} if the test passed, {@code false} if it
     * failed.
     */
    public void storeTestResult(String testClassName, boolean testResult) {
        studentTestResults.add(new TestResult(testClassName, testResult));
    }

    // -- GETTERS --
    /**
     * Gets the list of all stored test results.
     *
     * @return a list of {@link TestResult} objects representing the results of
     * all tests.
     */
    public List<TestResult> getTestResults() {
        return studentTestResults;
    }

    // -- SETTERS --
    /**
     * Sets the list of stored test results.
     *
     * @param studentTestResults the list of {@link TestResult} objects to be
     * stored.
     */
    public void setTestResults(List<TestResult> studentTestResults) {
        this.studentTestResults = studentTestResults;
    }

    // -- OVERRIDDEN METHODS --
    /**
     * Called when a test is marked with a result (either passed or failed).
     *
     * @param testResult {@code true} if the test passed, {@code false} if it
     * failed.
     */
    @Override
    public void onTestMarked(boolean testResult) {
        if (testResult) {
            totalPassedTests++;
        } else {
            totalFailedTests++;
        }
    }
}
