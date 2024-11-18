package proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import proxy.service.ChatBotGeneratorService;
import servicelocator.StudentService;

/**
 * Proxy implementation for the {@link proxy.service.ChatBotGeneratorService}
 * interface.
 * <p>
 * This proxy class dynamically loads and invokes the `generateChatBotLLM`
 * method from a student's implementation of the `ChatBotGenerator` class. It
 * uses a custom class loader to load the class file at runtime based on the
 * student's name.
 *
 * @author jalenearmstrong
 * @see <a href="https://refactoring.guru/design-patterns/proxy">Proxy Design
 * Pattern</a>
 * @see <a href="https://www.baeldung.com/java-classloaders">Java Class
 * Loaders</a>
 */
public class ChatBotGeneratorProxy implements ChatBotGeneratorService {

    // -- OVERRIDDEN METHODS --
    /**
     * Dynamically generates a chatbot by invoking the `generateChatBotLLM`
     * method from the `ChatBotGenerator` class of the current student.
     *
     * @param LLMcode an integer representing the code of the Large Language
     * Model.
     * @return a {@code String} containing the result of the chatbot generation,
     * or an error message if the process fails.
     */
    @Override
    public String generateChatBotLLM(int LLMcode) {
        try {
            String studentName = StudentService.getCurrentStudentName();
            String classFilePath = "src/main/java/students/" + studentName + "/ChatBotGenerator.class";

            CustomClassLoader classLoader = new CustomClassLoader();
            Class<?> chatBotGeneratorClass = classLoader.loadClassFromFile(classFilePath);
            Method method = chatBotGeneratorClass.getMethod("generateChatBotLLM", int.class);

            if (Modifier.isStatic(method.getModifiers())) {
                // Invoke static method
                return (String) method.invoke(null, LLMcode);
            } else {
                // Invoke instance method
                Object instance = chatBotGeneratorClass.getDeclaredConstructor().newInstance();
                return (String) method.invoke(instance, LLMcode);
            }
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException
                | InvocationTargetException | InstantiationException e) {
            return "Error: Could not invoke method.";
        }
    }

    // -- INNER CLASS --
    /**
     * Custom class loader for dynamically loading class files from the file
     * system.
     */
    private class CustomClassLoader extends ClassLoader {

        /**
         * Loads a class from a file at the specified file path.
         *
         * @param filePath the file path to the `.class` file.
         * @return the loaded {@code Class<?>} object.
         * @throws ClassNotFoundException if the class file is not found or
         * cannot be loaded.
         */
        public Class<?> loadClassFromFile(String filePath) throws ClassNotFoundException {
            try {
                File classFile = new File(filePath);
                byte[] classData = new byte[(int) classFile.length()];

                try (FileInputStream fis = new FileInputStream(classFile)) {
                    fis.read(classData);
                }

                // Define and load the class using the parent ClassLoader
                return defineClass(null, classData, 0, classData.length);
            } catch (IOException e) {
                throw new ClassNotFoundException("Class file not found at: " + filePath, e);
            }
        }
    }
}
