package group12;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class UnzipTest {

    @Test
    public void testUnzip() throws IOException {
        Unzip unzip = new Unzip();
        String src_zip_path = "src/test/resources/816032676_A1.zip";
        String dest_path = "src/test/resources/unzipped";

        if (new File(dest_path).exists()) {
            for (File file : new File(dest_path).listFiles()) {
                file.delete();
            }
        } else {
            Files.createDirectories(Paths.get(dest_path));
        }

        unzip.unzip(src_zip_path, dest_path);

        assertTrue(Files.exists(Paths.get(dest_path, "ChatBot.java")));
        assertTrue(Files.exists(Paths.get(dest_path, "ChatBotGenerator.java")));
        assertTrue(Files.exists(Paths.get(dest_path, "ChatBotPlatform.class")));
        assertTrue(Files.exists(Paths.get(dest_path, "ChatBotSimulation.class")));
    }
}
