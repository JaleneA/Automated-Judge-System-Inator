/**
 * @author jalenearmstrong
 * Test Suite For Evaluating System
 */

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class SystemEvaluation {
    private static final String DIRECTORY_PATH = "src/student-results";

    // PDF Generation Testing
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