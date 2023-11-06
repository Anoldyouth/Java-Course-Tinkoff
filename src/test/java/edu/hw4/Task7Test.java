package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task7Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withAge(2).make(),
                AnimalFactory.newFactory().withAge(10).make(),
                AnimalFactory.newFactory().withAge(7).make(),
                AnimalFactory.newFactory().withAge(1).make(),
                AnimalFactory.newFactory().withAge(3).make()
        ));

        var animal = Methods.task7(animals, 2);
        assertThat(animal.age()).isEqualTo(3);
    }
}
