/**
 * @author jalenearmstrong
 * This Is A Code Smell
 */

package factory;

import java.io.File;

import observer.TestMarkingListener;

public class DefaultTestGrading implements TestGrading {
    @Override
    public void runTests(File javaFile) {
        System.out.println("No Tests To Run");
    }

    @Override
    public void reset() {
        System.out.println("Nothing To Reset");
    }

    @Override
    public void addObserver(TestMarkingListener observer) {
        System.out.println("Nothing To Observe");
    }
}