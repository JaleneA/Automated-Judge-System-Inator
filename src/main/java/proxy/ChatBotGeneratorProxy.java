/**
 * @author jalenearmstrong
 * https://www.javatpoint.com/java-reflection
 * https://refactoring.guru/design-patterns/proxy
 */

package proxy;

import java.lang.reflect.InvocationTargetException;

import service.ChatBotGeneratorService;

public class ChatBotGeneratorProxy implements ChatBotGeneratorService {

    @Override
    public String generateChatBotLLM(int LLMcode) {
        try {
            Class<?> testclass = Class.forName("ChatBotGenerator");
            return (String) testclass.getMethod("generateChatBotLLM", int.class).invoke(null, LLMcode);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            return "ChatGPT-3.5";
        }
    }
}