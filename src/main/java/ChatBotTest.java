/**
 * @author jalenearmstrong
 * Test Suite For ChatBot
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import proxy.ChatBotProxy;
import proxy.service.ChatBotService;

public class ChatBotTest {

    private ChatBotService chatBotDefault;
    private ChatBotService chatBotCustom;

    @BeforeEach
    public void setUp() {
        chatBotDefault = new ChatBotProxy(0);
        chatBotCustom = new ChatBotProxy(1);
    }

    @Test
    public void testConstructorDefault() {
        assertNotNull(chatBotDefault.getChatBotName(), "ChatBot name should be initialized.");
        assertFalse(chatBotDefault.getChatBotName().isEmpty(), "ChatBot name should not be empty.");
    }

    @Test
    public void testConstructorCustom() {
        assertNotNull(chatBotCustom.getChatBotName(), "ChatBot name should be initialized with custom LLM code.");
        assertFalse(chatBotCustom.getChatBotName().isEmpty(), "ChatBot name should not be empty.");
    }

    @Test
    public void testGetChatBotName() {
        assertTrue(chatBotDefault.getChatBotName().matches("ChatGPT-3.5"), "ChatBot name should be ChatGPT-3.5.");
    }

    @Test
    public void testGetNumResponsesGeneratedInitiallyZero() {
        assertEquals(0, chatBotDefault.getNumResponsesGenerated(), "Number of responses generated should be zero initially.");
    }

    @Test
    public void testStaticMessageLimit() {
        assertEquals(10, chatBotDefault.getMessageLimit(), "Total message limit should be 10.");
    }

    @Test
    public void testLimitReached() {
        for (int i = 0; i < 10; i++) {
            chatBotDefault.prompt("Test message");
        }
        assertTrue(chatBotDefault.limitReached(), "Limit should be reached after 10 messages.");
    }

    @Test
    public void testPromptLimitReachedMessage() {
        for (int i = 0; i < 10; i++) {
            chatBotDefault.prompt("Test message");
        }
        String response = chatBotDefault.prompt("One more message");
        assertEquals("Daily Limit Reached. Wait 24 hours to resume chatbot usage.", response, "After reaching limit, chatbot should return limit reached message.");
    }

    @Test
    public void testToString() {
        String expectedName = chatBotCustom.getChatBotName();
        int expectedNumResponses = chatBotCustom.getNumResponsesGenerated();
        String expectedOutput = "ChatBot Name: " + expectedName + "     " +
                                "Number Messages Used: " + expectedNumResponses + "\n";
        String output = chatBotCustom.toString();
        assertEquals(expectedOutput, output, "toString output should match the expected format.");
    }
}