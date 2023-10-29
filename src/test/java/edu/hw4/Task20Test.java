package edu.hw4;

import edu.hw4.rules.MaxIntegerValueRule;
import edu.hw4.rules.MinIntegerValueRule;
import edu.hw4.rules.NotNullRule;
import edu.hw4.rules.Rule;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task20Test {
    @Test
    @DisplayName("Проверка вывода")
    void allErrors() {
        Animal animal = new Animal(null, Animal.Type.FISH, Animal.Sex.M, 21, 215, 310, false);

        Map<String, List<Rule>> rules = new HashMap<>();
        rules.put("height", List.of(new NotNullRule<Integer>(), new MinIntegerValueRule(250), new MaxIntegerValueRule(200)));

        var errorsMap = Methods.task20(animal, rules);

        String message = "Поле должно быть не больше заданного максимума\nПоле должно быть не меньше заданного минимума";
        assertThat(errorsMap.get("height")).isEqualTo(message);
    }
}
