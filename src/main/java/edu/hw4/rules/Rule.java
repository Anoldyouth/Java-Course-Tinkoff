package edu.hw4.rules;

import edu.hw4.ValidationError;

public interface Rule<T> {
    ValidationError check(T obj);
}
