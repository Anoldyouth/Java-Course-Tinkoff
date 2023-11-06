package edu.hw4.rules;

import edu.hw4.ValidationError;

public class MaxStringLengthRule implements Rule<String> {

    private final int max;

    public MaxStringLengthRule(int max) {
        this.max = max;
    }

    @Override
    public ValidationError check(String obj) {
        if (obj.length() <= max) {
            return null;
        }

        return new ValidationError("Поле должно быть не больше заданной длины", this.getClass().getSimpleName());
    }
}
