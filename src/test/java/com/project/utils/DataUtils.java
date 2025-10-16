package com.project.utils;

import com.github.javafaker.Faker;

public class DataUtils {

    private static Faker faker = new Faker();

    public static String createRandomProductName() {
        return faker.commerce().productName();
    }
}
