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
        System.out.println("Starting extraction from base directory: " + baseDirectory);

        List<File> studentZipFiles = new ArrayList<>();
        try {
            studentZipFiles = zipExtractor.extract(baseDirectory, outputDirectory);

            if (studentZipFiles.isEmpty()) {
                System.out.println("No student zip files found.");
                return studentZipFiles;
            }

            System.out.println(studentZipFiles.size() + " student zip files found.");
            for (File studentZip : studentZipFiles) {
                System.out.println("Student Zip File: " + studentZip.getName());
            }
        } catch (IOException e) {
            System.err.println("Error during extraction: " + e.getMessage());
            throw e;
        }

        return studentZipFiles;
    }

    public void cleanup() {
        File submissionsFolder = new File("src/main/java/submissions");
        File mainJavaFolder = new File("src/main/java");

        try {
            deleteDirectory(submissionsFolder);
            System.out.println("Deleted submissions folder: " + submissionsFolder.getPath());

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
            
            File[] studentIdFolders = mainJavaFolder.listFiles(File::isDirectory);
            if (studentIdFolders != null) {
                for (File studentFolder : studentIdFolders) {
                    if (isValidStudentFolder(studentFolder)) {
                        deleteDirectory(studentFolder);
                        System.out.println("Deleted student folder: " + studentFolder.getPath());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error during cleanup process.");
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println("Cleanup completed: all extracted files, student folders, and the submissions folder deleted.");
    }
    
    // Helper Method
    private boolean isValidStudentFolder(File folder) {
        String folderName = folder.getName();
        String regex = "^[a-zA-Z]+_\\d+$";
        return folderName.matches(regex);
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