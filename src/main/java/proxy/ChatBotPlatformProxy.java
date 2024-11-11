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
import java.lang.reflect.Method;

import proxy.service.ChatBotPlatformService;
import servicelocator.StudentService;

public class ChatBotPlatformProxy implements ChatBotPlatformService {

    public ChatBotPlatformProxy() {

    }

    @Override
    public boolean addChatBot(int LLMCode) {
        try {
            String studentName = StudentService.getCurrentStudentName();
            String classFilePath = "src/main/java/" + studentName + "/ChatBotPlatform.class";

            CustomClassLoader classLoader = new CustomClassLoader();
            Class<?> chatBotPlatformClass = classLoader.loadClassFromFile(classFilePath);
            Object chatBotPlatformInstance = chatBotPlatformClass.getDeclaredConstructor().newInstance();
            Method method = chatBotPlatformClass.getMethod("addChatBot", int.class);
            return (boolean) method.invoke(chatBotPlatformInstance, LLMCode);

        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getChatBotList() {
        try {
            String studentName = StudentService.getCurrentStudentName();
            String classFilePath = "src/main/java/" + studentName + "/ChatBotPlatform.class";

            CustomClassLoader classLoader = new CustomClassLoader();
            Class<?> chatBotPlatformClass = classLoader.loadClassFromFile(classFilePath);
            Object chatBotPlatformInstance = chatBotPlatformClass.getDeclaredConstructor().newInstance();
            Method method = chatBotPlatformClass.getMethod("getChatBotList");
            Object result = method.invoke(chatBotPlatformInstance);
            return (String) result;

        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            System.err.println("Error: " + e.getMessage());
            return "Error: Could not retrieve chat bot list.";
        }
    }

    @Override
    public String interactWithBot(int botNumber, String message) {
        try {
            String studentName = StudentService.getCurrentStudentName();
            String classFilePath = "src/main/java/" + studentName + "/ChatBotPlatform.class";

            CustomClassLoader classLoader = new CustomClassLoader();
            Class<?> chatBotPlatformClass = classLoader.loadClassFromFile(classFilePath);
            Object chatBotPlatformInstance = chatBotPlatformClass.getDeclaredConstructor().newInstance();
            Method method = chatBotPlatformClass.getMethod("interactWithBot", int.class, String.class);
            Object result = method.invoke(chatBotPlatformInstance, botNumber, message);
            return (String) result;

        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            System.err.println("Error: " + e.getMessage());
            return "Error: Could not interact with bot.";
        }
    }

    private class CustomClassLoader extends ClassLoader {
        public Class<?> loadClassFromFile(String filePath) throws ClassNotFoundException {
            try {
                File classFile = new File(filePath);
                if (!classFile.exists()) {
                    filePath = "src/main/java/mock/ChatBotPlatform.class";
                    classFile = new File(filePath);
                    // throw new ClassNotFoundException("Class file not found at: " + filePath);
                }

                byte[] classData = new byte[(int) classFile.length()];
                try (FileInputStream fis = new FileInputStream(classFile)) {
                    fis.read(classData);
                }
                return defineClass(null, classData, 0, classData.length);
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
                throw new ClassNotFoundException("Error loading class from file: " + filePath, e);
            }
        }
    }
}
