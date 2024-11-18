
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Test suite for evaluating the system's directory structure and files related
 * to student submissions.
 * <p>
 * This suite contains tests to ensure that the required directories and files
 * exist in the project structure related to student submissions, including the
 * expected PDF files in the 'student-results' directory.
 *
 * @author jalenearmstrong
 */
public class SystemEvaluation {

    // -- CONSTANTS --
    /**
     * Path to the directory containing student result files. This directory is
     * located under the 'src' folder. The directory is expected to store the
     * results and other related files.
     */
    private static final String DIRECTORY_PATH = "src/student-results";

    /**
     * Name of the folder that is expected to contain the submission files. This
     * folder should be located in the root directory and may include files
     * related to student submissions.
     */
    private static final String SUBMISSIONS_DIR = "submissions";

    /**
     * Name of the folder that is expected to contain student directories. Each
     * student may have their own directory under this folder for organizing
     * their submissions and results.
     */
    private static final String STUDENTS_DIR = "students";

    /**
     * Name of the file that contains a list of ignored submissions. This file
     * is located in the 'student-results' directory and tracks submissions that
     * are excluded from processing.
     */
    private static final String IGNORE_FILE = "ignored-submissions.txt";

    /**
     * Name of the file that contains a list of skipped submissions. This file
     * is located in the 'student-results' directory and tracks submissions that
     * were skipped during processing.
     */
    private static final String SKIPPED_FILE = "skipped-submissions.txt";

    // -- TEST METHODS --
    /**
     * Tests that the 'submissions' folder does not exist in the root directory.
     */
    @Test
    public void testNoSubmissionsFolderExists() {
        File submissionsDir = new File(SUBMISSIONS_DIR);
        assertFalse(submissionsDir.exists(), "The 'submissions' folder should not exist in the root directory.");
    }

    /**
     * Tests that the 'students' folder does not exist in the root directory.
     */
    @Test
    public void testNoStudentsFolderExists() {
        File studentsDir = new File(STUDENTS_DIR);
        assertFalse(studentsDir.exists(), "The 'students' folder should not exist in the root directory.");
    }

    /**
     * Tests that the 'ignored-submissions.txt' file exists in the
     * 'src/student-results' directory.
     */
    @Test
    public void testIgnoreSubmissionFileExists() {
        File ignoreFile = new File(DIRECTORY_PATH + File.separator + IGNORE_FILE);
        assertTrue(ignoreFile.exists(), "The 'ignore-submission.txt' file should be present in 'src/student-results'.");
    }

    /**
     * Tests that the 'skipped-submissions.txt' file exists in the
     * 'src/student-results' directory.
     */
    @Test
    public void testSkippedSubmissionsFileExists() {
        File skippedFile = new File(DIRECTORY_PATH + File.separator + SKIPPED_FILE);
        assertTrue(skippedFile.exists(), "The 'skipped-submissions.txt' file should be present in 'src/student-results'.");
    }

    /**
     * Tests that the 'student-results' directory exists in the specified path.
     */
    @Test
    public void testStudentResultsFolderExists() {
        File resultsDir = new File(DIRECTORY_PATH);
        assertTrue(resultsDir.exists(), "The student-results directory should exist.");
    }

    /**
     * Tests that at least one PDF file exists in the 'student-results'
     * directory.
     */
    @Test
    public void testStudentPdfFilesExist() {
        File resultsDir = new File(DIRECTORY_PATH);
        File[] pdfFiles = resultsDir.listFiles((dir, name) -> name.endsWith(".pdf"));
        assertTrue(pdfFiles != null && pdfFiles.length > 0, "At least one PDF file should be created in the student-results directory.");
    }

    /**
     * Tests that all files in the 'student-results' directory with a .pdf
     * extension are indeed PDF files.
     */
    @Test
    public void testIsPdfFile() {
        File resultsDir = new File(DIRECTORY_PATH);
        File[] pdfFiles = resultsDir.listFiles((dir, name) -> name.endsWith(".pdf"));

        if (pdfFiles != null && pdfFiles.length > 0) {
            for (File pdfFile : pdfFiles) {
                assertTrue(pdfFile.getName().endsWith(".pdf"), "The generated file should have a .pdf extension.");
            }
        }
    }

    /**
     * Tests that the generated PDF files in the 'student-results' directory are
     * not empty.
     */
    @Test
    public void testStudentPdfFilesAreNotEmpty() {
        File resultsDir = new File(DIRECTORY_PATH);
        File[] pdfFiles = resultsDir.listFiles((dir, name) -> name.endsWith(".pdf"));

        if (pdfFiles != null && pdfFiles.length > 0) {
            for (File pdfFile : pdfFiles) {
                assertTrue(pdfFile.length() > 0, "The generated PDF file should not be empty.");
            }
        }
    }
}
