package edu.project2.renderers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class ConsoleRenderer implements Renderer {
    /**
     * Преобразование лабиринта в строку для консоли
     *
     * @param maze лабиринт для отображения
     * @return преобразованный лабиринт
     */
    @Override
    public String render(@NotNull Maze maze) {
        StringBuilder rendered = new StringBuilder();

        for (int row = 0; row < maze.height(); row++) {
            for (int col = 0; col < maze.width(); col++) {
                if (maze.grid()[row][col].type() == Cell.Type.PASSAGE) {
                    rendered.append("  ");
                } else {
                    rendered.append("██");
                }
            }
            rendered.append("\n");
        }

        return rendered.toString();
    }

    /**
     * Преобразование лабиринта в строку с нанесением пути прохождения
     *
     * @param maze лабиринт для отображения
     * @param path путь для отображения
     * @return лабиринт с путем прохождения
     */
    @Override
    public String render(@NotNull Maze maze, List<Coordinate> path) {
        if (path == null) {
            return render(maze);
        }

        StringBuilder rendered = new StringBuilder();

        for (int row = 0; row < maze.height(); row++) {
            for (int col = 0; col < maze.width(); col++) {
                if (maze.grid()[row][col].type() == Cell.Type.PASSAGE) {
                    if (path.contains(new Coordinate(row, col))) {
                        rendered.append("[]");
                    } else {
                        rendered.append("  ");
                    }
                } else {
                    rendered.append("██");
                }
            }
            rendered.append("\n");
        }

        return rendered.toString();
    }
}
