//Name = Anmol Umesh Jawalkar
//Date = 3 September 

package com.example;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ApiTestSpecs {

	public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost:3939")
            .setContentType("application/json")
            .build();

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification notFoundResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .build();
}
