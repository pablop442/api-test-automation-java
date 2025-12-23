package com.project.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TestSuite {
    @JsonProperty("testCases")
    private List<TestCase> testCases;

    public List<TestCase> getTestCases(){
        return testCases;
    }
    
}
