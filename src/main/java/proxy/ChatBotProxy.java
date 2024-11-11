/**
 * @author jalenearmstrong
 * Helpful Documentation
 * https://www.baeldung.com/java-classloaders
 * https://refactoring.guru/design-patterns/proxy
 */

package proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import proxy.service.ChatBotService;
import servicelocator.StudentService;

public class ChatBotProxy implements ChatBotService {

    private Class<?> chatBotClass;
    private int LLMcode;

    public ChatBotProxy(int LLMcode) {
        this.LLMcode = LLMcode;
        loadChatBotClass();
    }

    private void loadChatBotClass() {
        try {
            String studentName = StudentService.getCurrentStudentName();
            String classFilePath = "src/main/java/" + studentName + "/ChatBot.class";
            CustomClassLoader classLoader = new CustomClassLoader();
            this.chatBotClass = classLoader.loadClassFromFile(classFilePath);
            
        } catch (ClassNotFoundException e) {
            try {
                String classFilePath = "src/main/java/testclasses/mock/ChatBot.class";
                CustomClassLoader classLoader = new CustomClassLoader();
                this.chatBotClass = classLoader.loadClassFromFile(classFilePath);
            } catch (ClassNotFoundException e2) {
                throw new RuntimeException("ChatBot class not found at the specified paths", e2);
            }
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

    public int getLLMcode() {
        return LLMcode;
    }

    public void setLLMcode(int LLMcode) {
        this.LLMcode = LLMcode;
    }

    private class CustomClassLoader extends ClassLoader {
        public Class<?> loadClassFromFile(String filePath) throws ClassNotFoundException {
            try {
                File classFile = new File(filePath);
                byte[] classData = new byte[(int) classFile.length()];
                try (FileInputStream fis = new FileInputStream(classFile)) {
                    fis.read(classData);
                }
                return defineClass(null, classData, 0, classData.length);
            } catch (IOException e) {
                throw new ClassNotFoundException("Class file not found at: " + filePath, e);
            }
        }
    }
}