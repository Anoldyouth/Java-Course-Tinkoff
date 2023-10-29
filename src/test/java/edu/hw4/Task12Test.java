package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task12Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withHeight(2).withWeight(2).make(),
                AnimalFactory.newFactory().withHeight(1).withWeight(3).make(),
                AnimalFactory.newFactory().withHeight(3).withWeight(1).make()
        ));

        var count = Methods.task12(animals);
        assertThat(count).isEqualTo(1);
    }
}
