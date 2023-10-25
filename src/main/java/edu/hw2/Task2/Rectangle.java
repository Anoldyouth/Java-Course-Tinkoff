package edu.hw2.Task2;

public class Rectangle {
    /** Ширина прямоугольника */
    final private int width;
    /** Высота прямоугольника */
    final private int height;

    /**
     * Конструктор
     *
     * @param width ширина
     * @param height высота
     */
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Установка новых параметров прямоугольника
     *
     * @param width новая ширина
     * @param height новая высота
     * @return новый прямоугольник
     */
    public Rectangle setParams(int width, int height) {
        return new Rectangle(width, height);
    }

    /**
     * Функция для вычисления площади
     *
     * @return площадь прямоугольника
     */
    public double area() {
        return width * height;
    }
}
