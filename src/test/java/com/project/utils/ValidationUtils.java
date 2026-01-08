package com.project.utils;

import org.hamcrest.Matchers;
import org.hamcrest.Matcher;
import io.restassured.response.Response;
import com.project.datamodel.TestCase;
import com.project.datamodel.Validation;

import static org.hamcrest.Matchers.*;

public class ValidationUtils {

    public void validateApiResponse(Response response, TestCase testCase) {
        
        response.then().statusCode(testCase.getExpectedStatusCode());

        if (testCase.getResponseValidations() != null) {
            for (Validation validation : testCase.getResponseValidations()) {
                String field = validation.getField();
                Object expected = validation.getExpected();
                String operator = validation.getOperator();

                Matcher<?> matcher = getMatcher(operator, expected);

                response.then().body(field, matcher).log().ifValidationFails();
            }
        }
    }

    private Matcher<?> getMatcher(String operator, Object expected){
        if(operator == null){
            return Matchers.equalTo(expected);
        }
        return switch (operator){
            case "greaterThan" -> greaterThan((Comparable) expected);
            case "lessThan" -> lessThan((Comparable) expected);
            case "greaterThanOrEqualTo" -> greaterThanOrEqualTo((Comparable) expected);
            case "lessThanOrEqualTo" -> lessThanOrEqualTo((Comparable) expected);
            case "contains" -> contains((Comparable) expected);
            default -> equalTo(expected);
        };

    }

}
