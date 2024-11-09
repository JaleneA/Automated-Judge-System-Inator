/**
 * @author jalenearmstrong
 * https://www.javatpoint.com/java-reflection
 * https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html
 * https://www.digitalocean.com/community/tutorials/thread-sleep-java
 * https://www.oracle.com/technical-resources/articles/java/javareflection.html
 */

package group12;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import observer.TestMarking;
import template.facade.ZipManager;

public class App {
    private static TestMarking testMarking;
    private static Map<String, Boolean> testResults = new HashMap<>();
    public static void main(String[] args) throws IOException {
        testMarking = new TestMarking();

         // Zip Handler
        String namingPattern = ".*_A1\\.zip";
        String baseDirectory = "src/test/resources";
        File outputDirectory = new File("src/main/java");

        ZipManager zipManager = new ZipManager(namingPattern);

        try {
            // Extract
            zipManager.extractAndPrepareFiles(baseDirectory, outputDirectory);

            // Run
            runJUnitTests();

            // Generate
            PDFGenerator.generatePDFReport("Test Results", testResults);

        } catch (IOException e) {
            System.err.println("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        } finally {
            // Cleanup
            zipManager.cleanup();
        }
    }

    private static void runJUnitTests() {
        String[] testClasses = {
            "ChatBotPlatformTest",
            "ChatBotTest",
            "ChatBotGeneratorTest"
        };

        waitForClasses(new String[]{
            "ChatBotPlatform",
            "ChatBot",
            "ChatBotGenerator"
        });

        for (String className : testClasses) {
            try {
                Class<?> testClass = Class.forName(className);
                Method[] methods = testClass.getDeclaredMethods();
                Object testInstance = testClass.getDeclaredConstructor().newInstance();

                System.out.println("\nTest Results for " + className + ":");

                Method beforeEachMethod = findBeforeEachMethod(methods);
                if (beforeEachMethod != null) {
                    beforeEachMethod.invoke(testInstance);
                }

                for (Method method : methods) {
                    if (method.isAnnotationPresent(org.junit.jupiter.api.Test.class)) {
                        String testKey = className + "." + method.getName();

                        try {
                            method.invoke(testInstance);
                            testResults.put(testKey, true);
                            testMarking.markTest(true);
                            System.out.println(testKey + ": PASSED");
                        } catch (IllegalAccessException | InvocationTargetException throwable) {
                            testResults.put(testKey, false);
                            testMarking.markTest(false);
                            System.err.println("Test Failed: " + testKey + " - " + throwable.getCause().getMessage());
                        }
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                System.err.println("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        }
        displayAccumulatedTestResults();
    }

    private static void waitForClasses(String[] classNames) {
        boolean allClassesAvailable;
        do {
            allClassesAvailable = true;
            for (String className : classNames) {
                try {
                    Class.forName(className);
                } catch (ClassNotFoundException e) {
                    allClassesAvailable = false;
                    break;
                }
            }
            if (!allClassesAvailable) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        } while (!allClassesAvailable);
    }

    private static Method findBeforeEachMethod(Method[] methods) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(org.junit.jupiter.api.BeforeEach.class)) {
                return method;
            }
        }
        return null;
    }

    private static void displayAccumulatedTestResults() {
        int passedCount = 0;
        int failedCount = 0;

        System.out.println("\nOverall Test Results:");
        for (Map.Entry<String, Boolean> entry : testResults.entrySet()) {
            String testName = entry.getKey();
            boolean result = entry.getValue();
            
            System.out.println(testName + ": " + (result ? "PASSED" : "FAILED"));
            
            if (result) {
                passedCount++;
            } else {
                failedCount++;
            }
        }

        System.out.println("\nTotal Passed Tests: " + passedCount);
        System.out.println("Total Failed Tests: " + failedCount);
    }

    public Map<String, Boolean> getTestResults() {
        return testResults;
    }

    public void setTestResults(Map<String, Boolean> testResults) {
        App.testResults = testResults;
    }
}