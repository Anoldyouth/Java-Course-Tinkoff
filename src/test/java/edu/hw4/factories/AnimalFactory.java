package edu.hw4.factories;

import com.github.javafaker.Faker;
import edu.hw4.Animal;
import java.util.Random;

public class AnimalFactory {
    private String name;
    private Animal.Type type;
    private Animal.Sex sex;
    private int age = -1;
    private int height = -1;
    private int weight = -1;
    private boolean bites;
    private static final Faker faker = new Faker();
    private static final Random random = new Random();
    private boolean isBitesInit = false;

    private AnimalFactory() {
    }

    public static AnimalFactory newFactory() {
        return new AnimalFactory();
    }

    public Animal make() {
        prepare();

        return new Animal(name, type, sex, age, height, weight, bites);
    }

    private void prepare() {
        if (name == null) {
            name = faker.animal().name();
        }

        if (type == null) {
            Animal.Type[] types = Animal.Type.values();
            type = types[random.nextInt(types.length)];
        }

        if (sex == null) {
            Animal.Sex[] sex = Animal.Sex.values();
            this.sex = sex[random.nextInt(sex.length)];
        }

        if (age == -1) {
            age = faker.number().numberBetween(0, 10);
        }

        if (height == -1) {
            height = faker.number().numberBetween(0, 10);
        }

        if (weight == -1) {
            weight = faker.number().numberBetween(0, 10);
        }

        if (!isBitesInit) {
            bites = faker.bool().bool();
        }
    }

    public AnimalFactory withHeight(int height) {
        this.height = height;

        return this;
    }

    public AnimalFactory withWeight(int weight) {
        this.weight = weight;

        return this;
    }

    public AnimalFactory withType(Animal.Type type) {
        this.type = type;

        return this;
    }

    public AnimalFactory withName(String name) {
        this.name = name;

        return this;
    }

    public AnimalFactory withSex(Animal.Sex sex) {
        this.sex = sex;

        return this;
    }

    public AnimalFactory withAge(int age) {
        this.age = age;

        return this;
    }

    public AnimalFactory canBite(boolean bites) {
        this.bites = bites;
        isBitesInit = true;

        return this;
    }
}
