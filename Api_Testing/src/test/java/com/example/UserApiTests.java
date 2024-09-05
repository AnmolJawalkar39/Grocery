//Name = Anmol Umesh Jawalkar
//Date = 4 September

package com.example;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserApiTests {

	@Test
    public void testUserRegistration() {
        given()
            .spec(ApiTestSpecs.requestSpec)
            .body("{ \"username\": \"testUser\", \"password\": \"Test@123\", \"email\": \"testuser@example.com\" }")
        .when()
            .post("/users/register")
        .then()
            .statusCode(201)
            .body("username", equalTo("testUser"))
            .body("email", equalTo("testuser@example.com"));
    }

    @Test
    public void testUserLogin() {
        given()
            .spec(ApiTestSpecs.requestSpec)
            .body("{ \"username\": \"Anmol\", \"password\": \"Abc@123\" }")
        .when()
            .post("/users/login")
        .then()
            .statusCode(200)
            .body("message", equalTo("Login successful"));
    }

    @Test
    public void testInvalidUserLogin() {
        given()
            .spec(ApiTestSpecs.requestSpec)
            .body("{ \"username\": \"InvalidUser\", \"password\": \"WrongPass\" }")
        .when()
            .post("/users/login")
        .then()
            .statusCode(401)
            .body("message", equalTo("Invalid credentials"));
    }
}
