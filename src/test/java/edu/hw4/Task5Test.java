package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withSex(Animal.Sex.F).make(),
                AnimalFactory.newFactory().withSex(Animal.Sex.M).make(),
                AnimalFactory.newFactory().withSex(Animal.Sex.F).make()
        ));

        var sex = Methods.task5(animals);
        assertThat(sex).isEqualTo(Animal.Sex.F);
    }

    @Test
    @DisplayName("Проверка при равенстве")
    void equal() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withSex(Animal.Sex.F).make(),
                AnimalFactory.newFactory().withSex(Animal.Sex.F).make(),
                AnimalFactory.newFactory().withSex(Animal.Sex.M).make(),
                AnimalFactory.newFactory().withSex(Animal.Sex.M).make()

        ));

        var sex = Methods.task5(animals);
        assertThat(sex).isEqualTo(Animal.Sex.M);
    }
}
