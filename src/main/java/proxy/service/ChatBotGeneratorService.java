package proxy.service;

/**
 * Interface representing a service for generating chatbots based on a specified
 * LLM (Large Language Model) code.
 * <p>
 * This interface is part of a proxy design pattern implementation that provides
 * an abstraction for chatbot generation services.
 * </p>
 *
 * @author jalenearmstrong
 * @see <a href="https://refactoring.guru/design-patterns/proxy">Proxy Design
 * Pattern</a>
 */
public interface ChatBotGeneratorService {

    // -- ABSTRACT METHODS --
    /**
     * Generates a chatbot using the specified LLM code.
     *
     * @param LLMcode an integer representing the code of the Large Language
     * Model to use for chatbot generation.
     * @return a {@code String} representing the details of the generated
     * chatbot.
     */
    String generateChatBotLLM(int LLMcode);
}
