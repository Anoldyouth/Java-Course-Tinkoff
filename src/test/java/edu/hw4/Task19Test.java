package edu.hw4;

import edu.hw4.rules.MaxIntegerValueRule;
import edu.hw4.rules.MaxStringLengthRule;
import edu.hw4.rules.MinIntegerValueRule;
import edu.hw4.rules.MinStringLengthRule;
import edu.hw4.rules.NotNullRule;
import edu.hw4.rules.Rule;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task19Test {
    @Test
    @DisplayName("Все бросает ошибки")
    void allErrors() {
        Animal animal = new Animal(null, Animal.Type.FISH, Animal.Sex.M, 21, 215, 310, false);

        Map<String, List<Rule>> rules = new HashMap<>();
        rules.put("name", List.of(new NotNullRule<String>(), new MinStringLengthRule(10), new MaxStringLengthRule(20)));
        rules.put("age", List.of(new NotNullRule<Integer>(), new MinIntegerValueRule(250), new MaxIntegerValueRule(20)));
        rules.put("height", List.of(new NotNullRule<Integer>(), new MinIntegerValueRule(250), new MaxIntegerValueRule(200)));
        rules.put("weight", List.of(new NotNullRule<Integer>(), new MinIntegerValueRule(500), new MaxIntegerValueRule(20)));

        var errorsMap = Methods.task19(animal, rules);

        assertThat(errorsMap.get("name").size()).isEqualTo(1);
        assertThat(errorsMap.get("name").stream().findFirst().get().ruleClass())
                .isEqualTo(NotNullRule.class.getSimpleName());
        assertThat(errorsMap.get("age").size()).isEqualTo(2);
        assertThat(errorsMap.get("height").size()).isEqualTo(2);
        assertThat(errorsMap.get("weight").size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Без ошибок")
    void successful() {
        Animal animal = new Animal("nice fish", Animal.Type.FISH, Animal.Sex.M, 1, 10, 310, false);

        Map<String, List<Rule>> rules = new HashMap<>();
        rules.put("name", List.of(new NotNullRule<String>(), new MinStringLengthRule(5), new MaxStringLengthRule(15)));
        rules.put("age", List.of(new NotNullRule<Integer>(), new MinIntegerValueRule(0), new MaxIntegerValueRule(2)));
        rules.put("height", List.of(new NotNullRule<Integer>(), new MinIntegerValueRule(5), new MaxIntegerValueRule(15)));
        rules.put("weight", List.of(new NotNullRule<Integer>(), new MinIntegerValueRule(100), new MaxIntegerValueRule(500)));

        var errorsMap = Methods.task19(animal, rules);

        assertThat(errorsMap.size()).isEqualTo(0);
    }
}
