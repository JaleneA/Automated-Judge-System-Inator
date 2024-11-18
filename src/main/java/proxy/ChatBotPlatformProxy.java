package proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import proxy.service.ChatBotPlatformService;
import servicelocator.StudentService;

/**
 * Proxy implementation for the {@link proxy.service.ChatBotPlatformService}
 * interface.
 * <p>
 * This proxy class dynamically loads and invokes methods such as `addChatBot`,
 * `getChatBotList`, and `interactWithBot` from a student's implementation of
 * the `ChatBotPlatform` class. It uses a custom class loader to load the class
 * file at runtime based on the current student's name.
 *
 * @author jalenearmstrong
 * @see <a href="https://refactoring.guru/design-patterns/proxy">Proxy Design
 * Pattern</a>
 * @see <a href="https://www.baeldung.com/java-classloaders">Java Class
 * Loaders</a>
 */
public class ChatBotPlatformProxy implements ChatBotPlatformService {

    // -- CONSTRUCTORS --
    /**
     * Default constructor for the proxy class.
     */
    public ChatBotPlatformProxy() {
    }

    // -- OVERRIDDEN METHODS --
    /**
     * Adds a chatbot to the platform by invoking the student's implementation
     * of the `addChatBot` method in their `ChatBotPlatform` class.
     *
     * @param LLMCode an integer representing the LLM code of the chatbot to be
     * added.
     * @return {@code true} if the chatbot was successfully added, {@code false}
     * otherwise.
     */
    @Override
    public boolean addChatBot(int LLMCode) {
        try {
            String studentName = StudentService.getCurrentStudentName();
            String classFilePath = "src/main/java/students/" + studentName + "/ChatBotPlatform.class";

            CustomClassLoader classLoader = new CustomClassLoader();
            Class<?> chatBotPlatformClass = classLoader.loadClassFromFile(classFilePath);
            Object chatBotPlatformInstance = chatBotPlatformClass.getDeclaredConstructor().newInstance();
            Method method = chatBotPlatformClass.getMethod("addChatBot", int.class);
            return (boolean) method.invoke(chatBotPlatformInstance, LLMCode);

        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException
                | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            return false;
        }
    }

    /**
     * Retrieves the list of available chatbots by invoking the student's
     * implementation of the `getChatBotList` method in their `ChatBotPlatform`
     * class.
     *
     * @return a {@code String} representing the list of chatbots, or an error
     * message if retrieval fails.
     */
    @Override
    public String getChatBotList() {
        try {
            String studentName = StudentService.getCurrentStudentName();
            String classFilePath = "src/main/java/students/" + studentName + "/ChatBotPlatform.class";

            CustomClassLoader classLoader = new CustomClassLoader();
            Class<?> chatBotPlatformClass = classLoader.loadClassFromFile(classFilePath);
            Object chatBotPlatformInstance = chatBotPlatformClass.getDeclaredConstructor().newInstance();
            Method method = chatBotPlatformClass.getMethod("getChatBotList");
            Object result = method.invoke(chatBotPlatformInstance);
            return (String) result;

        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException
                | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            return "Error: Could not retrieve chat bot list.";
        }
    }

    /**
     * Interacts with a specific chatbot on the platform by invoking the
     * student's implementation of the `interactWithBot` method in their
     * `ChatBotPlatform` class.
     *
     * @param botNumber an integer representing the bot to interact with.
     * @param message the message to send to the chatbot.
     * @return a {@code String} containing the response from the chatbot, or an
     * error message if interaction fails.
     */
    @Override
    public String interactWithBot(int botNumber, String message) {
        try {
            String studentName = StudentService.getCurrentStudentName();
            String classFilePath = "src/main/java/students/" + studentName + "/ChatBotPlatform.class";

            CustomClassLoader classLoader = new CustomClassLoader();
            Class<?> chatBotPlatformClass = classLoader.loadClassFromFile(classFilePath);
            Object chatBotPlatformInstance = chatBotPlatformClass.getDeclaredConstructor().newInstance();
            Method method = chatBotPlatformClass.getMethod("interactWithBot", int.class, String.class);
            Object result = method.invoke(chatBotPlatformInstance, botNumber, message);
            return (String) result;

        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException
                | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            return "Error: Could not interact with bot.";
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
                if (!classFile.exists()) {
                    throw new ClassNotFoundException("Class file not found at: " + filePath);
                }

                byte[] classData = new byte[(int) classFile.length()];
                try (FileInputStream fis = new FileInputStream(classFile)) {
                    fis.read(classData);
                }

                // Define and load the class using the parent ClassLoader
                return defineClass(null, classData, 0, classData.length);
            } catch (IOException e) {
                throw new ClassNotFoundException("Error loading class from file: " + filePath, e);
            }
        }
    }
}
