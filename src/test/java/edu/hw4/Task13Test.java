package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task13Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withName("test").make(),
                AnimalFactory.newFactory().withName("test name").make(),
                AnimalFactory.newFactory().withName("test name success").make()
        ));

        var list = Methods.task13(animals);
        assertThat(list.size()).isEqualTo(1);
    }
}
