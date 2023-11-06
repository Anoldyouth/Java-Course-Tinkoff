package edu.hw3.Task6;

import org.jetbrains.annotations.NotNull;

public record Stock(int price, String name) implements Comparable<Stock> {
    @Override
    public int compareTo(@NotNull Stock stock) {
        return Integer.compare(stock.price(), price());
    }
}
