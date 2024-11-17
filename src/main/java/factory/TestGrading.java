package factory;

import java.io.File;

import observer.TestMarkingListener;

/**
 * An interface for defining the behavior of a test grading system.
 * <p>
 * Classes implementing this interface are responsible for running tests,
 * managing the grading process, and supporting an observer mechanism to notify
 * listeners of test results.
 *
 * @author jalenearmstrong
 * @see TestMarkingListener
 */
public interface TestGrading {

    // -- ABSTRACT METHODS --
    /**
     * Adds an observer to be notified of test results.
     *
     * @param observer the {@link TestMarkingListener} to be added.
     */
    void addObserver(TestMarkingListener observer);

    /**
     * Resets the grading state, preparing for a new grading session.
     */
    void reset();

    /**
     * Runs the tests defined in the specified Java file.
     *
     * @param javaFile the Java file containing the test definitions.
     */
    void runTests(File javaFile);
}
