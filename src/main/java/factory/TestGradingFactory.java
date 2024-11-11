/**
 * @author jalenearmstrong
 * Helpful Documentation
 * https://refactoring.guru/design-patterns/factory-method
 */

package factory;

import factory.full.ChatBotGeneratorTestGrading;
import factory.full.ChatBotPlatformTestGrading;
import factory.full.ChatBotSimulationTestGrading;
import factory.full.ChatBotTestGrading;
import factory.structure.ChatBotStructureTestGrading;
import factory.structure.ChatBotPlatformStructureTestGrading;

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
            case "ChatBotStructure" -> {
                return new ChatBotStructureTestGrading(studentId);
            }
            case "ChatBotPlatformStructure" -> {
                return new ChatBotPlatformStructureTestGrading(studentId);
            }
            default -> {
                // To Remove
                System.out.println("No grading strategy available for class: " + className);
                return new DefaultTestGrading();
            }
        }
    }
}

