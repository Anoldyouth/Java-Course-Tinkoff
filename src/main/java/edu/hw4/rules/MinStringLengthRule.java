package edu.hw4.rules;

import edu.hw4.ValidationError;

public class MinStringLengthRule implements Rule<String> {

    private final int min;

    public MinStringLengthRule(int min) {
        this.min = min;
    }

    @Override
    public ValidationError check(String obj) {
        if (obj.length() >= min) {
            return null;
        }

        return new ValidationError("Поле должно быть не меньше заданной длины", this.getClass().getSimpleName());
    }
}
