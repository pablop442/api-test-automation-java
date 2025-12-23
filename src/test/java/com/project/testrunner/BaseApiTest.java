package com.project.testrunner;

import com.project.resources.endpoints.ApiEndpoints;
import org.junit.jupiter.api.BeforeAll;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseApiTest {
    private static final Dotenv dotenv = Dotenv.load();

    protected static String BASE_URL = dotenv.get("BASE_URL");
    protected static String USER_EMAIL = dotenv.get("USER_EMAIL");
    protected static String PASSWORD = dotenv.get("PASSWORD");
    protected static String TOKEN;
    protected static RequestSpecification requestSpec;

    @BeforeAll
    static void setup() {
                
        RestAssured.baseURI = BASE_URL;
        RestAssured.useRelaxedHTTPSValidation();

        // Login to get token
        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body("{\"email\": \"" + USER_EMAIL + "\", \"password\": \"" + PASSWORD + "\"}")
                .when()
                .post(ApiEndpoints.LOGIN)
                .then()
                .statusCode(201)
                .extract().response();

        TOKEN = response.jsonPath().getString("access_token");

        // Reusable request specification with token
        requestSpec = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + TOKEN);
    }
}