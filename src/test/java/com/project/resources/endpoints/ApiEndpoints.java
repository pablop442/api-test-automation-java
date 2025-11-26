package com.project.resources.endpoints;

public final class ApiEndpoints {
    public static final String LOGIN = "/auth/login";
    public static final String USERS = "/users";
    public static final String PRODUCTS = "/products";
    public static final String PROFILE = "/auth/profile";

    private ApiEndpoints() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    
}
