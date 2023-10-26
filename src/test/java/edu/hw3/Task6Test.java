package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockMarket;
import edu.hw3.Task6.StockMarketRealisation;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    @DisplayName("Успешный результат")
    void successful() {
        StockMarket market = new StockMarketRealisation();
        Stock lowStock = new Stock(1, "first");
        Stock mediumStock = new Stock(2, "second");
        Stock highStock = new Stock(3, "third");
        market.add(lowStock);
        market.add(mediumStock);
        market.add(highStock);

        assertThat(market.mostValuableStock()).isEqualTo(highStock);

        market.remove(highStock);
        assertThat(market.mostValuableStock()).isEqualTo(mediumStock);

        market.remove(lowStock);
        assertThat(market.mostValuableStock()).isEqualTo(mediumStock);
    }
}
