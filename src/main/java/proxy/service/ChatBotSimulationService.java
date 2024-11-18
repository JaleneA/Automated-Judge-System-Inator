package proxy.service;

/**
 * Interface representing a simulation service for chatbot interactions.
 * <p>
 * This interface defines a method for running a main simulation of chatbot
 * behavior.
 * </p>
 *
 * @author jalenearmstrong
 * @see <a href="https://refactoring.guru/design-patterns/proxy">Proxy Design
 * Pattern</a>
 */
public interface ChatBotSimulationService {

    // -- ABSTRACT METHODS --
    /**
     * Executes the main chatbot simulation.
     *
     * @return {@code true} if the simulation ran successfully, {@code false}
     * otherwise.
     */
    boolean main();
}
