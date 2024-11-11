/**
 * @author jalenearmstrong
 * Helpful Documentation
 * https://refactoring.guru/design-patterns/template-method
 */

package template;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipExtractor extends ZipExtractorTemplate {
    private final String mainZipPattern;
    private final Pattern studentZipPattern = Pattern.compile("^[A-Za-z]+_[A-Za-z]+_\\d+_A1\\.zip$");

    public ZipExtractor(String mainZipPattern) {
        this.mainZipPattern = mainZipPattern;
    }

    @Override
    protected File findZipFile(String baseDirectory) throws IOException {
        File dir = new File(baseDirectory);
        File[] files = dir.listFiles((d, name) -> name.matches(mainZipPattern));

        if (files == null || files.length == 0) {
            throw new IOException("Main zip file not found in directory: " + baseDirectory);
        }
        return files[0];
    }

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

                    // Skiping Files That Don't Follow The Naming Convention
                    if (!studentZipPattern.matcher(zipFileName).matches()) {
                        System.out.println("Skipping file: " + zipFileName + " (invalid naming convention for student folder)");
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

    public Map<String, List<File>> extractStudentZip(File studentZipFile) throws IOException {
        Map<String, List<File>> studentFilesMap = new HashMap<>();

        String studentId = extractStudentIdFromZipName(studentZipFile.getName());
        if (studentId == null) {
            System.err.println("Error: Invalid student ID in zip file name.");
            return studentFilesMap;
        }

        String studentName = extractStudentNameFromZipName(studentZipFile.getName());
        List<File> javaFiles = new ArrayList<>();

        File outputDir = new File("src/main/java/" + studentName);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(studentZipFile))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().endsWith(".java") && !entry.isDirectory()) {
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

    public String extractStudentIdFromZipName(String zipFileName) {
        String[] parts = zipFileName.split("_");
        return parts.length > 2 ? parts[2] : null;
    }
    public String extractStudentNameFromZipName(String zipFileName) {
        String[] parts = zipFileName.split("_");

        if (parts.length > 2) {
            String firstName = parts[0].toLowerCase();
            String studentId = parts[2];
            return firstName + "_" + studentId;
        }
        return null;
    }
}