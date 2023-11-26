package edu.hw7.factories;

import com.github.javafaker.Faker;
import edu.hw7.Person;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PersonFactory {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private static final Faker faker = new Faker();

    private PersonFactory() {
    }

    public static PersonFactory newFactory() {
        return new PersonFactory();
    }

    public Person make() {
        prepare();

        return new Person(id, name, address, phoneNumber);
    }

    private void prepare() {
        if (id <= 0) {
            id = faker.number().numberBetween(1, Integer.MAX_VALUE);
        }

        if (name == null) {
            name = faker.name().name();
        }

        if (address == null) {
            address = faker.address().streetAddress();
        }

        if (phoneNumber == null) {
            phoneNumber = faker.phoneNumber().phoneNumber();
        }
    }

    public PersonFactory withId(int id) {
        this.id = id;

        return this;
    }

    public PersonFactory withName(String name) {
        this.name = name;

        return this;
    }

    public PersonFactory withAddress(String address) {
        this.address = address;

        return this;
    }

    public PersonFactory withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;

        return this;
    }
}
