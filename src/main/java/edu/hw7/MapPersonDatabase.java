package edu.hw7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MapPersonDatabase implements PersonDatabase {
    private static final Map<Integer, Person> MAP = new HashMap<>();
    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();

    /**
     * Добавление человека в список
     *
     * @param person добавляемый человек
     */
    @Override
    public void add(Person person) {
        READ_WRITE_LOCK.writeLock().lock();
        try {
            MAP.put(person.id(), person);
        } finally {
            READ_WRITE_LOCK.writeLock().unlock();
        }
    }

    /**
     * Удалить человека по id
     *
     * @param id идентификатор удаляемого человека
     */
    @Override
    public void delete(int id) {
        READ_WRITE_LOCK.writeLock().lock();
        try {
            MAP.remove(id);
        } finally {
            READ_WRITE_LOCK.writeLock().unlock();
        }
    }

    public Person get(int id) {
        READ_WRITE_LOCK.readLock().lock();
        try {
            return MAP.getOrDefault(id, null);
        } finally {
            READ_WRITE_LOCK.readLock().unlock();
        }
    }

    /**
     * Поиск людей по имени
     *
     * @param name искомое имя
     * @return список найденных людей
     */
    @Override
    public List<Person> findByName(String name) {
        Searching searchByPhone = (Person person) -> Objects.equals(person.name(), name);
        return find(searchByPhone);
    }

    /**
     * Поиск людей по адресу
     *
     * @param address искомый адрес
     * @return список найденных людей
     */
    @Override
    public List<Person> findByAddress(String address) {
        Searching searchByPhone = (Person person) -> Objects.equals(person.address(), address);
        return find(searchByPhone);
    }

    /**
     * Поиск людей по номеру телефона
     *
     * @param phone искомый телефон
     * @return список найденных людей
     */
    @Override
    public List<Person> findByPhone(String phone) {
        Searching searchByPhone = (Person person) -> Objects.equals(person.phoneNumber(), phone);
        return find(searchByPhone);
    }

    /**
     * Поиск людей по заданной lambda-функции
     *
     * @param searching функция поиска
     * @return список найденных людей
     */
    private List<Person> find(Searching searching) {
        List<Person> found;

        READ_WRITE_LOCK.readLock().lock();
        try {
            found = MAP.values().stream().filter(searching::equal).toList();
        } finally {
            READ_WRITE_LOCK.readLock().unlock();
        }

        return found;
    }

    /**
     * Интерфейс для поиска людей по нужному параметру;
     */
    @FunctionalInterface
    private interface Searching {
        /**
         * Функция для проверки, является ли данный человек искомым
         *
         * @param person текущий проверяемый человек
         * @return искомый или нет
         */
        boolean equal(Person person);
    }
}
