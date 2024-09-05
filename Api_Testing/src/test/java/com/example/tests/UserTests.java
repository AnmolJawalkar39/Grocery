//Name = Anmol Umesh Jawalkar
// Date = 5 September

package com.example.tests;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserTests extends BaseTest {

	// Invalid login
	@Test
    public void testUserRegistration() {
        String requestBody = "{ \"username\": \"TestUser\", \"password\": \"Test@123\", \"email\": \"testuser@example.com\" }";

        given()
            .spec(requestSpec)
            .body(requestBody)
        .when()
            .post("/users/register")
        .then()
            .statusCode(201)  // Expecting 201 Created for successful registration
            .body("username", equalTo("TestUser"));
    }
	
	// valid Login
    @Test
    public void testUserLogin() {
        String requestBody = "{ \"username\": \"TestUser\", \"password\": \"Test@123\" }";

        given()
            .spec(requestSpec)
            .body(requestBody)
        .when()
            .post("/users/login")
        .then()
            .statusCode(200)  // Expecting 200 OK for successful login
            .body("message", equalTo("Login successful"));
    }

 // Step : Get All Data
    @Test
    public void testGetAllData() {
        when()
            .get("/data")
        .then()
            .spec(responseSpec)
            .body("users.size()", greaterThan(0));
    }
    @Test
    public void testCreateUserAndPlaceOrder() {
        // Step 1: Register a User
        String requestBody = "{ \"username\": \"EndToEndUser\", \"password\": \"Pass@123\", \"email\": \"endtoend@example.com\" }";
        Response registerResponse = given()
                .spec(requestSpec)
                .body(requestBody)
                .post("/users/register");

        int userId = registerResponse.jsonPath().getInt("id");

        // Step 2: Login the User
        String loginBody = "{ \"username\": \"EndToEndUser\", \"password\": \"Pass@123\" }";
        given()
                .spec(requestSpec)
                .body(loginBody)
                .post("/users/login")
                .then()
                .statusCode(200)
                .body("message", equalTo("Login successful"));

        // Step 3: Place an Order
        String orderBody = "{ \"userId\": " + userId + ", \"productId\": 1, \"quantity\": 2, \"totalPrice\": 20.0 }";
        given()
                .spec(requestSpec)
                .body(orderBody)
                .post("/orders")
                .then()
                .statusCode(201);
    }
}