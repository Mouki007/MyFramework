package com.pk.utilities;

public class Payload {

	public static String addPlace() {
		return "{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" + "    \"lng\": 33.427362\r\n"
				+ "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"Rahul house\",\r\n"
				+ "  \"phone_number\": \"(+91) 000 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n" + "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n" + "  ],\r\n" + "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"Telugu\"\r\n" + "}";
	}

	public static String complexJson() {

		return "{\r\n" + "\r\n" + "		\"dashboard\": {\r\n" + "\r\n" + "\"purchaseAmount\": 910,\r\n" + "\r\n"
				+ "		\"website\": \"rahulshettyacademy.com\"\r\n" + "\r\n" + "		},\r\n" + "\r\n"
				+ "		\"courses\": [\r\n" + "\r\n" + "		{\r\n" + "\r\n"
				+ "		\"title\": \"Selenium Python\",\r\n" + "\r\n" + "		\"price\": 50,\r\n" + "\r\n"
				+ "		\"copies\": 6\r\n" + "\r\n" + "		},\r\n" + "\r\n" + "		{\r\n" + "\r\n"
				+ "		\"title\": \"Cypress\",\r\n" + "\r\n" + "		\"price\": 40,\r\n" + "\r\n"
				+ "		\"copies\": 4\r\n" + "\r\n" + "		},\r\n" + "\r\n" + "		{\r\n" + "\r\n"
				+ "		\"title\": \"RPA\",\r\n" + "\r\n" + "		\"price\": 45,\r\n" + "\r\n"
				+ "		\"copies\": 10\r\n" + "\r\n" + "		}\r\n" + "\r\n" + "		]\r\n" + "\r\n" + "		}";
	}

	public static String addBook() {

		String jsonBook = "{\r\n" + "\r\n" + "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\"bcd\",\r\n" + "\"aisle\":\"227\",\r\n" + "\"author\":\"John foe\"\r\n" + "}\r\n" + "";
		return jsonBook;
	}

	public static String addBook(String isbnValue, String aisleValue) {

		String jsonBook = "{\r\n" + "\r\n" + "\"name\":\"Learn Appium Automation with Java\",\r\n" + "\"isbn\":\""
				+ isbnValue + "\",\r\n" + "\"aisle\":\"" + aisleValue + "\",\r\n" + "\"author\":\"John foe\"\r\n"
				+ "}\r\n" + "";
		return jsonBook;
	}

}
