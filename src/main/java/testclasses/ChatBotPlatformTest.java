/**
 * @author jalenearmstrong
 * @author ronaldowalker
 * 
 * Test Suite For ChatBotGenerator - 14 Tests | Functional : 3 | Structural : 11
 * jalenearmstrong - Functionality Tests + Modified Structural Test For Proxy Enivornment
 * ronaldowalker - Structural Tests
 */

package testclasses;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import proxy.ChatBotPlatformProxy;
import proxy.service.ChatBotPlatformService;

public class ChatBotPlatformTest {
    private ChatBotPlatformService chatBotPlatform;

    @BeforeEach
    public void setUp() {
        chatBotPlatform = new ChatBotPlatformProxy();
    }

    // Structural

    @Test
    public void testChatBotPlatformConstructorExists() {
        try {
            Constructor<?> constructor = chatBotPlatform.getClass().getDeclaredConstructor();
            assertNotNull(constructor);
        } catch (NoSuchMethodException  e) {
            throw new AssertionError("Constructor 'ChatBotPlatform()' does not exist in the ChatBotPlatform class");
        }
   }

   @Test
    public void testChatBotPlatformConstructorIsPublic() {
        try {
             Constructor<?> constructor = chatBotPlatform.getClass().getDeclaredConstructor();
            assertTrue(Modifier.isPublic(constructor.getModifiers()), 
                       "The 'ChatBotPlatform' constructor should be public in the ChatBotPlatform class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Constructor 'ChatBotPlatform()' does not exist in the ChatBotPlatform class", e);
        }
    }

    @Test
    public void testAddChatBotMethodExists() {
        try {
            Method method = chatBotPlatform.getClass().getDeclaredMethod("addChatBot", int.class);
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'addChatBot' not found in ChatBotPlatform Class.");
        }
    }

    @Test
    public void testAddChatBotIsPublic() {
        try {
            Method method = chatBotPlatform.getClass().getDeclaredMethod("addChatBot", int.class);
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'addChatBot' method should be public in the ChatBotPlatform class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'addChatBot' should exist in the ChatBotPlatform class", e);
        }
    }

    @Test
    public void testAddChatBotReturnBoolean() {
        try {
            Method method = chatBotPlatform.getClass().getDeclaredMethod("addChatBot", int.class);
            assertEquals(boolean.class, method.getReturnType(),
            "The 'addChatBot' method should return a boolean");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'addChatBot' should exist in the ChatBotPlatform class", e);
        }
    }

    @Test
    public void testGetChatBotListMethodExists() {
        try {
            Method method = chatBotPlatform.getClass().getDeclaredMethod("getChatBotList");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getChatBotList' not found in ChatBotPlatform Class.");
        }
    }

    @Test
    public void testGetChatBotListIsPublic() {
        try {
            Method method = chatBotPlatform.getClass().getDeclaredMethod("getChatBotList");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'getChatBotList' method should be public in the ChatBotPlatform class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getChatBotList' should exist in the ChatBotPlatform class", e);
        }
    }

    @Test
    public void testGetChatBotListReturnString() {
        try {
            Method method = chatBotPlatform.getClass().getDeclaredMethod("getChatBotList");
            assertEquals(String.class, method.getReturnType(),
            "The 'getChatBotList' method should return a string");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getChatBotList' should exist in the ChatBotPlatform class", e);
        }
    }

    @Test
    public void testInteractWithBotMethodExists() {
        try {
            Method method = chatBotPlatform.getClass().getDeclaredMethod("interactWithBot", int.class, String.class);
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'interactWithBot' not found in ChatBotPlatform Class.");
        }
    }

    @Test
    public void testInteractWithBotIsPublic() {
        try {
            Method method = chatBotPlatform.getClass().getDeclaredMethod("interactWithBot", int.class, String.class);
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'interactWithBot' method should be public in the ChatBotPlatform class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'interactWithBot' should exist in the ChatBotPlatform class", e);
        }
    }

    @Test
    public void testInteractWithBotReturnString() {
        try {
            Method method = chatBotPlatform.getClass().getDeclaredMethod("interactWithBot", int.class, String.class);
            assertEquals(String.class, method.getReturnType(),
            "The 'interactWithBot' method should return a string");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'interactWithBot' should exist in the ChatBotPlatform class", e);
        }
    }

    // Functionality Tests
    @Test
    public void testGetChatBotList() {
        chatBotPlatform.addChatBot(1);
        chatBotPlatform.addChatBot(2);
        
        String chatBotList = chatBotPlatform.getChatBotList();
        
        assertNotNull(chatBotList, "ChatBot list should not be null.");
        assertTrue(chatBotList.contains("Your ChatBots"), "ChatBot list should contain 'Your ChatBots' header.");
    }

    @Test
    public void testInteractWithValidBot() {
        chatBotPlatform.addChatBot(1);
        String response = chatBotPlatform.interactWithBot(0, "Hello");
        assertNotNull(response, "Response should not be null");
    }

    @Test
    public void testInteractWithInvalidBot() {
        String response = chatBotPlatform.interactWithBot(0, "Hello");
        assertEquals("Incorrect Bot Number (0) Selected. Try Again.", response, "Should indicate invalid bot number.");
    }
}