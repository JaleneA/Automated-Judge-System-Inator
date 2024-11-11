package group12;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

public class PDFGeneratorTest {
    
    //Hard coded PDF file name
    @Test
    public void testPDFFileCreation() {
        String title = "Test Report";
        Map<String, Boolean> testResults = Map.of("Test1", true, "Test2", false);

        PDFGenerator.generatePDFReport(title, testResults);

        File pdfFile = new File("TestResults.pdf");
        assertTrue(pdfFile.exists(), "The PDF file should have been created.");
        
        pdfFile.delete();
    }
}
