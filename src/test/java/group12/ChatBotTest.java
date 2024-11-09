package group12;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;

public class ChatBotTest {

    @BeforeAll
    public void testChatBotClassExists() {
        try {
            Class<?> testclass = Class.forName("group12.ChatBot");
            assertNotNull(testclass);
        } catch (ClassNotFoundException e) {
            throw new AssertionError("ChatBot class does not exist in the package 'group12'", e);
        }
    }

    @Test
    public void testChatBotClassIsPublic() {

        try {
            Class<?> testclass = Class.forName("group12.ChatBot");
            assertTrue(Modifier.isPublic(testclass.getModifiers()), 
                       "The 'ChatBot' class should be public");
        } catch (ClassNotFoundException e) {
            throw new AssertionError("ChatBot class does not exist in the package 'group12'", e);
        }

    }

    @Test
    public void testAttributeChatBotNameExists() {

        try {
            Field field = ChatBot.class.getDeclaredField("chatBotName");
            assertNotNull(field);

        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'chatBotName' does not exist in the ChatBot class");
        }

    }

    @Test
    public void testChatBotNameIsString() {
        try {
            Field field = ChatBot.class.getDeclaredField("chatBotName");
            assertEquals(String.class, field.getType());
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'chatBotName' should be of the String type", e);
        }
    }

    @Test
    public void testChatBotNameIsPrivate() {

        try {
            Field field = ChatBot.class.getDeclaredField("chatBotName");
            assertTrue(Modifier.isPrivate(field.getModifiers()), 
                       "The 'chatBotName' field should be private in the ChatBot class");
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Field 'chatBotName' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testAttributeNumResponsesGeneratedExists() {

        try {
            Field field = ChatBot.class.getDeclaredField("numResponsesGenerated");
            assertNotNull(field);
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'numResponsesGenerated' does not exist in the ChatBot class");
        }

    }

    @Test
    public void testNumResponsesGeneratedIsInt() {
        try {
            Field field = ChatBot.class.getDeclaredField("numResponsesGenerated");
            assertEquals(int.class, field.getType(), 
                         "The 'numResponsesGenerated' attribute should be of type int");
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'numResponsesGenerated' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testNumResponsesGeneratedIsPrivate() {

        try {
            Field field = ChatBot.class.getDeclaredField("numResponsesGenerated");
            assertTrue(Modifier.isPrivate(field.getModifiers()), 
                       "The 'numResponsesGenerated' field should be private in the ChatBot class");
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Field 'numResponsesGenerated' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testAttributemessageLimitExists() {

        try {
            Field field = ChatBot.class.getDeclaredField("messageLimit");
            assertNotNull(field);

        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'messageLimit' does not exist in the ChatBot class");
        }

    }

    @Test
    public void testMessageLimitIsInt() {
        try {
            Field field = ChatBot.class.getDeclaredField("messageLimit");
            assertEquals(int.class, field.getType(), 
                         "The 'messageLimit' attribute should be of type int");
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'messageLimit' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testMessageLimitIsPrivate() {

        try {
            Field field = ChatBot.class.getDeclaredField("messageLimit");
            assertTrue(Modifier.isPrivate(field.getModifiers()), 
                       "The 'messageLimit' field should be private in the ChatBot class");
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Field 'messageLimit' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testMessageLimitIsStatic() {

        try {
            Field field = ChatBot.class.getDeclaredField("messageLimit");
            assertTrue(Modifier.isStatic(field.getModifiers()));
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'messageLimit' should be static in the ChatBot class");
        }
    }

    @Test
    public void testMessageNumberIsStatic() {

        try {
            Field field = ChatBot.class.getDeclaredField("messageNumber");
            assertTrue(Modifier.isStatic(field.getModifiers()));
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'messageNumber' should be static in the ChatBot class");
        }
    }

    @Test
    public void testAttributemessageNumberExists() {

        try {
            Field field = ChatBot.class.getDeclaredField("messageNumber");
            assertNotNull(field);

        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'messageNumber' does not exist in the ChatBot class");
        }

    }

    @Test
    public void testmessageNumberIsInt() {
        try {
            Field field = ChatBot.class.getDeclaredField("messageNumber");
            assertEquals(int.class, field.getType(), 
                         "The 'messageNumber' attribute should be of type int");
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'messageNumber' should exist in the ChatBot class", e);
        }
    }

    @Test
    public void testMessageNumberIsPrivate() {

        try {
            Field field = ChatBot.class.getDeclaredField("messageNumber");
            assertTrue(Modifier.isPrivate(field.getModifiers()), 
                       "The 'messageNumber' field should be private in the ChatBot class");
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Field 'messageNumber' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testDefaultChatBotConstructorExists() {

        try {
            Constructor<ChatBot> defaultConstructor = ChatBot.class.getDeclaredConstructor();
            assertNotNull(defaultConstructor);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("default constructor 'ChatBot()' does not exist in the ChatBot class");
        }

    }

    @Test
    public void testDefaultChatBotConstructorIsPublic() {

        try {
            Constructor<ChatBot> defaultConstructor = ChatBot.class.getDeclaredConstructor();
            assertTrue(Modifier.isPublic(defaultConstructor.getModifiers()), 
                       "The 'ChatBot' constructor should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("default constructor 'ChatBot()' does not exist in the ChatBot class", e);
        }

    }

    @Test
    public void testOverloadhatBotConstructorExists() {

        try {
            Constructor<ChatBot> defaultConstructor = ChatBot.class.getDeclaredConstructor(int.class);
            assertNotNull(defaultConstructor);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("overload constructor 'ChatBot(int)' does not exist in the ChatBot class");
        }

    }

    @Test
    public void testOverloadhatBotConstructorIsPublic() {

        try {
            Constructor<ChatBot> defaultConstructor = ChatBot.class.getDeclaredConstructor();
            assertTrue(Modifier.isPublic(defaultConstructor.getModifiers()), 
                       "The 'ChatBot' overload constructor should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("overload constructor 'ChatBot()' does not exist in the ChatBot class", e);
        }

    }

    @Test
    public void testGetChatBotNameMethodExists() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("getChatBotName");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getChatBotName' not found in ChatBot Class.");
        }

    }

    @Test
    public void testGetChatBotNameIsPublic() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("getChatBotName");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'getChatBotName' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getChatBotName' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testGetChatBotNameReturnString() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("getChatBotName");
            assertEquals(String.class, method.getReturnType(),
            "The 'getChatBotName' method should return a String");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getChatBotName' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testGetNumResponsesGeneratedMethodExists() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("getNumResponsesGenerated");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getNumResponsesGenerated' not found in ChatBot Class.");
        }

    }

    @Test
    public void testGetNumResponsesGeneratedIsPublic() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("getNumResponsesGenerated");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'getNumResponsesGenerated' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getNumResponsesGenerated' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testGetNumResponsesGeneratedReturnInt() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("getNumResponsesGenerated");
            assertEquals(int.class, method.getReturnType(),
            "The 'getNumResponsesGenerated' method should return an int");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getNumResponsesGenerated' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testGetTotalNumResponsesGeneratedMethodExists() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("getTotalNumResponsesGenerated");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getTotalNumResponsesGenerated' not found in ChatBot Class.");
        }

    }

    @Test
    public void testGetTotalNumResponsesGeneratedIsStatic() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("getTotalNumResponsesGenerated");
            assertTrue(Modifier.isStatic(method.getModifiers()),
            "The 'getTotalNumResponsesGenerated' method should be static in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getTotalNumResponsesGenerated' should be static in the ChatBot class");
        }
    }

    @Test
    public void testGetTotalNumResponsesGeneratedReturnInt() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("getTotalNumResponsesGenerated");
            assertEquals(int.class, method.getReturnType(),
            "The 'getTotalNumResponsesGenerated' method should return an int");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getTotalNumResponsesGenerated' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testGetTotalNumResponsesGeneratedIsPublic() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("getTotalNumResponsesGenerated");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'getTotalNumResponsesGenerated' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getTotalNumResponsesGenerated' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testGetTotalNumMessagesRemainingMethodExists() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("getTotalNumMessagesRemaining");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getTotalNumMessagesRemaining' not found in ChatBot Class.");
        }

    }

    @Test
    public void testGetTotalNumMessagesRemainingIsStatic() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("getTotalNumMessagesRemaining");
            assertTrue(Modifier.isStatic(method.getModifiers()),
            "The 'getTotalNumMessagesRemaining' method should be static in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getTotalNumMessagesRemaining' should be static in the ChatBot class");
        }
    }

    @Test
    public void testGetTotalNumMessagesRemainingReturnInt() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("getTotalNumMessagesRemaining");
            assertEquals(int.class, method.getReturnType(),
            "The 'getTotalNumMessagesRemaining' method should return an Int");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getTotalNumMessagesRemaining' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testGetTotalNumMessagesRemainingIsPublic() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("getTotalNumMessagesRemaining");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'getTotalNumMessagesRemaining' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getTotalNumMessagesRemaining' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testLimitReachedMethodExists() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("limitReached");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'limitReached' not found in ChatBot Class.");
        }

    }

    @Test
    public void testLimitReachedIsStatic() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("limitReached");
            assertTrue(Modifier.isStatic(method.getModifiers()),
            "The 'limitReached' method should be static in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'limitReached' should be static in the ChatBot class");
        }
    }

    @Test
    public void testLimitReachedReturnBoolean() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("limitReached");
            assertEquals(boolean.class, method.getReturnType(),
            "The 'limitReached' method should return a boolean");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'limitReached' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testLimitReachedIsPublic() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("limitReached");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'limitReached' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'limitReached' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testGenerateResponseMethodExists() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("generateResponse");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'generateResponse' not found in ChatBot Class.");
        }

    }

    @Test
    public void testGenerateResponseReturnString() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("generateResponse");
            assertEquals(String.class, method.getReturnType(),
            "The 'generateResponse' method should return a boolean");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'generateResponse' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testGenerateResponseIsPublic() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("generateResponse");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'generateResponse' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'generateResponse' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testPromptMethodExists() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("prompt", String.class);
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'prompt' not found in ChatBot Class.");
        }

    }

    @Test
    public void testPromptReturnString() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("prompt", String.class);
            assertEquals(String.class, method.getReturnType(),
            "The 'prompt' method should return a boolean");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'prompt' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testPromptIsPublic() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("prompt", String.class);
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'prompt' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'prompt' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testToStringMethodExists() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("toString");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'toString' not found in ChatBot Class.");
        }

    }

    @Test
    public void testToStringReturnString() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("toString");
            assertEquals(String.class, method.getReturnType(),
            "The 'toString' method should return a boolean");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'toString' should exist in the ChatBot class", e);
        }

    }

    @Test
    public void testToStringIsPublic() {

        try {
            Method method = ChatBot.class.getDeclaredMethod("toString");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'toString' method should be public in the ChatBot class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'toString' should exist in the ChatBot class", e);
        }

    }

    

}
