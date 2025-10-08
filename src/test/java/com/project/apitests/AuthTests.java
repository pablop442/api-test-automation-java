package com.project.apitests;

import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

//TODO: replace magic strings with constants

public class AuthTests extends BaseApiTest {

    @Test
    void retreiveUserProfile() {
        Response response = requestSpec
                .when()
                .get("/auth/profile")
                .then()
                .statusCode(200)
                .extract().response();
        response.jsonPath().getString("email").equals("john@mail.com");
    }

    @Test
    void invalidLogin() {
        requestSpec
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer invalid_token")
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
