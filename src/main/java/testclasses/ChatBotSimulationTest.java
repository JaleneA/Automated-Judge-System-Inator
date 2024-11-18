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

/**
 * Test suite for {@link ChatBotSimulationProxy} which implements
 * {@link ChatBotSimulationService}.
 * <p>
 * This test suite contains 4 structural tests to validate the correct structure
 * and behavior of the {@code ChatBotSimulation} class within the proxy
 * environment.
 * <ul>
 * <li><a href=https://github.com/JaleneA>jalenearmstrong</a> is responsible for
 * the modified structural test for proxy environment.</li>
 * <li><a href=https://github.com/ronaldowalker/>ronaldowalker</a> is
 * responsible for structural tests.</li>
 * </ul>
 *
 * @author jalenearmstrong
 * @author ronaldowalker
 * @see ChatBotSimulationProxy
 * @see ChatBotSimulationService
 */
public class ChatBotSimulationTest {

    // -- CLASS VARIABLES --
    private static ChatBotSimulationService chatBotSimulation;

    // -- BUSINESS LOGIC METHODS --
    /**
     * Setup method to initialize the {@link ChatBotSimulationProxy} and call
     * the main method before each test.
     */
    @BeforeEach
    public void setUp() {
        chatBotSimulation = new ChatBotSimulationProxy();
        chatBotSimulation.main();
    }

    // -- STRUCTURAL TESTS --
    /**
     * Tests if the 'main' method exists in the {@code ChatBotSimulation} class.
     *
     * @throws AssertionError is the method does not exist.
     */
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

    /**
     * Tests if the 'main' method is public in the {@code ChatBotSimulation}
     * class.
     * 
     * @throws AssertionError if the method is not public.
     */
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

    /**
     * Tests if the 'main' method is static in the {@code ChatBotSimulation}
     * class.
     * 
     * @throws AssertionError if the method is not static.
     */
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

    /**
     * Tests if the 'main' method returns void in the {@code ChatBotSimulation}
     * class.
     * 
     * @throws AssertionError if the method does not return void.
     */
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
