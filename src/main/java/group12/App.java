package group12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

/**
 * This class contains the {@code main} method, the entry point for the program.
 * <p>
 * Note: Just Run And Witness The Magic ;-)
 * <p>
 * Todo: Split Into Different Classes So It Won't Look So Ugly
 *
 * @author jalenearmstrong
 *
 * @see factory.TestGradingFactory
 * @see template.ZipExtractor
 * @see template.facade.ZipManager
 * @see observer.TestGradingManager
 * @see observer.TestGradingObserver
 * @see servicelocator.StudentService
 */
public class App {

    // -- CONSTANTS --
    /**
     * The relative path to the file containing skipped submission logs.
     */
    private static final String LOG_FILE_PATH = "src/student-results/skipped-submissions.txt";

    // -- BUSINESS LOGIC METHODS --
    /**
     * The main entry point of the application that processes student
     * submissions.
     * <p>
     * This method manages the extraction and processing of ZIP files containing
     * student submissions. It uses the {@code ZipManager} to identify ZIP files
     * matching a specific pattern and the {@code ZipExtractor} to extract and
     * process their contents.
     *
     * <p>
     * Notes:
     * <ul>
     * <li>The method requires the base and output directories to exist prior to
     * execution.</li>
     * <li>ZIP file process expects files to follow a specific naming pattern,
     * defined by {@code mainZipPattern}.</li>
     * <li>Ensure that required files are accurately listed in the
     * {@code requiredFiles} set.</li>
     * </ul>
     * 
     * @param args (This main function does not accept any arguments)
     */
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
                System.out.println("No Student Submissions Found.");
                return;
            }

            processStudentZipFiles(zipExtractor, studentZipFiles, requiredFiles);

        } catch (IOException e) {
            System.err.println("Error: An Error Occurred");
        } finally {
            zipManager.cleanup();
        }
    }

    /**
     * Collects all Java source files (i.e., ".java") in the provided list and
     * parses them to the compileFiles() method.
     *
     * @param javaFiles A list of Java source files to compile.
     */
    public static void compileJavaFiles(List<File> javaFiles) {
        List<String> filePaths = new ArrayList<>();

        for (File javaFile : javaFiles) {
            if (javaFile.getName().endsWith(".java")) {
                filePaths.add(javaFile.getAbsolutePath());
            }
        }
        compileFiles(filePaths);
    }

    /**
     * Writes a message to the log file.
     * <p>
     * Writes the provided message to the log file.
     *
     * @param message The message to be written.
     */
    public static void logSkippedFiles(String message) {
        try {
            File logFile = new File(LOG_FILE_PATH);
            if (!logFile.exists()) {
                logFile.getParentFile().mkdirs();
                logFile.createNewFile();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
                writer.write(message);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    // -- HELPER METHODS --
    /**
     * Compiles a list of Java source files using the `javac` command.
     * <p>
     * This method takes a list of file paths pointing to Java source files and
     * attempts to compile them. It uses a {@link ProcessBuilder} to invoke the
     * `javac` command. Compilation output is streamed to the console, and any
     * errors or exceptions are logged.
     * <p>
     * Notes: Ensure that the `javac` command is available in the system's PATH
     * environment variable before invoking this method.
     *
     * @param filePaths a {@link List} of strings representing the file paths of
     * Java source files to be compiled.
     *
     * @throws IOException if an I/O error occurs during the process execution.
     * @throws InterruptedException if the current thread is interrupted while
     * waiting for the process to complete.
     */
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
            if (exitCode != 0) {
                System.err.println("Compilation Failed.");
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error Compiling Java Files: " + e.getMessage());
        }
    }

    /**
     * Extracts the student's name from the ZIP file name.
     *
     * @param zipFileName The name of the student's zipped submission file.
     * @return The extracted name if successful; {@code null} otherwise.
     */
    private static String extractNameFromZipName(String zipFileName) {
        String[] parts = zipFileName.split("_");

        if (parts.length > 2) {
            String firstName = parts[0].toLowerCase();
            String studentId = parts[2];
            return firstName + "_" + studentId;
        }
        return null;
    }

    /**
     * Extracts zipped files from the base directory into the output directory
     * using a {@link ZipManager}.
     *
     * @param zipManager The ZipManager instance used to extract the files.
     * @param baseDirectory The directory the files are extracted from.
     * @param outputDirectory The directory the files are extracted to.
     * @return a {@link List} of the extracted files.
     * @throws IOException if an I/O error occurs during the process execution.
     */
    private static List<File> extractStudentZipFiles(ZipManager zipManager, String baseDirectory, File outputDirectory) throws IOException {
        return zipManager.extractAndPrepareFiles(baseDirectory, outputDirectory);
    }

    /**
     * Determines if all the required files are included amongst the extracted
     * files.
     *
     * @param extractedFiles A list of the files extracted from the zipped
     * student submission.
     * @param requiredFiles The set of file names that must be present in every
     * submission.
     * @return {@code True} if the extracted files include all required files;
     * {@code False} otherwise.
     */
    private static boolean hasRequiredFiles(List<File> extractedFiles, Set<String> requiredFiles) {
        Set<String> extractedFileNames = extractedFiles.stream()
                .map(File::getName)
                .collect(Collectors.toSet());
        return extractedFileNames.containsAll(requiredFiles);
    }

    private static void modifyJavaFileWithPackage(File javaFile, String studentName) throws IOException {
        List<String> lines = Files.readAllLines(javaFile.toPath());
        String packageDeclaration = "package students." + studentName + ";\n";
        lines.add(0, packageDeclaration);  // Adding Package Declaration At The Top
        Files.write(javaFile.toPath(), lines);
    }

    /**
     * Processes a given list of zipped student submissions.
     * <p>
     * This method extracts the zipped student submissions, logging any skipped
     * files in the process. For each extracted submission, it then checks for
     * the presence of all required files and extracts the student's name from
     * the ZIP file name. All Java source files in the submission are then
     * compiled and grading tests are ran, concluding in a grade report PDF
     * being generated.
     *
     * @param zipExtractor The {@link ZipExtractor} instance used to extract the
     * files from the zipped submission.
     * @param studentZipFiles A {@link List} of zipped student submissions to be
     * extracted.
     * @param requiredFiles A {@link Set} of file names that mus be present in
     * every submission.
     * @throws IOException if an I/O error occurs during the process execution.
     */
    private static void processStudentZipFiles(ZipExtractor zipExtractor, List<File> studentZipFiles, Set<String> requiredFiles) throws IOException {
        for (File studentZip : studentZipFiles) {
            System.out.println("Processing Student Submission: " + studentZip.getName() + ".");

            Map<String, List<File>> studentFilesMap = zipExtractor.extractStudentZip(studentZip);
            for (Map.Entry<String, List<File>> entry : studentFilesMap.entrySet()) {
                String studentId = entry.getKey();
                List<File> extractedFiles = entry.getValue();

                if (!hasRequiredFiles(extractedFiles, requiredFiles)) {
                    String message = "Skipping Tests For Student Submission: " + studentZip.getName() + ". Reason: Missing Required Files.\n";
                    System.out.println(message);
                    logSkippedFiles(message);
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
                System.out.println("Compilation Successful For " + studentZip.getName() + ".\n-> Running Tests...\n");
                runTestsOnJavaFiles(extractedFiles, studentId);
            }
        }
    }

    /**
     * Runs grading tests on a student submission then generates a PDF report
     * compiling the test results.
     *
     * @param extractedFiles A {@link List} of all files extracted from the
     * submission ZIP.
     * @param studentId The ID of the student who owns the submission.
     */
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
        Map<String, Boolean> testResultsMap = new HashMap<>();
        for (TestResult result : studentTestObserver.getTestResults()) {
            testResultsMap.put(result.getTestClassName(), result.isPassed());
        }

        PDFGenerator.generatePDFReport(studentId, testResultsMap);
        studentTestObserver.reset();
    }

    /**
     * Sets the student name in the current submission report using the name in
     * the submission file.
     * <p>
     * Extracts the student's name from their zipped submission file. If the
     * extraction fails (i.e., returns {@code null}), an error message is
     * printed and the name is not set.
     *
     * @param zipFileName The name of the ZIP file containing their submission.
     */
    private static void setStudentNameFromZip(String zipFileName) {
        String studentName = extractNameFromZipName(zipFileName);
        if (studentName != null) {
            StudentService.setCurrentStudentName(studentName);
        } else {
            System.err.println("Invalid filename format. Could not extract student name.");
        }
    }
}
