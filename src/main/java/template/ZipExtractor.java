package template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * A concrete implementation of the {@link ZipExtractorTemplate} class that
 * extracts zip files, processes student submissions, and validates file names
 * based on a defined pattern.
 * <p>
 * This class demonstrates the Template Method design pattern, providing the
 * structure for the extraction process while allowing specific behaviours to be
 * defined in the subclass.
 *
 * @see ZipExtractorTemplate
 * @see
 * <a href="https://refactoring.guru/design-patterns/template-method">Template
 * Method Design Pattern</a>
 */
public class ZipExtractor extends ZipExtractorTemplate {

    // -- CLASS VARIABLES --
    /**
     * The path to the log file where skipped student submissions (due to
     * invalid naming conventions) are recorded.
     */
    private static final String LOG_FILE_PATH = "src/student-results/ignored-submissions.txt";

    /**
     * A pattern that represents the regular expression used to identify the
     * main zip file. This zip file contains all the student submissions and
     * must match the specified pattern for it to be processed. The pattern is
     * used to filter zip files when searching the base directory.
     */
    private final String mainZipPattern;

    /**
     * A regular expression pattern used to validate the naming convention of
     * student submission zip files. The convention requires that the student
     * zip files be named in the format: 'FirstName_LastName_StudentID_A1.zip'.
     * If the naming convention is not followed, the file will be skipped during
     * processing.
     */
    private final Pattern studentZipPattern = Pattern.compile("^[A-Za-z]+_[A-Za-z]+_\\d+_A1\\.zip$");

    // -- CONSTRUCTORS --
    /**
     * Constructs a new {@link ZipExtractor} for extracting zip files based on
     * the given pattern.
     *
     * @param mainZipPattern the regular expression pattern used to match main
     * zip file names.
     */
    public ZipExtractor(String mainZipPattern) {
        this.mainZipPattern = mainZipPattern;
    }

    // -- BUSINESS LOGIC METHODS --
    /**
     * Extracts the student ID from the zip file name using the defined naming
     * convention.
     *
     * @param zipFileName the name of the student zip file.
     * @return the student ID extracted from the file name, or null if the ID
     * cannot be extracted.
     */
    public String extractStudentIdFromZipName(String zipFileName) {
        String[] parts = zipFileName.split("_");
        return parts.length > 2 ? parts[2] : null;
    }

    /**
     * Extracts the student name from the zip file name.
     *
     * @param zipFileName the name of the student zip file.
     * @return the student name extracted from the file name, or null if the
     * name cannot be extracted.
     */
    public String extractStudentNameFromZipName(String zipFileName) {
        String[] parts = zipFileName.split("_");

        if (parts.length > 2) {
            String firstName = parts[0].toLowerCase();
            String studentId = parts[2];
            return firstName + "_" + studentId;
        }
        return null;
    }

    /**
     * Extracts Java source code files from a student submission zip.
     * <p>
     * This method maps student IDs to their extracted Java source code files.
     * It validates the naming convention and extracts specific Java files
     * related to the chatbot project.
     *
     * @param studentZipFile the student zip file to extract.
     * @return a map of student IDs to their extracted Java files.
     * @throws IOException if an error occurs during extraction.
     */
    public Map<String, List<File>> extractStudentZip(File studentZipFile) throws IOException {
        Map<String, List<File>> studentFilesMap = new HashMap<>();

        String studentId = extractStudentIdFromZipName(studentZipFile.getName());
        if (studentId == null) {
            System.err.println("Error: Invalid student ID in zip file name.");
            return studentFilesMap;
        }

        String studentName = extractStudentNameFromZipName(studentZipFile.getName());
        List<File> javaFiles = new ArrayList<>();

        File outputDir = new File("src/main/java/students/" + studentName);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(studentZipFile))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().equals("ChatBot.java")
                        || entry.getName().equals("ChatBotPlatform.java")
                        || entry.getName().equals("ChatBotGenerator.java")
                        || entry.getName().equals("ChatBotSimulation.java") && !entry.isDirectory()) {
                    File outputFile = new File(outputDir, entry.getName());
                    File parentDir = outputFile.getParentFile();
                    if (parentDir != null && !parentDir.exists()) {
                        parentDir.mkdirs();
                    }

                    try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, bytesRead);
                        }
                    }
                    javaFiles.add(outputFile);
                    zipInputStream.closeEntry();
                }
            }
        }
        studentFilesMap.put(studentId, javaFiles);
        return studentFilesMap;
    }

    /**
     * Logs messages for files that don't meet the naming convention.
     * <p>
     * This method writes messages to a log file (ignored-submissions.txt)
     * whenever a student zip file does not conform to the expected naming
     * convention.
     *
     * @param message the message to log.
     */
    public static void logMissingFiles(String message) {
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

    // -- OVERRIDDEN METHODS --
    /**
     * Extracts the main zip file and returns a list of student submission zip
     * files.
     *
     * @param mainZipFile the main zip file containing student zip submissions.
     * @param outputDir the directory where the student zips will be stored.
     * @return a list of {@link File} objects representing the student zip
     * files.
     * @throws IOException if an error occurs during extraction.
     */
    @Override
    protected List<File> extractMainZip(File mainZipFile, File outputDir) throws IOException {
        List<File> studentZips = new ArrayList<>();

        File submissionsDir = new File(outputDir, "submissions");
        if (!submissionsDir.exists()) {
            submissionsDir.mkdirs();
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(mainZipFile))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (!entry.isDirectory() && entry.getName().endsWith(".zip")) {
                    String zipFileName = entry.getName();

                    // Skip files that don't follow the naming convention
                    if (!studentZipPattern.matcher(zipFileName).matches()) {
                        String message = "Skipping Folder: " + zipFileName + " (Invalid Naming Convention For Student Submission)\n";
                        System.out.println(message);
                        logMissingFiles(message);
                        continue;
                    }

                    File studentZip = new File(submissionsDir, zipFileName);
                    try (FileOutputStream fileOutputStream = new FileOutputStream(studentZip)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, bytesRead);
                        }
                    }
                    studentZips.add(studentZip);
                }
                zipInputStream.closeEntry();
            }
        }
        return studentZips;
    }

    /**
     * Finds the main zip file in the given directory based on the main zip
     * pattern.
     *
     * @param baseDirectory the directory to search for the zip file.
     * @return the {@link File} object representing the main zip file.
     * @throws IOException if no zip file matching the pattern is found.
     */
    @Override
    protected File findZipFile(String baseDirectory) throws IOException {
        File dir = new File(baseDirectory);
        File[] files = dir.listFiles((d, name) -> name.matches(mainZipPattern));

        if (files == null || files.length == 0) {
            throw new IOException("Main zip file not found in directory: " + baseDirectory);
        }
        return files[0];
    }
}
