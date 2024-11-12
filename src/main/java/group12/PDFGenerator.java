/**
 * @author jalenearmstrong
 * PDF Generator-Inator
 * Adapted From: https://www.vogella.com/tutorials/JavaPDF/article.html
 * Note: Still To Modify
 */

 package group12;

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.util.Date;
 import java.util.Map;
 
 import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
 import com.itextpdf.text.Document;
 import com.itextpdf.text.DocumentException;
 import com.itextpdf.text.Element;
 import com.itextpdf.text.Font;
 import com.itextpdf.text.Paragraph;
 import com.itextpdf.text.Phrase;
 import com.itextpdf.text.pdf.PdfPCell;
 import com.itextpdf.text.pdf.PdfPTable;
 import com.itextpdf.text.pdf.PdfWriter;
 
 public class PDFGenerator {
     private static final Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
     private static final Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.RED);
     private static final Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
     private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

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
        }
    }

    private static void addMetaData(Document document) {
        document.addTitle("Submission Results");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Automated Judge System");
        document.addCreator("Automated Judge System");
    }

    
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
    }

     private static void addContent(Document document) throws DocumentException {
         Anchor anchor = new Anchor("Test Results", catFont);
         anchor.setName("Test Results");
 
         Chapter title = new Chapter(new Paragraph(anchor), 1);
         document.add(title);
     }

     private static void addTestResults(Document document, Map<String, Boolean> testResults) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 2);
        paragraph.add(new Paragraph("Test Results:", boldFont));
        addEmptyLine(paragraph, 1);
    
        PdfPTable table = new PdfPTable(2);

        PdfPCell c1 = new PdfPCell(new Phrase("Test Name"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
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

     private static void addEmptyLine(Paragraph paragraph, int number) {
         for (int i = 0; i < number; i++) {
             paragraph.add(new Paragraph(" "));
         }
     }
 } 
