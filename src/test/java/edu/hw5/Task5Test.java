package edu.hw5;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task5Test {
    static Arguments[] expectations() {
        return new Arguments[]{
                Arguments.of("А123ВЕ777", true),
                Arguments.of("О777ОО177", true),
                Arguments.of("123АВЕ777", false),
                Arguments.of("А123ВГ77", false),
                Arguments.of("А123ВЕ7777", false),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты")
    @MethodSource("expectations")
    void successful(String input, boolean output) {
        assertThat(Task5.validateAutoNumber(input)).isEqualTo(output);
    }
}
