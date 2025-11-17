# API Automation Tests
This project automates API testing using **Java 23**, **JUnit 5**, **Maven**, and **Rest Assured**.  
The goal is to verify the correct behavior of  [Fake Store](https://fakeapi.platzi.com) public API by covering the following scenarios:
- Schema validation: Validate response schema depending on the parameters of the request.
- Content validation: Validate if response content is correct according to API documentation.
- Authentication validation: Validate API security and correct API key behavior.

## Test Coverage
- Tests cover basic CRUD operations from the **/products** endpoints.
- Tests validate the correctness of the content and format of the response body.
- Test Data is generated dynamically from Faker library to ensure more coverage.

## Project Setup
- **Java:** 23  
- **Build tool:** Maven  
- **Test framework:** JUnit 5  
- **HTTP client/testing:** Rest Assured 

## Execute the Tests
- Clone the repository.
`git clone`
-  Remove old build artifacts.
`mvn clean`
- Build project and install dependencies.
`mvn install`
- Run tests.
`mvn test`

## Test Reports
Easily generate user friendly Test Reports thanks to the integration with **Allure Reports**
- Run Tests and generate the report.  
`./execute_tests.sh`
- Historic executions are saved in 'allure-results' folder.
- Report is automatically open after each execution.

#### Credentials
To access the API you will need the generic user name and password.
```java
USER_EMAIL=john@mail.com
PASSWORD=changeme 
```
The project loads this automatically via `dotenv-java` (see Config.java file).

### About Platzi Fake Store API
For more information and documentation regarding the test API, visit: [Fake Store](https://fakeapi.platzi.com)