/**
 * @author jalenearmstrong
 */

package observer;

import java.util.ArrayList;
import java.util.List;

public class TestGradingObserver implements TestMarkingListener {
    private List<TestResult> studentTestResults  = new ArrayList<>();
    private int totalPassedTests = 0;
    private int totalFailedTests = 0;

    @Override
    public void onTestMarked(boolean testResult) {
        if (testResult) {
            totalPassedTests++;
        } else {
            totalFailedTests++;
        }
    }

    public void storeTestResult(String testClassName, boolean testResult) {
        studentTestResults .add(new TestResult(testClassName, testResult));
    }

    public void displayTestResults() {
        System.out.println("Displaying test results...");
        System.out.println("Total Passed Tests: " + totalPassedTests);
        System.out.println("Total Failed Tests: " + totalFailedTests);
        System.out.println("Test Results List Size: " + studentTestResults .size());
    }    

    public void reset() {
        totalPassedTests = 0;
        totalFailedTests = 0;
        studentTestResults .clear();
    }

    public List<TestResult> getTestResults() {
        return studentTestResults ;
    }

    public void setTestResults(List<TestResult> studentTestResults ) {
        this.studentTestResults  = studentTestResults ;
    }
}