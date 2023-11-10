package edu.hw5;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task8Test {
    static Arguments[] expectationsFirst() {
        return new Arguments[]{
                Arguments.of("101", true),
                Arguments.of("0", true),
                Arguments.of("00", false),
                Arguments.of("0000", false),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты, первое регулярное выражение")
    @MethodSource("expectationsFirst")
    void successfulFirst(String input, boolean output) {
        assertThat(Task8.first(input)).isEqualTo(output);
    }

    static Arguments[] expectationsSecond() {
        return new Arguments[]{
                Arguments.of("011", true),
                Arguments.of("1001", true),
                Arguments.of("0111", false),
                Arguments.of("0", true),
                Arguments.of("101", false),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты, второе регулярное выражение")
    @MethodSource("expectationsSecond")
    void successfulSecond(String input, boolean output) {
        assertThat(Task8.second(input)).isEqualTo(output);
    }

    static Arguments[] expectationsThird() {
        return new Arguments[]{
                Arguments.of("000", true),
                Arguments.of("1010101", true),
                Arguments.of("00", false),
                Arguments.of("1", false),
                Arguments.of("100010001", true),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты, третье регулярное выражение")
    @MethodSource("expectationsThird")
    void successfulThird(String input, boolean output) {
        assertThat(Task8.third(input)).isEqualTo(output);
    }

    static Arguments[] expectationsFourth() {
        return new Arguments[]{
                Arguments.of("11", false),
                Arguments.of("111", false),
                Arguments.of("0110", true),
                Arguments.of("01110", true),
                Arguments.of("100010001", true),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты, четвертое регулярное выражение")
    @MethodSource("expectationsFourth")
    void successfulFourth(String input, boolean output) {
        assertThat(Task8.fourth(input)).isEqualTo(output);
    }

    static Arguments[] expectationsFifth() {
        return new Arguments[]{
                Arguments.of("101", true),
                Arguments.of("10", true),
                Arguments.of("1111", true),
                Arguments.of("1", true),
                Arguments.of("010", false),
                Arguments.of("1001", false),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты, пятое регулярное выражение")
    @MethodSource("expectationsFifth")
    void successfulFifth(String input, boolean output) {
        assertThat(Task8.fifth(input)).isEqualTo(output);
    }

    static Arguments[] expectationsSixth() {
        return new Arguments[]{
                Arguments.of("001", true),
                Arguments.of("0010000", true),
                Arguments.of("01", false),
                Arguments.of("0000", true),
                Arguments.of("111000", false),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты, шестое регулярное выражение")
    @MethodSource("expectationsSixth")
    void successfulSixth(String input, boolean output) {
        assertThat(Task8.sixth(input)).isEqualTo(output);
    }

    static Arguments[] expectationsSeventh() {
        return new Arguments[]{
                Arguments.of("001", true),
                Arguments.of("0101010", true),
                Arguments.of("0110", false),
                Arguments.of("1", true),
                Arguments.of("1011", false),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты, седьмое регулярное выражение")
    @MethodSource("expectationsSeventh")
    void successfulSeventh(String input, boolean output) {
        assertThat(Task8.seventh(input)).isEqualTo(output);
    }
}
