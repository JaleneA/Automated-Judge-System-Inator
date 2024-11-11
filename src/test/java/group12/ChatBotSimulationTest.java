package group12;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;

public class ChatBotSimulationTest {

    @BeforeAll
    public void testChatBotSimulationExists() {
        try {
            Class<?> testclass = Class.forName("group12.ChatBotSimulation");
            assertNotNull(testclass);
        } catch (ClassNotFoundException e) {
            throw new AssertionError("ChatBotSimulation class does not exist in the package 'group12'", e);
        }
    }

    @Test
    public void testChatBotSimulationIsPublic() {

        try {
            Class<?> testclass = Class.forName("group12.ChatBotSimulation");
            assertTrue(Modifier.isPublic(testclass.getModifiers()), 
                       "The 'ChatBotSimulation' class should be public");
        } catch (ClassNotFoundException e) {
            throw new AssertionError("ChatBotSimulation class does not exist in the package 'group12'", e);
        }

    }

    @Test
    public void testMainMethodExists() {
        try {
            Method method = ChatBotSimulation.class.getDeclaredMethod("main", String[].class);
            assertNotNull(method);
        } catch (NoSuchMethodException  e) {
            throw new AssertionError("Method 'main' not found in ChatBotSimulation Class.");
        }
    }

    @Test
    public void testMainIsPublic() {

        try {
            Method method = ChatBotSimulation.class.getDeclaredMethod("main", String[].class);
            assertTrue(Modifier.isPublic(method.getModifiers()), 
                       "The 'main' method should be public in the ChatBotSimulation class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'main' should exist in the ChatBotSimulation class", e);
        }

    }

    @Test
    public void testMainIsStatic() {

        try {
            Method method = ChatBotSimulation.class.getDeclaredMethod("main", String[].class);
            assertTrue(Modifier.isStatic(method.getModifiers()),
            "The 'main' method should be static in the ChatBotSimulation class");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("method 'main' should exist in the ChatBotSimulation class");
        }
    }

    @Test
    public void testMainReturnsVoid() {

        try {
            Method method = ChatBotSimulation.class.getDeclaredMethod("main", String[].class);
            assertEquals(void.class, method.getReturnType(),
            "The 'main' method should not return anyhting");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'main' should exist in the ChatBotSimulation class", e);
        }

    }

    
}
