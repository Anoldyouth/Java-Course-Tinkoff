package edu.hw2.Task3;

import edu.hw2.Task3.Interfaces.Connection;
import edu.hw2.Task3.Interfaces.ConnectionManager;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    /** Верхняя граница при генерации числа */
    private static final int UPPERBOUND = 5;

    /**
     * Функция, возвращающая соединение
     *
     * @return соединение
     */
    @Override
    public Connection getConnection() {
        if (generateNumber(UPPERBOUND) == 0) {
            return new FaultyConnection();
        }

        return new StableConnection();
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
