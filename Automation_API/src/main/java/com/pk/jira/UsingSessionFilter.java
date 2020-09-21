package com.pk.jira;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class UsingSessionFilter {
	public static String sessionCookie;

	@Test
	public void usingSessinFilter() {
		RestAssured.baseURI = "http://localhost:8081";
		SessionFilter sessionFilter = new SessionFilter();
		String resp = given().log().all().header("Content-Type", "application/json")
				.body("{ \"username\": \"vpsp012\", \"password\": \"jira@2020\" }").filter(sessionFilter).when().post("/rest/auth/1/session")
				.then().log().all().statusCode(200).extract().response().asString();

		JsonPath jp = new JsonPath(resp);

		System.out.println(jp.getString("session.name"));
		System.out.println(jp.getString("session.value"));

		//sessionCookie = jp.getString("session.name") + "=" + jp.getString("session.value");
		//System.out.println(sessionCookie);

		String response = given().log().all().header("Content-Type", "application/json").filter(sessionFilter)//.header("Cookie", sessionCookie)
				.body("{" + "\"fields\": {" + "\"project\":{" + "\"key\": \"SER\"" + "},"
						+ "\"summary\": \"Create a bug on home page\","
						+ "\"description\": \"creating of an issue using project key and issue type names using the rest api\","
						+ "\"issuetype\": {" + "\"name\": \"Bug\"" + "}" + "}}")
				.when().post("/rest/api/2/issue").then().log().all().statusCode(201).extract().asString();
		JsonPath jsnppath = new JsonPath(response);
		System.out.println(jsnppath.getString("id"));
	}

}
