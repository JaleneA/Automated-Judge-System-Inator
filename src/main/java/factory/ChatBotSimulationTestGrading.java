package factory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.platform.commons.PreconditionViolationException;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import observer.TestGradingManager;
import observer.TestGradingObserver;
import observer.TestMarker;
import observer.TestMarking;
import observer.TestMarkingListener;

/**
 * Implementation of the {@link TestGrading} interface that facilitates the
 * grading of student-written Java tests using JUnit 5.
 *
 * <p>
 * This class uses a factory design pattern and observes the test execution
 * process, notifying registered observers about the test results and storing
 * the test outcomes.
 *
 * <p>
 * <b>Key Features:</b>
 * <ul>
 * <li>Executes JUnit 5 tests based on a provided Java file.</li>
 * <li>Notifies observers of test results.</li>
 * <li>Stores results in a shared grading observer manager.</li>
 * </ul>
 *
 * <p>
 * For more information on the Factory Method pattern, see
 * <a href="https://refactoring.guru/design-patterns/factory-method">Factory
 * Method</a>.
 *
 * <p>
 * For details about JUnit 5, refer to the
 * <a href="https://junit.org/junit5/docs/current/user-guide/">JUnit 5 User
 * Guide</a>.
 *
 * @author jalenearmstrong
 * @see TestGrading
 * @see TestGradingManager
 * @see TestMarking
 * @see TestMarker
 */
public class ChatBotSimulationTestGrading implements TestGrading {

    // -- INSTANCE VARIABLES --
    /**
     * The ID of the student whose submission is being graded.
     */
    private String studentId;
    /**
     * The {@link TestMarker} responsible for marking the tests as passed or
     * failed.
     */
    private TestMarker testMarker;
    /**
     * The shared {@link TestGradingObserver} that stores and manages test
     * results.
     */
    private TestGradingObserver gradingObserver;
    /**
     * A {@link List} of {@link TestMarkingListener} observers that are notified
     * of test results.
     */
    private List<TestMarkingListener> observers = new ArrayList<>();

    // -- CONSTRUCTORS --
    /**
     * Constructs a new {@link ChatBotSimulationTestGrading} instance for
     * grading tests associated with a specific student.
     *
     * @param studentId the unique identifier for the student.
     */
    public ChatBotSimulationTestGrading(String studentId) {
        this.studentId = studentId;
        this.testMarker = new TestMarking();

        // Shared Observer Manager
        gradingObserver = TestGradingManager.getGradingObserver();
        if (!observers.contains(gradingObserver)) {
            observers.add(gradingObserver);
        }
    }

    // -- BUSINESS LOGIC METHODS --
    /**
     * Notifies all registered observers of the test result.
     *
     * @param testResult {@code true} if the test passed, {@code false}
     * otherwise.
     */
    private void notifyObservers(boolean testResult) {
        for (TestMarkingListener observer : observers) {
            observer.onTestMarked(testResult);
        }
    }

    // -- OVERRIDDEN METHODS --
    /**
     * Adds a new observer to be notified of test results.
     *
     * @param observer the observer to be added.
     */
    @Override
    public void addObserver(TestMarkingListener observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Resets the test marker state.
     */
    @Override
    public void reset() {
        testMarker.reset();
    }

    /**
     * Executes the JUnit 5 tests contained in the specified Java file.
     * <p>
     * The method discovers and runs tests defined in the file, marking each
     * test as passed or failed and notifying observers of the results.
     *
     * @param javaFile the Java source file containing the test definitions.
     */
    @Override
    public void runTests(File javaFile) {
        try {
            String testClassName = javaFile.getName().replace(".java", "Test");
            String fullClassName = "testclasses." + testClassName;

            org.junit.platform.launcher.Launcher launcher = LauncherFactory.create();

            LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                    .selectors(DiscoverySelectors.selectClass(fullClassName))
                    .build();

            TestPlan testPlan = launcher.discover(request);

            TestExecutionListener listener = new TestExecutionListener() {
                @Override
                public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
                    if (!testIdentifier.isTest()) {
                        return;
                    }
                    boolean testPassed = testExecutionResult.getStatus() == TestExecutionResult.Status.SUCCESSFUL;
                    testMarker.markTest(testPassed);
                    notifyObservers(testPassed);
                    gradingObserver.storeTestResult(testIdentifier.getDisplayName(), testPassed);
                }
            };

            launcher.registerTestExecutionListeners(listener);
            launcher.execute(testPlan);
            reset();

        } catch (PreconditionViolationException e) {
            System.err.println("Failed to execute tests for class: " + e.getMessage());
            testMarker.markTest(false);
        }
    }

    // -- GETTERS --
    /**
     * Returns the student ID.
     *
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the {@link TestMarker} used to mark tests.
     *
     * @return The test marker.
     */
    public TestMarker getTestMarker() {
        return testMarker;
    }

    /**
     * Returns the shared {@link TestGradingObserver}.
     *
     * @return The grading observer.
     */
    public TestGradingObserver getGradingObserver() {
        return gradingObserver;
    }

    /**
     * Returns the list of registered {@link TestMarkingListener} observers.
     *
     * @return The list of observers.
     */
    public List<TestMarkingListener> getObservers() {
        return observers;
    }

    // -- SETTERS --
    /**
     * Sets the student ID.
     *
     * @param studentId The student ID.
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Sets the test marker.
     *
     * @param testMarker The test marker.
     */
    public void setTestMarker(TestMarker testMarker) {
        this.testMarker = testMarker;
    }

    /**
     * Sets the grading observer.
     *
     * @param gradingObserver The grading observer.
     */
    public void setGradingObserver(TestGradingObserver gradingObserver) {
        this.gradingObserver = gradingObserver;
    }

    /**
     * Sets the list of {@link TestMarkingListener} observers.
     *
     * @param observers The list of observers.
     */
    public void setObservers(List<TestMarkingListener> observers) {
        this.observers = observers;
    }
}
