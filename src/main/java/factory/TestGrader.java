/**
 * @author jalenearmstrong
 */

package factory;

import java.io.File;

public class TestGrader {
    private final TestGrading testGrading;

    public TestGrader(String studentId, String className) {
        this.testGrading = TestGradingFactory.createTestGrading(studentId, className);
    }

    public void gradeSubmission(File javaFile) {
        testGrading.runTests(javaFile);
    }

    public void resetGrading() {
        testGrading.reset();
    }
}

