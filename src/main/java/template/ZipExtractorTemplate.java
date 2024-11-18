package template;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Abstract base class that defines the template method for extracting student
 * submission zip files.
 * <p>
 * This class follows the Template Method design pattern. It provides the
 * overall structure for extracting student submissions by defining the steps in
 * the `extract` method. The concrete implementation of specific steps (finding
 * the main zip file and extracting the contents) is left to the subclasses to
 * implement.
 * </p>
 *
 * @author jalenearmstrong
 * @see
 * <a href="https://refactoring.guru/design-patterns/template-method">Template
 * Method Design Pattern</a>
 */
public abstract class ZipExtractorTemplate {

    // -- ABSTRACT METHODS --
    /**
     * Abstract method to find the main zip file that contains student
     * submissions.
     * <p>
     * This method must be implemented by subclasses to locate the specific main
     * zip file based on the directory provided.
     *
     * @param baseDirectory the base directory where the zip files are located.
     * @return the {@link File} representing the main zip file.
     * @throws IOException if the zip file is not found or an error occurs
     * during file lookup.
     */
    protected abstract File findZipFile(String baseDirectory) throws IOException;

    /**
     * Abstract method to extract the main zip file and collect the student zip
     * files from it.
     * <p>
     * This method must be implemented by subclasses to extract files from the
     * main zip file and gather the student zip files into a list.
     *
     * @param mainZipFile the main zip file containing student submissions.
     * @param outputDir the directory where extracted student zip files will be
     * saved.
     * @return a list of {@link File} objects representing the student zip
     * files.
     * @throws IOException if an error occurs during extraction.
     */
    protected abstract List<File> extractMainZip(File mainZipFile, File outputDir) throws IOException;

    // -- CONCRETE METHODS --
    /**
     * The template method that defines the overall extraction process for
     * student submissions.
     * <p>
     * This method orchestrates the process by first finding the main zip file,
     * and then extracting the student submission zip files contained within it.
     * The specific implementation details for finding and extracting the zip
     * files are provided by concrete subclasses.
     *
     * @param baseDirectory the base directory where the zip files are located.
     * @param outputDir the directory where extracted files will be saved.
     * @return a list of {@link File} objects representing the student zip files
     * extracted.
     * @throws IOException if an error occurs during the extraction process.
     */
    public List<File> extract(String baseDirectory, File outputDir) throws IOException {
        // Step Uno: Find The Main Zip Folder
        File mainZipFile = findZipFile(baseDirectory);

        // Step Dos: Extract Main Zip & Collect Student Zip Folders
        List<File> studentZipFiles = extractMainZip(mainZipFile, outputDir);
        return studentZipFiles;
    }
}
