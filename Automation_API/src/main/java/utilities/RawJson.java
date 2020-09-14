package utilities;

import io.restassured.path.json.JsonPath;

public class RawJson {

	public static JsonPath parseJsonFromString(String response) {
		
		JsonPath json=new JsonPath(response);
		return json;
	}
}
