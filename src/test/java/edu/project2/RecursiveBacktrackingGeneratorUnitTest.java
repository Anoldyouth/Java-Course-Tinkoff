package edu.project2;

import edu.hw3.Task1;
import edu.project2.generators.RecursiveBacktrackingGenerator;
import edu.project2.solvers.WidthFirstCrawlSolver;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RecursiveBacktrackingGeneratorUnitTest {
    @Test
    @DisplayName("Успешный результат")
    void successful() {
        Maze maze = new RecursiveBacktrackingGenerator().generate(21, 23);

        assertThat(maze).isNotNull();
        assertThat(maze.grid().length).isEqualTo(21);
        assertThat(maze.grid()[0].length).isEqualTo(23);
        assertThat(maze.height()).isEqualTo(21);
        assertThat(maze.width()).isEqualTo(23);
    }

    static Arguments[] errors() {
        return new Arguments[]{
                Arguments.of(-1, 31),
                Arguments.of(31, -1),
                Arguments.of(30, 29),
                Arguments.of(29, 30),
        };
    }

    @ParameterizedTest
    @DisplayName("Проверка на ввод")
    @MethodSource("errors")
    void withErrors(int height, int width) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new RecursiveBacktrackingGenerator().generate(height, width)
        );
    }
}
