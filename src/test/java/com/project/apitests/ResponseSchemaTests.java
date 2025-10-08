package com.project.apitests;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;


public class ResponseSchemaTests extends BaseApiTest {

    @Test
    void getAllProducts(){

        requestSpec
            .when()
                .get("/products")
            .then() 
                .statusCode(200)
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("price", notNullValue());
    }
}
