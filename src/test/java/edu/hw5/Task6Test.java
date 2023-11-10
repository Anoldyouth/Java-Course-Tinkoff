package edu.hw5;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task6Test {
    static Arguments[] expectations() {
        return new Arguments[]{
                Arguments.of("abc", "achfdbaabgabcaabg", true),
                Arguments.of("abc", "a0b0c0", true),
                Arguments.of("abc", "dfghdfhdf", false),
                Arguments.of("[abc]", "[] a basic test []", true),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты")
    @MethodSource("expectations")
    void successful(String substring, String string, boolean output) {
        assertThat(Task6.isSubstring(substring, string)).isEqualTo(output);
    }
}
