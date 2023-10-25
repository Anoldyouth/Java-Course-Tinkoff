package edu.hw2.Task3;

import edu.hw2.Task3.exceptions.ConnectionException;
import edu.hw2.Task3.Interfaces.Connection;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    /** верхняя граница генерации числа */
    public final static int UPPERBOUND = 5;
    /** логгер */
    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * Функция выполнения команды
     *
     * @param command требуемая команда
     */
    @Override
    public void execute(String command) {
        if (generateNumber(UPPERBOUND) == 0) {
            throw new ConnectionException();
        }

        LOGGER.info("Command " + command + " is completed!");
    }

    /**
     * Функция для закрытия соединения
     */
    @Override
    public void close() {
        LOGGER.info("Connection " + this.getClass().getSimpleName() + " is closed");
    }

    /**
     * Функция для генерации случайного числа
     *
     * @param upperbound верхняя граница генерации
     * @return сгенерированное число
     */
    public int generateNumber(int upperbound) {
        return (new Random()).nextInt(upperbound);
    }
}
