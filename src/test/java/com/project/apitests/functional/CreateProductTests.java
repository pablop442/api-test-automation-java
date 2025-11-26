package com.project.apitests.functional;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;

import com.project.apitests.BaseApiTest;
import com.project.utils.FileUtils;
import com.project.utils.ApiUtils;
import com.project.utils.DataUtils;
import com.project.resources.endpoints.ApiEndpoints;

import io.qameta.allure.Feature;
import io.restassured.response.Response;

public class CreateProductTests extends BaseApiTest {

    private JSONObject jsonObject;
    private int createdProductId;

    @BeforeEach
    void handleData() {
        // Get Json body from file
        String jsonPath = "src/test/java/com/project/resources/data/newProduct.json";
        String body = FileUtils.readJsonFile(jsonPath);
        jsonObject = new JSONObject(body);

        // Get a valid categoryId from existing categories
        int categoryId = ApiUtils.getCategoryId();
        jsonObject.put("categoryId", categoryId);

        // Create a unique product name to avoid conflicts
        String uniqueProductName = DataUtils.createRandomProductName();
        jsonObject.put("title", uniqueProductName);

    }

    @AfterEach
    void teardown() {
        ApiUtils.deleteProduct(createdProductId);
    }

    
    @Feature("Create Product Functionality")
    @Test
    void createNewProduct() {

        Response response = requestSpec
                .body(jsonObject.toString())
                .when()
                .post(ApiEndpoints.PRODUCTS)
                .then()
                .statusCode(201)
                .extract().response();

        response.then()
                .body("title", equalTo(jsonObject.getString("title")))
                .body("price", equalTo(jsonObject.getInt("price")))
                .body("description", equalTo(jsonObject.getString("description")))
                .body("category.id", equalTo(jsonObject.getInt("categoryId")));

        createdProductId = response.jsonPath().getInt("id");
    }
}
