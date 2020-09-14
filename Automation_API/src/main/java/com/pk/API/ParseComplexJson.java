package com.pk.API;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import utilities.RawJson;
import utilities.Payload;

public class ParseComplexJson {
	
	@Test
	public void parseComlexJson() {
		JsonPath js = RawJson.parseJsonFromString(Payload.complexJson());

		// Print No of courses returned by API
		int courses = js.getInt("courses.size()");

		System.out.println(courses);

		// Print Purchase Amount

		int purAmnt = js.getInt("dashboard.purchaseAmount");

		System.out.println(purAmnt);

		// Print Title of the first course

		String firstCourseTitle = js.get("courses[0].title");
		//String secondCourseTitle = js.get("courses[1].title");
		//String thirdCourseTitle = js.get("courses[2].title");

		System.out.println(firstCourseTitle);
		
		//Print All course titles and their respective Prices
		int totalPrice=0;
		if (courses != 0) {
			for (int i = 0; i < courses; i++) {
               String courseTitle=js.get("courses["+i+"].title");
               System.out.println(i+" title is "+courseTitle);
             //Print no of copies sold by RPA Course
               if(courseTitle.equals("RPA"))
               {
            	 int rpaCopies =js.getInt("courses["+i+"].copies");
            	 System.out.println(" No of copies sold by RPA Course is "+rpaCopies);
               }
               int copies =js.getInt("courses["+i+"].copies");
               int coursePrice=js.getInt("courses["+i+"].price");
               totalPrice+=coursePrice*copies;
               System.out.println(i+" price is "+coursePrice);
               
			}
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		
		Assert.assertEquals(purAmnt, totalPrice);
	}

}
