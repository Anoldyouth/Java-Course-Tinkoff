package edu.hw5;

import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task4Test {
    static Arguments[] expectations() {
        return new Arguments[]{
                Arguments.of("~", true),
                Arguments.of("!", true),
                Arguments.of("@", true),
                Arguments.of("#", true),
                Arguments.of("$", true),
                Arguments.of("%", true),
                Arguments.of("^", true),
                Arguments.of("&", true),
                Arguments.of("*", true),
                Arguments.of("|", true),
                Arguments.of("test", false),
                Arguments.of("test|", true),
                Arguments.of("test|test", true),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты")
    @MethodSource("expectations")
    void successful(String input, boolean output) {
        assertThat(Task4.validatePassword(input)).isEqualTo(output);
    }
}
