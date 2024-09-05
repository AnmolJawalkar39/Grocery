//Name = Anmol Umesh Jawalkar
//Date = 4 September

package com.example;

import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OrderApiTests {

	@BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:3939";
    }

    @Test
    public void testCreateOrder() {
        String requestBody = "{ \"userId\": 1, \"productId\": 1, \"quantity\": 2, \"totalPrice\": 20.0 }";
        
        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/orders")
        .then()
            .statusCode(201)
            .body("userId", equalTo(1))
            .body("productId", equalTo(1));
    }

    @Test
    public void testGetOrder() {
        when()
            .get("/orders/1")
        .then()
            .statusCode(200)
            .body("userId", equalTo(1))
            .body("productId", equalTo(1));
    }
}