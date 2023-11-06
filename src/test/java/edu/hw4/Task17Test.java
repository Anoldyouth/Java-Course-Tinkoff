package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task17Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withType(Animal.Type.DOG).canBite(true).make(),
                AnimalFactory.newFactory().withType(Animal.Type.SPIDER).canBite(true).make(),
                AnimalFactory.newFactory().withType(Animal.Type.SPIDER).canBite(true).make(),
                AnimalFactory.newFactory().withType(Animal.Type.DOG).canBite(false).make(),
                AnimalFactory.newFactory().withType(Animal.Type.CAT).canBite(false).make()
        ));

        var isSpiderBitesMore = Methods.task17(animals);
        assertTrue(isSpiderBitesMore);
    }

    @Test
    @DisplayName("Недостаточно данных")
    void notEnoughData() {
        List<Animal> animals = new ArrayList<>(Arrays.asList(
                AnimalFactory.newFactory().withType(Animal.Type.CAT).canBite(true).make(),
                AnimalFactory.newFactory().withType(Animal.Type.SPIDER).canBite(true).make(),
                AnimalFactory.newFactory().withType(Animal.Type.SPIDER).canBite(true).make(),
                AnimalFactory.newFactory().withType(Animal.Type.CAT).canBite(false).make(),
                AnimalFactory.newFactory().withType(Animal.Type.CAT).canBite(true).make()
        ));

        var isSpiderBitesMore = Methods.task17(animals);
        assertFalse(isSpiderBitesMore);
    }
}
