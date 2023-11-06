package edu.project2.solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Direction;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WidthFirstCrawlSolver implements Solver {
    /**
     * Решение лабиринта поиском в ширину
     *
     * @param maze лабиринт для решения
     * @param start стартовая клетка
     * @param end конечная клетка
     * @return путь решения или null, есть нет решения
     */
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        Set<Coordinate> visited = new HashSet<>();
        Queue<Coordinate> queue = new LinkedList<>();
        Map<Coordinate, Coordinate> parentMap = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if (current.equals(end)) {
                return constructPath(parentMap, start, end);
            }

            for (Direction direction : Direction.values()) {
                int newCol = current.col() + direction.dx;
                int newRow = current.row() + direction.dy;
                Coordinate newCoordinate = new Coordinate(newRow, newCol);

                if (
                        isValid(newCoordinate, maze.width(), maze.height())
                                && !visited.contains(newCoordinate)
                                && maze.grid()[newRow][newCol].type() == Cell.Type.PASSAGE
                ) {
                    queue.add(newCoordinate);
                    visited.add(current);
                    parentMap.put(newCoordinate, current);
                }
            }
        }

        return null;
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
