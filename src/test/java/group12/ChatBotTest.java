package group12;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
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
   public void testDefaultChatBotConstructorExists(){

    try {
        Constructor<ChatBot> defaultConstructor = ChatBot.class.getDeclaredConstructor();
        assertNotNull(defaultConstructor);
    } catch (NoSuchMethodException  e) {
        throw new AssertionError("default constructor 'ChatBot()' does not exist in the ChatBot class");
    }

   }

   @Test
   public void testOverloadhatBotConstructorExists(){

    try {
        Constructor<ChatBot> defaultConstructor = ChatBot.class.getDeclaredConstructor(int.class);
        assertNotNull(defaultConstructor);
    } catch (NoSuchMethodException  e) {
        throw new AssertionError("overload constructor 'ChatBot(int)' does not exist in the ChatBot class");
    }
    
   }

   @Test
   public void testGetChatBotNameMethodExists(){

    try {
        Method method = ChatBot.class.getDeclaredMethod("getChatBotName");
        assertNotNull(method);
    } catch (NoSuchMethodException e) {
        throw new AssertionError("Method 'getChatBotName' not found in ChatBot Class.");
    }        

   }

   

    

}
