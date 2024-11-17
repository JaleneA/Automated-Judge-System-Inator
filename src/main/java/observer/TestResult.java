/**
 * Represents the result of a test, including the test class name and whether the test passed or failed.
 * <p>
 * This class encapsulates the details of an individual test's result. It stores
 * the name of the test class and the boolean value indicating whether the test
 * was successful or not.
 * <p>
 * This class is used to track the outcome of tests in a grading system, storing
 * results that can later be processed, displayed, or stored in a report.
 *
 * @author jalenearmstrong
 * @see TestGradingObserver
 */
package observer;

public class TestResult {

    /**
     * The name of the test class.
     */
    private final String testClassName;

    /**
     * {@code true} if the test passed, {@code false} if the test failed.
     */
    private final boolean passed;

    /**
     * Creates a new {@code TestResult} object with the specified test class
     * name and result.
     *
     * @param testClassName the name of the test class.
     * @param passed {@code true} if the test passed, {@code false} if the test
     * failed.
     */
    public TestResult(String testClassName, boolean passed) {
        this.testClassName = testClassName;
        this.passed = passed;
    }

    /**
     * Gets the name of the test class.
     *
     * @return the name of the test class.
     */
    public String getTestClassName() {
        return testClassName;
    }

    /**
     * Checks if the test passed.
     *
     * @return {@code true} if the test passed, {@code false} if the test
     * failed.
     */
    public boolean isPassed() {
        return passed;
    }
}
