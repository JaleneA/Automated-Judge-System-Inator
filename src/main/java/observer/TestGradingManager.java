/**
 * @author jalenearmstrong
 */

package observer;

public class TestGradingManager {
    private static TestGradingObserver gradingObserver = new TestGradingObserver();

    public static TestGradingObserver getGradingObserver() {
        return gradingObserver;
    }

    public static void resetGradingObserver() {
        gradingObserver = new TestGradingObserver();
    }
}
