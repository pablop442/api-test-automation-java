package com.project.apitests;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseApiTest {
    protected static String BASE_URL = "https://api.escuelajs.co/api/v1";
    protected static String TOKEN;
    protected static RequestSpecification requestSpec;

    @BeforeAll
    static void setup(){
        RestAssured.baseURI = BASE_URL;
        RestAssured.useRelaxedHTTPSValidation();

        //Login to get token
        Response response = RestAssured
            .given()
                .contentType("application/json")
                .body("{\"email\": \"john@mail.com\", \"password\": \"changeme\"}")
            .when()
                .post("/auth/login")
            .then()
                .statusCode(201)
                .extract().response();

        TOKEN = response.jsonPath().getString("access_token");

        //Reusable request specification with token
        requestSpec = RestAssured
            .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + TOKEN);
    }


}