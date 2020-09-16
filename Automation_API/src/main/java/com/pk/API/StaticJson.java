package com.pk.API;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import java.nio.file.Files;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;
import utilities.RawJson;

public class StaticJson {

	@Test

	public void addBook() throws IOException

	{

		RestAssured.baseURI = "http://216.10.245.166";

		Response resp = given().

				header("Content-Type", "application/json").

				body(GenerateStringFromResource("G:\\PK_Practice\\Automation_API\\JSONFiles\\myJson_One.json")).

				when().

				post("/Library/Addbook.php").

				then().assertThat().statusCode(200).

				extract().response();

		JsonPath js = RawJson.rawToJson(resp);

		String id = js.get("ID");

		System.out.println(id);

	}

	public static String GenerateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}

}

