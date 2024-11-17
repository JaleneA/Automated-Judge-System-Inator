package factory;

/**
 * A factory class for creating instances of {@link TestGrading} based on the
 * class name.
 * <p>
 * This class implements the Factory Method design pattern to instantiate
 * different {@link TestGrading} implementations based on the provided class
 * name. It helps to decouple the creation logic of the grading strategy from
 * the rest of the system.
 * <p>
 * The factory method {@link #createTestGrading(String, String)} returns an
 * appropriate {@link TestGrading} object based on the class name, which
 * corresponds to different types of grading implementations for different
 * chatbot-related courses.
 * <p>
 * For more information on the Factory Method design pattern, see
 * <a href="https://refactoring.guru/design-patterns/factory-method">Factory
 * Method</a>.
 *
 * @author jalenearmstrong
 * @see TestGrading
 * @see ChatBotGeneratorTestGrading
 * @see ChatBotTestGrading
 * @see ChatBotPlatformTestGrading
 * @see ChatBotSimulationTestGrading
 */
public class TestGradingFactory {

    /**
     * Creates an instance of {@link TestGrading} based on the specified class
     * name.
     * <p>
     * This method will instantiate the appropriate {@link TestGrading}
     * implementation depending on the class name provided, allowing for
     * flexible grading strategies for different chatbot-related classes.
     *
     * @param studentId the unique identifier for the student whose tests are
     * being graded.
     * @param className the name of the class for which the grading
     * implementation is needed.
     * @return a {@link TestGrading} instance for the specified class, or
     * {@code null} if the class name does not match any known case.
     */
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
