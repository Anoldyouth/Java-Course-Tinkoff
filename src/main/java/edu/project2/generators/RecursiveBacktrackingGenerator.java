package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.Direction;
import edu.project2.Maze;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecursiveBacktrackingGenerator implements Generator {

    private int width;
    private int height;
    private Cell[][] maze;

    /**
     * Генерирует лабиринт алгоритмом Recursive Backtracking
     *
     * @param height высота лабиринта (нечетное положительное число)
     * @param width ширина лабиринта (нечетное положительное число)
     * @return сгенерированный лабиринт
     */
    @Override
    public Maze generate(int height, int width) {
        if (height <= 0) {
            throw new IllegalArgumentException("Высота лабиринта должна быть больше нуля");
        }

        if (width <= 0) {
            throw new IllegalArgumentException("Ширина лабиринта должна быть больше нуля");
        }

        if (height % 2 == 0 || width % 2 == 0) {
            throw new IllegalArgumentException("Параметры должны быть нечетными");
        }

        this.width = width;
        this.height = height;
        this.maze = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.maze[i][j] = new Cell(Cell.Type.WALL);
            }
        }

        generateMaze(1, 1);

        return new Maze(height, width, this.maze);
    }

    /**
     * Рекурсивный вызов генератора
     *
     * @param row номер текущей строки
     * @param col номер текущего столбца
     */
    private void generateMaze(int row, int col) {
        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);

        for (Direction direction : directions) {
            int newCol = col + direction.dx * 2;
            int newRow = row + direction.dy * 2;

            if (isValid(newRow, newCol)) {
                maze[newRow][newCol] = new Cell(Cell.Type.PASSAGE);
                maze[row + direction.dy][col + direction.dx] = new Cell(Cell.Type.PASSAGE);
                generateMaze(newRow, newCol);
            }
        }
    }

    /**
     * Проверка валидности переданной клетки
     *
     * @param row строка проверяемой клетки
     * @param col столбец проверяемой клетки
     * @return флаг о валидности
     */
    private boolean isValid(int row, int col) {
        return row > 0 && row < height && col > 0 && col < width && maze[row][col].type() == Cell.Type.WALL;
    }
}
