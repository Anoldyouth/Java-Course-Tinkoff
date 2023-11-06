package edu.hw4;

import edu.hw4.factories.AnimalFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task16Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        var first = AnimalFactory.newFactory().withType(Animal.Type.DOG).withSex(Animal.Sex.F).withName("first").make();
        var second = AnimalFactory.newFactory().withType(Animal.Type.CAT).withSex(Animal.Sex.F).withName("second").make();
        var third = AnimalFactory.newFactory().withType(Animal.Type.DOG).withSex(Animal.Sex.M).withName("third").make();
        var fourth = AnimalFactory.newFactory().withType(Animal.Type.DOG).withSex(Animal.Sex.M).withName("fourth").make();

        List<Animal> animals = new ArrayList<>(Arrays.asList(first, second, third, fourth));

        var list = Methods.task16(animals);
        assertThat(list.get(0)).isEqualTo(second); // Раньше всех идет Cat
        assertThat(list.get(1)).isEqualTo(fourth); // По алфавиту имени
        assertThat(list.get(2)).isEqualTo(third); // Аналогично по алфавиту имени
        assertThat(list.get(3)).isEqualTo(first); // По полу F
    }
}
