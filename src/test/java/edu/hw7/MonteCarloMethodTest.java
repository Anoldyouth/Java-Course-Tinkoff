package edu.hw7;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class MonteCarloMethodTest {
    @Test
    void serial() {
        assertThat(MonteCarloMethod.serial(10_000_000)).isLessThan(3.3).isGreaterThan(3.0);
    }

    @Test
    void parallel() throws InterruptedException {
        assertThat(MonteCarloMethod.parallel(10_000_000, 6)).isLessThan(3.3).isGreaterThan(3.0);
    }
}
