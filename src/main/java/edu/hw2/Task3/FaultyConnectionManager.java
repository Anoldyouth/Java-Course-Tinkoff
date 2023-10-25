package edu.hw2.Task3;

import edu.hw2.Task3.Interfaces.Connection;
import edu.hw2.Task3.Interfaces.ConnectionManager;

public class FaultyConnectionManager implements ConnectionManager {
    /**
     * Функция получения соединения
     * @return соединение
     */
    @Override
    public Connection getConnection() {
        return new FaultyConnection();
    }
}
