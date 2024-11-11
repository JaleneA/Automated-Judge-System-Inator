/**
 * @author jalenearmstrong
 * @author ronaldowalker
 * 
 * Test Suite For ChatBotGenerator - 21 Tests | Functional : 3 | Structural : 18
 * jalenearmstrong - Functionality Tests + Modified Structural Test For Proxy Enivornment
 * ronaldowalker - Structural Tests
 */

 package testclasses.full;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import proxy.ChatBotPlatformProxy;
import proxy.service.ChatBotPlatformService;

public class ChatBotPlatformFullTest {
    private ChatBotPlatformService chatBotPlatform;

    @BeforeEach
    public void setUp() {
        chatBotPlatform = new ChatBotPlatformProxy();
    }

    // @Test
    // public void testChatBotPlatformClassIsPublic() {
    //     try {
    //         Class<?> testclass = Class.forName("group12.ChatBotPlatform");
    //         assertTrue(Modifier.isPublic(testclass.getModifiers()), 
    //                    "The 'ChatBotPlatform' class should be public");
    //     } catch (ClassNotFoundException e) {
    //         throw new AssertionError("ChatBotPlatform class does not exist in the package 'group12'", e);
    //     }
    // }

    @Test
    public void testAttributeBotsExists() {
        try {Field field = chatBotPlatform.getClass().getDeclaredField("bots");
            assertNotNull(field);
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'bots' does not exist in the ChatBotPlatform class");
        }
    }

    /*
    @Test
    public void testBotsIsArrayListOfChatBot() {
        try {
            Field field = chatBotPlatform.getClass().getDeclaredField("bots");
            assertEquals(ArrayList.class, field.getType());

            Type genericType = field.getGenericType();

            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;

                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                assertEquals(1, typeArguments.length, "The 'bots' field should have one type parameter");

                assertEquals(ChatBot.class, typeArguments[0]);
            } else {
                throw new AssertionError("The 'bots' field is not of type ChatBot");
            }
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Field 'bots' does not exist in ChatBotPlatform class", e);
        }
    }
    */

    @Test
    public void testBotIsPrivate() {
        try {
            Field field = chatBotPlatform.getClass().getDeclaredField("bots");
            assertTrue(Modifier.isPrivate(field.getModifiers()), 
                       "The 'bots' field should be private in the ChatBotPlatform class");
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Field 'bots' should exist in the ChatBotPlatform class", e);
        }
    }

    /*
    @Test
    public void testChatBotPlatformConstructorExists() {
        try {
            Constructor<ChatBotPlatform> constructor = chatBotPlatform.getClass().getDeclaredConstructor();
            assertNotNull(constructor);
        } catch (NoSuchMethodException  e) {
            throw new AssertionError("Constructor 'ChatBotPlatform()' does not exist in the ChatBotPlatform class");
        }
   }

   @Test
    public void testChatBotPlatformConstructorIsPublic() {
        try {
            Constructor<ChatBotPlatform> constructor = chatBotPlatform.getClass().getDeclaredConstructor();
            assertTrue(Modifier.isPublic(constructor.getModifiers()), 
                       "The 'ChatBotPlatform' constructor should be public in the ChatBotPlatform class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Constructor 'ChatBotPlatform()' does not exist in the ChatBotPlatform class", e);
        }
    }
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

    @Test
    public void testGetBotSizeMethodExists() {
        try {
            Method method = chatBotPlatform.getClass().getDeclaredMethod("getBotSize");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getBotSize' not found in ChatBotPlatform Class.");
        }
    }

    @Test
    public void testGetBotSizeIsPublic() {
        try {
            Method method = chatBotPlatform.getClass().getDeclaredMethod("getBotSize");
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'getBotSize' method should be public in the ChatBotPlatform class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getBotSize' should exist in the ChatBotPlatform class", e);
        }
    }

    @Test
    public void testGetBotSizeReturnInt() {
        try {
            Method method = chatBotPlatform.getClass().getDeclaredMethod("getBotSize");
            assertEquals(int.class, method.getReturnType(),
            "The 'getBotSize' method should return a int");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getBotSize' should exist in the ChatBotPlatform class", e);
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