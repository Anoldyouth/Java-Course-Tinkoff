package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task0 {
    private static final Logger LOGGER = LogManager.getLogger();

    private Task0() {
    }

    /** Напишите функцию, которая выводит в консоль фразу "Привет, мир!" при помощи метода LOGGER.info(). */
    public static void helloWorld() {
        LOGGER.info("Hello world!");
    }
}
