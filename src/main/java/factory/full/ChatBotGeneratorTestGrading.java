/**
 * @author jalenearmstrong
 * Helpful Documentation
 * https://refactoring.guru/design-patterns/factory-method
 * https://junit.org/junit5/docs/current/user-guide/
 */

package factory.full;

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

import factory.TestGrading;
import observer.TestGradingManager;
import observer.TestGradingObserver;
import observer.TestMarker;
import observer.TestMarking;
import observer.TestMarkingListener;

public class ChatBotGeneratorTestGrading implements TestGrading {
    private String studentId;
    private TestMarker testMarker;
    private TestGradingObserver gradingObserver;
    private List<TestMarkingListener> observers = new ArrayList<>();

    public ChatBotGeneratorTestGrading(String studentId) {
        this.studentId = studentId;
        this.testMarker = new TestMarking();

        // Shared Observer Manager
        gradingObserver = TestGradingManager.getGradingObserver();
        if (!observers.contains(gradingObserver)) {
            observers.add(gradingObserver);
        }
    }

    @Override
    public void runTests(File javaFile) {
        try {
            org.junit.platform.launcher.Launcher launcher = LauncherFactory.create();

            LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                    .selectors(DiscoverySelectors.selectClass(getFullClassName(javaFile)))
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

                    if (!testPassed) {
                        testExecutionResult.getThrowable().ifPresent(throwable ->
                            System.out.println("Test " + testIdentifier.getDisplayName() + " failed due to: " + throwable.getMessage()));
                    } else {
                        System.out.println("Test " + testIdentifier.getDisplayName() + " executed: PASSED");
                    }
                    gradingObserver.storeTestResult(testIdentifier.getDisplayName(), testPassed);
                }
            };

            launcher.registerTestExecutionListeners(listener);
            launcher.execute(testPlan);
            testMarker.displayResults();
            reset();

        } catch (PreconditionViolationException e) {
            System.err.println("Failed to execute tests for class: " + e.getMessage());
            testMarker.markTest(false);
        }
    }

    @Override
    public void reset() {
        testMarker.reset();
    }

    @Override
    public void addObserver(TestMarkingListener observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    private void notifyObservers(boolean testResult) {
        for (TestMarkingListener observer : observers) {
            observer.onTestMarked(testResult);
        }
    }

    // Getters & Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public TestMarker getTestMarker() {
        return testMarker;
    }

    public void setTestMarker(TestMarker testMarker) {
        this.testMarker = testMarker;
    }

    public TestGradingObserver getGradingObserver() {
        return gradingObserver;
    }

    public void setGradingObserver(TestGradingObserver gradingObserver) {
        this.gradingObserver = gradingObserver;
    }

    public List<TestMarkingListener> getObservers() {
        return observers;
    }

    public void setObservers(List<TestMarkingListener> observers) {
        this.observers = observers;
    }

    protected String getFullClassName(File javaFile) {
        return "testclasses.full." + javaFile.getName().replace(".java", "FullTest");
    }
    
}
