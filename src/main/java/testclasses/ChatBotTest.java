/**
 * @author jalenearmstrong
 * @author ronaldowalker
 * 
 * Test Suite For ChatBot - 41 Tests | Functional : 8 | Structural : 33
 * jalenearmstrong - Functionality Tests + Modified Structural Test For Proxy Enivornment
 * ronaldowalker - Structural Tests
 */

package testclasses;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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
        chatBotCustom = new ChatBotProxy(1);
        chatBotDefault = new ChatBotProxy(0);
    }

    // Structural Tests
    @Test
    public void testAttributeNumResponsesGeneratedExists() {
        try {
            Field field = chatBotDefault.getClass().getDeclaredField("numResponsesGenerated");
            assertNotNull(field);
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'numResponsesGenerated' does not exist in the ChatBot class");
        }
    }

    @Test
    public void testNumResponsesGeneratedIsInt() {
        try {
            Field field = chatBotDefault.getClass().getDeclaredField("numResponsesGenerated");
            assertEquals(int.class, field.getType(), 
                         "The 'numResponsesGenerated' attribute should be of type int");
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'numResponsesGenerated' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testNumResponsesGeneratedIsPrivate() {
        try {
            Field field = chatBotDefault.getClass().getDeclaredField("numResponsesGenerated");
            assertTrue(Modifier.isPrivate(field.getModifiers()), 
                       "The 'numResponsesGenerated' field should be private in the ChatBot class");
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Field 'numResponsesGenerated' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testAttributemessageNumberExists() {
        try {
            Field field = chatBotDefault.getClass().getDeclaredField("messageNumber");
            assertNotNull(field);
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'messageNumber' does not exist in the ChatBot class");
        }
    }

    @Test
    public void testmessageNumberIsInt() {
        try {
            Field field = chatBotDefault.getClass().getDeclaredField("messageNumber");
            assertEquals(int.class, field.getType(), 
                         "The 'messageNumber' attribute should be of type int");
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'messageNumber' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testDefaultChatBotConstructorExists() {
        try {
            Constructor<?> defaultConstructor = chatBotDefault.getClass().getDeclaredConstructor();
            assertNotNull(defaultConstructor);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("default constructor 'ChatBot()' does not exist in the ChatBot class");
        }
    }

    @Test
    public void testDefaultChatBotConstructorIsPublic() {
        try {
            Constructor<?> defaultConstructor = chatBotDefault.getClass().getDeclaredConstructor();
            assertTrue(Modifier.isPublic(defaultConstructor.getModifiers()), 
                       "The 'ChatBot' constructor should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("default constructor 'ChatBot()' does not exist in the ChatBot class", e);
        }
    }

    @Test
    public void testOverloadhatBotConstructorExists() {
        try {
            Constructor<?> defaultConstructor = chatBotDefault.getClass().getDeclaredConstructor(int.class);
            assertNotNull(defaultConstructor);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("overload constructor 'ChatBot(int)' does not exist in the ChatBot class");
        }
    }

    @Test
    public void testOverloadhatBotConstructorIsPublic() {

        try {
            Constructor<?> defaultConstructor = chatBotDefault.getClass().getDeclaredConstructor();
            assertTrue(Modifier.isPublic(defaultConstructor.getModifiers()), 
                       "The 'ChatBot' overload constructor should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("overload constructor 'ChatBot()' does not exist in the ChatBot class", e);
        }
    }

    @Test
    public void testGetChatBotNameMethodExists() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("getChatBotName");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getChatBotName' not found in ChatBot Class.");
        }
    }

    @Test
    public void testGetChatBotNameIsPublic() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("getChatBotName");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'getChatBotName' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getChatBotName' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testGetChatBotNameReturnString() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("getChatBotName");
            assertEquals(String.class, method.getReturnType(),
            "The 'getChatBotName' method should return a String");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getChatBotName' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testGetNumResponsesGeneratedMethodExists() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("getNumResponsesGenerated");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getNumResponsesGenerated' not found in ChatBot Class.");
        }
    }

    @Test
    public void testGetNumResponsesGeneratedIsPublic() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("getNumResponsesGenerated");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'getNumResponsesGenerated' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getNumResponsesGenerated' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testGetNumResponsesGeneratedReturnInt() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("getNumResponsesGenerated");
            assertEquals(int.class, method.getReturnType(),
            "The 'getNumResponsesGenerated' method should return an int");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getNumResponsesGenerated' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testGetTotalNumResponsesGeneratedMethodExists() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("getTotalNumResponsesGenerated");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getTotalNumResponsesGenerated' not found in ChatBot Class.");
        }
    }

    @Test
    public void testGetTotalNumResponsesGeneratedReturnInt() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("getTotalNumResponsesGenerated");
            assertEquals(int.class, method.getReturnType(),
            "The 'getTotalNumResponsesGenerated' method should return an int");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getTotalNumResponsesGenerated' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testGetTotalNumResponsesGeneratedIsPublic() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("getTotalNumResponsesGenerated");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'getTotalNumResponsesGenerated' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getTotalNumResponsesGenerated' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testGetTotalNumMessagesRemainingMethodExists() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("getTotalNumMessagesRemaining");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getTotalNumMessagesRemaining' not found in ChatBot Class.");
        }
    }

    @Test
    public void testGetTotalNumMessagesRemainingReturnInt() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("getTotalNumMessagesRemaining");
            assertEquals(int.class, method.getReturnType(),
            "The 'getTotalNumMessagesRemaining' method should return an Int");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getTotalNumMessagesRemaining' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testGetTotalNumMessagesRemainingIsPublic() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("getTotalNumMessagesRemaining");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'getTotalNumMessagesRemaining' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getTotalNumMessagesRemaining' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testLimitReachedMethodExists() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("limitReached");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'limitReached' not found in ChatBot Class.");
        }
    }

    @Test
    public void testLimitReachedReturnBoolean() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("limitReached");
            assertEquals(boolean.class, method.getReturnType(),
            "The 'limitReached' method should return a boolean");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'limitReached' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testLimitReachedIsPublic() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("limitReached");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'limitReached' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'limitReached' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testGenerateResponseMethodExists() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("generateResponse");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'generateResponse' not found in ChatBot Class.");
        }
    }

    @Test
    public void testGenerateResponseReturnString() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("generateResponse");
            assertEquals(String.class, method.getReturnType(),
            "The 'generateResponse' method should return a boolean");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'generateResponse' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testGenerateResponseIsPublic() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("generateResponse");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'generateResponse' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'generateResponse' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testPromptMethodExists() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("prompt", String.class);
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'prompt' not found in ChatBot Class.");
        }
    }

    @Test
    public void testPromptReturnString() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("prompt", String.class);
            assertEquals(String.class, method.getReturnType(),
            "The 'prompt' method should return a boolean");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'prompt' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testPromptIsPublic() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("prompt", String.class);
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'prompt' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'prompt' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testToStringMethodExists() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("toString");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'toString' not found in ChatBot Class.");
        }
    }

    @Test
    public void testToStringReturnString() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("toString");
            assertEquals(String.class, method.getReturnType(),
            "The 'toString' method should return a boolean");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'toString' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testToStringIsPublic() {
        try {
            Method method = chatBotDefault.getClass().getDeclaredMethod("toString");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'toString' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'toString' should exist in the ChatBot class", e);
        }
    }

    // Functionality Tests

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