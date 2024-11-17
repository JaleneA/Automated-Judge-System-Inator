package servicelocator;

/**
 * The StudentService class
 *
 * @author jalenearmstrong
 * @see <a href="https://www.baeldung.com/java-service-locator-pattern">Service
 * Locator Pattern and Java Implementation</a>
 */
public class StudentService {

    // -- INSTANCE VARIABLES
    /**
     *The current student's name.
     */
    private static String currentStudentName;

    // -- GETTERS --
    /**
     * Returns the current student's name.
     *
     * @return The current student's name.
     */
    public static String getCurrentStudentName() {
        return currentStudentName;
    }

    // -- SETTERS --
    /**
     * Sets the current student's name.
     *
     * @param studentName The current student's name.
     */
    public static void setCurrentStudentName(String studentName) {
        currentStudentName = studentName;
    }
}
