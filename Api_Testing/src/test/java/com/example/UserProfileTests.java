//Name = Anmol Umesh Jawalkar
//Date = 4 September

package com.example;

import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserProfileTests {

	@Test
    public void testUpdateUser() {
        String requestBody = "{ \"username\": \"UpdatedAnmol\", \"email\": \"updatedanmol@example.com\" }";
        
        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .put("/users/1")
        .then()
            .statusCode(200)
            .body("username", equalTo("UpdatedAnmol"))
            .body("email", equalTo("updatedanmol@example.com"));
    }

    @Test
    public void testDeleteUser() {
        when()
            .delete("/users/1")
        .then()
            .statusCode(204);
    }
}
