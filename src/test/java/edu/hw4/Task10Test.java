package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task10Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withType(Animal.Type.CAT).withAge(2).make(),
                AnimalFactory.newFactory().withType(Animal.Type.SPIDER).withAge(8).make(),
                AnimalFactory.newFactory().withType(Animal.Type.FISH).withAge(1).make(),
                AnimalFactory.newFactory().withType(Animal.Type.BIRD).withAge(3).make()
        ));

        var list = Methods.task10(animals);
        assertThat(list.size()).isEqualTo(3);
    }
}
