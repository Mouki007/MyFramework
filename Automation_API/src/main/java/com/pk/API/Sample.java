package com.pk.API;

import org.testng.annotations.Test;


import io.restassured.RestAssured;
import utilities.Payload;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Sample {

	@Test
	public void verifyPostRequeest() {
		//Validate if ADD place API is working as expected
		
		//given-- give all input details under given statement
		//when-- submit the API
		//then-- Validate the response code
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").headers("Content-type","application/json")
		.body(Payload.addPlace())
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat()
		.statusCode(200)
		.body("scope",equalTo("APP"))
		.header("Server",equalTo("Apache/2.4.18 (Ubuntu)"));
	}
}
