package edu.hw5;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task7Test {
    static Arguments[] expectationsFirst() {
        return new Arguments[]{
                Arguments.of("1101010", true),
                Arguments.of("000", true),
                Arguments.of("001", false),
                Arguments.of("0", false),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты, первое регулярное выражение")
    @MethodSource("expectationsFirst")
    void successfulFirst(String input, boolean output) {
        assertThat(Task7.first(input)).isEqualTo(output);
    }

    static Arguments[] expectationsSecond() {
        return new Arguments[]{
                Arguments.of("111", true),
                Arguments.of("010", true),
                Arguments.of("001", false),
                Arguments.of("0", true),
                Arguments.of("1", true),
                Arguments.of("10", false),
                Arguments.of("01", false),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты, второе регулярное выражение")
    @MethodSource("expectationsSecond")
    void successfulSecond(String input, boolean output) {
        assertThat(Task7.second(input)).isEqualTo(output);
    }

    static Arguments[] expectationsThird() {
        return new Arguments[]{
                Arguments.of("111", true),
                Arguments.of("010", true),
                Arguments.of("00", true),
                Arguments.of("1", true),
                Arguments.of("1011010", false),
                Arguments.of("", false),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты, третье регулярное выражение")
    @MethodSource("expectationsThird")
    void successfulThird(String input, boolean output) {
        assertThat(Task7.third(input)).isEqualTo(output);
    }
}
