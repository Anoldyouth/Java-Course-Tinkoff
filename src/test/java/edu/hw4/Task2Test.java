package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withWeight(3).make(),
                AnimalFactory.newFactory().withWeight(6).make(),
                AnimalFactory.newFactory().withWeight(4).make(),
                AnimalFactory.newFactory().withWeight(5).make(),
                AnimalFactory.newFactory().withWeight(1).make()
        ));
        var newList = Methods.task2(animals, 3);

        assertNotSame(newList, animals);
        assertThat(newList.getFirst().weight()).isEqualTo(6);
        assertThat(newList.getLast().weight()).isEqualTo(4);
    }

    @Test
    @DisplayName("Отрицательное количество элементов")
    void negativeCount() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withWeight(3).make(),
                AnimalFactory.newFactory().withWeight(6).make()
        ));
       assertThrows(
               IllegalArgumentException.class,
               () -> Methods.task2(animals, -3)
       );
    }
}
