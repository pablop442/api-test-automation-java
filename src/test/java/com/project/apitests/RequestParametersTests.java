package com.project.apitests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.restassured.response.Response;

public class RequestParametersTests {
    @Test
    void validFlightPagination(){

        int resultLimit = 90;

       Response response = given()
                .queryParam("access_key", Config.API_KEY)
                .queryParam("limit", resultLimit)
            .when()
                .get(Config.BASE_URL + "/flights")
            .then()
                .statusCode(200)
                .body("pagination.limit", equalTo(resultLimit))
                .extract().response();

        int paginationCount = response.path("pagination.count");
        int dataSize = response.path("data.size()");

        assertEquals(dataSize, paginationCount, "Pagination count should be equal to the number of flights in the data array");
        assertTrue(paginationCount <= resultLimit, "The pagination count should always be less or equal the limit passed in the parameters");
    }
}
