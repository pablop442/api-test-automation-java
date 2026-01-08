package com.project.resources.endpoints;

import java.util.HashMap;

public final class ApiEndpoints {
    public static final String LOGIN = "/auth/login";
    public static final String USERS = "/users";
    public static final String PRODUCTS = "/products";
    public static final String PROFILE = "/auth/profile";
    public static final String PRODUCT_DETAIL = "/products/{productId}";

    private static final HashMap<String, String> ENDPOINT_MAP = new HashMap<>();

    static{
        ENDPOINT_MAP.put("login", LOGIN);
        ENDPOINT_MAP.put("users", USERS);
        ENDPOINT_MAP.put("products", PRODUCTS);
        ENDPOINT_MAP.put("profile", PROFILE);
        ENDPOINT_MAP.put("productDetail", PRODUCT_DETAIL);
    }

    public ApiEndpoints(){
    }

    public String getEndpoint(String endpoint){
        return ENDPOINT_MAP.get(endpoint);
    }
    
}
