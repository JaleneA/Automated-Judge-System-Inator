package template.facade;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import template.ZipExtractor;
import template.ZipExtractorTemplate;

/**
 * Facade class for managing zip file extraction, student submission
 * preparation, and cleanup tasks.
 * <p>
 * This class serves as a simplified interface to the `ZipExtractor` and
 * `ZipExtractorTemplate` classes, providing high-level methods for extracting
 * student submissions, managing files, and performing cleanup operations after
 * grading.
 *
 * @author jalenearmstrong
 * @see <a href="https://refactoring.guru/design-patterns/facade">Facade Design
 * Pattern</a>
 */
public class ZipManager {

    // -- CLASS VARIABLES --
    private ZipExtractorTemplate zipExtractor;

    // -- CONSTRUCTORS --
    /**
     * Constructor that initializes the ZipManager with a main zip pattern.
     *
     * @param mainZipPattern the pattern used by the {@link ZipExtractor} to
     * find zip files.
     */
    public ZipManager(String mainZipPattern) {
        this.zipExtractor = new ZipExtractor(mainZipPattern);
    }

    // -- BUSINESS LOGIC METHODS --
    /**
     * Performs the cleanup operation after grading by deleting student-related
     * files and directories.
     * <p>
     * The method deletes the `submissions` and `students` directories and
     * removes any `.java` files that are not test files from the main Java
     * directory.
     * <p>
     * After the cleanup, a message indicating that grading has been completed
     * is printed.
     */
    public void cleanup() {
        File submissionsFolder = new File("src/main/java/submissions");
        File studentsFolder = new File("src/main/java/students");
        File mainJavaFolder = new File("src/main/java");

        try {
            deleteDirectory(submissionsFolder);
            deleteDirectory(studentsFolder);

            File[] files = mainJavaFolder.listFiles((dir, name) -> name.endsWith(".java") && !name.endsWith("Test.java"));
            if (files != null) {
                for (File file : files) {
                    if (file.delete()) {
                        System.out.println("Deleted file: " + file.getPath());
                    } else {
                        System.err.println("Failed to delete file: " + file.getPath());
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error during cleanup process.");
        }

        System.out.println("Grading Completed! Grade Reports Are Now Available :D.");
    }

    /**
     * Extracts and prepares student submission files from the specified base
     * directory into the output directory.
     * <p>
     * The method extracts zip files, checks for valid submission counts, and
     * prepares the list of extracted files. If there are too many submissions
     * (more than 50), the process will terminate.
     *
     * @param baseDirectory the directory containing the zip files to extract.
     * @param outputDirectory the directory where the extracted files will be
     * stored.
     * @return a list of extracted {@link File} objects representing the student
     * submissions.
     * @throws IOException if an error occurs during the extraction process.
     */
    public List<File> extractAndPrepareFiles(String baseDirectory, File outputDirectory) throws IOException {
        System.out.println("Automated-Judge-System-Inator: Commencing Extraction\n");

        List<File> studentZipFiles = new ArrayList<>();
        try {
            studentZipFiles = zipExtractor.extract(baseDirectory, outputDirectory);

            if (studentZipFiles.isEmpty()) {
                System.out.println("No Student Submissions Found.");
                return studentZipFiles;
            }
            if (studentZipFiles.size() > 50) {
                System.out.println("Too Many Student Submissions (" + studentZipFiles.size() + "). Should Be Atmost 50.");
                System.exit(0);
            } else {
                System.out.println(studentZipFiles.size() + " Student Submissions Found.\n");
            }
        } catch (IOException e) {
            System.err.println("Error during extraction: " + e.getMessage());
            throw e;
        }

        return studentZipFiles;
    }

    // -- HELPER METHODS --
    /**
     * Deletes the specified directory and all of its contents (including
     * subdirectories and files).
     *
     * @param file the directory or file to be deleted.
     */
    private void deleteDirectory(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File child : file.listFiles()) {
                    deleteDirectory(child);
                }
            }
            file.delete();
        }
    }

    // -- GETTERS --
    /**
     * Returns the current {@link ZipExtractorTemplate} being used by this
     * `ZipManager`.
     *
     * @return the {@link ZipExtractorTemplate} instance.
     */
    public ZipExtractorTemplate getZipExtractor() {
        return zipExtractor;
    }

    // -- SETTERS --
    /**
     * Sets the {@link ZipExtractorTemplate} to be used by this `ZipManager`.
     *
     * @param zipExtractor the {@link ZipExtractorTemplate} to set.
     */
    public void setZipExtractor(ZipExtractorTemplate zipExtractor) {
        this.zipExtractor = zipExtractor;
    }
}
