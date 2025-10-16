package com.project.apitests.auth;

import org.junit.jupiter.api.Test;

import com.project.apitests.BaseApiTest;

import io.restassured.response.Response;

public class AuthTests extends BaseApiTest {
    protected static String INVALID_TOKEN = "invalid_token";

    @Test
    void retreiveUserProfile() {
        Response response = requestSpec
                .when()
                .get("/auth/profile")
                .then()
                .statusCode(200)
                .extract().response();
        response.jsonPath().getString("email").equals(BaseApiTest.USER_EMAIL);
    }

    @Test
    void invalidLogin() {
        requestSpec
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + INVALID_TOKEN)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401);
    }

    @Test
    void missingLoginCredentials() {
        requestSpec
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer ")
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401);
    }
}
