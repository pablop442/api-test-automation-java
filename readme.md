# API Automation Tests
This project automates API testing using **Java 23**, **JUnit 5**, **Maven**, and **Rest Assured**.  
The goal is to verify the correct behavior of  [Aviationstack](https://aviationstack.com/) public API.
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
#### API Key
To access the API (free tier) you will need to sign up and get an API key. The API key should not be hardcoded, instead store it in an .env file:
```java
API_KEY=your_api_key_here
BASE_URL=https://api.aviationstack.com/v1
```
The project loads this automatically via `dotenv-java` (see Config.java file).