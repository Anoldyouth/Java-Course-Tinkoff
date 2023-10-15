package edu.hw2.Task2;

public class Square extends Rectangle {
    /**
     * Конструктор квадрата
     *
     * @param sideLength размер стороны квадрата
     */
    public Square(int sideLength) {
        super(sideLength, sideLength);
    }

    /**
     * Переопределенная функция по установке параметров
     *
     * @param width новая ширина
     * @param height новая высота
     * @return квадрат или прямоугольник
     */
    @Override
    public Rectangle setParams(int width, int height) {
        if (width == height) {
            return new Square(width);
        }

        return new Rectangle(width, height);
    }
}
