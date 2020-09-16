package utilities;

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
}
