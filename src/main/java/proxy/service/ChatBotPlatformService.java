/**
 * @author jalenearmstrong
 * https://refactoring.guru/design-patterns/proxy
 */

package proxy.service;

public interface ChatBotPlatformService {
    boolean addChatBot(int LLMcode);
    String getChatBotList();
    String interactWithBot(int botNumber, String message);
}
