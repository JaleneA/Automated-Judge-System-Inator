/**
 * @author jalenearmstrong
 * Test Suite For Evaluating System
 */

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class SystemEvaluation {
    private static final String DIRECTORY_PATH = "src/student-results";
    private static final String SUBMISSIONS_DIR = "submissions";
    private static final String STUDENTS_DIR = "students";
    private static final String IGNORE_FILE = "ignored-submissions.txt";
    private static final String SKIPPED_FILE = "skipped-submissions.txt";

    @Test
    public void testNoSubmissionsFolderExists() {
        File submissionsDir = new File(SUBMISSIONS_DIR);
        assertFalse(submissionsDir.exists(), "The 'submissions' folder should not exist in the root directory.");
    }

    @Test
    public void testNoStudentsFolderExists() {
        File studentsDir = new File(STUDENTS_DIR);
        assertFalse(studentsDir.exists(), "The 'students' folder should not exist in the root directory.");
    }

    @Test
    public void testIgnoreSubmissionFileExists() {
        File ignoreFile = new File(DIRECTORY_PATH + File.separator + IGNORE_FILE);
        assertTrue(ignoreFile.exists(), "The 'ignore-submission.txt' file should be present in 'src/student-results'.");
    }

    @Test
    public void testSkippedSubmissionsFileExists() {
        File skippedFile = new File(DIRECTORY_PATH + File.separator + SKIPPED_FILE);
        assertTrue(skippedFile.exists(), "The 'skipped-submissions.txt' file should be present in 'src/student-results'.");
    }

    @Test
    public void testStudentResultsFolderExists() {
        File resultsDir = new File(DIRECTORY_PATH);
        assertTrue(resultsDir.exists(), "The student-results directory should exist.");
    }

    @Test
    public void testStudentPdfFilesExist() {
        File resultsDir = new File(DIRECTORY_PATH);
        File[] pdfFiles = resultsDir.listFiles((dir, name) -> name.endsWith(".pdf"));
        assertTrue(pdfFiles != null && pdfFiles.length > 0, "At least one PDF file should be created in the student-results directory.");
    }

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