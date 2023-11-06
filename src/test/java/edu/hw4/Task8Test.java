package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task8Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withWeight(1).withHeight(1).make(),
                AnimalFactory.newFactory().withWeight(2).withHeight(2).make(),
                AnimalFactory.newFactory().withWeight(3).withHeight(3).make(),
                AnimalFactory.newFactory().withWeight(4).withHeight(4).make()
        ));

        var animal = Methods.task8(animals, 4);
        assertThat(animal.get().weight()).isEqualTo(3);
    }

    @Test
    @DisplayName("Животное не найдено")
    void notFind() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withWeight(2).withHeight(2).make(),
                AnimalFactory.newFactory().withWeight(3).withHeight(3).make(),
                AnimalFactory.newFactory().withWeight(4).withHeight(4).make()
        ));

        var animal = Methods.task8(animals, 2);
        assertThat(animal.isPresent()).isEqualTo(false);
    }
}
