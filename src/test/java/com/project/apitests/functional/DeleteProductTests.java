package com.project.apitests.functional;

import com.project.apitests.BaseApiTest;
import com.project.utils.ApiUtils;
import com.project.resources.endpoints.ApiEndpoints;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import io.qameta.allure.Feature;
import io.restassured.response.Response;

public class DeleteProductTests extends BaseApiTest {

    private int createdProductId;

    @BeforeEach
    void createData() {
        createdProductId = ApiUtils.createNewProduct();
        System.out.println("Created product with ID: " + createdProductId);
    }

    @Feature("Delete Product Functionality")
    @Test
    @Tag("Regression")
    void deleteProduct() {
        Response response = requestSpec
                .when()
                .delete(ApiEndpoints.PRODUCTS + "/" + createdProductId)
                .then()
                .statusCode(200)
                .extract().response();

        response.equals(true);
    }
}
