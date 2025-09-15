package com.project.apitests;

import io.restassured.RestAssured;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class ResponseSchemaTests {

    @Test
    void validFlightContent(){
        given()
            .queryParam("access_key", Config.API_KEY)
        .when()
            .get(Config.BASE_URL + "/flights")
        .then() 
            .statusCode(200)
            .body("data[0].flight_date", notNullValue())
            .body("data[0].airline.name", notNullValue())
            .body("data[0].flight.number", notNullValue());
    }
}
