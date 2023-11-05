package edu.hw3.Task5;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public record Person(@NotNull String name, String surname) implements Comparable<Person> {
    @Override
    public String toString() {
        if (surname() == null) {
            return name();
        }

        return name() + " " + surname();
    }

    @Override
    public int compareTo(@NotNull Person person) {
        if (!Objects.equals(surname(), person.surname())) {
            return surname().compareTo(person.surname());
        }

        return name().compareTo(person.name());
    }
}
