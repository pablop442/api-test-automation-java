package com.project.utils;

import java.util.Map;

import org.json.JSONObject;

import com.project.datamodel.TestCase;
import com.project.resources.endpoints.ApiEndpoints;
import com.project.testrunner.BaseApiTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiHandler extends BaseApiTest {

    public Response executeRequest(TestCase testCase) {

        final ApiEndpoints apiEndpoints = new ApiEndpoints();

        RequestSpecification request = RestAssured.given().spec(requestSpec);

        if (testCase.getRequestBodyFile() != null) {

            String jsonPath = "src/test/java/com/project/resources/data/" + testCase.getRequestBodyFile();
            String body = FileUtils.readJsonFile(jsonPath);
            JSONObject jsonObject = new JSONObject(body);

            // Get a valid categoryId from existing categories
            int categoryId = ApiUtils.getCategoryId();
            jsonObject.put("categoryId", categoryId);

            // Create a unique product name to avoid conflicts
            String uniqueProductName = DataUtils.createRandomProductName();
            jsonObject.put("title", uniqueProductName);

            // Update test case yaml bodyUpdates with api values
            if (testCase.getBodyUpdates() != null && !testCase.getBodyUpdates().isEmpty()) {
                testCase.setBodyUpdates(Map.of(
                        "categoryId", categoryId,
                        "title", uniqueProductName));
            }

            request = request.body(jsonObject.toString());
        }

        if (testCase.getHeaders() != null) {
            request = request.headers(testCase.getHeaders());
        }

        if (testCase.getPathParams() != null && !testCase.getPathParams().isEmpty()) {
            request = request.pathParams(testCase.getPathParams());
        }

        if (testCase.getQueryParams() != null) {
            request = request.queryParams(testCase.getQueryParams());
        }

        String fullEndpointPath = apiEndpoints.getEndpoint(testCase.getEndpointKey());

        switch (testCase.getHttpMethod().toUpperCase()) {
            case "GET":
                return request.when().get(fullEndpointPath);
            case "POST":
                return request.when().post(fullEndpointPath);
            case "PUT":
                return request.when().put(fullEndpointPath);
            case "DELETE":
                return request.when().delete(fullEndpointPath);
            default:
                throw new IllegalArgumentException(
                        "Unsupported HTTP Method: " + testCase.getHttpMethod().toUpperCase());
        }

    }
}
