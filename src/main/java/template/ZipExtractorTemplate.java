/**
 * @author jalenearmstrong
 * Helpful Documentation
 * https://refactoring.guru/design-patterns/template-method
 */

package template;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class ZipExtractorTemplate {
    public List<File> extract(String baseDirectory, File outputDir) throws IOException {
        // Step Uno: Find The Main Zip Folder
        File mainZipFile = findZipFile(baseDirectory);

        // Step Dos: Extract Main Zip & Collect Student Zip Folders
        List<File> studentZipFiles = extractMainZip(mainZipFile, outputDir);
        return studentZipFiles;
    }

    protected abstract File findZipFile(String baseDirectory) throws IOException;
    protected abstract  List<File> extractMainZip(File mainZipFile, File outputDir) throws IOException;
}