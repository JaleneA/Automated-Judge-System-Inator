package factory.structure;

import java.io.File;

import factory.full.ChatBotPlatformTestGrading;

public class ChatBotPlatformStructureTestGrading extends ChatBotPlatformTestGrading {

    public ChatBotPlatformStructureTestGrading(String studentId) {
        super(studentId);
    }

    @Override
    protected String getFullClassName(File javaFile) {
        String testing = "testclasses.structure." + javaFile.getName().replace(".java", "StructureTest");
        System.out.println(testing);
        return testing;
    }

    @Override
    public void runTests(File javaFile) {
        super.runTests(javaFile);
    }
}