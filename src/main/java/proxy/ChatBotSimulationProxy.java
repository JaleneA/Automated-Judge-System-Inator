package proxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import proxy.service.ChatBotSimulationService;
import servicelocator.StudentService;

/**
 * Proxy implementation for the {@link proxy.service.ChatBotSimulationService}
 * interface.
 * <p>
 * This proxy class dynamically loads the `ChatBotSimulation` class from the
 * student's directory at runtime and invokes its `main()` method. The proxy
 * pattern is used to control access to the `ChatBotSimulation` class, which is
 * dynamically loaded based on the student's name and the specific file path.
 *
 * @author jalenearmstrong
 * @see <a href="https://refactoring.guru/design-patterns/proxy">Proxy Design
 * Pattern</a>
 * @see <a href="https://www.baeldung.com/java-classloaders">Java Class
 * Loaders</a>
 */
public class ChatBotSimulationProxy implements ChatBotSimulationService {

    // -- INSTANCE VARIABLES --
    private Class<?> chatBotSimulationClass;

    // -- OVERRIDDEN METHODS --
    /**
     * The main method for the proxy.
     * <p>
     * This method loads the student's `ChatBotSimulation` class from the file
     * system using a custom class loader, returning {@code true} or
     * {@code false} depending on loading success.
     *
     * @return {@code true} if the class was loaded successfully, {@code false}
     * otherwise.
     */
    @Override
    public boolean main() {
        try {
            // Get the current student's name from the service locator.
            String studentName = StudentService.getCurrentStudentName();
            String classFilePath = "src/main/java/students/" + studentName + "/ChatBotSimulation.class";

            // Use custom class loader to load the class.
            CustomClassLoader classLoader = new CustomClassLoader();
            chatBotSimulationClass = classLoader.loadClassFromFile(classFilePath);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    // -- GETTERS --
    /**
     * Returns the dynamically loaded ChatBotSimulation class.
     *
     * @return the loaded {@code Class} object of the `ChatBotSimulation` class.
     */
    public Class<?> getChatBotSimulationClass() {
        return chatBotSimulationClass;
    }

    // -- INNER CLASSES --
    /**
     * Custom class loader to load the `ChatBotSimulation.class` dynamically
     * from a file system.
     */
    private class CustomClassLoader extends ClassLoader {

        /**
         * Loads a class from the file system at the specified file path.
         *
         * @param filePath the path to the `.class` file.
         * @return the loaded {@code Class} object.
         * @throws ClassNotFoundException if the class cannot be loaded from the
         * specified path.
         */
        public Class<?> loadClassFromFile(String filePath) throws ClassNotFoundException {
            try {
                // Create a file object based on the given path.
                File classFile = new File(filePath);
                if (!classFile.exists()) {
                    throw new ClassNotFoundException("Class file not found at: " + filePath);
                }

                // Read the bytes from the class file.
                byte[] classData = new byte[(int) classFile.length()];
                try (FileInputStream fis = new FileInputStream(classFile)) {
                    fis.read(classData);
                }

                // Define the class from the byte array.
                return defineClass(null, classData, 0, classData.length);
            } catch (IOException e) {
                throw new ClassNotFoundException("Error loading class from file: " + filePath, e);
            }
        }
    }
}
