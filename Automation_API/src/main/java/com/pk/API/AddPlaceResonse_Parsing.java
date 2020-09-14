package com.pk.API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import utilities.Payload;
import utilities.RawJson;

public class AddPlaceResonse_Parsing {

	@Test
	public void parseResponse() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//Post request
		String response = given().log().all().queryParam("key", "qaclick123")
				.headers("Content-type", "application/json").body(Payload.addPlace()).when()
				.post("maps/api/place/add/json").then().assertThat()
				// have removed .log().all(). because i need to check whether the same response
				// will get stored under response variable
				.statusCode(200).body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.18 (Ubuntu)"))
				.extract().response().asString();
		// System.out.println(response);

		//JsonPath json = new JsonPath(response);
		//String placeId = json.getString("place_id");    OR   
		String placeId =RawJson.parseJsonFromString(response).getString("place_id"); 
		
		String address="125 Nellore, AP";
		//Put request
		given().log().all().queryParam("key", "qaclick123").headers("Content-type", "application/json")
				.body("{\r\n" + "    \"place_id\": \"" + placeId + "\",\r\n"
						+ "    \"address\":\""+address+"\",\r\n" + "    \"key\":\"qaclick123\"\r\n" + "     }")
				.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		//Get Request
		
		String getResponse=given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		String actualAddress=RawJson.parseJsonFromString(getResponse).getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, address);
		
	}
}
