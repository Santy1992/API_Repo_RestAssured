package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.PojoSerializedLocation;
import pojo.PojoSerializedTest;

public class TestDataBuild {
	public PojoSerializedTest addPlacePayload(String name, String language, String address) {
		PojoSerializedTest pojoSerial = new PojoSerializedTest();
		pojoSerial.setAccuracy(50);
		pojoSerial.setAddress(address);
		pojoSerial.setLanguage(language);
		pojoSerial.setPhoneNumber("(+91)9023450354");
		pojoSerial.setWebsite("https://rahulshettyacademy.com");
		pojoSerial.setName(name);
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe_park");
		mylist.add("shop");
		pojoSerial.setTypes(mylist);
		PojoSerializedLocation loc = new PojoSerializedLocation();
		loc.setLat(-38.38375);
		loc.setLng(33.657498);
		pojoSerial.setLocation(loc);
		
		return pojoSerial;
	}
	
	public String deletePlacePayload(String place_Id) {
		return "{\\r\\n    \\\"place_id\\\":\\\""+place_Id+"   \\t \\t\\/\\/(This value comes from Add place response)\\r\\n}\\r\\n";
	}
}
