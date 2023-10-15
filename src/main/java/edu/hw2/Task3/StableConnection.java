package edu.hw2.Task3;

import edu.hw2.Task3.Interfaces.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    /** логгер */
    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * Функция выполнения команды
     *
     * @param command требуемая команда
     */
    @Override
    public void execute(String command) {
        LOGGER.info("Command " + command + " is completed!");
    }

    /**
     * Функция закрытия соединения
     */
    @Override
    public void close() {
        LOGGER.info("Connection " + this.getClass().getSimpleName() + " is closed");
    }
}
