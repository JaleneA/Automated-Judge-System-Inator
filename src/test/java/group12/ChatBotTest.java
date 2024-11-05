package group12;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class ChatBotTest {

    @Test
    public void testAttributeChatBotNameExists() {

        try {
            Field field = ChatBot.class.getDeclaredField("chatBotName");
            assertNotNull(field);
            
        } catch (NoSuchFieldException  e) {
            throw new AssertionError("Attribute 'chatBotName' does not exist in the ChatBot class");
        }

    }

    @Test
    public void testAttributenumResponsesGeneratedExists() {

        try {
            Field field = ChatBot.class.getDeclaredField("numResponsesGenerated");
            assertNotNull(field);
            
        } catch (NoSuchFieldException  e) {
            throw new AssertionError("Attribute 'numResponsesGenerated' does not exist in the ChatBot class");
        }

    }

    @Test
    public void testAttributemessageLimitExists() {

        try {
            Field field = ChatBot.class.getDeclaredField("messageLimit");
            assertNotNull(field);
            
        } catch (NoSuchFieldException  e) {
            throw new AssertionError("Attribute 'messageLimit' does not exist in the ChatBot class");
        }

    }

    @Test
    public void testAttributemessageNumberExists() {

        try {
            Field field = ChatBot.class.getDeclaredField("messageNumber");
            assertNotNull(field);
            
        } catch (NoSuchFieldException  e) {
            throw new AssertionError("Attribute 'messageNumber' does not exist in the ChatBot class");
        }

    }

    @Test
    public void testChatBotMethodExists() {
        try {
            Method method = ChatBot.class.getDeclaredMethod("ChatBot");
            assertNotNull(method);
        } catch (NoSuchMethodException  e) {
            throw new AssertionError("Method 'ChatBot' not found in ChatBot Class.");
        }
    }


    
}
