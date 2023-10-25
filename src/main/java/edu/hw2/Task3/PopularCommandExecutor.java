package edu.hw2.Task3;

import edu.hw2.Task3.Interfaces.Connection;
import edu.hw2.Task3.Interfaces.ConnectionManager;
import edu.hw2.Task3.exceptions.ConnectionException;

public final class PopularCommandExecutor {
    /** менеджер получения соединения */
    private final ConnectionManager manager;
    /** максимальное количество попыток исполнение команды */
    private final int maxAttempts;

    /**
     * Конструктор
     *
     * @param manager менеджер получения соединения
     * @param maxAttempts максимальное количество попыток исполнение команды
     */
    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    /**
     * Функция с исполняемой командой
     */
    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    /**
     * Функция для попыток исполнения команды
     *
     * @param command требуемая команда
     */
    void tryExecute(String command) {
        for (int i = 0; i < maxAttempts; i++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);

                return;
            } catch (Exception exception) {
                if (i == maxAttempts - 1) {
                    throw new ConnectionException(exception);
                }
            }
        }
    }
}
