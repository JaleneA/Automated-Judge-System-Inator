package group12;

import java.lang.reflect.Field;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
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

    
    
}
