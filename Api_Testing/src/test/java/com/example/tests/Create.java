package com.example.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Create {

	@Test
    public void testCreateProductWithQueryAndPathParameters() {
        String requestBody = "{\n" +
                "    \"name\": \"TestProduct\",\n" +
                "    \"category\": \"Electronics\",\n" +
                "    \"price\": 99.99,\n" +
                "    \"stockQuantity\": 50\n" +
                "}";

        given()
            .baseUri("http://localhost:4040")
            .header("Authorization", "Bearer your_token_here")
            .header("Content-Type", "application/json")
            .queryParam("discount", "10")
            .body(requestBody)
        .when()
            .post("/products")
        .then()
            .statusCode(201)  // Adjust status code based on your API
            .log().all();
    }

    @Test
    public void testGetProductWithQueryAndPathParameters() {
        given()
            .baseUri("http://localhost:4040")
            .header("Authorization", "Bearer your_token_here")
            .header("Accept", "*/*")
            .queryParam("category", "Electronics")
        .when()
            .get("/products/1")
        .then()
            .statusCode(200)  // Adjust status code based on your API
            .body("category", equalTo("Electronics"))  // Adjust based on your expected value
            .log().all();
    }
}
