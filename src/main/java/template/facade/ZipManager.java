package template.facade;

import java.io.File;
import java.io.IOException;
import java.util.List;

import template.ZipExtractor;
import template.ZipExtractorTemplate;

public class ZipManager {
    private ZipExtractorTemplate zipExtractor;
    private List<File> extractedFiles;

    public ZipManager(String namingPattern) {
        this.zipExtractor = new ZipExtractor(namingPattern);
    }

    public List<File> extractAndPrepareFiles(String baseDirectory, File outputDirectory) throws IOException {
        extractedFiles = zipExtractor.extract(baseDirectory, outputDirectory);
        System.out.println(extractedFiles.size() + " .java files extracted.");
        return extractedFiles;
    }

    public void cleanup() {
        if (extractedFiles != null) {
            for (File file : extractedFiles) {
                if (file.exists() && file.isFile()) {
                    file.delete();
                }
            }
            System.out.println("Extracted files deleted after use.");
        }
    }

    public ZipExtractorTemplate getZipExtractor() {
        return zipExtractor;
    }

    public void setZipExtractor(ZipExtractorTemplate zipExtractor) {
        this.zipExtractor = zipExtractor;
    }
}

