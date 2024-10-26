package group12;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {

    public void unzip(String src_zip_path, String dest_path) throws IOException {
        File dir = new File(dest_path);
        if (!dir.exists()) {
            dir.mkdirs();
            setPermissions(Paths.get(dest_path));
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(Paths.get(src_zip_path)))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                Path newPath = Paths.get(dest_path, entry.getName());

                if (entry.isDirectory()) {
                    Files.createDirectories(newPath);
                    setPermissions(newPath);
                } else {
                    Files.createDirectories(newPath.getParent());
                    Files.copy(zipInputStream, newPath);
                }
                zipInputStream.closeEntry();
            }
        }
    }

    private void setPermissions(Path path) {
        try {
            Files.setPosixFilePermissions(path, PosixFilePermissions.fromString("rwxrwxrwx"));
        } catch (UnsupportedOperationException | IOException e) {
            System.out.println("Could not set permissions: " + e.getMessage());
        }
    }
}
