/**
 * @author jalenearmstrong
 */

package factory;

import java.io.File;
import observer.TestMarkingListener;

public interface TestGrading {
    void runTests(File javaFile);
    void reset();
    void addObserver(TestMarkingListener observer);
}

