package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle(1, 1)),
            Arguments.of(new Square(1))
        };
    }

    @ParameterizedTest
    @DisplayName("Площадь прямоугольника")
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        assertThat(rect.setParams(20, 10).area()).isEqualTo(200.0);
    }

    @Test
    @DisplayName("Проверка принадлежности классу квадрата")
    void instanceOfSquare()
    {
        Square square = new Square(2);

        assertThat(square).isInstanceOf(Square.class);

        assertThat(square.setParams(4, 4)).isInstanceOf(Square.class);

        assertThat(square.setParams(20, 10)).isNotInstanceOf(Square.class);
    }

    @Test
    @DisplayName("Проверка принадлежности классу прямоугольника")
    void instanceOfRectangle() {
        Square square = new Square(2);

        assertThat(square.setParams(20, 10)).isNotInstanceOf(Square.class).isInstanceOf(Rectangle.class);
    }
}
