package edu.hw3;

import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task7Test {
    @Test
    @DisplayName("Успешный результат")
    void successful() {
        TreeMap<String, String> tree = new TreeMap<>(new NullComparator());
        tree.put(null, "test");

        assertThat(tree.containsKey(null)).isTrue();
    }
}
