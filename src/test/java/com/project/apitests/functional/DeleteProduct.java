package com.project.apitests.functional;

import com.project.apitests.BaseApiTest;
import com.project.utils.ApiUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

public class DeleteProduct extends BaseApiTest {

    private int createdProductId;

    @BeforeEach
    void createData() {
        createdProductId = ApiUtils.createNewProduct();
        System.out.println("Created product with ID: " + createdProductId);
    }

    @Test
    void deleteProduct() {
        Response response = requestSpec
                .when()
                .delete("products/" + createdProductId)
                .then()
                .statusCode(200)
                .extract().response();

        response.equals(true);
    }
}
