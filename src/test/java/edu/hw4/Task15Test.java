package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task15Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withType(Animal.Type.DOG).withWeight(10).withAge(10).make(),
                AnimalFactory.newFactory().withType(Animal.Type.CAT).withWeight(15).withAge(15).make(),
                AnimalFactory.newFactory().withType(Animal.Type.CAT).withWeight(15).withAge(16).make(),
                AnimalFactory.newFactory().withType(Animal.Type.CAT).withWeight(7).withAge(8).make(),
                AnimalFactory.newFactory().withType(Animal.Type.SPIDER).withWeight(6).withAge(7).make()
        ));

        var map = Methods.task15(animals, 8, 15);
        assertThat(map.size()).isEqualTo(2);
        assertThat(map.get(Animal.Type.DOG)).isEqualTo(10);
        assertThat(map.get(Animal.Type.CAT)).isEqualTo(22);
    }
}
