package edu.hw3;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task1Test {
    static Arguments[] expectations() {
        return new Arguments[]{
                Arguments.of("Hello world!", "Svool dliow!"),
                Arguments.of(
                        "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler",
                        "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
                ),
                Arguments.of("", "")
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты")
    @MethodSource("expectations")
    void successful(String input, String output) throws Exception {
        assertThat(Task1.atbash(input)).isEqualTo(output);
    }
}
