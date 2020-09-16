package com.pk.API;

import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;
import utilities.Payload;
import utilities.RawJson;

public class DynamicJson {

	// pass the single hard coded values into dynamic json file
	@Test
	public void addBook()

	{

		RestAssured.baseURI = "http://216.10.245.166";

		Response resp = given().

				header("Content-Type", "application/json").

				body(Payload.addBook("axyfe", "777")).

				when().

				post("/Library/Addbook.php").

				then().assertThat().statusCode(200).

				extract().response();

		JsonPath js = RawJson.rawToJson(resp);

		String id = js.get("ID");

		System.out.println(id);

	}

// pass the multiple hard coded values into dynamic json file using data provider
	@Test(dataProvider = "BooksData")

	public void addBook(String isbnValue, String aisleValue) {

		RestAssured.baseURI = "http://216.10.245.166";

		Response resp = given().

				header("Content-Type", "application/json").

				body(Payload.addBook(isbnValue, aisleValue)).

				when().

				post("/Library/Addbook.php").

				then().assertThat().statusCode(200).

				extract().response();

		JsonPath js = RawJson.rawToJson(resp);

		String id = js.get("ID");

		System.out.println(id);

	}

	@DataProvider(name = "BooksData")

	public Object[][] getBooksData() {
		return new Object[][] { { "evemu", "2345" }, { "nkari", "324" }, { "eshbi", "986" } };

	}

	// array=collection of elements

	// multidimensional array= collection of arrays
}
