package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//Execute this code when place_id is null
		//Write a code that will give you place_id
		
		StepsDefinition m = new StepsDefinition();
		if (StepsDefinition.place_Id == null) {
			m.add_place_payload_with_something_something_something("Shetty", "French", "Asia");
			m.user_calls_API_with_post_http_request("AddPlaceAPI", "POST");
			m.verify_placeid_created_maps_to_name_using_API("Shetty", "GetPlaceAPI");
		}
	}
}
