/**
 * @author jalenearmstrong
 * ZipExtractor-Inator
 * https://www.baeldung.com/java-compress-and-uncompress
 * https://docs.oracle.com/javase/8/docs/api/java/util/zip/package-summary.html
 */

package group12;

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

public class ZipExtractor {

    public static List<File> extractZip(File zipFile, File outputDir) throws IOException {
        List<File> extractedFiles = new ArrayList<>();

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                File file = new File(outputDir, entry.getName());

                if (entry.isDirectory()) {
                    file.mkdirs();
                } else if (entry.getName().endsWith(".java")) {
                    writeToFile(zipInputStream, file);
                    extractedFiles.add(file);
                }
                zipInputStream.closeEntry();
            }
        }
        return extractedFiles;
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
