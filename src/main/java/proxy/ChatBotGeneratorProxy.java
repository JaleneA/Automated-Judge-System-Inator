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
import java.lang.reflect.Modifier;

import proxy.service.ChatBotGeneratorService;
import servicelocator.StudentService;

public class ChatBotGeneratorProxy implements ChatBotGeneratorService {
    @Override
    public String generateChatBotLLM(int LLMcode) {
        try {
            String studentName = StudentService.getCurrentStudentName();
            String classFilePath = "src/main/java/" + studentName + "/ChatBotGenerator.class";
    
            CustomClassLoader classLoader = new CustomClassLoader();
            Class<?> chatBotGeneratorClass = classLoader.loadClassFromFile(classFilePath);
            Method method = chatBotGeneratorClass.getMethod("generateChatBotLLM", int.class);

            if (Modifier.isStatic(method.getModifiers())) {
                return (String) method.invoke(null, LLMcode);
            } else {
                Object instance = chatBotGeneratorClass.getDeclaredConstructor().newInstance();
                return (String) method.invoke(instance, LLMcode);
            }
            
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            System.err.println("Error: " + e.getMessage());
            return "Error: Could not invoke method.";
        }
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
                System.err.println("Error: " + e.getMessage());
                throw new ClassNotFoundException("Class file not found at: " + filePath, e);
            }
        }
    }
}
