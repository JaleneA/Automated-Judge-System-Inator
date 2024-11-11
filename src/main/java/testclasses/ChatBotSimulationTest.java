/**
 * @author jalenearmstrong
 * @author ronaldowalker
 * 
 * Test Suite For ChatBotSimulation - 4 Tests | Structural : 4
 * jalenearmstrong - Modified Structural Test For Proxy Enivornment
 * ronaldowalker - Structural Tests
 */

package testclasses;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import proxy.ChatBotSimulationProxy;
import proxy.service.ChatBotSimulationService;

public class ChatBotSimulationTest {

    private static ChatBotSimulationService chatBotSimulation;

    @BeforeEach
    public void setUp() {
        chatBotSimulation = new ChatBotSimulationProxy();
        chatBotSimulation.main();
    }

    @Test
    public void testMainMethodExists() {
        try {
            Class<?> chatBotSimulationClass = ((ChatBotSimulationProxy) chatBotSimulation).getChatBotSimulationClass();
            Method method = chatBotSimulationClass.getDeclaredMethod("main", String[].class);
            assertNotNull(method, "The 'main' method should exist in ChatBotSimulation class.");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'main' not found in ChatBotSimulation Class.", e);
        }
    }

    @Test
    public void testMainIsPublic() {
        try {
            Class<?> chatBotSimulationClass = ((ChatBotSimulationProxy) chatBotSimulation).getChatBotSimulationClass();
            Method method = chatBotSimulationClass.getDeclaredMethod("main", String[].class);
            assertTrue(Modifier.isPublic(method.getModifiers()), "The 'main' method should be public in ChatBotSimulation.");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'main' not found in ChatBotSimulation Class.", e);
        }
    }

    @Test
    public void testMainIsStatic() {
        try {
            Class<?> chatBotSimulationClass = ((ChatBotSimulationProxy) chatBotSimulation).getChatBotSimulationClass();
            Method method = chatBotSimulationClass.getDeclaredMethod("main", String[].class);
            assertTrue(Modifier.isStatic(method.getModifiers()), "The 'main' method should be static in ChatBotSimulation.");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'main' not found in ChatBotSimulation Class.", e);
        }
    }

    @Test
    public void testMainReturnsVoid() {
        try {
            Class<?> chatBotSimulationClass = ((ChatBotSimulationProxy) chatBotSimulation).getChatBotSimulationClass();
            Method method = chatBotSimulationClass.getDeclaredMethod("main", String[].class);
            assertEquals(void.class, method.getReturnType(), "The 'main' method should return void in ChatBotSimulation.");
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Method 'main' not found in ChatBotSimulation Class.", e);
        }
    }
}

