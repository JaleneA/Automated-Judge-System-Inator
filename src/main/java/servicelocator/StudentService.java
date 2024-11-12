/**
 * @author jalenearmstrong
 * Helpful Documentation
 * https://www.baeldung.com/java-service-locator-pattern
 */

package servicelocator;

public class StudentService {
    private static String currentStudentName;
 
    public static void setCurrentStudentName(String studentName) {
        currentStudentName = studentName;
    }

    public static String getCurrentStudentName() {
        return currentStudentName;
    }
}
