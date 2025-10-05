package com.nopcommerce.utils;

import com.github.javafaker.Faker;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.substring;

public class TestDataFactory {
    private static final Faker faker = new Faker();


    private static String firstName = faker.name().firstName().toLowerCase();
    private static String lastName = faker.name().lastName().toLowerCase();
    private static final String password = firstName + "@" + faker.number().numberBetween(1000, 9999);
    private static final String confirmPassword = password;

    public static String getTwoRandomChars() {
        return UUID.randomUUID().toString().substring(0, 2);
    }

    public static String getEmail() {
        return firstName + "." + lastName + faker.number().numberBetween(1000, 9999) + "@autotest.com";
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getPassword() {
        return password;
    }

    public static String getConfirmPassword() {
        return confirmPassword;
    }

}
