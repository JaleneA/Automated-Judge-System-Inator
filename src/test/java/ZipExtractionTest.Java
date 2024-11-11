package group12;

import org.junit.jupiter.api.*;
import template.facade.ZipManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class ZipManagerTest {

    private static final String BASE_DIR = "testBaseDir";
    private static final String OUTPUT_DIR = "testOutputDir";
    private static final String TEST_ZIP_FILE = BASE_DIR + "/test_A1.zip";
    private ZipManager zipManager;

    @BeforeEach
    void setUp() throws IOException {
        // Set up base and output directories
        Files.createDirectories(new File(BASE_DIR).toPath());
        Files.createDirectories(new File(OUTPUT_DIR).toPath());

        // Create a sample zip file
        createSampleZipFile(TEST_ZIP_FILE);

        // Initialize ZipManager with a naming pattern to match test_A1.zip
        zipManager = new ZipManager(".*_A1\\.zip");
    }

    @AfterEach
    void tearDown() throws IOException {
        // Delete test directories and files
        deleteDirectory(new File(BASE_DIR));
        deleteDirectory(new File(OUTPUT_DIR));
    }

    @Test
    void testExtractAndPrepareFiles() throws IOException {
        File outputDir = new File(OUTPUT_DIR);
        List<File> extractedFiles = zipManager.extractAndPrepareFiles(BASE_DIR, outputDir);

        // Verify files were extracted
        assertFalse(extractedFiles.isEmpty(), "No files were extracted.");
        assertTrue(outputDir.listFiles().length > 0, "Output directory is empty.");
    }

    @Test
    void testCleanup() throws IOException {
        File outputDir = new File(OUTPUT_DIR);
        zipManager.extractAndPrepareFiles(BASE_DIR, outputDir);

        // Verify files exist before cleanup
        assertTrue(outputDir.listFiles().length > 0, "No files to clean up.");

        // Perform cleanup
        zipManager.cleanup();

        // Verify files are deleted after cleanup
        assertEquals(0, outputDir.listFiles().length, "Extracted files were not deleted.");
    }

    // Helper method to create a sample zip file
    private void createSampleZipFile(String zipFilePath) throws IOException {
        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            ZipEntry entry = new ZipEntry("Sample.java");
            zipOut.putNextEntry(entry);
            zipOut.write("public class Sample {}".getBytes());
            zipOut.closeEntry();
        }
    }

    // Helper method to delete a directory recursively
    private void deleteDirectory(File directory) throws IOException {
        if (directory.exists()) {
            Files.walk(directory.toPath())
                    .map(java.nio.file.Path::toFile)
                    .forEach(File::delete);
        }
    }
}
