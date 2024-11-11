/**
 * @author jalenearmstrong
 * @author ronaldowalker
 * 
 * Test Suite For ChatBotGenerator - 13 Tests | Functional : 7 | Structural : 6
 * jalenearmstrong - Functionality Tests + Modified Structural Test For Proxy Enivornment
 * ronaldowalker - Structural Tests
 */

package testclasses;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import proxy.ChatBotGeneratorProxy;
import proxy.service.ChatBotGeneratorService;

public class ChatBotGeneratorTest {
    private static ChatBotGeneratorService chatBotGenerator;

    @BeforeEach
    public void setUp() {
        chatBotGenerator = new ChatBotGeneratorProxy();
    }

    // @Test
    // public void testChatBotGeneratorExists() {
    //     try {
    //         Class<?> testclass = Class.forName("group12.ChatBotGenerator");
    //         assertNotNull(testclass);
    //     } catch (ClassNotFoundException e) {
    //         throw new AssertionError("ChatBotGenerator class does not exist in the package 'group12'", e);
    //     }
    // }

    // @Test
    // public void testChatBotGeneratorIsPublic() {

    //     try {
    //         Class<?> testclass = Class.forName("group12.ChatBotGenerator");
    //         assertTrue(Modifier.isPublic(testclass.getModifiers()), 
    //                    "The 'ChatBotGenerator' class should be public");
    //     } catch (ClassNotFoundException e) {
    //         throw new AssertionError("ChatBotGenerator class does not exist in the package 'group12'", e);
    //     }
    // }

    // Structural Tests

    @Test
    public void testGenerateChatBotLLMMethodExists() {
        try {
            Method method = chatBotGenerator.getClass().getDeclaredMethod("generateChatBotLLM", int.class);
            assertNotNull(method);
        } catch (NoSuchMethodException  e) {
            throw new AssertionError("Method 'generateChatBotLLM' with int parameter not found in ChatBotGenerator Class.");
        }
    }

    @Test
    public void testGenerateChatBotLLMIsPublic() {
        try {
            Method method = chatBotGenerator.getClass().getDeclaredMethod("generateChatBotLLM", int.class);
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'generateChatBotLLM' method should be public in the ChatBotGenerator class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Field 'generateChatBotLLM' should exist in the ChatBotGenerator class", e);
        }

    }

    @Test
    public void testGenerateChatBotLLMIsStatic() {
        try {
            Method method = chatBotGenerator.getClass().getDeclaredMethod("generateChatBotLLM", int.class);
            assertTrue(Modifier.isStatic(method.getModifiers()),
            "The 'generateChatBotLLM' method should be static in the ChatBotGenerator class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("method 'generateChatBotLLM' should be static in the ChatBotGenerator class");
        }
    }

    @Test
    public void testGenerateChatBotLLMReturnString() {
        try {
            Method method = chatBotGenerator.getClass().getDeclaredMethod("generateChatBotLLM", int.class);
            assertEquals(String.class, method.getReturnType(),
            "The 'generateChatBotLLM' method should return a String");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'generateChatBotLLM' should exist in the ChatBotGenerator class", e);
        }
    }

    // Functionality Tests

    @Test
    public void testGenerateChatBotLLMDefault() {
        String expected = "ChatGPT-3.5";
        String actual = chatBotGenerator.generateChatBotLLM(0);
        assertEquals(expected, actual, "Expected default bot name for LLM code 0.");
    }

    @Test
    public void testGenerateChatBotLLMLLaMa() {
        String expected = "LLaMa";
        String actual = chatBotGenerator.generateChatBotLLM(1);
        assertEquals(expected, actual, "Expected 'LLaMa' for LLM code 1.");
    }

    @Test
    public void testGenerateChatBotLLMMistral7B() {
        String expected = "Mistral7B";
        String actual = chatBotGenerator.generateChatBotLLM(2);
        assertEquals(expected, actual, "Expected 'Mistral7B' for LLM code 2.");
    }

    @Test
    public void testGenerateChatBotLLMBard() {
        String expected = "Bard";
        String actual = chatBotGenerator.generateChatBotLLM(3);
        assertEquals(expected, actual, "Expected 'Bard' for LLM code 3.");
    }

    @Test
    public void testGenerateChatBotLLMClaude() {
        String expected = "Claude";
        String actual = chatBotGenerator.generateChatBotLLM(4);
        assertEquals(expected, actual, "Expected 'Claude' for LLM code 4.");
    }

    @Test
    public void testGenerateChatBotLLMSolar() {
        String expected = "Solar";
        String actual = chatBotGenerator.generateChatBotLLM(5);
        assertEquals(expected, actual, "Expected 'Solar' for LLM code 5.");
    }

    @Test
    public void testGenerateChatBotLLMInvalidCode() {
        String expected = "ChatGPT-3.5";
        String actual = chatBotGenerator.generateChatBotLLM(-1);
        assertEquals(expected, actual, "Expected default bot name for an invalid LLM code.");
    }
}