import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Validate if Add Place API works
		//Given: All Input details, When: Submit the API(resourse and http), Then: Validate the response
		// Comment any one of the below part while running
		RestAssured.baseURI = "https://rahulshettyacademy.com";
//		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
//		.body(payload.AddPlace())
//		.when().post("/maps/api/place/add/json")
//		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
//		.header("server", "Apache/2.4.18(Ubuntu)");
		
		//Add Place --> Update place with new address --> Get place to validate \
		// if new address is present in response
		// Add place and store the response in the form of String
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(payload.AddPlace())
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("server", "Apache/2.4.18(Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		//For parsing JSON
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");//location.lat when the object in under multiple curly brackets
		System.out.println(placeId);
		
		//Update Place
		String newAddress ="70 winter walk, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address Successfully updated"));
		
		//Get Place and validate using new address
		String getAddress = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		//Created a reusable method class to parse json
		JsonPath js1 = ReusableMethods.rawToJson(getAddress);
		String actualAddress = js1.getString("address");
		Assert.assertEquals(actualAddress, newAddress);
		// Now for assertion we cannot use AssertThat as we not in given().when().then format which 
		// is given by Rest assured.
		// Java itself doesnot have any assertion method.
		// For testing we cab use two frameworks: Cucumber junit, testNG
	}

}
