package com.example.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

	private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {
        // Set up the base URI and request/response specifications
        RestAssured.baseURI = "http://localhost:4040";

        requestSpec = new RequestSpecBuilder()
            .setBaseUri(RestAssured.baseURI)
            .setContentType("application/json")
            .addHeader("Authorization", "Bearer your_token_here")  // Example authorization header
            .build();

        responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType("application/json")
            .build();
    }

    @Test
    public void testCreateProductWithQueryAndPathParameters() {
        String requestBody = "{\n" +
                "    \"name\": \"TestProduct\",\n" +
                "    \"category\": \"Electronics\",\n" +
                "    \"price\": 99.99,\n" +
                "    \"stockQuantity\": 50\n" +
                "}";

        //Headers
        Response response = given()
            .baseUri("http://localhost:4040")
            .header("Authorization", "Bearer your_token_here")
            .header("Content-Type", "application/json")
            .queryParam("discount", "10")
            .body(requestBody)
        .when()
            .post("/products")
        .then()
            .statusCode(201)  // Adjust status code based on your API
            .extract().response();
        
        // Log or verify the response details to confirm the product was created correctly
        response.prettyPrint();
    }

    @Test
    public void testGetProductWithQueryAndPathParameters() {
        int createdProductId = 6; 
        given()
            .baseUri("http://localhost:4040")
            .header("Authorization", "Bearer your_token_here")
            .header("Accept", "*/*")
            .queryParam("category", "Electronics")
        .when()
            .get("/products/" + createdProductId)
        .then()
            .statusCode(200)  // Adjust status code based on your API
            .body("category", equalTo("Electronics"))
            .log().all();
    }

}
