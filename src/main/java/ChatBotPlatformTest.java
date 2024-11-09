/**
 * @author jalenearmstrong
 * Test Suite For ChatBotPlatform
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import proxy.ChatBotPlatformProxy;
import proxy.service.ChatBotPlatformService;

// Needs Work

public class ChatBotPlatformTest {

    private ChatBotPlatformService chatBotPlatform;

    @BeforeEach
    public void setUp() {
        chatBotPlatform = new ChatBotPlatformProxy();
    }

    @Test
    public void testAddChatBotSuccessfully() {
        boolean result = chatBotPlatform.addChatBot(1);
        assertTrue(result, "Should successfully add a ChatBot.");
    }

    @Test
    public void testGetChatBotList() {
        chatBotPlatform.addChatBot(1);
        chatBotPlatform.addChatBot(2);
        
        String chatBotList = chatBotPlatform.getChatBotList();
        assertTrue(chatBotList.contains("Your ChatBots"), "ChatBot list should contain 'Your ChatBots' header.");
    }

    @Test
    public void testInteractWithValidBot() {
        chatBotPlatform.addChatBot(1);
        String response = chatBotPlatform.interactWithBot(0, "Hello");
        assertNotNull(response);
    }

    @Test
    public void testInteractWithInvalidBot() {
        String response = chatBotPlatform.interactWithBot(0, "Hello");
        assertEquals("Incorrect Bot Number (0) Selected. Try Again.", response, "Should indicate invalid bot number.");
    }
}