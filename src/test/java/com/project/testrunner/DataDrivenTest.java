package com.project.testrunner;

import com.project.datamodel.TestCase;
import com.project.datamodel.TestSuite;
import com.project.utils.ApiHandler;
import com.project.utils.HooksManager;
import com.project.utils.ValidationUtils;

import io.restassured.response.Response;
import io.qameta.allure.Allure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.ParameterizedTest;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public class DataDrivenTest extends BaseApiTest {

    private final ApiHandler apiHandler = new ApiHandler();
    private final ValidationUtils validationUtils = new ValidationUtils();

    private static final String TEST_DATA_PATH = "src/test/java/com/project/resources/testdata/api_tests.yaml";

    public static Stream<TestCase> getTestData() {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TestSuite testSuite = mapper.readValue(new File(TEST_DATA_PATH), TestSuite.class);

            //Tag reading logic
            String tagFilter = System.getProperty("test.tag");
            if(tagFilter != null && !tagFilter.trim().isEmpty()){
                System.out.println("Filtering test cases with tag: " + tagFilter);
                return testSuite.getTestCases().stream()
                        .filter(testCase -> testCase.getTag() != null && testCase.getTag().contains(tagFilter));
            }

            return testSuite.getTestCases().stream();

        } catch (IOException e) {
            System.err.println("FATAL: Error loading test data from YAML: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Could not load test data" + e);
        }
    }

    @ParameterizedTest(name = "TC-{index}: {0}")
    @MethodSource("getTestData")
    void genericApiTestRunner(TestCase testCase) {

        // Set Allure test case name
        Allure.getLifecycle().updateTestCase(result -> result.setName(testCase.getName()));

        // Handle preconditions
        if(testCase.getPrecondition() != null){
            HooksManager.executePrecondition(testCase.getPrecondition(), testCase);
        }

        Response response = apiHandler.executeRequest(testCase);
        validationUtils.validateApiResponse(response, testCase);
    }

}
