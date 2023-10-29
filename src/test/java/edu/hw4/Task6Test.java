package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withType(Animal.Type.CAT).withWeight(3).make(),
                AnimalFactory.newFactory().withType(Animal.Type.CAT).withWeight(4).make(),
                AnimalFactory.newFactory().withType(Animal.Type.SPIDER).withWeight(2).make()
        ));

        var map = Methods.task6(animals);
        assertThat(map.keySet().size()).isEqualTo(2);
        assertThat(map.get(Animal.Type.CAT).weight()).isEqualTo(4);
        assertThat(map.get(Animal.Type.SPIDER).weight()).isEqualTo(2);
    }
}
