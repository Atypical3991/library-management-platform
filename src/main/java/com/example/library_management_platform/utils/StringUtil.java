package com.example.library_management_platform.utils;

public class StringUtil {

    //convertToSlug :- A method to convert Strings to its Slug
    public static String convertToSlug(String input) {
        if (input == null) {
            return "";
        }

        // Replace spaces with hyphens and convert to lowercase
        String slug = input.trim().toLowerCase().replaceAll(" ", "-");

        // Remove special characters and non-alphanumeric characters
        slug = slug.replaceAll("[^a-z0-9-]", "");

        // Replace consecutive hyphens with a single hyphen
        slug = slug.replaceAll("-+", "-");

        // Remove leading and trailing hyphens
        slug = slug.replaceAll("^-|-$", "");

        return slug;
    }
}
