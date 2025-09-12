package com.project.apitests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class FirstApiTest {

    @BeforeAll
    static void setup(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
    
    @Test
    void getPostById(){
        given().relaxedHTTPSValidation()
            .when()
                .get("/posts/1")
            .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }

    @Test
    void getPostTitle(){
        given().relaxedHTTPSValidation()
            .when()
                .get("/posts/1")
            .then() 
                .statusCode(200)
                .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));

    }
}
