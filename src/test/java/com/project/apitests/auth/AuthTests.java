package com.project.apitests.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import com.project.apitests.BaseApiTest;
import com.project.resources.endpoints.ApiEndpoints;

import io.qameta.allure.Feature;
import io.restassured.response.Response;

public class AuthTests extends BaseApiTest {
    protected static String INVALID_TOKEN = "invalid_token";

    @Feature("Authentication Tests")
    @Test
    @Tag("Regression")
    @Tag("Smoke")
    void retreiveUserProfile() {
        Response response = requestSpec
                .when()
                .get(ApiEndpoints.PROFILE)
                .then()
                .statusCode(200)
                .extract().response();
        response.jsonPath().getString("email").equals(BaseApiTest.USER_EMAIL);
    }

    @Feature("Authentication Tests")
    @Tag("Regression")
    @Test
    void invalidLogin() {
        requestSpec
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + INVALID_TOKEN)
                .when()
                .post(ApiEndpoints.LOGIN)
                .then()
                .statusCode(401);
    }

    @Feature("Authentication Tests")
    @Tag("Regression")
    @Test
    void missingLoginCredentials() {
        requestSpec
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer ")
                .when()
                .post(ApiEndpoints.LOGIN)
                .then()
                .statusCode(401);
    }
}
