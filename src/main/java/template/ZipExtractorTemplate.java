/**
 * @author jalenearmstrong
 * ZipExtractor-Inator
 * https://www.baeldung.com/java-compress-and-uncompress
 * https://docs.oracle.com/javase/8/docs/api/java/util/zip/package-summary.html
 */

package template;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public abstract class ZipExtractorTemplate {

    // Template Method: Defines The Steps For Extraction
    public final List<File> extract(String baseDirectory, File outputDir) throws IOException {
        File zipFile = findZipFile(baseDirectory); // Step Uno
        return extractZip(zipFile, outputDir); // Step Dos
        }

    protected abstract File findZipFile(String baseDirectory) throws IOException;

    public static List<File> extractZip(File zipFile, File outputDir) throws IOException {
        List<File> extractedFiles = new ArrayList<>();

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (shouldExtract(entry)) {
                    File file = new File(outputDir, entry.getName());
                    if (entry.isDirectory()) {
                        file.mkdirs();
                    } else {
                        writeToFile(zipInputStream, file);
                        extractedFiles.add(file);
                    }
                }
                zipInputStream.closeEntry();
            }
        }
        return extractedFiles;
    }

    protected static boolean shouldExtract(ZipEntry entry) {
        return entry.getName().endsWith(".java");
    }

    private static void writeToFile(InputStream inputStream, File file) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        }
    }
}
