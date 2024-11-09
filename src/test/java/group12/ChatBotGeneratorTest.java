package group12;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;

public class ChatBotGeneratorTest {

    @BeforeAll
    public void testChatBotGeneratorExists() {
        try {
            Class<?> testclass = Class.forName("group12.ChatBotGenerator");
            assertNotNull(testclass);
        } catch (ClassNotFoundException e) {
            throw new AssertionError("ChatBotGenerator class does not exist in the package 'group12'", e);
        }
    }

    @Test
    public void testChatBotGeneratorIsPublic() {

        try {
            Class<?> testclass = Class.forName("group12.ChatBotGenerator");
            assertTrue(Modifier.isPublic(testclass.getModifiers()), 
                       "The 'ChatBotGenerator' class should be public");
        } catch (ClassNotFoundException e) {
            throw new AssertionError("ChatBotGenerator class does not exist in the package 'group12'", e);
        }

    }

    @Test
    public void testGenerateChatBotLLMMethodExists() {
        try {
            Method method = ChatBotGenerator.class.getDeclaredMethod("generateChatBotLLM", int.class);
            assertNotNull(method);
        } catch (NoSuchMethodException  e) {
            throw new AssertionError("Method 'generateChatBotLLM' with int parameter not found in ChatBotGenerator Class.");
        }
    }

    @Test
    public void testGenerateChatBotLLMIsPublic() {

        try {
            Method method = ChatBotGenerator.class.getDeclaredMethod("generateChatBotLLM", int.class);
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'generateChatBotLLM' method should be public in the ChatBotGenerator class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Field 'generateChatBotLLM' should exist in the ChatBotGenerator class", e);
        }

    }

    @Test
    public void testGenerateChatBotLLMIsStatic() {

        try {
            Method method = ChatBotGenerator.class.getDeclaredMethod("generateChatBotLLM", int.class);
            assertTrue(Modifier.isStatic(method.getModifiers()),
            "The 'generateChatBotLLM' method should be static in the ChatBotGenerator class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("method 'generateChatBotLLM' should be static in the ChatBotGenerator class");
        }
    }

    @Test
    public void testGenerateChatBotLLMReturnString() {

        try {
            Method method = ChatBotGenerator.class.getDeclaredMethod("generateChatBotLLM", int.class);
            assertEquals(String.class, method.getReturnType(),
            "The 'generateChatBotLLM' method should return a String");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'generateChatBotLLM' should exist in the ChatBotGenerator class", e);
        }

    }

    @Test
    public void testGenerateChatBotLLM1() {
        assertEquals("LLaMa", ChatBotGenerator.generateChatBotLLM(1));
    }

    @Test
    public void testGenerateChatBotLLM2() {
        assertEquals("Mistral7B", ChatBotGenerator.generateChatBotLLM(2));
    }

    @Test
    public void testGenerateChatBotLLM3() {
        assertEquals("Bard", ChatBotGenerator.generateChatBotLLM(3));
    }

    @Test
    public void testGenerateChatBotLLM4() {
        assertEquals("Claude", ChatBotGenerator.generateChatBotLLM(4));
    }

    @Test
    public void testGenerateChatBotLLM5() {
        assertEquals("Solar", ChatBotGenerator.generateChatBotLLM(5));
    }

    @Test
    public void testGenerateChatBotLLM6() {
        assertEquals("ChatGPT-3.5", ChatBotGenerator.generateChatBotLLM(0));  // Test an unknown code
        assertEquals("ChatGPT-3.5", ChatBotGenerator.generateChatBotLLM(6));  // Test another unknown code
    }
    
}
