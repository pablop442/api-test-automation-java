package com.project.utils;

import com.project.datamodel.TestCase;
import com.project.testrunner.BaseApiTest;

public class HooksManager extends BaseApiTest {
    public static void executePrecondition(String precondition, TestCase testCase) {
        if (precondition == null || precondition.isEmpty()) {
            return;
        }
        switch (precondition) {
            case "createProduct" -> {
                int productId = ApiUtils.createNewProduct();
                testCase.getPathParams().put("productId", productId);
            }
            case "deleteProduct" -> {
                int productId = (int) testCase.getPathParams().get("productId");
                if (testCase.getPathParams() == null) {
                    testCase.setPathParams(new java.util.HashMap<>());
                }
                ApiUtils.deleteProduct(productId);
            }
        }
    }
}
