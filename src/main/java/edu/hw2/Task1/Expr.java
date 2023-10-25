package edu.hw2.Task1;

/**
 * Интерфейс для работы с математическими выражениями
 */
public sealed interface Expr {
    /**
     * Функция для вычисления выражения
     *
     * @return вычисленное выражение
     */
    double evaluate();

    /**
     * Создает хранилище для хранения числа
     *
     * @param number число для хранения
     */
    public record Constant(double number) implements Expr {
        /**
         * Функция для возврата хранимого числа
         *
         * @return хранимое число
         */
        @Override
        public double evaluate() {
            return number;
        }
    }

    /**
     * Хранилище с функцией возврата числа с противоположным знаком
     *
     * @param number выражение
     */
    public record Negate(Expr number) implements Expr {
        /**
         * Функция для возврата числа с противоположным знаком
         *
         * @return число с противоположным знаком
         */
        @Override
        public double evaluate() {
            return -number.evaluate();
        }
    }

    /**
     * Хранилище с функцией вычисления степени числа
     *
     * @param base основание степени
     * @param exponent показатель степени
     */
    public record Exponent(Expr base, int exponent) implements Expr {
        /**
         * Функция, вычисляющая степень числа
         *
         * @return степень числа
         */
        @Override
        public double evaluate() {
            return Math.pow(base.evaluate(), exponent);
        }
    }

    /**
     * Хранилище с функцией вычисления суммы чисел
     *
     * @param first первое слагаемое
     * @param second второе слагаемое
     */
    public record Addition(Expr first, Expr second) implements Expr {
        /**
         * Функция, вычисляющая сумму чисел
         *
         * @return сумма чисел
         */
        @Override
        public double evaluate() {
            return first.evaluate() + second.evaluate();
        }
    }

    /**
     * Хранилище с функцией вычисления произведения чисел
     *
     * @param first первый множитель
     * @param second второй множитель
     */
    public record Multiplication(Expr first, Expr second) implements Expr {
        /**
         * Функция, вычисляющая произведение чисел
         *
         * @return произведение чисел
         */
        @Override
        public double evaluate() {
            return first.evaluate() * second.evaluate();
        }
    }
}
