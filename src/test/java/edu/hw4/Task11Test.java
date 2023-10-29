package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task11Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().canBite(true).withHeight(100).make(),
                AnimalFactory.newFactory().canBite(false).withHeight(150).make(),
                AnimalFactory.newFactory().canBite(false).withHeight(50).make(),
                AnimalFactory.newFactory().canBite(true).withHeight(150).make(),
                AnimalFactory.newFactory().canBite(true).withHeight(50).make()
        ));

        var list = Methods.task11(animals);
        assertThat(list.size()).isEqualTo(1);
    }
}
