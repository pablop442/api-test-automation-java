package com.project.apitests.functional;

import com.project.utils.ApiUtils;
import com.project.utils.DataUtils;
import com.project.utils.FileUtils;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;

import com.project.apitests.BaseApiTest;

import io.qameta.allure.Feature;
import io.restassured.response.Response;

public class UpdateProductTests extends BaseApiTest {

    private int createdProductId;
    private JSONObject jsonObject;

    @BeforeEach
    void createData() {
        createdProductId = ApiUtils.createNewProduct();
        System.out.println("Created product with ID: " + createdProductId);

        // Get Json body from file
        String jsonPath = "src/test/java/com/project/resources/data/updateProduct.json";
        String body = FileUtils.readJsonFile(jsonPath);
        jsonObject = new JSONObject(body);

        // Create a unique product name to avoid conflicts
        String uniqueProductName = DataUtils.createRandomProductName();
        jsonObject.put("title", uniqueProductName);
    }

    @AfterEach
    void teardown() {
        ApiUtils.deleteProduct(createdProductId);
    }

    @Feature("Update Product Functionality")
    @Test
    void updateProduct() {
        Response response = requestSpec
                .body(jsonObject.toString())
                .when()
                .put("products/" + createdProductId)
                .then()
                .statusCode(200)
                .extract().response();

        response.then()
                .body("title", equalTo(jsonObject.getString("title")))
                .body("price", equalTo(jsonObject.getInt("price")));
    }

}
