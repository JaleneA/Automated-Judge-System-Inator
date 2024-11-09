/**
 * @author jalenearmstrong
 * Test Suite For ChatBotGenerator
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import proxy.ChatBotGeneratorProxy;
import proxy.service.ChatBotGeneratorService;

public class ChatBotGeneratorTest {
    private ChatBotGeneratorService chatBotGenerator;

    @BeforeEach
    public void setUp() {
        chatBotGenerator = new ChatBotGeneratorProxy();
    }

    @Test
    public void testGenerateChatBotLLMDefault() {
        // Test for Default
        String expected = "ChatGPT-3.5";
        String actual = chatBotGenerator.generateChatBotLLM(0);
        assertEquals(expected, actual, "Expected default bot name for LLM code 0.");
    }

    @Test
    public void testGenerateChatBotLLMLLaMa() {
        // Test for LLM code 1
        String expected = "LLaMa";
        String actual = chatBotGenerator.generateChatBotLLM(1);
        assertEquals(expected, actual, "Expected 'LLaMa' for LLM code 1.");
    }

    @Test
    public void testGenerateChatBotLLMMistral7B() {
        // Test for LLM code 2
        String expected = "Mistral7B";
        String actual = chatBotGenerator.generateChatBotLLM(2);
        assertEquals(expected, actual, "Expected 'Mistral7B' for LLM code 2.");
    }

    @Test
    public void testGenerateChatBotLLMBard() {
        // Test for LLM code 3
        String expected = "Bard";
        String actual = chatBotGenerator.generateChatBotLLM(3);
        assertEquals(expected, actual, "Expected 'Bard' for LLM code 3.");
    }

    @Test
    public void testGenerateChatBotLLMClaude() {
        // Test for LLM code 4
        String expected = "Claude";
        String actual = chatBotGenerator.generateChatBotLLM(4);
        assertEquals(expected, actual, "Expected 'Claude' for LLM code 4.");
    }

    @Test
    public void testGenerateChatBotLLMSolar() {
        // Test for LLM code 5
        String expected = "Solar";
        String actual = chatBotGenerator.generateChatBotLLM(5);
        assertEquals(expected, actual, "Expected 'Solar' for LLM code 5.");
    }

    @Test
    public void testGenerateChatBotLLMInvalidCode() {
        String expected = "ChatGPT-3.5"; // Default value
        String actual = chatBotGenerator.generateChatBotLLM(-1);
        assertEquals(expected, actual, "Expected default bot name for an invalid LLM code.");
    }
}