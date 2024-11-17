package factory;

import java.io.File;

/**
 * A utility class for grading student submissions using the {@link TestGrading}
 * interface.
 * <p>
 * This class initializes a {@link TestGrading} instance for a specific student
 * and provides methods to grade submissions and reset the grading state.
 * <p>
 * The grading process involves executing JUnit tests contained in a provided
 * Java file and delegating the results to the underlying {@link TestGrading}
 * implementation.
 *
 * @author jalenearmstrong
 * @see TestGrading
 * @see TestGradingFactory
 */
public class TestGrader {

    /**
     * The {@link TestGrading} instance used to grade the student's submission.
     */
    private final TestGrading testGrading;

    /**
     * Constructs a {@code TestGrader} for the specified student and class.
     *
     * @param studentId the unique identifier for the student.
     * @param className the name of the class for which the test grading is
     * performed.
     */
    public TestGrader(String studentId, String className) {
        this.testGrading = TestGradingFactory.createTestGrading(studentId,
                className);
    }

    /**
     * Grades the student's submission by running the JUnit tests contained in
     * the provided Java file.
     *
     * @param javaFile the Java source file containing the student's test
     * implementation.
     */
    public void gradeSubmission(File javaFile) {
        testGrading.runTests(javaFile);
    }

    /**
     * Resets the grading state, clearing any previous results.
     */
    public void resetGrading() {
        testGrading.reset();
    }
}
