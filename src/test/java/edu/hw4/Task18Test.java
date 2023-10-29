package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task18Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animalsFirst = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withType(Animal.Type.FISH).withWeight(10).make(),
                AnimalFactory.newFactory().withType(Animal.Type.CAT).withWeight(15).make(),
                AnimalFactory.newFactory().withType(Animal.Type.DOG).withWeight(17).make(),
                AnimalFactory.newFactory().withType(Animal.Type.FISH).withWeight(11).make()
        ));

        List<Animal> animalsSecond = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withType(Animal.Type.SPIDER).withWeight(10).make(),
                AnimalFactory.newFactory().withType(Animal.Type.CAT).withWeight(15).make(),
                AnimalFactory.newFactory().withType(Animal.Type.DOG).withWeight(17).make(),
                AnimalFactory.newFactory().withType(Animal.Type.FISH).withWeight(9).make()
        ));

        var fish = Methods.task18(animalsFirst, animalsSecond);
        assertThat(fish.weight()).isEqualTo(11);
    }

    @Test
    @DisplayName("Один лист")
    void oneList() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withType(Animal.Type.FISH).withWeight(10).make(),
                AnimalFactory.newFactory().withType(Animal.Type.CAT).withWeight(15).make(),
                AnimalFactory.newFactory().withType(Animal.Type.DOG).withWeight(17).make(),
                AnimalFactory.newFactory().withType(Animal.Type.FISH).withWeight(11).make()
        ));

        assertThrows(
                IllegalArgumentException.class,
                () -> Methods.task18(animals)
        );
    }
}
