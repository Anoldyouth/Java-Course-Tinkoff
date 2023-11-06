package edu.hw4.rules;

import edu.hw4.ValidationError;

public class NotNullRule<T> implements Rule<T> {
    @Override
    public ValidationError check(T obj) {
        if (obj != null) {
            return null;
        }

        return new ValidationError("Поле должно быть не null", this.getClass().getSimpleName());
    }
}
