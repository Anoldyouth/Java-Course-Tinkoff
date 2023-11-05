package edu.hw3;

import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task8Test {
    @Test
    @DisplayName("Успешный результат")
    void successful() {
        var iter = new BackwardIterator<>(List.of(1,2,3));

        for (int i = 3; i > 0 ; i--) {
            assertThat(iter.next()).isEqualTo(i);
        }
    }
}
