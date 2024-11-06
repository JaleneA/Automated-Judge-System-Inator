/**
 * @author jalenearmstrong
 * https://refactoring.guru/design-patterns/observer
 */

package observer;

import java.util.ArrayList;
import java.util.List;

public class TestMarking implements TestMarker {
    private int passedTests = 0;
    private int failedTests = 0;
    private List<TestMarkingListener> listeners = new ArrayList<>();

    public void addListener(TestMarkingListener listener) {
        listeners.add(listener);
    }

    @Override
    public void markTest(boolean testResult) {
        if (testResult) {
            passedTests++;
        } else {
            failedTests++;
        }
        notifyListeners(testResult);
    }

    private void notifyListeners(boolean testResult) {
        for (TestMarkingListener listener : listeners) {
            listener.onTestMarked(testResult);
        }
    }

    @Override
    public int calculatePassedTests() {
        return passedTests;
    }

    @Override
    public int calculateFailedTests() {
        return failedTests;
    }

    @Override
    public void displayResults() {
        System.out.println("Passed Tests: " + calculatePassedTests());
        System.out.println("Failed Tests: " + calculateFailedTests());
    }

    public List<TestMarkingListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<TestMarkingListener> listeners) {
        this.listeners = listeners;
    }
}