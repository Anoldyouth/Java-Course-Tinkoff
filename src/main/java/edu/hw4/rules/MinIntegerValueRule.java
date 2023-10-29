package edu.hw4.rules;

import edu.hw4.ValidationError;

public class MinIntegerValueRule implements Rule<Integer> {

    private final int min;

    public MinIntegerValueRule(int min) {
        this.min = min;
    }

    @Override
    public ValidationError check(Integer obj) {
        if (obj >= min) {
            return null;
        }

        return new ValidationError("Поле должно быть не меньше заданного минимума", this.getClass().getSimpleName());
    }
}
