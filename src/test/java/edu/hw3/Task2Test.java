package edu.hw3;

import edu.hw3.exceptions.InvalidStringContentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class Task2Test {
    static Arguments[] expectations() {
        return new Arguments[]{
                Arguments.of("()()()", new String[] {"()", "()", "()"}),
                Arguments.of("((()))", new String[] {"((()))"}),
                Arguments.of("((()))(())()()(()())", new String[] {"((()))", "(())", "()", "()", "(()())"}),
                Arguments.of("((())())(()(()()))", new String[] {"((())())", "(()(()()))"}),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты")
    @MethodSource("expectations")
    void successful(String input, String[] output) throws Exception {
        assertThat(Task2.clusterize(input)).isEqualTo(output);
    }

    @ParameterizedTest
    @ValueSource(strings = {"(", ")", "(()", "((())())(()(()())", "())", "test"})
    @DisplayName("Проверка несбалансированных строк и неверные символы")
    void invalidStringContent(String invalid) {
        assertThrows(
                InvalidStringContentException.class,
                () -> Task2.clusterize(invalid)
        );
    }
}
