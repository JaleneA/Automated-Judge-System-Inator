/**
 * @author jalenearmstrong
 * Helpful Documentation
 * https://refactoring.guru/design-patterns/facade
 */

package template.facade;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import template.ZipExtractor;
import template.ZipExtractorTemplate;

public class ZipManager {
    private ZipExtractorTemplate zipExtractor;

    public ZipManager(String mainZipPattern) {
        this.zipExtractor = new ZipExtractor(mainZipPattern);
    }

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
                System.out.println("Too Many Student Submissions (" +  studentZipFiles.size() + "). Should Be Atmost 50.");
                System.exit(0);
            } else{
                System.out.println(studentZipFiles.size() + " Student Submissions Found.\n");
            }
        } catch (IOException e) {
            System.err.println("Error during extraction: " + e.getMessage());
            throw e;
        }

        return studentZipFiles;
    }

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

    // Helper Method
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

    public ZipExtractorTemplate getZipExtractor() {
        return zipExtractor;
    }

    public void setZipExtractor(ZipExtractorTemplate zipExtractor) {
        this.zipExtractor = zipExtractor;
    }
}