package com.project.datamodel;

import java.util.List;
import java.util.Map;

public class TestCase {
    //ss
    private String name;
    private String httpMethod;
    private String endpointKey;
    private Map<String, Object> pathParams;
    private Map<String, Object> queryParams;
    private Map<String, String> headers;
    private String precondition;
    private String requestBodyFile;
    private int expectedStatusCode;
    private List<Validation> responseValidations;
    private List<String> tag;
    private Map<String, Object> bodyUpdates;

    public TestCase(){

    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

     public String getHttpMethod(){
        return httpMethod;
    }

    public String getEndpointKey(){
        return endpointKey;
    }

     public Map<String, Object> getPathParams(){
        return pathParams;
    }

     public Map<String, Object> getQueryParams(){
        return queryParams;
    }

    public Map<String, String> getHeaders(){
        return headers;
    }

    public String getPrecondition(){
        return precondition;
    }

     public String getRequestBodyFile(){
        return requestBodyFile;
    }

     public int getExpectedStatusCode(){
        return expectedStatusCode;
    }

    public List<Validation> getResponseValidations(){
        return responseValidations;
    }

    public List<String> getTag(){
        return tag;
    }

    public Map<String, Object> getBodyUpdates(){
        return bodyUpdates;
    }

    public String setName(String name){
        return this.name = name;
    }

     public String setHttpMethod(String httpMethod){
        return this.httpMethod = httpMethod;
    }

    public String setEndpointKey(String endpointKey){
       return this.endpointKey = endpointKey;
    }

     public Map<String, Object> setPathParams(Map<String, Object> pathParams){
        return this.pathParams = pathParams;
    }

    public Map<String,Object> setQueryParams(Map<String, Object> queryParams){
        return this.queryParams = queryParams;
    }

    public Map<String,String> setHeaders(Map<String, String> headers){
        return this.headers = headers;
    }
    
    public String setPrecondition(String precondition){
        return this.precondition = precondition;
    }

     public String setRequestBodyFile(String requestBodyFile){
        return this.requestBodyFile = requestBodyFile;
    }

     public int setExpectedStatusCode(int expectedStatusCode){
        return this.expectedStatusCode = expectedStatusCode;
    }

    public List<Validation> setResponseValidations(List<Validation> responseValidations){
        return this.responseValidations = responseValidations;
    }

     public List<String> setTag(List<String> tag){
        return this.tag = tag;
    }

    public Map<String, Object> setBodyUpdates(Map<String, Object> bodyUpdates){
        return this.bodyUpdates = bodyUpdates;
    }



    
}
