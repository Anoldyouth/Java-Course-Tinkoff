package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task9Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withType(Animal.Type.CAT).make(),
                AnimalFactory.newFactory().withType(Animal.Type.SPIDER).make(),
                AnimalFactory.newFactory().withType(Animal.Type.FISH).make(),
                AnimalFactory.newFactory().withType(Animal.Type.BIRD).make()
        ));

        var paws = Methods.task9(animals);
        assertThat(paws).isEqualTo(14);
    }

    @Test
    @DisplayName("Пустой лист")
    void emptyList() {
        List<Animal> animals = new ArrayList<>();

        var paws = Methods.task9(animals);
        assertThat(paws).isEqualTo(0);
    }
}
