/**
 * @author jalenearmstrong
 * https://www.javatpoint.com/java-reflection
 * https://refactoring.guru/design-patterns/proxy
 */

package proxy;

import java.lang.reflect.InvocationTargetException;

import proxy.service.ChatBotService;

public class ChatBotProxy implements ChatBotService {

    private Class<?> chatBotClass;
    private int LLMcode;

    public ChatBotProxy(int LLMcode) {
        this.LLMcode = LLMcode;
        try {
            this.chatBotClass = Class.forName("ChatBot");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("ChatBot class not found", e);
        }
    }

    private Object createChatBotInstance() {
        try {
            return chatBotClass.getDeclaredConstructor(int.class).newInstance(LLMcode);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Error creating ChatBot instance with LLM code " + LLMcode, e);
        }
    }

    private Object invokeMethod(Object instance, String methodName, Class<?>[] parameterTypes, Object... args) {
        try {
            return instance.getClass().getMethod(methodName, parameterTypes).invoke(instance, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Method invocation failed: " + methodName, e);
        }
    }

    private Object invokeStaticMethod(String methodName) {
        try {
            return chatBotClass.getMethod(methodName).invoke(null);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Error invoking static method " + methodName, e);
        }
    }

    @Override
    public String prompt(String requestMessage) {
        Object chatBotInstance = createChatBotInstance();
        return (String) invokeMethod(chatBotInstance, "prompt", new Class<?>[]{String.class}, requestMessage);
    }

    @Override
    public String getChatBotName() {
        Object chatBotInstance = createChatBotInstance();
        return (String) invokeMethod(chatBotInstance, "getChatBotName", new Class<?>[]{});
    }

    @Override
    public int getNumResponsesGenerated() {
        Object chatBotInstance = createChatBotInstance();
        return (int) invokeMethod(chatBotInstance, "getNumResponsesGenerated", new Class<?>[]{});
    }

    @Override
    public int getMessageLimit() {
        Object chatBotInstance = createChatBotInstance();
        return (int) invokeMethod(chatBotInstance, "getMessageLimit", new Class<?>[]{});
    }

    @Override
    public boolean limitReached() {
        Object chatBotInstance = createChatBotInstance();
        return (boolean) invokeMethod(chatBotInstance, "limitReached", new Class<?>[]{});
    }

    @Override
    public int getTotalNumResponsesGenerated() {
        return (int) invokeStaticMethod("getTotalNumResponsesGenerated");
    }

    @Override
    public int getTotalNumMessagesRemaining() {
        return (int) invokeStaticMethod("getTotalNumMessagesRemaining");
    }

    @Override
    public String toString() {
        Object chatBotInstance = createChatBotInstance();
        String chatBotName = (String) invokeMethod(chatBotInstance, "getChatBotName", new Class<?>[]{});
        int numResponsesGenerated = (int) invokeMethod(chatBotInstance, "getNumResponsesGenerated", new Class<?>[]{});
        
        return "ChatBot Name: " + chatBotName + "     " +
            "Number Messages Used: " + numResponsesGenerated + "\n";
    }
}