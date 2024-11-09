package template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ZipExtractor extends ZipExtractorTemplate {
    private final String namingPattern;

    public ZipExtractor(String namingPattern) {
        this.namingPattern = namingPattern;
    }

    @Override
    protected File findZipFile(String baseDirectory) throws IOException {
        File dir = new File(baseDirectory);
        if (!dir.isDirectory()) {
            throw new IOException("Base directory does not exist: " + baseDirectory);
        }

        for (File file : dir.listFiles()) {
            if (file.getName().matches(namingPattern)) {
                return file;
            }
        }
        throw new FileNotFoundException("No zip file found matching the naming pattern: " + namingPattern);
    }
}
