package com.pk.API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import java.nio.file.Files;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.pk.utilities.RawJson;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

public class StaticJson {

	@Test

	public void addBook() throws IOException

	{

		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").

				body(GenerateStringFromResource("G:\\PK_Practice\\Automation_API\\JSONFiles\\myJson_One.json")).

				when().post("maps/api/place/add/json")
				.then().log().all().assertThat()
				.statusCode(200)
				.body("scope",equalTo("APP")).header("Server",equalTo("Apache/2.4.18 (Ubuntu)"))

				.extract().response().asString();
		JsonPath js = RawJson.parseJsonFromString(response);
		
		String place_id = js.get("place_id");

		System.out.println(place_id);

	}

	public static String GenerateStringFromResource(String path) throws IOException {

		//System.out.println(new String(Files.readAllBytes(Paths.get(path))));
		return new String(Files.readAllBytes(Paths.get(path)));

	}

}

