package proxy.service;

/**
 * Interface representing a service for managing chatbots on a platform.
 * <p>
 * This interface provides methods for adding chatbots, retrieving a list of
 * chatbots, and interacting with a specific chatbot on the platform.
 * </p>
 *
 * @author jalenearmstrong
 * @see <a href="https://refactoring.guru/design-patterns/proxy">Proxy Design
 * Pattern</a>
 */
public interface ChatBotPlatformService {

    // -- ABSTRACT METHODS --
    /**
     * Adds a chatbot to the platform using the specified LLM code.
     *
     * @param LLMcode an integer representing the code of the Large Language
     * Model to use for the chatbot.
     * @return {@code true} if the chatbot was successfully added, {@code false}
     * otherwise.
     */
    boolean addChatBot(int LLMcode);

    /**
     * Retrieves a list of all chatbots currently on the platform.
     *
     * @return a {@code String} containing the list of chatbots.
     */
    String getChatBotList();

    /**
     * Sends a message to a specific chatbot on the platform and retrieves the
     * bot's response.
     *
     * @param botNumber an integer representing the identifier of the chatbot to
     * interact with.
     * @param message the message to send to the chatbot.
     * @return the chatbot's response as a {@code String}.
     */
    String interactWithBot(int botNumber, String message);
}
