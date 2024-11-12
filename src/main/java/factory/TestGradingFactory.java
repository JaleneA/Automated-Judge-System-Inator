/**
 * @author jalenearmstrong
 * Helpful Documentation
 * https://refactoring.guru/design-patterns/factory-method
 */

package factory;

public class TestGradingFactory {
    
    public static TestGrading createTestGrading(String studentId, String className) {
        switch (className) {
            case "ChatBotGenerator" -> {
                return new ChatBotGeneratorTestGrading(studentId);
            }
                
            case "ChatBot" -> {
                return new ChatBotTestGrading(studentId);
            }
                
            case "ChatBotPlatform" -> {
                return new ChatBotPlatformTestGrading(studentId);
            }

            case "ChatBotSimulation" -> {
                return new ChatBotSimulationTestGrading(studentId);
            }
        }
                return null;
    }
}

