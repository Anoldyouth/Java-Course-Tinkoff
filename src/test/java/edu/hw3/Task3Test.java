package edu.hw3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task3Test {
    static Arguments[] expectations() {
        return new Arguments[]{
                Arguments.of(Arrays.asList("a", "bb", "a", "bb"), new HashMap<>() {{
                    put("bb", 2);
                    put("a", 2);
                }}),
                Arguments.of(Arrays.asList("this", "and", "that", "and"), new HashMap<>() {{
                    put("this", 1);
                    put("that", 1);
                    put("and", 2);
                }}),
                Arguments.of(Arrays.asList("код", "код", "код", "bug"), new HashMap<>() {{
                    put("код", 3);
                    put("bug", 1);
                }}),
                Arguments.of(Arrays.asList(1, 1, 2, 2), new HashMap<>() {{
                    put(1, 2);
                    put(2, 2);
                }}),
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты")
    @MethodSource("expectations")
    <T>
    void successful(List<T> input, HashMap<T, Integer> output) {
        assertThat(Task3.freqDict(input)).isEqualTo(output);
    }
}
