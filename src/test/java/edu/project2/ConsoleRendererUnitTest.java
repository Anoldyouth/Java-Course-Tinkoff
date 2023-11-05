package edu.project2;

import edu.project2.renderers.ConsoleRenderer;
import edu.project2.solvers.DepthFirstCrawlSolver;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConsoleRendererUnitTest {
    @Test
    @DisplayName("Успешный результат")
    void successful() {
        Cell[][] grid = new Cell[][] {
                {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
                {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
                {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
                {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
                {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
        };
        Maze maze = new Maze(5, 5, grid);

        String rendered = new ConsoleRenderer().render(maze);
        String expected = "██████████\n██  ██████\n██    ████\n████    ██\n██████████\n";

        assertThat(rendered).isEqualTo(expected);
    }

    @Test
    @DisplayName("С отображением пути")
    void notExist() {
        Cell[][] grid = new Cell[][] {
                {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
                {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
                {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
                {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
                {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
        };
        Maze maze = new Maze(5, 5, grid);
        List<Coordinate> coordinates = List.of(
                new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(2, 2),
                new Coordinate(3, 2),
                new Coordinate(3, 3)
        );

        String rendered = new ConsoleRenderer().render(maze, coordinates);
        String expected = "██████████\n██[]██  ██\n██[][]████\n████[][]██\n██████████\n";

        assertThat(rendered).isEqualTo(expected);
    }
}
