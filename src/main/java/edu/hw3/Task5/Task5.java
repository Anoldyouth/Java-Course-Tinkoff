package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class Task5 {
    private Task5() {
    }

    /**
     * Напишите функцию сортировки, которая принимает массив имен,
     * сортирует их по фамилии по возрастанию/убыванию (ASC/DESC)
     * и возвращает новый массив контактов с заданной сортировкой.
     *
     * @param names массив контактов
     * @param order порядок сортировки
     * @return отсортированные контакты
     */
    public static List<Person> parseContacts(@NotNull Person[] names, SortingOrderEnum order) {
        List<Person> forSort = new ArrayList<>(Arrays.stream(names).toList());

        switch (order) {
            case ASC -> Collections.sort(forSort);
            case DESC -> forSort.sort(Collections.reverseOrder());
            default -> {
            }
        }

        return forSort;
    }
}
