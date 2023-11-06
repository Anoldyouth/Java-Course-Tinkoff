package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    private Task3() {
    }

    /**
     * На вход подаётся список объектов одного типа. Верните частотный словарь этого списка.
     *
     * @param list список объектов
     * @return частотный словарь
     */
    public static <T> Map<T, Integer> freqDict(List<T> list) {
        Map<T, Integer> freqFDict = new HashMap<>();

        for (T word: list) {
            if (!freqFDict.containsKey(word)) {
                freqFDict.put(word, 1);
                continue;
            }

            freqFDict.put(word, freqFDict.get(word) + 1);
        }

        return freqFDict;
    }
}
