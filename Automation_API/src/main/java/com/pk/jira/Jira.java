package com.pk.jira;

import org.testng.annotations.Test;

import com.pk.utilities.RawJson;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class Jira {
	
	
	@Test
	public void JiraAPICreateIssue()
	{
		RestAssured.baseURI = "http://localhost:8081";
		//Creating Issue/Defect
		String resp = given().log().all().header("Content-Type", "application/json")
				.body("{ \"username\": \"vpsp012\", \"password\": \"jira@2020\" }").when().post("/rest/auth/1/session")
				.then().log().all().statusCode(200).extract().response().asString();

		JsonPath jp = new JsonPath(resp);

		System.out.println(jp.getString("session.name"));
		System.out.println(jp.getString("session.value"));

		String sessionKey= jp.getString("session.name") + "=" + jp.getString("session.value");
		System.out.println(sessionKey);
		
		RestAssured.baseURI= "http://localhost:8081";
		Response res=given().header("Content-Type", "application/json").
		header("Cookie",sessionKey).
		body("{"+
    "\"fields\": {"+
       "\"project\":{"+
          "\"key\": \"SER\""+
       "},"+
       "\"summary\": \"Create a bug on home page\","+
       "\"description\": \"creating of an issue using project key and issue type names using the rest api\","+
       "\"issuetype\": {"+
          "\"name\": \"Bug\""+
       "}"+
   "}}").when().
		post("/rest/api/2/issue").then().statusCode(201).extract().response();
		
		   JsonPath js= RawJson.rawToJson(res);
		   String id=js.get("id");
		   System.out.println(id);
		
				
		
		
		
		}
}
