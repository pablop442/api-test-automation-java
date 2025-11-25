package com.project.utils;

import com.github.javafaker.Faker;
import java.util.concurrent.ThreadLocalRandom;

public class DataUtils {

    private static Faker faker = new Faker();

    public static String createRandomProductName() {
        return faker.commerce().productName();
    }

    public static Integer createRandomPageLimit(){
        return 10 * (ThreadLocalRandom.current().nextInt(1, 6));
    }

    public static Integer createRandomMinPrice(){
        return 10 * (ThreadLocalRandom.current().nextInt(1, 5));
    }

    public static Integer createRandomMaxPrice(Integer minPrice){
        return minPrice + 10 * (ThreadLocalRandom.current().nextInt(1, 10));
    }
}
