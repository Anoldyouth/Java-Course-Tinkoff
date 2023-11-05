package edu.project2;

import edu.project2.solvers.DepthFirstCrawlSolver;
import edu.project2.solvers.WidthFirstCrawlSolver;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DepthFirstCrawlSolverUnitTest {
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

        List<Coordinate> list = new DepthFirstCrawlSolver().solve(maze, new Coordinate(1, 1), new Coordinate(3, 3));

        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("Путь не существует")
    void notExist() {
        Cell[][] grid = new Cell[][] {
                {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
                {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
                {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
                {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
                {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
        };
        Maze maze = new Maze(5, 5, grid);

        List<Coordinate> list = new DepthFirstCrawlSolver().solve(maze, new Coordinate(1, 1), new Coordinate(4, 4));

        assertThat(list).isNull();
    }
}
