package group12;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;

public class ChatBotPlatformTest {

    @BeforeAll
    public void testChatBotPlatformClassExists() {

        try {
            Class<?> testClass = Class.forName("group12.ChatBotPlatform");
            assertNotNull(testClass);
        } catch (ClassNotFoundException e) {
            throw new AssertionError("ChatBotPlatform class does not exist in the package 'group12'", e);
        }

    }

    @Test
    public void testAttributeBotsExists() {

        try {Field field = ChatBotPlatform.class.getDeclaredField("bots");
            assertNotNull(field);
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Attribute 'bots' does not exist in the ChatBotPlatform class");
        }

    }

    @Test
    public void testBotsIsArrayListOfChatBot() {
        try {
            Field field = ChatBot.class.getDeclaredField("bots");
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
            throw new AssertionError("Field 'bots' does not exist in ChatBot class", e);
        }
    }

    @Test
    public void testChatBotPlatformConstructorExists() {

        try {
            Constructor<ChatBotPlatform> constructor = ChatBotPlatform.class.getDeclaredConstructor();
            assertNotNull(constructor);
        } catch (NoSuchMethodException  e) {
            throw new AssertionError("Constructor 'ChatBotPlatform()' does not exist in the ChatBotPlatform class");
        }

   }

    @Test
    public void testAddChatBotMethodExists() {

        try {
            Method method = ChatBotPlatform.class.getDeclaredMethod("addChatBot", Integer.class);
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'addChatBot' not found in ChatBotPlatform Class.");
        }

    }

    @Test
    public void testgetChatBotListMethodExists() {

        try {
            Method method = ChatBotPlatform.class.getDeclaredMethod("getChatBotList");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getChatBotList' not found in ChatBotPlatform Class.");
        }

    }

    @Test
    public void testinteractWithBotMethodExists() {

        try {
            Method method = ChatBotPlatform.class.getDeclaredMethod("interactWithBot", Integer.class, String.class);
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'interactWithBot' not found in ChatBotPlatform Class.");
        }

    }

    @Test
    public void testgetBotSizeMethodExists() {

        try {
            Method method = ChatBotPlatform.class.getDeclaredMethod("getBotSize");
            assertNotNull(method);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'getBotSize' not found in ChatBotPlatform Class.");
        }

    }

    
}
