package com.project.utils;

import java.util.List;

import org.json.JSONObject;

import com.project.apitests.BaseApiTest;

import io.restassured.response.Response;

public class ApiUtils extends BaseApiTest {

    public static int getCategoryId(){
        Response response = requestSpec
            .when()
                .get("/categories")
            .then() 
                .statusCode(200)
                .extract().response();
        List<Integer> categoryIds = response.jsonPath().getList("id");
        return categoryIds.get(0);
    }

    public static String getCategoryName(int categoryId){
        Response response = requestSpec
            .when()
                .get("/categories/" + categoryId)
            .then() 
                .statusCode(200)
                .extract().response();
        return response.jsonPath().getString("name");
    }

       public static int createNewProduct(){

        String jsonPath = "src/test/java/com/project/resources/data/newProduct.json";
        String body = FileUtils.readJsonFile(jsonPath);
        JSONObject jsonObject = new JSONObject(body);

         // Get a valid categoryId from existing categories
        int categoryId = ApiUtils.getCategoryId();
        jsonObject.put("categoryId", categoryId);

        // Create a unique product name to avoid conflicts
        String uniqueProductName = DataUtils.createRandomProductName();
        jsonObject.put("title", uniqueProductName);


        Response response = requestSpec
            .body(jsonObject.toString())
            .when()
                .post("/products")
            .then() 
                .statusCode(201)
                .extract().response();
        
        return response.jsonPath().getInt("id");
    }

    public static void deleteProduct(int productId) {
        requestSpec
            .when()
                .delete("products/" + productId)
            .then()
                .statusCode(200);
    }
}
