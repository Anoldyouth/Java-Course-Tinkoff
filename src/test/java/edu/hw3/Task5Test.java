package edu.hw3;

import edu.hw3.Task5.Person;
import edu.hw3.Task5.SortingOrderEnum;
import edu.hw3.Task5.Task5;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task5Test {
    static Arguments[] expectations() {
        return new Arguments[]{
                Arguments.of(new Person[]{
                        new Person("John", "Locke"),
                        new Person("Thomas", "Aquinas"),
                        new Person("David", "Hume"),
                        new Person("Rene", "Descartes"),

                }, SortingOrderEnum.ASC, List.of(
                        new Person("Thomas", "Aquinas"),
                        new Person("Rene", "Descartes"),
                        new Person("David", "Hume"),
                        new Person("John", "Locke")
                )),

                Arguments.of(new Person[]{
                        new Person("Paul", "Erdos"),
                        new Person("Leonhard", "Euler"),
                        new Person("Carl", "Gauss")

                }, SortingOrderEnum.DESC, List.of(
                        new Person("Carl", "Gauss"),
                        new Person("Leonhard", "Euler"),
                        new Person("Paul", "Erdos")
                )),

                Arguments.of(new Person[]{}, SortingOrderEnum.ASC, List.of())
        };
    }

    @ParameterizedTest
    @DisplayName("Успешные результаты")
    @MethodSource("expectations")
    void successful(Person[] inputPersons, SortingOrderEnum inputSort, List output) throws Exception {
        assertThat(Task5.parseContacts(inputPersons, inputSort)).isEqualTo(output);
    }
}
