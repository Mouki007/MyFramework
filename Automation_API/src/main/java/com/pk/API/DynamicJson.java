package com.pk.API;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import com.pk.utilities.Payload;
import com.pk.utilities.RawJson;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;

public class DynamicJson {
	String id="";
	// pass the single hard coded values into dynamic json file
	@Test
	public void addBook()

	{

		RestAssured.baseURI = "http://216.10.245.166";

		Response resp = given().

				header("Content-Type", "application/json").

				body(Payload.addBook("axyft", "735")).

				when().

				post("/Library/Addbook.php").

				then().assertThat().statusCode(200).

				extract().response();

		JsonPath js = RawJson.rawToJson(resp);

		 id = js.get("ID");

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

		id = js.get("ID");

		System.out.println(id);

	}
	
	@AfterMethod
	
	public void deleteBook() {
		//Delete book
		
				Response deleteresp = given().

						header("Content-Type", "application/json").

						body("{\r\n" + 
								" \r\n" + 
								"\"ID\" : \""+id+"\"\r\n" + 
								" \r\n" + 
								"}\r\n" + 
								"").

						when().

						post("/Library/DeleteBook.php").

						then().assertThat().statusCode(200).

						extract().response();

				JsonPath js1 = RawJson.rawToJson(deleteresp);
				System.out.println(js1.getString("msg"));
	}

	@DataProvider(name = "BooksData")

	public Object[][] getBooksData() {
		return new Object[][] { { "evmu", "2345" }, { "nkri", "324" }, { "ehbi", "986" } };

	}

	// array=collection of elements

	// multidimensional array= collection of arrays
}
