package com.project.apitests.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import com.project.apitests.BaseApiTest;
import com.project.utils.ApiUtils;
import com.project.utils.DataUtils;
import com.project.resources.endpoints.ApiEndpoints;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class RequestParametersTests extends BaseApiTest {
    @Feature("Request Parameters Validation")
    @Test
    @Tag("Regression")
    void validPriceRange() {
        //Restarting spec to avoid interference from other tests
        RequestSpecification spec = given().spec(requestSpec);

        int minPrice = DataUtils.createRandomMinPrice();
        int maxPrice = DataUtils.createRandomMaxPrice(minPrice);
        System.out.println("Testing price range: " + minPrice + " - " + maxPrice);

        Response response = spec
                .queryParam("price_min", minPrice)
                .queryParam("price_max", maxPrice)
                .when()
                .get(ApiEndpoints.PRODUCTS)
                .then()
                .statusCode(200)
                .extract().response();

        response.jsonPath().getList("price").forEach(price -> {
            assertTrue((Integer) price >= minPrice && (Integer) price <= maxPrice,
                    "Price " + price + " is out of range");
        });
    }

    @Feature("Request Parameters Validation")
    @Severity(io.qameta.allure.SeverityLevel.CRITICAL)
    @Test
    @Tag("Regression")
    void validPaginationLimit() {
        //Restarting spec to avoid interference from other tests
        RequestSpecification spec = given().spec(requestSpec);

        int limit = DataUtils.createRandomPageLimit();

        Response response = spec
                .queryParam("offset", 0)
                .queryParam("limit", limit)
                .when()
                .get(ApiEndpoints.PRODUCTS)
                .then()
                .statusCode(200)
                .extract().response();
     
        int expectedSize = response.jsonPath().getList("id").size();

        if (expectedSize > limit) {
            assertEquals(limit, expectedSize);
        } else {
            System.out.println("Not enough products. Total products less than the limit. Actual size: " + expectedSize);
        }
        
    }

    @Feature("Request Parameters Validation")
    @Test
    @Tag("Regression")
    @Tag("Sanity")
    void validCategoryName() {
        //Restarting spec to avoid interference from other tests
        RequestSpecification spec = given().spec(requestSpec);

        int categoryId = ApiUtils.getCategoryId();
        String expectedCategoryName = ApiUtils.getCategoryName(categoryId);

        Response response = spec
                .queryParam("categoryId", categoryId)
                .when()
                .get(ApiEndpoints.PRODUCTS)
                .then()
                .statusCode(200)
                .extract().response();

        response.jsonPath().getList("category.name").forEach(name -> {
            assertEquals(expectedCategoryName, name);
        });
    }

}
