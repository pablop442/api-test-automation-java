package com.project.apitests.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.project.apitests.BaseApiTest;
import com.project.utils.ApiUtils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class RequestParametersTests extends BaseApiTest {
    @Test
    void validPriceRange() {
        //Restarting spec to avoid interference from other tests
        RequestSpecification spec = given().spec(requestSpec);

        int minPrice = 60;
        int maxPrice = 70;

        Response response = spec
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
        //Restarting spec to avoid interference from other tests
        RequestSpecification spec = given().spec(requestSpec);

        int limit = 10;

        Response response = spec
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
        //Restarting spec to avoid interference from other tests
        RequestSpecification spec = given().spec(requestSpec);

        int categoryId = ApiUtils.getCategoryId();
        String expectedCategoryName = ApiUtils.getCategoryName(categoryId);

        Response response = spec
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
