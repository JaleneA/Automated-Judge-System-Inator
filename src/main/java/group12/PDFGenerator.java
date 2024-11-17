package group12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * The {@link PDFGenerator} class generates a PDF that organizes the submission
 * test results into an human-readable format.
 * <p>
 * Todo: Still To Modify
 *
 * @author jalenearmstrong (Editor)
 * @see <a href=https://www.vogella.com/tutorials/JavaPDF/article.html>Creating
 * PDF with Java and iText - Tutorial</a>
 */
public class PDFGenerator {

    // -- CONSTANTS --
    /**
     * Font for main titles
     */
    private static final Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    /**
     * Font for failed test results.
     */
    private static final Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.RED);
    /**
     * Font for bold text.
     */
    private static final Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    /**
     * Font for smaller bold text.
     */
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    // -- BUSINESS LOGIC METHODS --
    /**
     * Generates a PDF report for a student with their test results.
     * <p>
     * The report includes metadata, a title page, content section, and a table
     * of test results. The PDF is saved in a specified directory.
     *
     * @param studentId the student ID for whom the report is being generated
     * @param testResults a Map containing test names as keys and boolean values
     * indicating whether each test passed (true) or failed (false)
     */
    public static void generatePDFReport(String studentId, Map<String, Boolean> testResults) {
        String directoryPath = "src/student-results";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String FILE = directoryPath + File.separator + studentId + "_GradeReport.pdf";

        int totalTests = testResults.size();
        int totalPassed = (int) testResults.values().stream().filter(result -> result).count();
        int totalFailed = totalTests - totalPassed;

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();

            addMetaData(document);
            addTitlePage(document, studentId, totalTests, totalPassed, totalFailed);
            addContent(document);
            addTestResults(document, testResults);

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            // Handle exception
        }
    }

    // -- HELPER METHODS --
    /**
     * Adds the main content section to the provided {@link Document} object.
     *
     * @param document the document to which the content will be added.
     * @throws DocumentException if there is an error while adding the content.
     */
    private static void addContent(Document document) throws DocumentException {
        Paragraph title = new Paragraph("Test Feedback", catFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
    }

    /**
     * Appends the given number of empty lines to the end of the given
     * {@link Paragraph} object.
     *
     * @param paragraph The paragraph to which the empty lines will be added.
     * @param number The number of empty lines to add.
     */
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    /**
     * Adds meta data to the given {@link Document} object.
     * <p>
     * Adds metadata to the provided Document object. The metadata includes the
     * title, subject, keywords, author, and creator of the document. This
     * information can be useful for document management and search.
     *
     * @param document The document to which the meta data will be added.
     */
    private static void addMetaData(Document document) {
        document.addTitle("Submission Results");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Jalene Armstrong");
        document.addCreator("Automated Judge System-Inator");
    }

    /**
     * Adds a test results table to the provided {@link Document} object. The
     * table includes test names and their corresponding results, formatted with
     * appropriate styles for passed and failed tests.
     *
     * @param document The document object to which the test results will be
     * added.
     * @param testResults The test results which will be added to the document
     * object.
     * @throws DocumentException if there is an error while adding the test
     * results to the document.
     */
    private static void addTestResults(Document document, Map<String, Boolean> testResults) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 2);
        paragraph.add(new Paragraph("Test Results:", boldFont));
        addEmptyLine(paragraph, 1);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{3, 1});

        PdfPCell c1 = new PdfPCell(new Phrase("Test Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Result"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        for (Map.Entry<String, Boolean> entry : testResults.entrySet()) {
            table.addCell(entry.getKey());
            Phrase resultPhrase;
            if (entry.getValue()) {
                resultPhrase = new Phrase("PASSED", boldFont);
            } else {
                resultPhrase = new Phrase("FAILED", redFont);
            }
            PdfPCell resultCell = new PdfPCell(resultPhrase);
            resultCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(resultCell);
        }
        document.add(paragraph);
        document.add(table);
    }

    /**
     * Adds a title page to the provided {@link Document} object.
     * <p>
     * The title page includes the university name, course title and code,
     * assignment name, student ID, and a test result summary. Additionally, it
     * includes information about the report's generation, including time and
     * date of generation and the username of the device generated it.
     *
     * @param document The document to which the title page will be added.
     * @param studentId The ID of the student the report is about.
     * @param totalTests The total number of tests performed.
     * @param totalPassed The total number of tests passed.
     * @param totalFailed The total number of tests failed.
     * @throws DocumentException if there is an error while adding the title
     * page to the document.
     */
    private static void addTitlePage(Document document, String studentId, int totalTests, int totalPassed, int totalFailed) throws DocumentException {
        Paragraph titlePage = new Paragraph();

        Paragraph universityParagraph = new Paragraph("THE UNIVERSITY OF THE WEST INDIES, ST AUGUSTINE", catFont);
        universityParagraph.setAlignment(Element.ALIGN_CENTER);
        titlePage.add(universityParagraph);

        Paragraph courseParagraph = new Paragraph("COMP 2603 OBJECT ORIENTED PROGRAMMING 1", catFont);
        courseParagraph.setAlignment(Element.ALIGN_CENTER);
        titlePage.add(courseParagraph);

        Paragraph assignmentParagraph = new Paragraph("ASSIGNMENT 1 GRADE SHEET", catFont);
        assignmentParagraph.setAlignment(Element.ALIGN_CENTER);
        titlePage.add(assignmentParagraph);

        addEmptyLine(titlePage, 2);

        Paragraph studentIdParagraph = new Paragraph("Student ID: " + studentId, boldFont);
        studentIdParagraph.setAlignment(Element.ALIGN_CENTER);
        titlePage.add(studentIdParagraph);

        addEmptyLine(titlePage, 1);

        Paragraph testSummaryParagraph = new Paragraph("Test Summary", boldFont);
        testSummaryParagraph.setAlignment(Element.ALIGN_CENTER);
        titlePage.add(testSummaryParagraph);

        Paragraph totalTestsParagraph = new Paragraph("Total Tests: " + totalTests, smallBold);
        totalTestsParagraph.setAlignment(Element.ALIGN_CENTER);
        titlePage.add(totalTestsParagraph);

        Paragraph totalPassedParagraph = new Paragraph("Total Passed: " + totalPassed, smallBold);
        totalPassedParagraph.setAlignment(Element.ALIGN_CENTER);
        titlePage.add(totalPassedParagraph);

        Paragraph totalFailedParagraph = new Paragraph("Total Failed: " + totalFailed, smallBold);
        totalFailedParagraph.setAlignment(Element.ALIGN_CENTER);
        titlePage.add(totalFailedParagraph);

        addEmptyLine(titlePage, 3);

        Paragraph reportInfoParagraph = new Paragraph("Report Generated By: " + System.getProperty("user.name") + ", " + new Date(), smallBold);
        reportInfoParagraph.setAlignment(Element.ALIGN_CENTER);
        titlePage.add(reportInfoParagraph);

        document.add(titlePage);
        document.newPage();
    }
}
