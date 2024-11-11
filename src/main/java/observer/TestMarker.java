/**
 * @author jalenearmstrong
 * https://refactoring.guru/design-patterns/observer
 */

package observer;

public interface TestMarker {
    void markTest(boolean testResult);
    int calculatePassedTests();
    int calculateFailedTests();
    void displayResults();
    void reset();
}
