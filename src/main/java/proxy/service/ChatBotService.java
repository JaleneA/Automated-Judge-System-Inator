/**
 * @author jalenearmstrong
 * https://refactoring.guru/design-patterns/proxy
 */

package proxy.service;

public interface ChatBotService {
    String prompt(String requestMessage);
    String getChatBotName();
    int getNumResponsesGenerated();
    int getMessageLimit();
    boolean limitReached();
    int getTotalNumResponsesGenerated();
    int getTotalNumMessagesRemaining();
}
