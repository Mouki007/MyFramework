package com.pk.utilities;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RawJson {

	public static JsonPath parseJsonFromString(String response) {
		
		JsonPath json=new JsonPath(response);
		return json;
	}
	public static JsonPath rawToJson(Response response) {
		
		JsonPath json=new JsonPath(response.asString());
		return json;
	}
	public static String getSessionKey() {
		String resp = given().log().all().header("Content-Type", "application/json")
				.body("{ \"username\": \"vpsp012\", \"password\": \"jira@2020\" }").when().post("/rest/auth/1/session")
				.then().log().all().statusCode(200).extract().response().asString();

		JsonPath jp = new JsonPath(resp);

		System.out.println(jp.getString("session.name"));
		System.out.println(jp.getString("session.value"));

		return jp.getString("session.name") + ":" + jp.getString("session.value");
	}
}
