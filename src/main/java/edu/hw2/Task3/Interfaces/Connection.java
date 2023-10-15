package edu.hw2.Task3.Interfaces;

public interface Connection extends AutoCloseable {
    /**
     * Функция по выполнению команды
     *
     * @param command требуемая команда
     * @throws Exception ошибка при выполнении
     */
    void execute(String command) throws Exception;
}
