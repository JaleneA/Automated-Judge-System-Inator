/**
 * @author jalenearmstrong
 * Note: App.java Just Run And Witness The Magic, To Split Into Different Classes So It Won't Look So Ugly
 */

package group12;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import factory.TestGrading;
import factory.TestGradingFactory;
import observer.TestGradingManager;
import observer.TestGradingObserver;
import observer.TestResult;
import servicelocator.StudentService;
import template.ZipExtractor;
import template.facade.ZipManager;

public class App {
    public static void main(String[] args) {
        String mainZipPattern = "Submission_Batch_\\d+\\.zip";
        String baseDirectory = "src/main/java/resources";
        File outputDirectory = new File("src/main/java");

        Set<String> requiredFiles = new HashSet<>();
        requiredFiles.add("ChatBotPlatform.java");
        requiredFiles.add("ChatBotSimulation.java");
        requiredFiles.add("ChatBotGenerator.java");
        requiredFiles.add("ChatBot.java");

        ZipManager zipManager = new ZipManager(mainZipPattern);
        ZipExtractor zipExtractor = new ZipExtractor(mainZipPattern);

        try {
            List<File> studentZipFiles = extractStudentZipFiles(zipManager, baseDirectory, outputDirectory);
            if (studentZipFiles.isEmpty()) {
                System.out.println("No student zip files found.");
                return;
            }

            processStudentZipFiles(zipExtractor, studentZipFiles, requiredFiles);

        } catch (IOException e) {
            System.err.println("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        } finally {
            zipManager.cleanup();
        }
    }

        private static List<File> extractStudentZipFiles(ZipManager zipManager, String baseDirectory, File outputDirectory) throws IOException {
            return zipManager.extractAndPrepareFiles(baseDirectory, outputDirectory);
        }

        private static void processStudentZipFiles(ZipExtractor zipExtractor, List<File> studentZipFiles, Set<String> requiredFiles) throws IOException {
        for (File studentZip : studentZipFiles) {
            System.out.println("Processing student zip file: " + studentZip.getName());

            Map<String, List<File>> studentFilesMap = zipExtractor.extractStudentZip(studentZip);
            for (Map.Entry<String, List<File>> entry : studentFilesMap.entrySet()) {
                String studentId = entry.getKey();
                List<File> extractedFiles = entry.getValue();

                if (!hasRequiredFiles(extractedFiles, requiredFiles)) {
                    System.out.println("Skipping tests for student " + studentId + " - missing required files.");
                    continue;
                }

                String studentName = extractNameFromZipName(studentZip.getName());
                if (studentName != null) {
                    setStudentNameFromZip(studentZip.getName());
                } else {
                    System.err.println("Invalid filename format. Could not extract student name.");
                }

                for (File javaFile : extractedFiles) {
                    if (javaFile.getName().endsWith(".java")) {
                        modifyJavaFileWithPackage(javaFile, studentName);
                    }
                }
                compileJavaFiles(extractedFiles);
                runTestsOnJavaFiles(extractedFiles, studentId);
            }
        }
    }

    private static void modifyJavaFileWithPackage(File javaFile, String studentName) throws IOException {
        List<String> lines = Files.readAllLines(javaFile.toPath());
        String packageDeclaration = "package " + studentName + ";\n";
        lines.add(0, packageDeclaration);  // Adding Package Declaration At The Top
        Files.write(javaFile.toPath(), lines);
    }

    private static void setStudentNameFromZip(String zipFileName) {
        String studentName = extractNameFromZipName(zipFileName);
        if (studentName != null) {
            StudentService.setCurrentStudentName(studentName);
        } else {
            System.err.println("Invalid filename format. Could not extract student name.");
        }
    }

    private static String extractNameFromZipName(String zipFileName) {
        String[] parts = zipFileName.split("_");
    
        if (parts.length > 2) {
            String firstName = parts[0].toLowerCase();
            String studentId = parts[2];
            return firstName + "_" + studentId;
        }
        return null;
    }

    private static boolean hasRequiredFiles(List<File> extractedFiles, Set<String> requiredFiles) {
        Set<String> extractedFileNames = extractedFiles.stream()
                .map(File::getName)
                .collect(Collectors.toSet());
        return extractedFileNames.containsAll(requiredFiles);
    }

    public static void compileJavaFiles(List<File> javaFiles) {
        List<String> filePaths = new ArrayList<>();

        for (File javaFile : javaFiles) {
            if (javaFile.getName().endsWith(".java")) {
                filePaths.add(javaFile.getAbsolutePath());
            }
        }
        compileFiles(filePaths);
    }

    private static void compileFiles(List<String> filePaths) {
        try {
            List<String> command = new ArrayList<>();
            command.add("javac");
            command.addAll(filePaths);

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Compilation successful for all files.");
            } else {
                System.err.println("Compilation failed.");
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error compiling Java files: " + e.getMessage());
        }
    }

    private static void runTestsOnJavaFiles(List<File> extractedFiles, String studentId) {
        TestGradingObserver studentTestObserver = TestGradingManager.getGradingObserver();

        for (File javaFile : extractedFiles) {
            if (javaFile.getName().endsWith(".java")) {
                String className = javaFile.getName().replace(".java", "");

                TestGrading testGrading = TestGradingFactory.createTestGrading(studentId, className);
                testGrading.addObserver(studentTestObserver);
                testGrading.runTests(javaFile);
            }
        }

        studentTestObserver.displayTestResults();
        Map<String, Boolean> testResultsMap = new HashMap<>();
        for (TestResult result : studentTestObserver.getTestResults()) {
            testResultsMap.put(result.getTestClassName(), result.isPassed());
        }

       PDFGenerator.generatePDFReport(studentId, testResultsMap);
        System.out.println("Resetting results for student " + studentId);
        studentTestObserver.reset();
    }
}