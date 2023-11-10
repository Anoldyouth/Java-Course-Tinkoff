package edu.hw5;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task1Test {
    static Arguments[] expectations() {
        return new Arguments[]{
                Arguments.of(
                        List.of(
                                "2022-03-12, 20:20 - 2022-03-12, 23:50",
                                "2022-04-01, 21:30 - 2022-04-02, 01:20"
                        ),
                        "3ч 40м"
                ),
                Arguments.of(
                        List.of(
                                "2022-03-12, 20:20 - 2022-03-13, 20:19"
                        ),
                        "23ч 59м"
                ),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты")
    @MethodSource("expectations")
    void successful(List<String> input, String output) {
        assertThat(Task1.averageSessionTime(input)).isEqualTo(output);
    }

    static Arguments[] errors() {
        return new Arguments[]{
                Arguments.of(List.of(
                        "2022-03-12, 20:20 - 2022-11-12, 23:50" // больше суток
                )),
                Arguments.of(List.of(
                        "2022-03-13, 20:20 - 2022-11-12, 23:50" // нарушение порядка
                )),
                Arguments.of(List.of(
                        "2022-03-12, 20:20 - 2022-03-12, 20:20" // ровно сутки
                )),
                Arguments.of(List.of(
                        "2022-03-12, 20:20, 2022-03-12, 20:00" // неверный формат
                )),
        };
    }

    @ParameterizedTest
    @DisplayName("С ошибками")
    @MethodSource("errors")
    void errors(List<String> input) {
        assertThrows(
                IllegalArgumentException.class,
                () -> Task1.averageSessionTime(input)
        );
    }
}
