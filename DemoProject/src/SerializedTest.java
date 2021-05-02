import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SerializedTest {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
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
		
		Response res = given().queryParam("key", "qaclick123")
		.body(pojoSerial)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		String resString = res.asString();
		System.out.println(resString);
	}

}
