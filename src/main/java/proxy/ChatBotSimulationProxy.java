/**
 * @author jalenearmstrong
 * Helpful Documentation
 * https://www.baeldung.com/java-classloaders
 * https://refactoring.guru/design-patterns/proxy
 */

 package proxy;

 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;

 import proxy.service.ChatBotSimulationService;
 import servicelocator.StudentService;
 
 public class ChatBotSimulationProxy implements ChatBotSimulationService {
 
     private Class<?> chatBotSimulationClass;
 
     @Override
     public boolean main() {
         try {
             String studentName = StudentService.getCurrentStudentName();
             String classFilePath = "src/main/java/" + studentName + "/ChatBotSimulation.class";
             CustomClassLoader classLoader = new CustomClassLoader();
             chatBotSimulationClass = classLoader.loadClassFromFile(classFilePath);
             return true;
         } catch (ClassNotFoundException e) {
             return false;
         }
     }

     public Class<?> getChatBotSimulationClass() {
         return chatBotSimulationClass;
     }
 
     private class CustomClassLoader extends ClassLoader {
         public Class<?> loadClassFromFile(String filePath) throws ClassNotFoundException {
             try {
                 File classFile = new File(filePath);
                 if (!classFile.exists()) {
                     throw new ClassNotFoundException("Class file not found at: " + filePath);
                 }
 
                 byte[] classData = new byte[(int) classFile.length()];
                 try (FileInputStream fis = new FileInputStream(classFile)) {
                     fis.read(classData);
                 }
                 return defineClass(null, classData, 0, classData.length);
             } catch (IOException e) {
                 throw new ClassNotFoundException("Error loading class from file: " + filePath, e);
             }
         }
     }
 }
 