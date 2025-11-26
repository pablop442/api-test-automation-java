package com.project.apitests.schema;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import io.qameta.allure.Feature;
import io.restassured.response.Response;

import com.project.apitests.BaseApiTest;
import com.project.utils.ApiUtils;
import com.project.resources.endpoints.ApiEndpoints;


public class ResponseSchemaTests extends BaseApiTest {

    @Feature("Response Schema Validation")
    @Test
    void getAllProducts(){
        requestSpec
            .when()
                .get(ApiEndpoints.PRODUCTS)
            .then() 
                .statusCode(200)
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("price", notNullValue());
    }

    @Feature("Response Schema Validation")
    @Test
    void getSingleProductById(){

        final int productId = ApiUtils.createNewProduct();

        Response response = requestSpec
            .when() 
                .get(ApiEndpoints.PRODUCTS + "/" + productId)
            .then() 
                .statusCode(200)
                .extract().response();
        
        response.then()
            .body("id", equalTo(productId));

        ApiUtils.deleteProduct(productId);
    }

    @Feature("Response Schema Validation")
    @Test 
    void getAllUsers(){
        requestSpec
            .when()
                .get(ApiEndpoints.USERS)
            .then() 
                .statusCode(200)
                .body("id", notNullValue())
                .body("email", notNullValue())
                .body("name", notNullValue())
                .body("password", notNullValue());
    }

    
}
