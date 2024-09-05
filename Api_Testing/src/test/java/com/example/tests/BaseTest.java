package com.example.tests;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.*;

public class BaseTest {

	protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost:4040";

        requestSpec = given()
                .contentType("application/json");

        responseSpec = expect()
                .statusCode(200)
                .contentType("application/json");
    }
}
