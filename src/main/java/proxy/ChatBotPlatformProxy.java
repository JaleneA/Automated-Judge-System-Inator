/**
 * @author jalenearmstrong
 * https://www.javatpoint.com/java-reflection
 * https://refactoring.guru/design-patterns/proxy
 */

package proxy;

import java.lang.reflect.InvocationTargetException;

import proxy.service.ChatBotPlatformService;

public class ChatBotPlatformProxy implements ChatBotPlatformService {

    private Class<?> chatBotPlatformClass;

    public ChatBotPlatformProxy() {
        try {
            this.chatBotPlatformClass = Class.forName("ChatBotPlatform");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("ChatBotPlatform class not found", e);
        }
    }

    private Object invokeMethod(String methodName, Class<?>[] parameterTypes, Object... args) {
        try {
            Object chatBotPlatformInstance = chatBotPlatformClass.getDeclaredConstructor().newInstance();
            return chatBotPlatformClass.getMethod(methodName, parameterTypes).invoke(chatBotPlatformInstance, args);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Method invocation failed: " + methodName, e);
        }
    }

    @Override
    public boolean addChatBot(int LLMCode) {
        return (boolean) invokeMethod("addChatBot", new Class<?>[]{int.class}, LLMCode);
    }

    @Override
    public String getChatBotList() {
        return (String) invokeMethod("getChatBotList", new Class<?>[]{});
    }

    @Override
    public String interactWithBot(int botNumber, String message) {
        return (String) invokeMethod("interactWithBot", new Class<?>[]{int.class, String.class}, botNumber, message);
    }
}