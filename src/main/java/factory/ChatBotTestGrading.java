/**
 * @author jalenearmstrong
 * Helpful Documentation
 * https://refactoring.guru/design-patterns/factory-method
 * https://junit.org/junit5/docs/current/user-guide/
 */

package factory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.commons.PreconditionViolationException;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import observer.TestMarker;
import observer.TestMarking;
import observer.TestMarkingListener;
import observer.TestGradingManager;
import observer.TestGradingObserver;

public class ChatBotTestGrading implements TestGrading {
    private String studentId;
    private TestMarker testMarker;
    private List<TestMarkingListener> observers = new ArrayList<>();
    private TestGradingObserver gradingObserver;

    public ChatBotTestGrading(String studentId) {
        this.studentId = studentId;
        this.testMarker = new TestMarking();
        this.gradingObserver = new TestGradingObserver();
        
        // Shared Observer Manager
        gradingObserver = TestGradingManager.getGradingObserver();
        if (!observers.contains(gradingObserver)) {
            observers.add(gradingObserver);
        }
    }

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
            System.err.println("Error executing tests for class: " + e.getMessage());
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
}