package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withName("first").make(),
                AnimalFactory.newFactory().withName("second").make(),
                AnimalFactory.newFactory().withName("third").make()
        ));

        var animal = Methods.task4(animals);
        assertThat(animal.name()).isEqualTo("second");
    }
}
