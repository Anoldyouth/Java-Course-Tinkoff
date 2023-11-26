package edu.hw7;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class RaceConditionTest {
    @Test
    void success() {
        assertThat(RaceCondition.count(100, 1000)).isEqualTo(100_000);
    }
}
