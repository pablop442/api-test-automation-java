package com.project.apitests;

import io.github.cdimascio.dotenv.Dotenv;


public class Config {
    private static final Dotenv dotenv = Dotenv.load();

    public static final String API_KEY = dotenv.get("AVIATIONSTACK_API_KEY");
    public static final String BASE_URL = "https://api.aviationstack.com/v1";
}
