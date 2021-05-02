package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.PojoSerializedLocation;
import pojo.PojoSerializedTest;
import resources.APIResourses;
import resources.TestDataBuild;
import resources.Utils;

public class StepsDefinition extends Utils {
	RequestSpecification res;
	Response response;
	ResponseSpecification resspec;
	TestDataBuild data = new TestDataBuild();
	static String place_Id;
	JsonPath js;
	
	@Given("^Add Place Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void add_place_payload_with_something_something_something(String name, String language, String address) throws IOException {
		
		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
    }

	// Without Examples keyword
	@Given("^Add Place Payload$")
    public void add_place_payload() throws IOException {
		res = given().spec(requestSpecification()).body(data);
    }

    @When("user calls {String} with {String} Http request$")
    public void user_calls_API_with_post_http_request(String resourse, String httpMethod) {
    	
    	//Constructor will be called with value of resourse which you pass
    	APIResourses resourceAPI = APIResourses.valueOf(resourse);
    	System.out.println(resourceAPI.getResource());
    	resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    	
    	if (httpMethod.equalsIgnoreCase("POST")) {
    		response = res.when().post(resourceAPI.getResource())
        			.then().spec(resspec).extract().response();
    	}else if (httpMethod.equalsIgnoreCase("GET")) {
    		response = res.when().get(resourceAPI.getResource())
        			.then().spec(resspec).extract().response();
    	}
    	
    }

    @Then("^the API call is success with Status Code 200$")
    public void the_api_call_is_success_with_status_code_200() {
         	assertEquals(response.getStatusCode(),200);
    }

    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void in_response_body_is(String keyValue, String expectedValue) {
        	assertEquals(getJSONPath(response, keyValue), expectedValue);
    }
    
    @And("^verify placeID created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
    public void verify_placeid_created_maps_to_name_using_API(String Expectedname, String strArg1) throws IOException {
    	//Request Spec
    	place_Id = getJSONPath(response, "place_id");
    	res = given().spec(requestSpecification()).queryParam("place_id", place_Id);
    	user_calls_API_with_post_http_request(strArg1,"GET");
    	String Actualname = getJSONPath(response, "name");
    	assertEquals(Actualname, Expectedname);
    }
    
    @Given("^DeletePlace Payload$")
    public void deleteplace_payload() throws IOException {
    	res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_Id));
    }

}
