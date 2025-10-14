package com.project.apitests.schema;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

import io.restassured.response.Response;

import com.project.apitests.BaseApiTest;
import com.project.utils.FileUtils;
import org.json.JSONObject;


public class ResponseSchemaTests extends BaseApiTest {

    @Test
    void getAllProducts(){
        requestSpec
            .when()
                .get("/products")
            .then() 
                .statusCode(200)
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("price", notNullValue());
    }

    @Test
    void getSingleProductById(){

        String productId = "239";

        Response response = requestSpec
            .when() 
                .get("products/" + productId)
            .then() 
                .statusCode(200)
                .extract().response();
        
        response.jsonPath().getString("id").equals(productId);
    }

    @Test
    void createNewProduct(){

        String jsonPath = "src/test/java/com/project/resources/data/newProduct.json";
        String body = FileUtils.readJsonFile(jsonPath);
        JSONObject jsonObject = new JSONObject(body);

        //TODO: We need to make first a GET to retrieve valid category ids, then modify the json file

        Response response = requestSpec
            .body(body)
            .when()
                .post("/products")
            .then() 
                .statusCode(201)
                .extract().response();
        
        response.jsonPath().getString("title").equals(jsonObject.get("title"));
        response.jsonPath().getString("price").equals(jsonObject.get("price"));
        response.jsonPath().getString("description").equals(jsonObject.get("description"));
        response.jsonPath().getString("category.id").equals(jsonObject.get("categoryId"));
    }
}
