package edu.hw5;

import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task2Test {

    @Test
    @DisplayName("Успешное нахождение всех пятниц 13 данного года")
    void successSearchByYear() {
        assertThat(Task2.searchAllFridayThirteenthOfTheYear(1925))
                .isEqualTo(List.of(
                        LocalDate.of(1925, 2, 13),
                        LocalDate.of(1925, 3, 13),
                        LocalDate.of(1925, 11, 13)
                ));
    }

    @Test
    @DisplayName("Успешное нахождение ближайшей пятницы")
    void successSearchNext() {
        assertThat(Task2.searchNextFridayThirteenth(LocalDate.of(1925, 2, 13)))
                .isEqualTo(LocalDate.of(1925, 3, 13));
    }

    @Test
    @DisplayName("Успешное нахождение ближайшей пятницы в следующем году")
    void successSearchNextInNextYear() {
        assertThat(Task2.searchNextFridayThirteenth(LocalDate.of(1925, 12, 13)))
                .isEqualTo(LocalDate.of(1926, 8, 13));
    }
}
