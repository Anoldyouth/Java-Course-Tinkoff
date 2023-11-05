package edu.project2.solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Direction;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DepthFirstCrawlSolver implements Solver {
    private Maze maze;

    /**
     * Решение лабиринта методом поиска в глубину
     *
     * @param maze лабиринт
     * @param start стартовая клетка
     * @param end конечная клетка
     * @return путь решения или null, если не найден путь
     */
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Set<Coordinate> visited = new HashSet<>();
        Map<Coordinate, Coordinate> parentMap = new HashMap<>();
        this.maze = maze;

        deeper(start, end, visited, parentMap);

        if (!visited.contains(end)) {
            return null;
        }

        return constructPath(parentMap, start, end);
    }

    /**
     * Рекурсивное погружение в глубину
     *
     * @param current текущая клетка
     * @param end конечная клетка
     * @param visited посещенные клетки
     * @param parentMap текущий найденный путь в виде связей
     */
    private void deeper(
            Coordinate current,
            Coordinate end,
            Set<Coordinate> visited,
            Map<Coordinate, Coordinate> parentMap
    ) {
        int row = current.row();
        int col = current.col();

        visited.add(current);

        if (current.equals(end)) {
            return;
        }

        for (Direction direction : Direction.values()) {
            int newCol = col + direction.dx;
            int newRow = row + direction.dy;
            Coordinate newCoordinate = new Coordinate(newRow, newCol);

            if (
                    isValid(newCoordinate, maze.width(), maze.height())
                            && !visited.contains(newCoordinate)
                            && maze.grid()[newRow][newCol].type() == Cell.Type.PASSAGE
            ) {
                parentMap.put(newCoordinate, current);
                deeper(newCoordinate, end, visited, parentMap);
            }
        }
    }

    /**
     * Проверка на валидность переданной клетки
     *
     * @param coord координаты клетки
     * @param width ширина лабиринта
     * @param height высота лабиринта
     * @return флаг о валидности
     */
    private boolean isValid(Coordinate coord, int width, int height) {
        return coord.col() >= 0 && coord.col() < width && coord.row() >= 0 && coord.row() < height;
    }

    /**
     * Преобразование словаря в лист с путем из стартовой точки в конечную
     *
     * @param parentMap словарь
     * @param start стартовая клетка
     * @param end конечная клетка
     * @return путь с решением в виде листа
     */
    private List<Coordinate> constructPath(Map<Coordinate, Coordinate> parentMap, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate current = end;

        while (!current.equals(start)) {
            path.add(current);
            current = parentMap.get(current);
        }

        path.add(start);
        Collections.reverse(path);
        return path;
    }
}
