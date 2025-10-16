package com.project.apitests.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.project.apitests.BaseApiTest;

import io.restassured.response.Response;

//TODO: parameterized tests for price range, limit, categoryId

public class RequestParametersTests extends BaseApiTest {
    @Test
    void validPriceRange() {

        int minPrice = 60;
        int maxPrice = 70;

        Response response = requestSpec
                .queryParam("price_min", minPrice)
                .queryParam("price_max", maxPrice)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .extract().response();

        response.jsonPath().getList("price").forEach(price -> {
            assertTrue((Integer) price >= minPrice && (Integer) price <= maxPrice,
                    "Price " + price + " is out of range");
        });
    }

    @Test
    void validPaginationLimit() {

        int limit = 10;

        Response response = requestSpec
                .queryParam("offset", 0)
                .queryParam("limit", limit)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .extract().response();

        int expectedSize = response.jsonPath().getList("id").size();
        assertEquals(limit, expectedSize);
    }

    @Test
    void validCategoryName() {

        int categoryId = 62;
        String expectedCategoryName = "Shoes";

        Response response = requestSpec
                .queryParam("categoryId", categoryId)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .extract().response();

        response.jsonPath().getList("category.name").forEach(name -> {
            assertEquals(expectedCategoryName, name);
        });
    }

}
