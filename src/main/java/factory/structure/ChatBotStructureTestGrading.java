package factory.structure;

import java.io.File;

import factory.full.ChatBotTestGrading;

public class ChatBotStructureTestGrading extends ChatBotTestGrading {

    public ChatBotStructureTestGrading(String studentId) {
        super(studentId);
    }

    @Override
    protected String getFullClassName(File javaFile) {
        return "testclasses.structure." + javaFile.getName().replace(".java", "StructureTest");
    }

    @Override
    public void runTests(File javaFile) {
        super.runTests(javaFile);
    }
}