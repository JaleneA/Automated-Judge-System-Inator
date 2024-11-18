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

/**
 * Test suite for {@link ChatBotPlatformProxy} which implements
 * {@link ChatBotPlatformService}.
 * <p>
 * This test suite contains 14 tests, consisting of 3 functionality tests and 11
 * structural tests. It is designed to verify the correct behavior and structure
 * of the {@code ChatBotPlatform} class within the proxy environment.
 * <ul>
 * <li><a href=https://github.com/JaleneA>jalenearmstrong</a> is responsible for
 * functionality tests and a modified structural test for proxy
 * environment.</li>
 * <li><a href=https://github.com/ronaldowalker/>ronaldowalker</a> is
 * responsible for structural tests.</li>
 * </ul>
 *
 * @author jalenearmstrong
 * @author ronaldowalker
 * @see ChatBotPlatformProxy
 * @see ChatBotPlatformService
 */
public class ChatBotPlatformTest {

    // -- INSTANCE VARIABLES --
    private ChatBotPlatformService chatBotPlatform;

    // -- BUSINESS LOGIC METHODS --
    /**
     * Setup method to initialize the {@link ChatBotPlatformProxy} before each
     * test.
     */
    @BeforeEach
    public void setUp() {
        chatBotPlatform = new ChatBotPlatformProxy();
    }

    // -- STRUCTURAL TESTS --
    /**
     * Tests if the constructor 'ChatBotPlatform()' exists in the
     * {@code ChatBotPlatformProxy} class.
     *
     * @throws AssertionError if the method does not exist.
     */
    @Test
    public void testChatBotPlatformConstructorExists() {
        try {
            Constructor<?> constructor = chatBotPlatform.getClass().getDeclaredConstructor();
            assertNotNull(constructor);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Constructor 'ChatBotPlatform()' does not exist in the ChatBotPlatform class");
        }
    }

    /**
     * Tests if the constructor 'ChatBotPlatform()' is public in the
     * {@link ChatBotPlatformProxy} class.
     *
     * @throws AssertionError if the method is not public.
     */
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

    /**
     * Tests if the 'addChatBot' method exists in the
     * {@link ChatBotPlatformProxy} class.
     *
     * @throws AssertionError if the method does not exist
     */
    @Test
    public void testAddChatBotMethodExists() {
        try {
            Method method = chatBotPlatform.getClass().getDeclaredMethod("addChatBot", int.class);
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'addChatBot' not found in ChatBotPlatform Class.");
        }
    }

    /**
     * Tests if the 'addChatBot' method is public in the
     * {@link ChatBotPlatformProxy} class.
     *
     * @throws AssertionError if the method is not public.
     */
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

    /**
     * Tests if the 'addChatBot' method returns a boolean in the
     * {@link ChatBotPlatformProxy} class.
     *
     * @throws AssertionError if the method does not return a boolean.
     */
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

    /**
     * Tests if the 'getChatBotList' method exists in the
     * {@link ChatBotPlatformProxy} class.
     *
     * @throws AssertionError if the method does not exist.
     */
    @Test
    public void testGetChatBotListMethodExists() {
        try {
            Method method = chatBotPlatform.getClass().getDeclaredMethod("getChatBotList");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getChatBotList' not found in ChatBotPlatform Class.");
        }
    }

    /**
     * Tests if the 'getChatBotList' method is public in the
     * {@link ChatBotPlatformProxy} class.
     *
     * @throws AssertionError if the method is not public.
     */
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

    /**
     * Tests if the 'getChatBotList' method returns a String in the
     * {@link ChatBotPlatformProxy} class.
     *
     * @throws AssertionError if the method does not return a String.
     */
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

    /**
     * Tests if the 'interactWithBot' method exists in the
     * {@link ChatBotPlatformProxy} class.
     *
     * @throws AssertionError if the method does not exist.
     */
    @Test
    public void testInteractWithBotMethodExists() {
        try {
            Method method = chatBotPlatform.getClass().getDeclaredMethod("interactWithBot", int.class, String.class);
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'interactWithBot' not found in ChatBotPlatform Class.");
        }
    }

    /**
     * Tests if the 'interactWithBot' method is public in the
     * {@link ChatBotPlatformProxy} class.
     *
     * @throws AssertionError if the method is not public.
     */
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

    /**
     * Tests if the 'interactWithBot' method returns a String in the
     * {@link ChatBotPlatformProxy} class.
     *
     * @throws AssertionError if the method does not return a String.
     */
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

    // -- FUNCTIONALITY TESTS --
    /**
     * Tests the behavior of 'getChatBotList' method after adding chatbots with
     * codes 1 and 2.
     */
    @Test
    public void testGetChatBotList() {
        chatBotPlatform.addChatBot(1);
        chatBotPlatform.addChatBot(2);

        String chatBotList = chatBotPlatform.getChatBotList();

        assertNotNull(chatBotList, "ChatBot list should not be null.");
        assertTrue(chatBotList.contains("Your ChatBots"), "ChatBot list should contain 'Your ChatBots' header.");
    }

    /**
     * Tests the interaction with a valid bot after it has been added to the
     * platform.
     */
    @Test
    public void testInteractWithValidBot() {
        chatBotPlatform.addChatBot(1);
        String response = chatBotPlatform.interactWithBot(0, "Hello");
        assertNotNull(response, "Response should not be null");
    }

    /**
     * Tests the interaction with an invalid bot, expecting an error message.
     */
    @Test
    public void testInteractWithInvalidBot() {
        String response = chatBotPlatform.interactWithBot(0, "Hello");
        assertEquals("Incorrect Bot Number (0) Selected. Try Again.", response, "Should indicate invalid bot number.");
    }
}
