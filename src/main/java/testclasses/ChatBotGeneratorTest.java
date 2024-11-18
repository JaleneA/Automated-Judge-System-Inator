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

/**
 * Test suite for {@link ChatBotGeneratorProxy} which implements
 * {@link ChatBotGeneratorService}.
 * <p>
 * This test suite contains 10 tests, comprising 7 functionality tests and 3
 * structural tests. It is designed to verify the correct behavior and structure
 * of the {@link ChatBotGenerator} class within the proxy environment.
 * <p>
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
 * @see ChatBotGeneratorProxy
 * @see ChatBotGeneratorService
 */
public class ChatBotGeneratorTest {

    // -- CLASS VARIABLES --
    private static ChatBotGeneratorService chatBotGenerator;

    // -- BUSINESS LOGIC METHODS --
    /**
     * Setup method to initialize the {@link ChatBotGeneratorProxy} before each
     * test.
     */
    @BeforeEach
    public void setUp() {
        chatBotGenerator = new ChatBotGeneratorProxy();
    }

    // -- STRUCTURAL TESTS --
    /**
     * Tests if the method 'generateChatBotLLM' exists in the
     * {@link ChatBotGeneratorProxy} class.
     *
     * @throws AssertionError if the method does not exist.
     */
    @Test
    public void testGenerateChatBotLLMMethodExists() {
        try {
            Method method = chatBotGenerator.getClass().getDeclaredMethod("generateChatBotLLM", int.class);
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'generateChatBotLLM' with int parameter not found in ChatBotGenerator Class.");
        }
    }

    /**
     * Tests if the 'generateChatBotLLM' method is public in the
     * {@link ChatBotGeneratorProxy} class.
     *
     * @throws AssertionError if the method is not public.
     */
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

    /**
     * Tests if the 'generateChatBotLLM' method returns a String in the
     * {@link ChatBotGeneratorProxy} class.
     * 
     * @throws AssertionError if the method does not return a String.
     */
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

    // -- FUNCTIONALITY TESTS --
    /**
     * Tests the default behavior of 'generateChatBotLLM' with LLM code 0, which
     * should return "ChatGPT-3.5".
     */
    @Test
    public void testGenerateChatBotLLMDefault() {
        String expected = "ChatGPT-3.5";
        String actual = chatBotGenerator.generateChatBotLLM(0);
        assertEquals(expected, actual, "Expected default bot name for LLM code 0.");
    }

    /**
     * Tests the behavior of 'generateChatBotLLM' with LLM code 1, which should
     * return "LLaMa".
     */
    @Test
    public void testGenerateChatBotLLMLLaMa() {
        String expected = "LLaMa";
        String actual = chatBotGenerator.generateChatBotLLM(1);
        assertEquals(expected, actual, "Expected 'LLaMa' for LLM code 1.");
    }

    /**
     * Tests the behavior of 'generateChatBotLLM' with LLM code 2, which should
     * return "Mistral7B".
     */
    @Test
    public void testGenerateChatBotLLMMistral7B() {
        String expected = "Mistral7B";
        String actual = chatBotGenerator.generateChatBotLLM(2);
        assertEquals(expected, actual, "Expected 'Mistral7B' for LLM code 2.");
    }

    /**
     * Tests the behavior of 'generateChatBotLLM' with LLM code 3, which should
     * return "Bard".
     */
    @Test
    public void testGenerateChatBotLLMBard() {
        String expected = "Bard";
        String actual = chatBotGenerator.generateChatBotLLM(3);
        assertEquals(expected, actual, "Expected 'Bard' for LLM code 3.");
    }

    /**
     * Tests the behavior of 'generateChatBotLLM' with LLM code 4, which should
     * return "Claude".
     */
    @Test
    public void testGenerateChatBotLLMClaude() {
        String expected = "Claude";
        String actual = chatBotGenerator.generateChatBotLLM(4);
        assertEquals(expected, actual, "Expected 'Claude' for LLM code 4.");
    }

    /**
     * Tests the behavior of 'generateChatBotLLM' with LLM code 5, which should
     * return "Solar".
     */
    @Test
    public void testGenerateChatBotLLMSolar() {
        String expected = "Solar";
        String actual = chatBotGenerator.generateChatBotLLM(5);
        assertEquals(expected, actual, "Expected 'Solar' for LLM code 5.");
    }

    /**
     * Tests the behavior of 'generateChatBotLLM' with an invalid LLM code,
     * which should return the default value "ChatGPT-3.5".
     */
    @Test
    public void testGenerateChatBotLLMInvalidCode() {
        String expected = "ChatGPT-3.5";
        String actual = chatBotGenerator.generateChatBotLLM(-1);
        assertEquals(expected, actual, "Expected default bot name for an invalid LLM code.");
    }
}
