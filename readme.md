# 🖥️ API Automation Tests
This project automates API testing using **Java 23**, **JUnit 5**, **Maven**, and **Rest Assured**.  
The goal is to verify the correct behavior of  [Fake Store](https://fakeapi.platzi.com) public API by covering the following scenarios:
- Schema validation: Validate response schema depending on the parameters of the request.
- Content validation: Validate if response content is correct according to API documentation.
- Authentication validation: Validate API security and correct API key behavior.

## 📊 CRUD Operations in the Project
- CRUD is a simple way to describe the four basic things you can do with information in the API: Create, Read, Update, and Delete. 
- Tests in this project focus on these four actions for **/products** endpoints.
- Tests make sure the API behaves correctly every time one of these actions is performed.
- This includes verifying the returnde prodcut information, the structure of the response, and the status codes.

## 🧩 Endpoints coverage

#### Create Product
Adding new product to the Fake Store. Verifies:
- The product was successfully created.
- The response contains the same information that was sent.
- The API returns the correct status code (201).

#### Update Product
Modifies existing product. Verifies:
- The API accepts the update.
- The returned product reflects correct new values.
- The correct status is returned (200).

#### Delete Product
Removes the product from the system. Verifies:
- The product is no longer available.
- The API responds with correct confirmation.
- The API returns the correct status code (200).


## 🛠️ Project Setup
- **Java:** 23  
- **Build tool:** Maven  
- **Test framework:** JUnit 5  
- **HTTP client/testing:** Rest Assured 

## 🏁 Execute the Tests
- Clone the repository.
`git clone`
-  Remove old build artifacts.
`mvn clean`
- Build project and install dependencies.
`mvn install`
- Run tests.
`mvn test`

## 📗 Test Reports
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

### ℹ️ About Platzi Fake Store API
For more information and documentation regarding the test API, visit: [Fake Store](https://fakeapi.platzi.com)