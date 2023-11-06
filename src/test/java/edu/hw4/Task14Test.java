package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task14Test {
    static Arguments[] datasets() {
        return new Arguments[]{
                Arguments.of(new ArrayList<>(Arrays.asList(
                        AnimalFactory.newFactory().withType(Animal.Type.DOG).withHeight(20).make(),
                        AnimalFactory.newFactory().withType(Animal.Type.DOG).withHeight(30).make(),
                        AnimalFactory.newFactory().withType(Animal.Type.CAT).withHeight(30).make()
                )), 25, true),
                Arguments.of(new ArrayList<>(Arrays.asList(
                        AnimalFactory.newFactory().withType(Animal.Type.DOG).withHeight(20).make(),
                        AnimalFactory.newFactory().withType(Animal.Type.DOG).withHeight(30).make(),
                        AnimalFactory.newFactory().withType(Animal.Type.CAT).withHeight(35).make()
                )), 30, false)
        };
    }

    @ParameterizedTest
    @DisplayName("Успешный тест")
    @MethodSource("datasets")
    void successful(List<Animal> animals, int minHeight, boolean isPresent) {
        assertThat(Methods.task14(animals, minHeight)).isEqualTo(isPresent);
    }
}
