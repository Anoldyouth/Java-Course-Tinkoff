package edu.hw3;

import edu.hw3.exceptions.WrongInputException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class Task4Test {
    static Arguments[] expectations() {
        return new Arguments[]{
                Arguments.of(2, "II"),
                Arguments.of(12, "XII"),
                Arguments.of(16, "XVI"),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты")
    @MethodSource("expectations")
    void successful(int input, String output) throws Exception {
        assertThat(Task4.convertToRoman(input)).isEqualTo(output);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4000})
    @DisplayName("Проверка на границы")
    void wrongInput(int invalid) {
        assertThrows(
                WrongInputException.class,
                () -> Task4.convertToRoman(invalid)
        );
    }
}
