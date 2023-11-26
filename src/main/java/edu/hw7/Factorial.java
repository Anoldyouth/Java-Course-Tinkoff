package edu.hw7;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class Factorial {
    private Factorial() {}

    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Отрицательное число");
        }

        if (n == 0 || n == 1) {
            return new BigInteger(String.valueOf(1));
        }

        return LongStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
