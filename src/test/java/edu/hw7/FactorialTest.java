package edu.hw7;

import java.math.BigInteger;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class FactorialTest {
    @Test
    void success() {
        assertThat(Factorial.factorial(15)).isEqualTo(new BigInteger("1307674368000"));
    }
}
