package edu.hw4.rules;

import edu.hw4.ValidationError;

public class MaxIntegerValueRule implements Rule<Integer> {

    private final int max;

    public MaxIntegerValueRule(int max) {
        this.max = max;
    }

    @Override
    public ValidationError check(Integer obj) {
        if (obj <= max) {
            return null;
        }

        return new ValidationError("Поле должно быть не больше заданного максимума", this.getClass().getSimpleName());
    }
}
