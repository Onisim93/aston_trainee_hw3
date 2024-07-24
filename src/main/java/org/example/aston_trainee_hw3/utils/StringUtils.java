package org.example.aston_trainee_hw3.utils;

public class StringUtils {

    private StringUtils(){}

    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isBlank()) {
            return input;
        }
        input = input.toLowerCase();
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
