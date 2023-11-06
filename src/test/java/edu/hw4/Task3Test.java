package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withType(Animal.Type.CAT).make(),
                AnimalFactory.newFactory().withType(Animal.Type.SPIDER).make(),
                AnimalFactory.newFactory().withType(Animal.Type.FISH).make(),
                AnimalFactory.newFactory().withType(Animal.Type.CAT).make(),
                AnimalFactory.newFactory().withType(Animal.Type.SPIDER).make(),
                AnimalFactory.newFactory().withType(Animal.Type.CAT).make()
        ));

        var freqMap = Methods.task3(animals);
        assertThat(freqMap.get(Animal.Type.CAT)).isEqualTo(3);
        assertThat(freqMap.get(Animal.Type.SPIDER)).isEqualTo(2);
        assertThat(freqMap.get(Animal.Type.FISH)).isEqualTo(1);
    }
}
