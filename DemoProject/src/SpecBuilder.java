import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

	public static void main(String[] args) {
		
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		PojoSerializedTest pojoSerial = new PojoSerializedTest();
		pojoSerial.setAccuracy(50);
		pojoSerial.setAddress("221 Baker Street");
		pojoSerial.setLanguage("English-En");
		pojoSerial.setPhoneNumber("(+91)9023450354");
		pojoSerial.setWebsite("https://rahulshettyacademy.com");
		pojoSerial.setName("Frontline House");
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe_park");
		mylist.add("shop");
		pojoSerial.setTypes(mylist);
		PojoSerializedLocation loc = new PojoSerializedLocation();
		loc.setLat(-38.38375);
		loc.setLng(33.657498);
		pojoSerial.setLocation(loc);
		
		//Creating Request Spec builder
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
//		Response res = given().spec(req)
//		.body(pojoSerial)
//		.when().post("/maps/api/place/add/json")
//		.then().assertThat().statusCode(200).extract().response();
		
		// We can break the above code in Request and other
		RequestSpecification res = given().spec(req).body(pojoSerial);
		
		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		Response response = res.when().post("/maps/api/place/add/json")
		.then().spec(resspec).extract().response();

		String resString = response.asString();
		System.out.println(resString);
	}

}
