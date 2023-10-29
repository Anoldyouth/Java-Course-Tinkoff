package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withHeight(3).make(),
                AnimalFactory.newFactory().withHeight(6).make(),
                AnimalFactory.newFactory().withHeight(4).make(),
                AnimalFactory.newFactory().withHeight(5).make(),
                AnimalFactory.newFactory().withHeight(1).make()
        ));
        var newList = Methods.task1(animals);

        assertNotSame(newList, animals);
        assertThat(newList.getFirst().height()).isEqualTo(1);
        assertThat(newList.getLast().height()).isEqualTo(6);
    }
}
